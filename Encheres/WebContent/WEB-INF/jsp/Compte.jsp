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
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Creer Compte</title>



<!-- Déclaration des variables -->
<c:set
	var="colorIdentifiant"
	value="black"
	scope="page" />
<c:set
	var="colorMdp"
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
<c:if test="${requestScope.erreurIdentifiant == true }">
	<c:set
		var="ColorIdentifiant"
		value="red"
		scope="page" />
	<c:set
		var="textErreur"
		value="${textErreur} identifiant existe déjà\n"
		scope="page" />
	<c:set
		var="erreur"
		value="true"
		scope="page" />
</c:if>

<c:if test="${requestScope.erreurMdp == true }">
	<c:set
		var="ColorMdp"
		value="red"
		scope="page" />
	<c:set
		var="textErreur"
		value="${textErreur} confirmation différente du mot de passe\n"
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
.ProblemeIdentifiant {
	color: ${ColorIdentifiant
}

}
.ProblemeMdp {
	color: ${ColorMdp
}
</style>
</head>

<!-- Corp de la JSP -->
<body>
	<%@include file="EnTeteEni.jspf"%>
	<br>
	<br>
	<br>

	<h1>Mon Profil</h1>

	<form
		method="post"
		action="<c:url value="/ServletCompte"/>">

		<table>
			<tr>
				<th>
					<div>
						<label for="pseudo">Pseudo:</label>
						<c:if test="${param.modifMdp}">
							<span
								name="pseudoFige"
								id="pseudoFige">${utilisateurAffiche.pseudo }</span>
						</c:if>
						<c:if test="${!param.modifMdp}">
							<input
								type="text"
								name="pseudo"
								id="pseudo"
								placeholder="Entrer votre pseudo"
								value="${utilisateurAffiche.pseudo }"
								required
								pattern="[A-Za-z0-9]{1,30}"
								autofocus>
						</c:if>
					</div>
				</th>

				<th>
					<div>
						<label for="nom">Nom:</label>
						<c:choose>
							<c:when test="${param.creation}">
								<input
									type="text"
									name="nom"
									id="nom"
									placeholder="Entrer votre nom"
									value="${utilisateurAffiche.nom }"
									required
									pattern="[A-Za-z]{2,30}">
							</c:when>
							<c:otherwise>
								<span
									name="nomFige"
									id="nomFige">${utilisateurAffiche.nom }</span>
							</c:otherwise>
						</c:choose>

						<!--<c:if test="${!param.creation || param.modifMdp}">
							<span
								name="nomFige"
								id="nomFige">${utilisateurAffiche.nom }</span>

						</c:if>
						<c:if test="${param.creation}">
							<input
								type="text"
								name="nom"
								id="nom"
								placeholder="Entrer votre nom"
								value="${utilisateurAffiche.nom }"
								required
								pattern="[A-Za-z]{2,30}">
						</c:if>-->
					</div>
				</th>
			</tr>

			<tr>
				<th>
					<div>
						<label for="prenom">Prénom:</label>

						<c:choose>
							<c:when test="${param.creation}">
								<input
									type="text"
									name="prenom"
									id="prenom"
									placeholder="Entrer votre prénom"
									value="${utilisateurAffiche.prenom }"
									required
									pattern="[A-Za-z]{2,30}">
							</c:when>
							<c:otherwise>
								<span
									name="prenomFige"
									id="prenomFige">${utilisateurAffiche.prenom }</span>
							</c:otherwise>
						</c:choose>

						<!--<c:if test="${!param.creation && param.modifMdp}">
							<span
								name="prenomFige"
								id="prenomFige">${utilisateurAffiche.prenom }</span>
						</c:if>
						<c:if test="${param.creation}">
							<input
								type="text"
								name="prenom"
								id="prenom"
								placeholder="Entrer votre prénom"
								value="${utilisateurAffiche.prenom }"
								required
								pattern="[A-Za-z]{2,30}">
						</c:if>-->
					</div>
				</th>
				<th>
					<div>

						<label for="email">Email:</label>

						<c:if test="${param.modifMdp}">
							<span
								name="emailFige"
								id="emailFige">${utilisateurAffiche.email }</span>
						</c:if>
						<c:if test="${!param.modifMdp}">
							<input
								type="email"
								name="email"
								id="email"
								placeholder="Entrer votre email"
								value="${utilisateurAffiche.email }"
								required>
						</c:if>
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div>
						<label for="telephone">Téléphone:</label>
						<c:if test="${param.modifMdp}">
							<span
								name="telephoneFige"
								id="telephoneFige">${utilisateurAffiche.telephone}</span>
						</c:if>
						<c:if test="${!param.modifMdp}">
							<input
								type="text"
								name="telephone"
								id="telephone"
								placeholder="Entrer votre téléphone"
								value="${utilisateurAffiche.telephone }"
								required
								pattern="[0-9]{10}">
						</c:if>
					</div>
				</th>
				<th>
					<div>
						<label for="rue">Rue:</label>
						<c:if test="${param.modifMdp}">
							<span
								name="rueFige"
								id="rueFige">${utilisateurAffiche.rue }</span>
						</c:if>
						<c:if test="${!param.modifMdp}">
							<input
								type="text"
								name="rue"
								id="rue"
								placeholder="Entrer votre rue"
								value="${utilisateurAffiche.rue }"
								required>
						</c:if>
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div>
						<label for="codePostal">Code Postal:</label>
						<c:if test="${param.modifMdp}">
							<span
								name="codePostalFige"
								id="codePostalFige">${utilisateurAffiche.codePostal }</span>
						</c:if>
						<c:if test="${!param.modifMdp}">
							<input
								type="text"
								name="codePostal"
								id="codePostal"
								placeholder="Entrer votre code postal"
								value="${utilisateurAffiche.codePostal }"
								required
								pattern="[0-9]{5}">
						</c:if>
					</div>
				</th>
				<th>
					<div>
						<label for="ville">Ville:</label>
						<c:if test="${param.modifMdp}">
							<span
								name="villeFige"
								id="villeFige">${utilisateurAffiche.ville }</span>
						</c:if>
						<c:if test="${!param.modifMdp}">
							<input
								type="text"
								name="ville"
								id="ville"
								placeholder="Entrer votre ville"
								value="${utilisateurAffiche.ville }"
								required>
						</c:if>
					</div>
				</th>
			</tr>
			<!-- Partie spéciale "Créer compte" -->

			<c:if test="${param.creation}">

				<tr>
					<th>
						<div class="ProblemeMdp">
							<label for="mdp">Mot de passe:</label> <input
								type="password"
								name="mdp"
								id="mdp"
								placeholder="Entrer votre mot de passe"
								required>
						</div>
					</th>
					<th>
						<div class="ProblemeMdp">
							<label for="confirmation">Confirmation:</label> <input
								type="password"
								name="confirmation"
								id="confirmation"
								placeholder="Confirmer votre nouveau mot de passe"
								required>
						</div>
					</th>
				</tr>
				<tr>
					<th>
						<div>
							<input
								type="submit"
								value="Créer" />
						</div>
					</th>
					<th>
						<div>

							<a href="<%=request.getContextPath()%>"> <input
								type="button"
								value="Annuler" /></a>
						</div>
					</th>
				</tr>

			</c:if>


			<!-- Partie spéciale "Modifier mon profil" -->

			<c:if test="${empty param.creation}">
				<input
					type="hidden"
					name="modificationCompte"
					value="true">
					
				<tr>

					<th>
						<div class="ProblemeMdp">
							<label for="oldMdp">Mot de passe actuel:</label> <input
								type="password"
								name="oldMdp"
								id="oldMdp"
								placeholder="Veuillez entrer votre mot de passe actuel"
								required>
						</div>
					</th>
				</tr>
				<tr>
					<th><a
						href="<c:url value="/ServletCompte?modifMdp=true&modification=true"/>">Modifier
							mon mot de passe</a></th>
				</tr>

				<c:if test="${param.modifMdp}">
				<input
					type="hidden"
					name="modifMdp"
					value="true">
					
					<tr>
						<th>
							<div class="ProblemeMdp">
								<label for="newMdp">Nouveau mot de passe:</label> <input
									type="password"
									name="newMdp"
									id="newMdp"
									placeholder="Veuillez entrer votre nouveau mot de passe">
							</div>
						</th>
						<th>
							<div class="ProblemeMdp">
								<label for="confirmation">Confirmation:</label> <input
									type="password"
									name="confirmation"
									id="confirmation"
									placeholder="Veuillez confirmer votre nouveau mot de passe"
									required>
							</div>
						</th>
					</tr>
				</c:if>
				<tr>
					<th>
						<div>
							<label for="credit">Crédit:</label> <span
								name="credit"
								id="credit">${utilisateurAffiche.credit}</span>

						</div>
					</th>
				</tr>

				<tr>
					<th>
						<div>
							<input
								type="submit"
								value="Enregistrer" />
						</div>
					</th>
					<th>
						<div>

							<a href="<c:url value="/ServletCompte?suppression=true"/>"> <input
								type="button"
								value="Supprimer mon compte" /></a>
						</div>
					</th>
				</tr>

			</c:if>
		</table>
	</form>

</body>
</html>