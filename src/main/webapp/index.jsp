<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/index.css" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./js/ml.js"></script>
<script src="./js/init.js"></script>
<title>Labored Crime - TACS 1C 2011</title>
</head>
<body>
	<div id="signInDiv">
		<%
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();
			if (user != null) {
		%>
		<p>
			Bienvenido
			<%=user.getNickname()%>
			<a href="<%=userService.createLogoutURL(request.getRequestURI())%>">
				Salir</a>
		</p>
		<div id="divisionIzquierda">
			<ul id="main_categories">
			</ul>
		</div>
		<div id="divisionDerecha">
			<form id="formBuscar">
				<input id="txtBuscar" class="input" /> <input class="btn"
					id="btBuscar" type="submit" value="Buscar">
			</form>
			<div id="resultadoBusqueda">
				<ul id="listaBusqueda">
				</ul>
			</div>
		</div>

		<%
			} else {
		%>
			<a href="<%=userService.createLoginURL(request.getRequestURI())%>">¡Hacé click aquí para entrar!</a>
		<%
			}
		%>
	</div>

</body>
</html>