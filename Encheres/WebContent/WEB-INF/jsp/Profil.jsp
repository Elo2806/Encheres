<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>
<link href="css/styleProfil.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil</title>
</head>

<body>

<%@include file ="EnTeteEni.jspf"%>
<div class="content">
  <form action="" method="get">
  
        <div>    <label for="pseudo">Pseudo:</label>
        <span id="pseudo">${utilisateurAffiche.pseudo}</span></div>
        
        <div>    <label for="nom">Nom:</label>
        <span id="nom" >${utilisateurAffiche.nom}</span></div>
        
           <div>    <label for="prenom">Prénom:</label>
        <span id="prenom">${utilisateurAffiche.prenom}</span></div>
        
           <div>    <label for="email">Email:</label>
        <span id="email">${utilisateurAffiche.email}</span></div>
        
           <div>    <label for="telephone">Téléphone:</label>
        <span id="telephone">${utilisateurAffiche.telephone}</span></div>
        
           <div>    <label for="rue">Rue:</label>
        <span id="rue">${utilisateurAffiche.rue}</span></div>
        
         <div>    <label for="codePostal">Code Postal:</label>
        <span id="codePostal">${utilisateurAffiche.codePostal}</span></div>
        
         <div>    <label for="ville">Ville:</label>
        <span id="ville">${utilisateurAffiche.ville}</span></div>
        
 <a href="${pageContext.request.contextPath}/ServletCompte?modification=true"><input type="button" value="Modifier"/></a>
         <a href="${pageContext.request.contextPath}/ServletAccueil"><input type="button" value="Accueil"/></a>
        
        </form>
        
       
</div>

</body>
</html>