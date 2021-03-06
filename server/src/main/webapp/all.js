function getUserBdd(name) {
	getUserGeneric(name, "v1/user/");
}

function getUserGeneric(name, url) {
	$.getJSON(url + name, function (data) {
		afficheUser(data);
	});
}

function getForAll() {
	getSecure("v1/secure/who");
}

function getByAnnotation() {
	getSecure("v1/secure/byannotation");
}

/*
 * Cette fonction permet de vérifier si un utilisateur existe dans la base
 * si oui, le login et le mot de passe sont stockés
 */

function getSecure(url) {
	if ($("#userlogin").val() != "") {
		$.ajax({
			type: "GET"
			, url: url
			, dataType: 'json'
			, beforeSend: function (req) {
				req.setRequestHeader("Authorization", "Basic " + btoa($("#userlogin").val() + ":" + $("#passwdlogin").val()));
			}
			, success: function (data) {
				afficheUser(data);
				if (data.id == -1) {
					$("#erreurSaisie").text("Le nom d'utilisateur ou le mot de passe est faux");
				}
                /*if(role()===3){
                    $("#nav navbar-nav pull-left").append("<li><a id='lienUsers' href='#'>Liste Utilisateurs</a></li>");
                    $("#nav navbar-nav pull-left").append("<li><a id='lienProducts' href='#'>Liste Produits</a></li>");
                }*/
				else {
					sessionStorage.setItem("login", $("#userlogin").val());
					sessionStorage.setItem("password", $("#passwdlogin").val());
                    sessionStorage.setItem("id",data.id);
                    sessionStorage.setItem("role",data.role);
					$("#navRight").append("<p id=\"bienvenue\">Bienvenue, " + sessionStorage.getItem("login") + "</p>");
					loadPage();
				}
			}
			, error: function (jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});
	}
	else {
		$.getJSON(url, function (data) {
			afficheUser(data);
		});
	}
}

//affichage : 0 afficherUser
function verifieEtAffiche(affichage){
    if (sessionStorage.getItem("login") != "" && sessionStorage.getItem("id") != null && sessionStorage.getItem("role") != null) {
        var name =
        $.ajax({
            type: "GET"
            , url: "v1/secure/who"
			, dataType: 'json'
			, beforeSend: function (req) {
				req.setRequestHeader("Authorization", "Basic " + sessionStorage.getItem("login") + ":" + sessionStorage.getItem("password"));
			}
			, success: function (data) {
                if(data.id===sessionStorage.getItem("id") && sessionStorage.getItem("login")===data.login && sessionStorage.getItem("role")===data.role && role()===3){
                    if(affichage===0){
                        afficheListUsers();
                    }
                }
			}
			, error: function (jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
			}
		});
    }
    console.log("temoin verif");
}
function role(){
     return $.ajax({
            type: "GET"
            , url: url
			, dataType: 'json'
			, beforeSend: function (req) {
				req.setRequestHeader("Authorization", "Basic " + $("login").val() + ":" + $("password").val());
			}
			, success: function (data) {
                return data.role;
			}
			, error: function (jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
                location.replace(connection.html);
			}
		});
}


/*
 * Les deux fonctions suivantes permettent d'ajouter un produit à la base
 * /!\ A vérifier !!!!
 */

function postProductBdd(name, quantite, description, prix, promotion, imagePath){
    postProductGeneric(name, quantite, description, prix, promotion, imagePath, "v1/product/");
}

function postProductGeneric(name, quantite, description, prix, promotion, imagePath, url) {
    console.log("postProductGeneric " + url)
    $.ajax({
        type: 'POST'
        , contentType: 'application/json'
        , url: url
        , dataType: "json"
        , data: JSON.stringify({
            "name": name
            , "amount": quantite
            , "description": description
            , "baseprice": prix
            , "promotionalamount": promotion
            , "imageurl": imagePath
            , "id": 0
        })
        , success: function (data, textStatus, jqXHR) {
            console.log("success");
            listProductsBdd();
        }
        , error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

/*
 * Les deux fonctions suivantes permettent d'ajouter un utilisateur à la base
 */

function postUserBdd(name, alias, email, pwd) {
	postUserGeneric(name, alias, email, pwd, "v1/user/");
}

function postUserGeneric(name, alias, email, pwd, url) {
	console.log("postUserGeneric " + url)
	$.ajax({
		type: 'POST'
		, contentType: 'application/json'
		, url: url
		, dataType: "json"
		, data: JSON.stringify({
			"name": name
			, "alias": alias
			, "email": email
			, "password": pwd
			, "id": 0
		})
		, success: function (data, textStatus, jqXHR) {
			afficheUser(data);
		}
		, error: function (jqXHR, textStatus, errorThrown) {
			console.log('postUser error: ' + textStatus);
		}
	});
}

function listUsersBdd() {
	listUsersGeneric("v1/user/");
}

function listUsersGeneric(url) {
	$.getJSON(url, function (data) {
		afficheListUsers(data)
	});
}

function listProductsBdd() {
	listProductsGeneric("v1/product/");
}

function listProductsGeneric(url) {
	$.getJSON(url, function(data) {
		afficheListProducts(data)
	});
}

function afficheListProducts(data) {
	$("#mainContainer").empty();
	var ligne;
	for(var i=0; i<data.length; i++) {
		console.log(data[i]);
		if(i%6==0)  {
			if(i!=0) $("#mainContainer").append(ligne);
			ligne=$("<div class=\"row\"></div>");
		}
		var divProduct=$("<div class=\"col-sm-6 col-md-2\"></div>");
		var thumbnail=$("<div class=\"thumbnail\"></div>");
		thumbnail.append($("<a href=\"#\" class=\"linkProduct\"><img src=\""+data[i].imageurl+"\" alt =\"...\"></a>"));
		var caption=$("<div class=\"caption\"></div>");
		caption.append($("<h3>"+data[i].name+"</h3>"));
		caption.append($("<p>"+data[i].description+"</p>"));
		caption.append($("<p><a href=\"#\" id=\"acheterBtn\" class=\"btn btn-primary\" role=\"button\"><img src=\"img/panier.jpg\" class=\"logo2\"></a> <a href=\"#\" class=\"btn btn-default\" role=\"button\"><img src=\"img/coeur.png\" class=\"logo2\"></a></p>"));
		thumbnail.append(caption);
		divProduct.append(thumbnail);
		ligne.append(divProduct);
	} 
	$("#mainContainer").append(ligne);
}

function afficheListUsers() {
    $.ajax({
            type: "GET"
            , url: "v1/user/limit/1/3"
			, dataType: 'json'
			, success: function (data) {
               var result = "";
                $("#mainContainer").empty();
                var tab = document.createElement('table');
                tab.appendTo("#mainContainer");
                result+= "<table id=\'tableau\'><thead><tr><th>Id</th><th>Role</th><th>Préom</th><th>Nom</th><th>Login</th><th>Adresse</th><th>Societe</th><th>Téléphone</th><th>Sexe</th><th>Points Fid.</th><th>Date naissance</th><th>Date inscription</th><th>Newsletter</th><th>Email</th>"
                $.each(data, function (i,item) {
                    result += "<tr><td>"+item.id+"</td><td>";
                    switch(item.role){
                        case 0:
                            result +="Utilisateur</td><td>";
                          break;
                        case 1:
                            result +="Conseillé</td><td>";
                            break;
                        case 2:
                            result +="Vendeur</td><td>";
                            break;
                        case 3:
                            result +="Admin</td><td>";
                            break;
                    }
                    result +=item.name+"</td><td>";
                    result +=item.surname+"</td><td>";
                    result +=item.alias+"</td><td>";
                    result +=item.adresse+"</td><td>";
                    result +=item.societe+"</td><td>";
                    result +=item.tel+"</td><td>";
                    if(item.sexe===0){
                        result +="femme</td><td>";
                    }if(item.sexe===1){
                        result +="homme</td><td>";
                    }else{
                        result +="autre</td><td>";
                    }
                    result +=item.ptsFidelite+"</td><td>";
                    var dateNaissance = item.dateNaissance;
                    result +=String(dateNaissance)+"</td><td>";
                    var dateInscription = item.dateInscription;
                    result +=String(dateInscription)+"</td><td>";
                    if(item.newsLetter){
                        result +="inscrit</td><td>";
                    }else{
                        result +="non inscrit</td><td>";
                    }
                    result +=item.email+"</td></tr></table>";
                    tab.innerHTML=result;
                }); 
            }
			, error: function (jqXHR, textStatus, errorThrown) {
				alert('error: ' + textStatus);
                location.replace(connection.html);
			}
    });
}

function afficheUser(data) {
	console.log(data);
	$("#reponse").html(data.id + " : <b>" + data.alias + "</b> (" + data.name + ")");
}

function afficheListUsers(data) {
	var html = '<ul>';
	var index = 0;
	for (index = 0; index < data.length; ++index) {
		html = html + "<li>" + data[index].name + "</li>";
	}
	html = html + "</ul>";
	$("#reponse").html(html);
}

function loadPage() {
	var childNodes = document.getElementById("navRight").childNodes;
	if (sessionStorage.getItem("login") === "null" && sessionStorage.getItem("password") === "null") {
		$("#monCompteBtn").hide();
		$("#signupBtn").show();
		$("#signinBtn").show();
	}
	else {
		if (childNodes[0] === undefined) {
			$("#navRight").append("<p id=\"bienvenue\">Bienvenue, " + sessionStorage.getItem("login") + "</p>");
		}
		$("#monCompteBtn").show();
		$("#signupBtn").hide();
		$("#signinBtn").hide();
	}
	cleanPage();
	listProductsBdd();
	$("#mainContainer").show();
	$("#carousel").show();
}

function deconnection() {
	sessionStorage.setItem("login", null);
	sessionStorage.setItem("password", null);
    sessionStorage.setItem("role", null);
    sessionStorage.setItem("id", null);
	$("#bienvenue").remove();
	loadPage();
}

function tailleCarousel() {
	var height = document.body.clientHeight - 50;
	var width = document.body.clientWidth;
	var elem = document.getElementById("carousel");
	var img = document.getElementsByClassName("img-responsive");
	elem.style.height = height;
	for (var i = 0; i < img.length; i++) {
		img[i].style.width = width;
		img[i].style.height = height;
	}
}	
function cleanPage() {
	$("#mainContainer").hide();
	$("#carousel").hide();
	$("#panierContainer").hide();
	$("#whishlistContainer").hide();
    $("#inscriptionContainer").hide();
    $("#connectionContainer").hide();
    $("#addProductContainer").hide();
 	$("#descriptionContainer").hide();
    $("#contactContainer").hide();
}

function panierShow(){
	cleanPage();
	$("#panierContainer").show();
}

function whishlistShow(){
    cleanPage();
    $("#whishlistContainer").show();
}

function signUp() {
 	cleanPage();
 	$("#inscriptionContainer").show();
 }
 
 function signIn() {
 	cleanPage();
 	$("#connectionContainer").show();
 }
 
 function description() {
 	cleanPage();
 	$("#descriptionContainer").show();
 }

function contactShow(){
	cleanPage();
	$("#contactContainer").show();
 }
 
 function ajoutProduct() {
 	cleanPage();
 	$("#addProductContainer").show();
 }

 function userListShow() {
 	cleanPage();
 	verifieEtAffiche(0);
 }

 function productListShow() {
 	cleanPage();
 	$("#addProductContainer").show();
 }

//Ajout d'un item au panier, ajouter un champs hidden dans le html, pour chaque produit
//<input type="hidden" value="valeur_que_tu_veux_transmettre"> 
function ajoutPanier(panier, valeur){
    panier.push(valeur);
}


