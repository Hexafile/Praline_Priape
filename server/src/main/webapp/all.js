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

function getSecure(url) {
    if ($("#userlogin").val() != "") {
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            beforeSend: function (req) {
                req.setRequestHeader("Authorization", "Basic " + btoa($("#userlogin").val() + ":" + $("#passwdlogin").val()));
            },
            success: function (data) {
                afficheUser(data);
                if(data.id == -1){
                    $("#erreurSaisie").text("Le nom d'utilisateur ou le mot de passe est faux");
                }else{
                    sessionStorage.setItem("login", $("#userlogin").val());
                    sessionStorage.setItem("password", $("#passwdlogin").val());
                    $("#navRight").append("<p id=\"bienvenue\">Bienvenue, "+sessionStorage.getItem("login")+"</p>");
                    loadPage();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('error: ' + textStatus);
            }
        });
    } else {
        $.getJSON(url, function (data) {
            afficheUser(data);
        });
    }
}

function postProductBdd(name, vendeur, quantite, description, prix, promotion, image, url){
    postProductGeneric(name, vendeur, quantite, description, prix, promotion, image, "v1/product/");
}

function postUserGeneric(name, vendeur, quantite, description, prix, promotion, image) {
    console.log("postUserGeneric " + url)
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: "json",
        data: JSON.stringify({
            "name": name,
            "alias": alias,
            "email": email,
            "password": pwd,
            "id": 0
        }),
        success: function (data, textStatus, jqXHR) {
            afficheUser(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log('postUser error: ' + textStatus);
        }
    });
}

function postUserBdd(name, alias, email, pwd) {
    postUserGeneric(name, alias, email, pwd, "v1/user/");
}

function postUserGeneric(name, alias, email, pwd, url) {
    console.log("postUserGeneric " + url)
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: url,
        dataType: "json",
        data: JSON.stringify({
            "name": name,
            "alias": alias,
            "email": email,
            "password": pwd,
            "id": 0
        }),
        success: function (data, textStatus, jqXHR) {
            afficheUser(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
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
    if(sessionStorage.getItem("login") === "null" && sessionStorage.getItem("password") === "null"){
        $("#monCompteBtn").hide();
        $("#signupBtn").show();
        $("#signinBtn").show();
    }else{
        if(childNodes[0] === undefined){
            $("#navRight").append("<p id=\"bienvenue\">Bienvenue, "+sessionStorage.getItem("login")+"</p>");
        }
        $("#monCompteBtn").show();
        $("#signupBtn").hide();
        $("#signinBtn").hide();
    }
    cleanPage();
    $("#mainContainer").show();
}

function signUp() {
    cleanPage();
    $("#inscriptionContainer").show();
}

function signIn() {
    cleanPage();
    $("#connectionContainer").show();
}

function description(){
    cleanPage();
    $("#descriptionContainer").show();
}

function ajoutProduct(){
    cleanPage();
    $("#addProductContainer").show();
}

function deconnection() {
    sessionStorage.setItem("login", null);
    sessionStorage.setItem("password", null);
    $("#bienvenue").remove();
    loadPage();
}

function cleanPage() {
     $("#mainContainer").hide();
     $("#inscriptionContainer").hide();
     $("#connectionContainer").hide();
     $("#addProductContainer").hide();
     $("#descriptionContainer").hide();
    
}