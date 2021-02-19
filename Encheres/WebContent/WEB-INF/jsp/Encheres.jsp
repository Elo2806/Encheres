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
<meta
	name="viewport"
	content="width=device-width, initial-scale=1">
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<link
	href="css/styleAccueil.css"
	rel="stylesheet" />
<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8">


<title>Vente</title>

<!-- Déclaration des variables -->
<c:set
	var="colorProposition"
	value="black"
	scope="page" />

<c:set
	var="textErreur"
	value="Cette insertion a échoué car : \n"
	scope="page" />
<c:set
	var="erreur"
	value="false"
	scope="page" />
<c:set
	var="textReussite"
	value="Super\n"
	scope="page" />
<c:set
	var="reussite"
	value="false"
	scope="page" />



<!-- Tests des attributs d'erreur pour changer la couleur de champs en erreur  -->
<c:if test="${requestScope.enchereInsuffisante == true }">
	<c:set
		var="colorProposition"
		value="red"
		scope="page" />
	<c:set
		var="textErreur"
		value="${textErreur} Montant enchere insuffisant\n"
		scope="page" />
	<c:set
		var="erreur"
		value="true"
		scope="page" />
</c:if>
<c:if test="${requestScope.bravo == true }">
	<c:set
		var="colorProposition"
		value="black"
		scope="page" />
	<c:set
		var="textReussite"
		value="${textReussite} Votre enchère est prise en compte"
		scope="page" />
	<c:set
		var="reussite"
		value="true"
		scope="page" />
</c:if>
<c:if test="${erreur == 'true' }">
	<script type="text/javascript">
		var txt = '${textErreur}';
		alert(txt);
	</script>
</c:if>
<c:if test="${reussite == 'true' }">
	<script type="text/javascript">
		var txt = '${textReussite}';
		alert(txt);
	</script>
</c:if>


<!-- Mise en forme -->
<style type="text/css">
.ProblemeProposition {
	color: ${colorProposition
}
}
</style>

</head>
<body>
	<div class="container" style="margin-top: 30px;">
		

		<%@include file="EnTeteEni.jspf"%>
		
				<c:choose>
					<c:when test="${empty requestScope.venteTerminee}">
						<h2 class="titre">Détail vente</h2>
					</c:when>
					<c:when test="${!empty requestScope.vainqueur}">
						<h2 class = "titre">Vous avez remporté la vente</h2>
					</c:when>
					<c:otherwise>
						<h2 class = "titre">${requestScope.articleEnVente.enchereMax.utilisateur.pseudo}
							a remporté l'enchere</h2>
					</c:otherwise>
				</c:choose>
			</div>
