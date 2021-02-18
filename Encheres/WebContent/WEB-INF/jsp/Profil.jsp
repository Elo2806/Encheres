<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link href="css/styleAccueil.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Profil</title>
</head>

<body>
<div class="container" style="margin: 50px auto;">

<%@include file ="EnTeteEni.jspf"%>

        <form class="form" action="" method="get">
<br><br>
            <div class="form-group row">
                <div class="col-md-2"> <label for="pseudo" class="col-auto col-form-label">Pseudo:</label></div>
                <div class="col-md-4"> <span id="pseudo" class="form-control">${utilisateurAffiche.pseudo}</span></div>
                <div class="col-md-1"></div>
                <div class="col-md-1"> <label for="nom" class="col-auto col-form-label">Nom:</label></div>
                <div class="col-md-4"> <span id="nom" class="form-control">${utilisateurAffiche.nom}</span></div> 
            </div>

            <div class="form-group row">
                <div class="col-md-2"> <label for="prenom" class="col-auto col-form-label">Prénom:</label></div>
                <div class="col-md-4"> <span id="prenom" class="form-control">${utilisateurAffiche.prenom}</span></div>
 				<div class="col-md-1"></div>
                <div class="col-md-1"> <label for="email" class="col-auto col-form-label">Email:</label></div>
                <div class="col-md-4"> <span id="email" class="form-control">${utilisateurAffiche.email}</span></div>
            </div>

            <div class="form-group row">
                <div class="col-md-2"> <label for="telephone" class="col-auto col-form-label">Téléphone:</label></div>
                <div class="col-md-4"> <span id="telephone" class="form-control">${utilisateurAffiche.telephone}</span></div>
 				<div class="col-md-1"></div>
                <div class="col-md-1"> <label for="rue" class="col-auto col-form-label">Rue:</label></div>
                <div class="col-md-4"> <span id="rue" class="form-control">${utilisateurAffiche.rue}</span></div>
            </div>

            <div class="form-group row">
                <div class="col-md-2"> <label for="codePostal" class="col-auto col-form-label">Code Postal:</label></div>
                <div class="col-md-4"> <span id="codePostal" class="form-control">${utilisateurAffiche.codePostal}</span></div>
 				<div class="col-md-1"></div>
                <div class="col-md-1"> <label for="ville" class="col-auto col-form-label">Ville:</label></div>
                <div class="col-md-4"> <span id="ville" class="form-control">${utilisateurAffiche.ville}</span></div>
            </div>
            
	<div class="container" style="margin-top: 50px; text-align:center">
            
            <a href="${pageContext.request.contextPath}/ServletCompte?modification=true"><input type="button" value="Modifier" /></a>
            <a href="${pageContext.request.contextPath}/ServletAccueil"><input type="button" value="Accueil" /></a>
            </div>
        </form>
  
    </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>

</html>