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
			<label for="descriptionArticle">Description</label>
			<p name="descriptionArticle" id="descriptionArticle">BLA BLA BLA BLA BLA</p>
		</div>
		<div>
			<label for="CategorieArticle">Categorie</label> <span
				id="CategorieArticle">Informatique</span>
		</div>
		<div>
			<label for="MeilleureOffre">Meilleur offre</label> <span
				id="MeilleureOffre"><span id="nbPointsMeilleur">210</span id="pseudoMeilleur">
				pts par <span>Bob</span></span>
		</div>
		<div>
			<label for="MiseAprix">Mise à prix</label>
			<span id="MiseAprix"/><span id="MiseAprix"/>185<span id="prix">
				points</span>
		</div>
		<div>
			<label for="FinEnchere">Mise à prix</label>
			<span id="FinEnchere"><span id="FinEnchere">185</span>
		</div>
	</div>
</body>
</html>