<div class="container">
			<div class="row">
				<div class="col-6 photoEncheres">

					<img
						class="photo-left"
						alt="photo objet"
						src="images/ecommerce-navigation.png">
				</div>
				
				<div class="col-6" id="encadre">
					<div class="descriptionVente">
					
						<div class="container encheres">
							<div class="row">
								<div class="col-md-12">
									<p id="libelleArticle">${requestScope.articleEnVente.nomArticle }</p>
								</div>
							</div>
						</div>
						<br>
						<div class="container encheres">
							<div class="row">
								<div class="col-md-4">
									<p class="text-info">Description :</p>
								</div>
								<div class="col-md-8">
									<span id="descriptionArticle">${requestScope.articleEnVente.description}</span>
								</div>
							</div>
						</div>
						<br>

						<div class="container encheres">
							<div class="row">
								<div class="col-md-4">
									<p class="text-info">Categorie :</p>
								</div>

								<div class="col-md-8">
									<span id="CategorieArticle">${requestScope.articleEnVente.categorie.libelle}</span>
								</div>
							</div>
						</div>
						<br>


						<div class="container encheres">
							<div class="row">
								<div class="col-md-4">
									<p class="text-info">Meilleure offre :</p>
								</div>
								<div class="col-md-8">
									<span id="nbPointsMeilleur">${requestScope.articleEnVente.enchereMax.montantEnchere}</span>
									pts par <span id="pseudoMeilleur">${requestScope.articleEnVente.enchereMax.utilisateur.pseudo}</span>
								</div>
							</div>
						</div>
						<br>


						<div class="container encheres">
							<div class="row">
								<div class="col-md-4">

									<p class="text-info">Mise à prix :</p>
								</div>
								<div class="col-md-8">
									<span id="prix">${requestScope.articleEnVente.prixInitial}</span>
									points
								</div>
							</div>
						</div>
						<br>

						<div class="container encheres">
							<div class="row">
								<div class="col-md-4">
									<p class="text-info">Fin de l'enchere</p>
								</div>
								<div class="col-md-8">
									<span id="dateFin">${requestScope.articleEnVente.dateFinEncheres}</span>
								</div>
							</div>
						</div>
						<br>

						<div class="container encheres">
							<div class="row">
								<div class="col-md-4">
									<p class="text-info">Retrait : </p>
								</div>
								<div class="col-md-8">
									<div id="Adresse">
										<span>${requestScope.articleEnVente.retrait.rue}</span><br>
										<span id="codePostal">${requestScope.articleEnVente.retrait.codePostal}</span><br>
										<span id="ville">${requestScope.articleEnVente.retrait.ville}</span>
									</div>
								</div>
							</div>
						</div>
						<br>

						<div class="container encheres">
							<div class="row">
								<div class="col-md-4">
									<p class="text-info">Vendeur :</p>
								</div>
								<div class="col-md-8">
									<span id="vendeur">${requestScope.articleEnVente.vendeur.pseudo}</span>
								</div>
							</div>
						</div>
						<br>

						<div class="container encheres">
							<div class="row">
							
								
						<c:choose>
							<c:when test="${empty requestScope.venteTerminee}">
								<form
									method="post"
									action="<c:url value="/ServletEnchere"/>">
									<input
										type="hidden"
										name="noArticle"
										value="${param.noArticle}">
									<div class="ProblemeProposition">
									<p class="text-info">
										<label for="proposition"> Ma proposition : </label> <input
											type="number"
											name="proposition"
											id="proposition"
											value=${requestScope.meilleureEnchere }> 
											
											<input
											type="submit"
											class="btn btn-primary btn-lg btn-encherir"
											value="Enchérir">
											</p>
									</div>
								</form>
							</c:when>
							<c:when test="${!empty requestScope.vendeur && requestScope.articleEnVente.prixVente ==0}">
								<form
									method="post"
									action="<c:url value="/ServletEnchere"/>">
									<input
										type="hidden"
										name="retraitEffectue"
										value="true" /> <input
										type="hidden"
										name="noArticle"
										value="${param.noArticle}"> <input
										type="submit"
										class="btn btn-primary btn-lg"
										value="Retrait Effectué">
								</form>
								<br>
							</c:when>
							<c:when test="${!empty requestScope.vainqueur}">
							
								<div class="container encheres">
								<div class="row">
								<div class="col-md-4">
									<p class="text-info">Tel :</p> 
								</div> 
								<div class="col-md-8">
								<span id="vendeurTelephone">${requestScope.articleEnVente.vendeur.telephone}</span>
								</div>
								</div>
								</div>
								<br>
								
								<div class="container encheres">
								<div class="row">
								<div class="col-md-4">
									<p class="text-info">Mail : </p>
									</div><div class="col-md-8">
								<span id="vendeurEmail">${requestScope.articleEnVente.vendeur.email}</span>
									</div>
									</div>
								</div>
								<br>
								
								<div class="container btn-centre">
								<a href="<c:url value="/accueil"/>"><input
									type="button"
									class="btn btn-primary btn-lg btn-encheres"
									value="Back"></a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value="/accueil"/>"><input
									type="button"
									class="btn btn-primary btn-lg btn-encheres"
									value="Back"></a>
							</c:otherwise>
						</c:choose>
						</div>
						
						</div>
						</div>
						<br>
						
						
						
					</div>
				</div>
			</div>
			</div>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>