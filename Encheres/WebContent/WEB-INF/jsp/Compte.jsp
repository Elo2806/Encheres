<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creer Compte</title>
</head>

<body>
	<%@include file="EnTeteEni.jspf"%>
	<br>
	<br>
	<br>

	<h1>Mon Profil</h1>

	<form method="post" action="<%=request.getContextPath()%>/ServletProfil">

		<table>
			<tr>
				<th>
					<div>
						<label for="pseudo">Pseudo:</label> <input type="text"
							name="pseudo" id="pseudo"
							placeholder="Veuillez entrer votre pseudo">
					</div>
				</th>

				<th>
					<div>
						<label for="nom">Nom:</label> <input type="text" name="nom"
							id="nom" placeholder="Veuillez entrer votre nom">
					</div>
				</th>
			</tr>

			<tr>
				<th>
					<div>
						<label for="prenom">Prénom:</label> <input type="text"
							name="prenom" id="prenom"
							placeholder="Veuillez entrer votre prénom">
					</div>
				</th>
				<th>
					<div>
						<label for="email">Email:</label> <input type="text" name="email"
							id="email" placeholder="Veuillez entrer votre email">
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div>
						<label for="telephone">Téléphone:</label> <input type="text"
							name="telephone" id="telephone"
							placeholder="Veuillez entrer votre téléphone">
					</div>
				</th>
				<th>
					<div>
						<label for="rue">Rue:</label> <input type="text" name="rue"
							id="rue" placeholder="Veuillez entrer votre rue">
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div>
						<label for="codePostal">Code Postal:</label> <input type="text"
							name="codePostal" id="codePostal"
							placeholder="Veuillez entrer votre code postal">
					</div>
				</th>
				<th>
					<div>
						<label for="ville">Ville:</label> <input type="text" name="ville"
							id="ville" placeholder="Veuillez entrer votre ville">
					</div>
				</th>
			</tr>
			<!-- Partie spéciale "Créer compte" -->
			<tr>
				<th>
					<div>
						<label for="mdp">Mot de passe:</label> <input type="password"
							name="mdp" id="mdp"
							placeholder="Veuillez entrer votre mot de passe">
					</div>
				</th>
				<th>
					<div>
						<label for="confirmation">Confirmation:</label> <input
							type="password" name="confirmation" id="confirmation"
							placeholder="Veuillez confirmer votre nouveau mot de passe">
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div>
						<input type="submit" value="Créer" />
					</div>
				</th>
				<th>
					<div>

						<a href="<%=request.getContextPath()%>"> <input type="button"
							value="Annuler" /></a>
					</div>
				</th>
			</tr>

			<!-- Partie spéciale "Modifier mon profil" -->

			<tr>
				<th>
					<div>
						<label for="oldMdp">Mot de passe actuel:</label> <input
							type="password" name="oldMdp" id="oldMdp"
							placeholder="Veuillez entrer votre mot de passe actuel">
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div>
						<label for="newMdp">Nouveau mot de passe:</label> <input
							type="password" name="newMdp" id="newMdp"
							placeholder="Veuillez entrer votre nouveau mot de passe">
					</div>
				</th>
				<th>
					<div>
						<label for="confirmation">Confirmation:</label> <input
							type="password" name="ville" id="confirmation"
							placeholder="Veuillez confirmer votre nouveau mot de passe">
					</div>
				</th>
			</tr>
			<tr>
				<th>
					<div>
						<label for="credit">Crédit:</label> <input type="text"
							name="credit" id="credit">
					</div>
				</th>
			</tr>

			<tr>
				<th>
					<div>
						<input type="submit" value="Enregistrer" />
					</div>
				</th>
				<th>
					<div>

						<a href="<%=request.getContextPath()%>"> <input type="button"
							value="Supprimer mon compte" /></a>
					</div>
				</th>
			</tr>


		</table>
	</form>

</body>
</html>