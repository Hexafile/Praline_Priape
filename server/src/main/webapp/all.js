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
				else {
					sessionStorage.setItem("login", $("#userlogin").val());
					sessionStorage.setItem("password", $("#passwdlogin").val());
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

/*
 * Les deux fonctions suivantes permettent d'ajouter un produit à la base
 * /!\ A vérifier !!!!
 */

function postProductBdd(name, quantite, description, prix, promotion){
    postProductGeneric(name, quantite, description, prix, promotion, "v1/product/");
}

function postProductGeneric(name, quantite, description, prix, promotion, url) {
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
            , "id": 0
        })
        , success: function (data, textStatus, jqXHR) {
            console.log("success");
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
	console.log(data);
	var ligne;
	for(var i=0; i<data.length; i++) {
		/*if(i%6==0) ligne=$("<div class=\"row\"></div>");
		var divProduct=$("<div class=\"col-sm-6 col-md-2\"></div>");
		var thumbnail=$("<div class=\"thumbnail\"></div>");
		thumbnail.append*/
		console.log(data[i].id+'  '+data[i].name+'  '+data[i].description+'  '+data[i].basePrice);
	} 
	
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
	$("#mainContainer").show();
	$("#carousel").show();
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

function ajoutProduct() {
	cleanPage();
	$("#addProductContainer").show();
}

function deconnection() {
	sessionStorage.setItem("login", null);
	sessionStorage.setItem("password", null);
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
	$("#inscriptionContainer").hide();
	$("#connectionContainer").hide();
	$("#addProductContainer").hide();
	$("#descriptionContainer").hide();
	$("#carousel").hide();
	$("#panierContainer").hide();
	$("#whishlistContainer").hide();
}

function panierShow(){
	cleanPage();
	$("#panierContainer").show();
}

function whishlistShow(){
    cleanPage();
    $("#whishlistContainer").show();
}
