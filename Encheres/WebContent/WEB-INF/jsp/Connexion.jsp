<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link href="css/styleConnexion.css" rel="stylesheet"/>
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
	<section class="container blockConnection col-6">
		<form method="post" action="<c:url value="/ServletConnexion"/>">
			<!-- <div id="identifiants" class="pave"> -->
				<div class="form-group row">
					<label class="col-4" for="identifiant" class="element">Identifiant : </label>
					<input class="col-8" type="text" name="identifiant" class="element" id="identifiant" required value="${param.identifiant }">
				</div>
				<div class="form-group row">
					<label class="col-4" for="motdepasse" class="element">Mot de passe : </label>
					<input class="col-8" type="password" name="motdepasse" class="element" id="motdepasse" required value="${param.motdepasse }">
				</div>
			<!-- </div> -->
			
			<div class="form-group row">
				<button class="col-4" type="submit" id="btn_connexion">Connexion</button>
				<div class="col-1"></div>
				
				<div class="form-group col-7">
				<div class="row">
					<input class="col-1" id="case" type="checkbox" name="memoriser" value="Se souvenir de moi">
					<label class="col-11" for="memoriser">Se souvenir de moi</label>
				</div>
				<div class="row">
					<a href="#">Mot de passe oublié</a>
				</div>
			</div>
			</div>
		</form>
		
		<a class="row col-12" href="<c:url value="/ServletCompte?creation=true&modification=true"/>" id="lien_creer_compte" class="pave"><button class="row col-12" name="creer" id="creer_compte">Creer un compte</button></a>
	</section>
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</div>
</body>
</html>