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

<title>Compte</title>


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
		var="colorIdentifiant"
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
		var="colorMdp"
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
	color: ${colorIdentifiant
}

}
.ProblemeMdp {
	color: ${colorMdp
}
}
</style>
</head>

<!-- Corp de la JSP -->
<body>
	<div class="container">
		<%@include file="EnTeteEni.jspf"%>
		<br> <br> <br>

		<h1 class="titre">Mon Profil</h1>

		<section class="container blockConnection">
			<form
				class="form"
				method="post"
				action="<c:url value="/ServletCompte"/>">

				<table>
					<tr>
						<th>
							<div class="divCompte">
								<div class="ProblemeIdentifiant">
									<label
										class="labelCompte"
										for="pseudo">Pseudo:</label>
									<c:if test="${param.modifMdp}">
										<span id="pseudoFige">${utilisateurAffiche.pseudo }</span>
									</c:if>
									<c:if test="${!param.modifMdp}">
										<input
											class="inputCompte"
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
							</div>
						</th>

						<th>
							<div class="divCompte">

								<label
									class="labelCompte"
									for="nom">Nom:</label>
								<c:choose>
									<c:when test="${param.creation}">
										<input
											class="inputCompte"
											type="text"
											name="nom"
											id="nom"
											placeholder="Entrer votre nom"
											value="${utilisateurAffiche.nom }"
											required
											pattern="[A-Za-z]{2,30}">
									</c:when>
									<c:otherwise>
										<span id="nomFige">${utilisateurAffiche.nom }</span>
									</c:otherwise>
								</c:choose>

							</div>
						</th>
					</tr>

					<tr>
						<th>
							<div class="divCompte">
								<label
									class="labelCompte"
									for="prenom">Prénom:</label>

								<c:choose>
									<c:when test="${param.creation}">
										<input
											class="inputCompte"
											type="text"
											name="prenom"
											id="prenom"
											placeholder="Entrer votre prénom"
											value="${utilisateurAffiche.prenom }"
											required
											pattern="[A-Za-z]{2,30}">
									</c:when>
									<c:otherwise>
										<span id="prenomFige">${utilisateurAffiche.prenom }</span>
									</c:otherwise>
								</c:choose>

								<!--Même méthode avec if:
						<c:if test="${!param.creation && param.modifMdp}">
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
							<div class="divCompte">
								<div class="ProblemeIdentifiant">

									<label
										class="labelCompte"
										for="email">Email:</label>

									<c:if test="${param.modifMdp}">
										<span id="emailFige">${utilisateurAffiche.email }</span>
									</c:if>
									<c:if test="${!param.modifMdp}">
										<input
											class="inputCompte"
											type="email"
											name="email"
											id="email"
											placeholder="Entrer votre email"
											value="${utilisateurAffiche.email }"
											required>
									</c:if>
								</div>
							</div>
						</th>
					</tr>
					<tr>
						<th>
							<div class="divCompte">
								<label
									class="labelCompte"
									for="telephone">Téléphone:</label>
								<c:if test="${param.modifMdp}">
									<span id="telephoneFige">${utilisateurAffiche.telephone}</span>
								</c:if>
								<c:if test="${!param.modifMdp}">
									<input
										class="inputCompte"
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
							<div class="divCompte">
								<label
									class="labelCompte"
									for="rue">Rue:</label>
								<c:if test="${param.modifMdp}">
									<span id="rueFige">${utilisateurAffiche.rue }</span>
								</c:if>
								<c:if test="${!param.modifMdp}">
									<input
										class="inputCompte"
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
							<div class="divCompte">
								<label
									class="labelCompte"
									for="codePostal">Code Postal:</label>
								<c:if test="${param.modifMdp}">
									<span id="codePostalFige">${utilisateurAffiche.codePostal }</span>
								</c:if>
								<c:if test="${!param.modifMdp}">
									<input
										class="inputCompte"
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
							<div class="divCompte">
								<label
									class="labelCompte"
									for="ville">Ville:</label>
								<c:if test="${param.modifMdp}">
									<span id="villeFige">${utilisateurAffiche.ville }</span>
								</c:if>
								<c:if test="${!param.modifMdp}">
									<input
										class="inputCompte"
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
								<div class="divCompte">
									<div class="ProblemeMdp">
										<label
											class="labelCompte"
											for="mdp">Mot de passe:</label> <input
											class="inputCompte"
											type="password"
											name="mdp"
											id="mdp"
											placeholder="Entrer votre mot de passe"
											required>
									</div>
								</div>
							</th>
							<th>
								<div class="divCompte">
									<div class="ProblemeMdp">
										<label
											class="labelCompte"
											for="confirmation">Confirmation:</label> <input
											class="inputCompte"
											type="password"
											name="confirmation"
											id="confirmation"
											placeholder="Confirmer ce mdp"
											required>
									</div>
								</div>
							</th>
						</tr>
						<tr>
							<th>
								<div class="divCompte">
									<input
										type="submit"
										value="Créer" />
								</div>
							</th>
							<th>
								<div class="divCompte">

									<a href="<c:url value="/ServletAccueil"/>"> <input
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
								<div class="divCompte">
									<div class="ProblemeMdp">
										<label
											class="labelCompte"
											for="oldMdp">Mot de passe actuel:</label> <input
											class="inputCompte"
											type="password"
											name="oldMdp"
											id="oldMdp"
											placeholder="Entrer votre mdp actuel"
											required>
									</div>
								</div>
							</th>
						</tr>
						<tr>
							<th colspan="2">
							<div class="divCompte">
									<div class="lienMdp">
										<a class="btn btn-info btn-sm" href="<c:url value="/ServletCompte?modifMdp=true&modification=true"/>">Modifier
											mon mot de passe</a>
									</div>
								</div></th>
						</tr>

						<c:if test="${param.modifMdp}">
							<input
								type="hidden"
								name="modifMdp"
								value="true">

							<tr>
								<th>
									<div class="divCompte">
										<div class="ProblemeMdp">
											<label
												class="labelCompte"
												for="newMdp">Nouveau mdp:</label> <input
												class="inputCompte"
												type="password"
												name="newMdp"
												id="newMdp"
												placeholder="Entrer votre nouveau mdp">
										</div>
									</div>
								</th>
								<th>
									<div class="divCompte">
										<div class="ProblemeMdp">
											<label
												class="labelCompte"
												for="confirmation">Confirmation:</label> <input
												class="inputCompte"
												type="password"
												name="confirmation"
												id="confirmation"
												placeholder="Confirmer ce mdp"
												required>
										</div>
									</div>
								</th>
							</tr>
						</c:if>
						<tr>
							<th>
								<div class="divCompte">
									<label
										class="labelCompte"
										for="credit">Crédit:</label> <span class="nbPoints" id="credit">${utilisateurAffiche.credit}</span>

								</div>
							</th>
						</tr>

						<tr>
							<th>
								<div class="divCompte">
									<div class="btn_submit">
										<button
											class="btn btn-primary btn-lg"
											name="enregistrer"
											type="submit" />
										Enregistrer
										</button>
									</div>
								</div>
							</th>
							<th>
								<div class="divCompte">
									<div class="btn_submit">

										<button
											class="btn btn-primary btn-lg"
											name="supprimer"
											type="submit" />
										Supprimer mon compte
										</button>
									</div>
								</div>
							</th>
						</tr>

					</c:if>
				</table>
			</form>
			</section>
		</div>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>