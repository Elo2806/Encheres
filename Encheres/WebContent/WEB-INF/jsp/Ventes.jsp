<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enchère</title>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>
<!-- image à insérer dynamiquement -->
	<img alt="photo article" src="">
	<h1>Nouvelle vente</h1>
	<form method="post" action="/ServletVente">
		<div>
			<label for="article">Article : </label>
			<input type="text"
				name="article" id="article" required>
		</div>
		<div>
			<label for="description">Description : </label>
			<textarea name="description" row="10" cols="30" id="description" required></textarea>
	 
		</div>
		<div>
			<label for="categorie">Categorie : </label>
			<select name="categorie" size="1" id="categorie">
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sportloisirs">Sport&Loisirs</option>				
			</select>
		</div>
		<div>
			<label for="photo">Photo de l'article : </label>
			<input type="button" value="UPLOADER" name="photo" id="photo">
		</div>
		<div>
			<label for="prixdepart">Mise à prix : </label>
			<input type="number"
				name="prixdepart" id="prixdepart" required>
		</div>
		<div>
			<label for="datedebut">Début de l'enchère : </label>
			<input
				type="date" name="datedebut" id="datedebut" required>
		</div>
		<div>
			<label for="datefin">Fin de l'enchère : </label>
			<input type="date"
				name="datefin" id="datefin" required>
		</div>
		<div id="retrait">Retrait
	
			<div>
				<label for="rue">Rue : </label>
				<input type="text" name="rue" id="rue" value="${requestScope.rue}">
			</div>
			<div>
				<label for="codepostal">Code postal : </label>
				<input type="text" name="codepostal" id="codepostal" value="${requestScope.cp}">
			</div>
			<div>
				<label for="ville">Ville : </label>
				<input type="text" name="ville" id="ville" value="${requestScope.ville}">
			</div>
		</div>
		<div>
			<button type="submit" value="Enregistrer">Enregistrer</button>
			<button type="reset" value="Anuler">Anuler</button>
			
		<c:if test="${requestScope.booleen}">
			<input onclick="" type="button" name="supprimer" id="supprimer" value="Annuler la vente">
		</c:if>
		</div>
	</form>


</body>
</html>