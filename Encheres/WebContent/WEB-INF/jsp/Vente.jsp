<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vente</title>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>
	<div>
		<h2>Détail vente</h2>
	</div>
	<div>
		<img alt="Image de l'objet" src="">
	</div>
	<div class="descriptionVente">
		<div>

			<p name="libelleArticle" id="libelleArticle">PC Gamer pour
				travailler</p>
		</div>
		<div>
			<label for="descriptionArticle :">Description :</label>
			<p name="descriptionArticle" id="descriptionArticle">BLA BLA BLA
				BLA BLA</p>
		</div>
		<div>
			<label for="CategorieArticle">Categorie :</label> <span
				name="CategorieArticle" id="CategorieArticle">Informatique</span>
		</div>
		<div>
			<label for="MeilleureOffre">Meilleur offre :</label> <span
				name="MeilleureOffre"><span id="nbPointsMeilleur">210</span>
				pts par <span id="pseudoMeilleur">Bob</span></span>
		</div>
		<div>
			<label for="MiseAprix">Mise à prix :</label> <span name="MiseAprix"
				id="prix">185</span> points
		</div>
		<div>
			<label for="dateFin">Fin de l'enchere</label> <span name="dateFin"
				id="dateFin">09/10/2018</span>
		</div>
		<div>
			<label for="Adresse">Retrait : </label> <span name="Adresse"
				id="Adresse">10 allée des Alouettes</span><br> <span
				id="codePostal">44800</span> <span id="ville">Saint Herblain</span>
		</div>
		<div>
			<label for="vendeur">Vendeur : </label> <span name="vendeur"
				id="vendeur">jojo44</span>
		</div>
		<form action="">
			<div>
				<label for="proposition">Ma proposition : </label> 
				<input
					type="number" name="proposition" id="proposition" value=220 style="width: 43px; ">
				<input type="submit" value="Enchérir">
			</div>
		</form>
	</div>
	</div>
</body>
</html>