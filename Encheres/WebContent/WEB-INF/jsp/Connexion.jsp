<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link href="css/styleAccueil.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>

<c:set var="ColorIdentifiant" value="black" scope="page" />
<c:if test="${requestScope.erreurIdentifiant == true }">
	<c:set var="ColorIdentifiant" value="red" scope="page" />
	<script type="text/javascript">
		alert('Identifiant non existant');
	</script>
</c:if>

</head>
<body>
<div class="container pageConnection">
	<%@include file="EnTeteEni.jspf"%>
	
	<section class="container blockConnection col-md-6">
		<form method="post" action="<c:url value="/ServletConnexion"/>">
		
				<div class="form-group row">
					<label class="col-md-4" for="identifiant" class="element">Identifiant : </label>
					<input class="col-md-8" type="text" name="identifiant" class="element" id="identifiant" autofocus required value="${param.identifiant }">
				</div>
				<div class="form-group row">
					<label class="col-md-4" for="motdepasse" class="element">Mot de passe : </label>
					<input class="col-md-8" type="password" name="motdepasse" class="element" id="motdepasse" required value="${param.motdepasse }">
				</div>
		
			
			<div class="form-group row">
				<button class="col-md-4 btn btn-primary btn-lg" type="submit" id="btn_connexion">Connexion</button>
				
				<div class="col-md-2"></div>
				
				<div class="form-group col-md-6">
				<div class="row">
					<input class="col-md-1" id="case" type="checkbox" name="memoriser" value="Se souvenir de moi">
					<label class="col-md-11" for="memoriser">Se souvenir de moi</label>
				</div>
				<div class="row">
				<div class="col-md-1"></div>
				<a class="col-md-11" href="#">Mot de passe oublié</a>
				</div>
			</div>
			</div>
		</form>
		
		<a href="<c:url value="/ServletCompte?creation=true&modification=true"/>" id="lien_creer_compte" class="pave">
		<button class="row col-md-12 btn btn-primary btn-lg" name="creer" id="creer_compte">Creer un compte</button></a>
	</section>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</div>
</body>
</html>