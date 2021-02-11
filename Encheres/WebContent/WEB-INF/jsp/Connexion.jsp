<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
<style type="text/css">
	section{
	text-align: center;
	}
	
</style>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>
	<section>
		<form method="post" action="<%=request.getContextPath()%>/ServletConnexion">
			<div>
				<label class="intitule" for="identifiant">Identifiant : </label>
				<inpu class="champ"t type="text" name="identifiant" id="identifiant">
			</div>
			<div>
				<label class="intitule" for="motdepasse">Mot de passe : </label>
				<input class="champ" type="password" name="motdepasse" id="motdepasse">
			</div>
			
			<div>
				<button type="submit">Connexion</button>
				
				<input type="checkbox" name="memoriser" value="Se souvenir de moi"><label for="memoriser">Se souvenir de moi</label>
				<a href="">Mot de passe oublié</a>
			</div>
		</form>
		
		<a href="<%=request.getContextPath()%>/ServletCompte?creation=true&modification=true"><button name="creer">Créer un compte</button></a>
	</section>

</body>
</html>