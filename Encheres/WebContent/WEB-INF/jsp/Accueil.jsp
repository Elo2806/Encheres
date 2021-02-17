<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/styleAccueil.css" rel="stylesheet"/>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Accueil</title>
<style type="text/css">
.vente {
	color: red;
}
</style>
</head>
<body>

<!-- Section en tête du contenu -->
<header>
<!-- Section LOGO -->
	<%@include file="../jsp/EnTeteEni.jspf"%>
</header>
<!-- Section MENU -->
<main>

<nav>
<ul>
	<c:choose>
		<c:when test="${!empty sessionScope.utilisateur}">
			
				<span><li><a href="<c:url value="/ServletVente"/>">Vendre un article</a></li></span>
				<span><li><a href="<c:url value="/ServletCompte"/>">Mon profil</a></li></span> 
				<span><li><a href="<c:url value="/ServletConnexion"/>">Déconnexion</a></li></span>
			
		</c:when>
		<c:otherwise>
			<div>
				<span><li><a href="<c:url value="/ServletConnexion"/>">S'inscrire - Se connecter</a></li></span>
			</div>
		</c:otherwise>
	</c:choose>
	</ul>
	</nav>


	<div>
		<h1>Liste des enchères</h1>
	</div>

	<div>
		<h3>Filtres :</h3>
	</div>
	<form
		method="post"
		action="<c:url value="/ServletAccueil?recherche=true"/>">
		<div>
			<input
				name="rechercheTexte"
				type="search"
				placeholder="Le nom de l'article contient"
				value="<c:if test="${!empty param.rechercheTexte}">${param.rechercheTexte}</c:if>" 
			/>
		</div>
		<div>
			<label for="categorieFiltre">Categorie : </label>
			<select
				name="categorieFiltre"
				size="1"
				id="categorieFiltre">
				<c:forEach
					var="categorie"
					items="${mapCategories}">
					<option value="${categorie.key}">${categorie.value.libelle}</option>
				</c:forEach>
			</select>
		</div>

		<!-- Attribut en plus lors de la connection -->
		<c:if test="${!empty sessionScope.utilisateur}">
			<div id="Filtres">
				<div>
					<div>
						<input
							type="radio"
							name="typeFiltre"
							value="achat"
							onclick="selectType(false)"
							<c:if test="${param.typeFiltre == 'achat'}">checked="checked"</c:if>
							<c:if test="${ empty param.recherche}">checked="checked"</c:if>>
						<label>Achats</label>
					</div>
					<div id="venteid">
						<div>
							<input
								class="achat"
								id="chktest"
								type="checkbox"
								name="chkEncheresOuvertes"
								<c:if test="${param.chkEncheresOuvertes == 'on'}">checked="checked"</c:if>>
							<label for="chkEncheresOuvertes">enchères ouvertes</label>
						</div>
						<div>
							<input
								class="achat"
								type="checkbox"
								name="chkMesEnchereEnCours"
								<c:if test="${param.chkMesEnchereEnCours== 'on'}">checked="checked"</c:if>>
							<label>mes enchères en cours</label>
						</div>
						<div>
							<input
								class="achat"
								type="checkbox"
								name="chkMesEncheresRemportes"
								<c:if test="${param.chkMesEncheresRemportes == 'on'}">checked="checked"</c:if>>
							<label>mes enchères remportées</label>
						</div>
					</div>
				</div>
				<div>
					<div>
						<input
							type="radio"
							name="typeFiltre"
							value="vente"
							onclick="selectType(false)"
							<c:if test="${param.typeFiltre == 'vente'}">checked="checked"</c:if>>
						<label>Ventes</label>
					</div>
					<div>
						<div>
							<input
								class="vente"
								type="checkbox"
								name="chkVentesEnCours"
								<c:if test="${param.chkVentesEnCours == 'on'}">checked="checked"</c:if>>
							<label>mes ventes en cours</label>
						</div>
						<div>
							<input
								class="vente"
								type="checkbox"
								name="chkVentesNonDebutes"
								<c:if test="${param.chkVentesNonDebutes == 'on'}">checked="checked"</c:if>>
							<label>ventes non débutées</label>
						</div>
						<div>
							<input
								class="vente"
								type="checkbox"
								name="chkVentesTerminees"
								<c:if test="${param.chkVentesTerminees == 'on'}">checked="checked"</c:if>>
							<label>ventes terminées</label>
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<div>
			<input
				type="submit"
				value="Rechercher" />
		</div>

	</form>

	<!-- Liste des enchères  -->
	<!--<div class="Enchere"> a supprimer si affichage ok-->
	
	<section id="encheres">

		<c:forEach
			var="article"
			items="${mapArticlesAffiches}">

			<!--  <option value="${article.key}">${categorie.value.libelle}</option> -->
<div class="articles">
			<div>
				<img
					alt="photo objet"
					src="">
			</div>

			<div>
				<div>
					<c:choose>
					<c:when test="${(!empty sessionScope.utilisateur) and (sessionScope.utilisateur.actif eq 'true')}">
						<a href="<c:url value="/ServletEnchere?noArticle=${article.key}"/>"
							id="libelleArticle">${article.value.nomArticle}</a>
					</c:when>
					<c:otherwise>
						<span>${article.value.nomArticle}</span>
					</c:otherwise>
					</c:choose>
				</div>
				<div>
					Prix : <span id="nbPoints">
								<c:if test="${article.value.enchereMax.montantEnchere > 0} ">${article.value.enchereMax.montantEnchere}</c:if> 
								<c:if test="${article.value.enchereMax.montantEnchere == 0}">${article.value.prixInitial}</c:if>
						   </span>
					points
				</div>
				<div>
					Fin de l'enchère : <span id="dateFinEnchere">${article.value.dateFinEncheres}</span>
				</div>
				<div>
					Vendeur : <span>${article.value.vendeur.pseudo}</span>
				</div>
			</div>
</div>
		</c:forEach>
	</section>	
	<!-- </div> a supprimer -->

	<script src="<c:url value="/js/accueil.js"/>"></script>
	<!--<script src="<c:url value="/WEB-INF/Accueil/accueil.js"/>"></script> A revoir-->

	<!-- selection par défaut -->
	<c:if test="${empty param.recherche}">
		<script type="text/javascript">
			selectType(true, 'vente');
		</script>
	</c:if>
	<c:if test="${param.recherche}">
		<c:if test="${param.typeFiltre == 'achat'}">
			<script type="text/javascript">
				selectType(true, 'vente');
			</script>
		</c:if>
		<c:if test="${param.typeFiltre != 'achat'}">
			<script type="text/javascript">
				selectType(true, 'achat');
			</script>
		</c:if>
	</c:if>
	</main>
</body>
</html>