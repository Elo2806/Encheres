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

	.pave{
	display:flex;
	justify-content: space-between;
	width: 30%;
	margin-right:35%;
	margin-left:35%;
	}
	.element{
	display:flex;
	align-items:center;
	height : 25px;
	width: 100%;
	margin:5px;
	}
	.intitule{
	display:flex;
	flex-direction:column;
	align-items: flex-start;
	justify-content:space-between;
	width: 40%;
	}
	.intitule{
	color:${ColorIdentifiant}
	}
	.champ{
	display:flex;
	flex-direction:column;
	align-items: flex-start;
	justify-content:space-between;
	width: 60%;
	}
	#identifiants{
	padding-top: 20px;
	}
	#choix{
	display:flex;
	flex-direction:column;
	align-items: flex-end;
	justify-content: space-between;
	padding:10px;
	margin: 20px 5px 20px 5px;
	}
	#case{
	margin: 10px;
	}
	#btn_connexion{
	display: flex;
	justify-content:center;
	align-items: center;
	padding:20px 50px 20px 50px;
	margin: 20px;
	}
	#connexion{
	margin-bottom: 20px;
	}
	#creer_compte{
	padding: 40px 60px 40px 60px;
	width: 100%
	}
	#lien_creer_compte{
	margin-top:50px;
	justify-content: center;
	text-decoration: none;
	}
	#btn_connexion,#creer_compte{
	border-radius: 10px;
	}
	
</style>
</head>
<body>
	<%@include file="EnTeteEni.jspf"%>
	<section>
		<form method="post" action="<%=request.getContextPath()%>/ServletConnexion">
			<div id="identifiants" class="pave">
				<div class="intitule">
					<label for="identifiant" class="element">Identifiant : </label>
					<label for="motdepasse" class="element">Mot de passe : </label>
				</div>
				<div class="champ">
					<input type="text" name="identifiant" class="element" id="identifiant" required value="${param.identifiant }">
					<input type="password" name="motdepasse" class="element" id="motdepasse" required value="${param.motdepasse }">
				</div>
			</div>
			
			<div id="connexion" class="pave">
				<button type="submit" id="btn_connexion">Connexion</button>
				<div id="choix">
					<span><input id="case" type="checkbox" name="memoriser" value="Se souvenir de moi"><label for="memoriser">Se souvenir de moi</label></span>
					<a href="">Mot de passe oublié</a>
				</div>
			</div>
		</form>
		
		<a href="<%=request.getContextPath()%>/ServletCompte?creation=true&modification=true" id="lien_creer_compte" class="pave"><button name="creer" id="creer_compte">Créer un compte</button></a>
	</section>

</body>
</html>