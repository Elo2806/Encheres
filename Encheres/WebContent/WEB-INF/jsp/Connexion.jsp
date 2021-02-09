<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>
	
	<form action="<%=request.getContextPath()%>/ServletAccueil">
		<div>
			<label for="identifiant">Identifiant : </label>
			<input type="text" name="identifiant" id="identifiant">
		</div>
		<div>
			<label for="motdepasse">Mot de passe : </label>
			<input type="password" name="motdepasse" id="motdepasse">
		</div>
		
		<div>
			<button type="submit">Connexion</button>
			
			<input type="checkbox" name="memoriser" value="Se souvenir de moi"><label for="memoriser">Se souvenir de moi</label>
			<a href="">Mot de passe oublié</a>
		</div>
	</form>
	
	<a href=""><button name="creer">Créer un compte</button></a>


</body>
</html>