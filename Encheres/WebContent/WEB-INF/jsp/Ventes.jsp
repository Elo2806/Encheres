<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link href="css/styleAccueil.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enchère</title>
</head>
<body>
<div class="container">
	<%@include file="EnTeteEni.jspf"%>
<!-- image à insérer dynamiquement -->
<div class="container">
<div class="row photo">
				<img
						class="photo-left"
						alt="photo objet"
						src="images/ecommerce-navigation.png">
	</div>
	<div class="col-6" id="encadre">
	<h1>Nouvelle vente</h1>
	<form method="post" action="<c:url value="/ServletVente"/>">
	
	
		<div>
			<label for="article">Article : </label>
			<input type="text"
				name="article" id="article" required pattern="[A-Za-z0-9]{1,30}">
		</div>
		<div>
			<label for="description">Description : </label>
			<textarea name="description" cols="30" id="description" required></textarea>
	 
		</div>
		<div>
			<label for="categorie">Categorie : </label>
			<select name="categorie" id="categorie" required>
			<c:forEach var="categorie" items="${mapCategories}">
				<option value="${categorie.key}">${categorie.value.libelle}</option>
				</c:forEach>	
			</select>
			
		</div>
		<div>
			<label for="photo">Photo de l'article : </label>
			<input type="button" value="UPLOADER" name="photo" id="photo"  value="${param.photo}">
		</div>
		<div>
			<label for="prixdepart">Mise à prix : </label>
			<input type="number"
				name="prixdepart" id="prixdepart" value="${param.prixdepart}">
		</div>
		<div>
			<label for="datedebut">Début de l'enchère : </label>
			<input
				type="date" name="datedebut" id="datedebut" required value="${param.datedebut}">
			<input type="time" name="heuredebut" id="heuredebut" required value="${param.heuredebut}">
		</div>
		<div>
			<label for="datefin">Fin de l'enchère : </label>
			<input type="date" name="datefin" id="datefin" required value="${param.datefin}">
			<input type="time" name="heurefin" id="heurefin" required value="${param.heurefin}">
		</div>
		<div id="retrait">Retrait
	
			<div>
				<label for="rue">Rue : </label>
				<input 
					type="text" 
					name="rue" 
					id="rue" 
					required 
					<c:if test="${empty param.rue}">value="${requestScope.rue}"</c:if>
				   	<c:if test="${param.rue}">value="${param.rue}"</c:if>>
			</div>
			<div>
				<label for="codepostal">Code postal : </label>
				<input 
					type="text" 
					name="codepostal" 
					id="codepostal" 
					required 
					pattern="[0-9]{5}" 
					<c:if test="${empty param.codepostal}"> value="${requestScope.cp}"</c:if>
				    <c:if test="${param.codepostal}"> value="${param.codepostal}"</c:if>>
			</div>
			<div>
				<label for="ville">Ville : </label>
				<input 
					type="text" 
					name="ville" 
					id="ville" 
					required 
					<c:if test="${empty param.ville}">value="${requestScope.ville}"</c:if>
				    <c:if test="${param.ville}">value="${param.ville}"</c:if>>
			</div>
		</div>
		<div>
			<button type="submit" value="Enregistrer">Enregistrer</button>
		<a href="<c:url value="/accueil"/>">	<button type="button" value="Annuler">Annuler</button></a>
			
		<c:if test="${requestScope.venteCree}">
			<input onclick="" type="button" name="supprimer" id="supprimer" value="Annuler la vente">
		</c:if>
		</div>
	</form>
</div>
</div>
</body>
</html>