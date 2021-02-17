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
<c:if test="${erreur == 'true' }">
	<script type="text/javascript">
		var txt = '${textErreur}';
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

			<p id="libelleArticle">PC Gamer pour travailler</p>
		</div>
		<div>
			<p>Description :</p>
			<span id="descriptionArticle">BLA BLA BLA BLA BLA</span>
		</div>
		<div>
			<p>
				Categorie : <span id="CategorieArticle">Informatique</span>
			</p>
		</div>
		<div>
			<p>
				Meilleur offre : <span id="nbPointsMeilleur">210</span> pts par <span
					id="pseudoMeilleur">Bob</span>
			</p>
		</div>
		<div>
			<p>
				Mise à prix : <span id="prix">185</span> points
			</p>
		</div>
		<div>
			<p>
				Fin de l'enchere <span id="dateFin">09/10/2018</span>
			</p>
		</div>
		<div>
			<span>Retrait : </span>
			<div id="Adresse">
				<span>10 allée des Alouettes</span><br> <span id="codePostal">44800</span>
				<span id="ville">Saint Herblain</span>
			</div>
		</div>
		<div>
			<p>Vendeur : <span id="vendeur">jojo44</span></p>
		</div>
		<form method="post" action="<c:url value="/ServletEnchere"/>">
			<div class="ProblemeProposition">>
				<label for="proposition">Ma proposition : </label> <input
					type="number" name="proposition" id="proposition" value=220
					style="width: 43px;"> <input type="submit" value="Enchérir">
			</div>
		</form>
	</div>

</body>
</html>