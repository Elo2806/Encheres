<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>

<c:set var="ColorIdentifiant" value="black" scope="page" />
<c:if test="${requestScope.erreurIdentifiant == true }">
	<c:set var="ColorIdentifiant" value="red" scope="page" />
	<script type="text/javascript">
		alert('Identifiant non existant');
	</script>
</c:if>

<style type="text/css">
	
	.intitule{
	display:flex;
	flex-direction:column;
	justify-content: flex-start;
	border: black solid;
	}
	.intitule{
	color:${ColorIdentifiant}
	}
	.champ{
	display:flex;
	flex-direction:column;
	justify-content: flex-start;
	border: black solid;
	}
	#identifiants{
	display:flex;
	justify-content: center;
	}
	
</style>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>
	<section>
		<form method="post" action="<%=request.getContextPath()%>/ServletConnexion">
			<div id="identifiants">
				<div class="intitule">
					<label for="identifiant">Identifiant : </label>
					<label for="motdepasse">Mot de passe : </label>
				</div>
				<div class="champ">
					<input type="text" name="identifiant" id="identifiant" value="${param.identifiant }">
					<input type="password" name="motdepasse" id="motdepasse" value="${param.motdepasse }">
				</div>
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