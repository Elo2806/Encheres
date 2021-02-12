<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profil</title>
</head>

<body>

<%@include file ="EnTeteEni.jspf"%>

  <form action=>
        <div>    <label for="pseudo">Pseudo:</label>
        <span name="pseudo" id="pseudo">${ }</span></div>
        
        <div>    <label for="nom">Nom:</label>
        <span name="nom" id="nom" ></span></div>
        
           <div>    <label for="prenom">Prénom:</label>
        <span name="prenom" id="prenom"></span></div>
        
           <div>    <label for="email">Email:</label>
        <span name="email" id="email"></span></div>
        
           <div>    <label for="telephone">Téléphone:</label>
        <span name="telephone" id="telephone"></span></div>
        
           <div>    <label for="rue">Rue:</label>
        <span name="rue" id="rue"></span></div>
        
         <div>    <label for="codePostal">Code Postal:</label>
        <span name="codePostal" id="codePostal"></span></div>
        
         <div>    <label for="ville">Ville:</label>
        <span name="ville" id="ville"></span></div>
        
        </form>
        
        <a href="<%=request.getContextPath()%>/ServletCompte"><input type="button" value="Modifier"/></a>
         <a href="<%=request.getContextPath()%>/ServletAccueil"><input type="button" value="Accueil"/></a>
        
</div>

</body>
</html>