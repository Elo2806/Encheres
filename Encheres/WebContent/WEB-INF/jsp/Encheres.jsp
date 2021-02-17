<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	scope="page"/>
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
	color: ${colorProposition}
}
</style>

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

			<p id="libelleArticle">${requestScope.articleEnVente.nomArticle }</p>
		</div>
		<div>
			<p>Description :</p>
			<span id="descriptionArticle">${requestScope.articleEnVente.description}</span>
		</div>
		<div>
			<p>
				Categorie : <span id="CategorieArticle">${requestScope.articleEnVente.categorie.libelle}</span>
			</p>
		</div>
		<div>
			<p>
				Meilleur offre : <span id="nbPointsMeilleur">${requestScope.articleEnVente.enchereMax.montantEnchere}</span> pts par <span
					id="pseudoMeilleur">${requestScope.articleEnVente.enchereMax.utilisateur.pseudo}</span>
			</p>
		</div>
		<div>
			<p>
				Mise à prix : <span id="prix">${requestScope.articleEnVente.prixInitial}</span> points
			</p>
		</div>
		<div>
			<p>
				Fin de l'enchere <span id="dateFin">${requestScope.articleEnVente.dateFinEncheres}</span>
			</p>
		</div>
		<div>
			<span>Retrait : </span>
			<div id="Adresse">
				<span>${requestScope.articleEnVente.retrait.rue}</span><br> <span id="codePostal">${requestScope.articleEnVente.retrait.codePostal}</span>
				<span id="ville">${requestScope.articleEnVente.retrait.ville}</span>
			</div>
		</div>
		<div>
			<p>Vendeur : <span id="vendeur">${requestScope.articleEnVente.vendeur.pseudo}</span></p>
		</div>
		<form method="post" action="<c:url value="/ServletEnchere"/>">
		<input type="hidden" name="noArticle" value="${param.noArticle}">
			<div class="ProblemeProposition">>
				<label for="proposition">Ma proposition : </label> <input
					type="number" name="proposition" id="proposition" value=${requestScope.meilleureEnchere }
					style="width: 43px;"> <input type="submit" value="Enchérir">
			</div>
		</form>
	</div>

</body>
</html>