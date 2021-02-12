<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>

	<c:choose>
		<c:when test="${!empty sessionScope.utilisateur}">
			<div>
				<span><a href="<c:url value="/ServletConnexion"/>">Enchères</a></span>
				<span><a href="<c:url value="/ServletVente"/>">Vendre un
						article</a></span> <span><a href="<c:url value="/ServletProfil"/>">Mon
						profil</a></span> <span><a href="<c:url value="/ServletConnexion"/>">Déconnexion</a></span>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				<span><a href="<c:url value="/ServletConnexion"/>">S'inscrire
						- Se connecter</a></span>
			</div>
		</c:otherwise>
	</c:choose>

	<div>
		<h1>Liste des enchères</h1>
	</div>

	<div>
		<h3>Filtres :</h3>
	</div>
	<form action="<c:url value="/ServletAccueil"/>">
		<div>
			<input type="button" /><input type="text">
		</div>
		<div>
			<Label for="Categories">Catégories :</Label> <select
				name="Categories">
				<!-- Coder en dur en attendant d'être mis en dynamique -->
				<option value=1>Informatique</option>
				<option value=2>Ameublement</option>
				<option value=3>Vêtement</option>
				<option value=4>Sport&amp;Loisirs</option>
			</select>
		</div>

		<!-- Attribut en plus lors de la connection -->
		<c:if test="${!empty sessionScope.utilisateur}">
			<div>
				<div>
					<div>
						<input type="radio" name="type"><label>Achats</label>
					</div>
					<div>
						<div>
							<input type="checkbox" name="chkEncheresOuvertes"><label>enchères
								ouvertes</label>
						</div>
						<div>
							<input type="checkbox" name="chkMesEnchereEnCours"><label>mes
								enchères en cours</label>
						</div>
						<div>
							<input type="checkbox" name="chkMesEncheresRemportes"><label>mes
								enchères remportées</label>
						</div>
					</div>
				</div>
				<div>
					<div>
						<input type="radio" name="type"><label>Ventes</label>
					</div>
					<div>
						<div>
							<input type="checkbox" name="chkVentesEnCours"><label>mes
								ventes en cours</label>
						</div>
						<div>
							<input type="checkbox" name="chkVentesNonDebutes"><label>ventes
								non débutées</label>
						</div>
						<div>
							<input type="checkbox" name="chkVentesTerminees"><label>ventes
								terminées</label>
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<div>
			<input type="submit" value="Rechercher" />
		</div>

	</form>

	<!-- Liste des enchères à mettre dynamiquement -->
	<div class="Enchere">
		<div>
			<img alt="photo objet" src="">
		</div>
		<div>
			<div>
				<a href="<c:url value="/ServletEnchere"/>" id="libelleArticle">PC
					Gamer pour travailler</a>
			</div>
			<div>
				Prix : <span id="nbPoints">210</span> points
			</div>
			<div>
				Fin de l'enchère : <span id="dateFinEnchere">10/08/2018</span>
			</div>
			<div>
				Vendeur : <span><a href="<c:url value="/ServletProfil"/>"
					id="pseudoVendeur" name=id="pseudoVendeur">jojo44</a></span>
			</div>
		</div>

	</div>
</body>
</html>