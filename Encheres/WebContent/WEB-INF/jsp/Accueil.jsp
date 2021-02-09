<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>
	<div>
		<span><a href="<%=request.getContextPath() %>/ServletConnexion">S'inscrire - Se connecter</a></span>
	</div>
	<div>
		<h1>Liste des enchères</h1>
	</div>
	<div>
		<h3>Filtres :</h3>
	</div>
	<div>
		<input type="button" /><input type="text">
	</div>
	<div>
		<Label for="Categories">Catégories :</Label> <select name="Categories">
			<!-- Coder en dur en attendant d'être mis en dynamique -->
			<option value=1>Informatique</option>
			<option value=2>Ameublement</option>
			<option value=3>Vêtement</option>
			<option value=4>Sport&amp;Loisirs</option>
		</select>
	</div>
	<div>
		<input type="button" value="Rechercher" />
	</div>
	<!-- Attribut en plus lors de la connection -->
	<div>
		<div>
			<div>
				<input type="radio" name="type"><label>Achats</label>
			</div>
			<div>
				<div>
					<input type="checkbox"><label>enchères ouvertes</label>
				</div>
				<div>
					<input type="checkbox"><label>mes enchères en cours</label>
				</div>
				<div>
					<input type="checkbox"><label>mes enchères
						remportées</label>
				</div>
			</div>
		</div>
		<div>
			<div>
				<input type="radio" name="type"><label>Ventes</label>
			</div>
			<div>
				<div>
					<input type="checkbox"><label>mes ventes en cours</label>
				</div>
				<div>
					<input type="checkbox"><label>ventes non débutées</label>
				</div>
				<div>
					<input type="checkbox"><label>ventes terminées</label>
				</div>
			</div>
		</div>
	</div>
	<!-- Liste des enchères à mettre dynamiquement -->
	<div class="Enchere">
		<div>
			<img alt="photo objet" src="">
		</div>
		<div>
			<div>
				<a href="" id="libelleArticle">PC Gamer pour travailler</a>
			</div>
			<div>
				Prix : <span id="nbPoints">210</span> points
			</div>
			<div>
				Fin de l'enchère : <span id="dateFinEnchere">10/08/2018</span>
			</div>
			<div>
				Vendeur : <span id="pseudoVendeur">jojo44</span>
			</div>
		</div>

	</div>
</body>
</html>