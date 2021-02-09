<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<form method="post" action="/ServletEnchere">
		<div>
			<label for="article">Article : </label>
			<input type="text"
				name="article" id="article">
		</div>
		<div>
			<label for="description">Description : </label>
			<textarea name="description" row="10" cols="30" id="description"></textarea>
	 
		</div>
		<div>
			<label for="categorie">Categorie : </label>
			<select name="categorie" size="1" id="categorie">
<!-- <option value="catégorie">Catégorie</option> 
	à ajouter dynamiquement pour chaque catégorie existante -->
				<option value="maison">Maison</option>
				<option value="jardin">Jardin</option>
				<option value="informatique">Informatique</option>
				
			</select>
		</div>
		<div>
			<label for="photo">Photo de l'article : </label>
			<input type="button" value="UPLOADER" name="photo" id="photo">
		</div>
		<div>
			<label for="prixdepart">Mise à prix : </label>
			<input type="number"
				name="prixdepart" id="prixdepart">
		</div>
		<div>
			<label for="datedebut">Début de l'enchère : </label>
			<input
				type="date" name="datedebut" id="datedebut">
		</div>
		<div>
			<label for="datefin">Fin de l'enchère : </label>
			<input type="date"
				name="datefin" id="datefin">
		</div>
		<div id="retrait">Retrait
<!-- Insérer dynamiquement l'adresse du vendeur par défaut -->
			<div>
				<label for="rue">Rue : </label>
				<input type="text" name="rue" id="rue">
			</div>
			<div>
				<label for="codepostal">Code postal : </label>
				<input type="text" name="codepostal" id="codepostal">
			</div>
			<div>
				<label for="ville">Ville : </label>
				<input type="text" name="ville" id="ville">
			</div>
		</div>
		<div>
			<button type="submit" value="Enregistrer">Enregistrer</button>
			<button type="reset" value="Anuler">Anuler</button>
			
<!-- si la vente a déjà été créée : + mettre un lien de suppression -->
			<input onclick="" type="button" name="supprimer" id="supprimer" value="Annuler la vente">
		</div>
	</form>


</body>
</html>