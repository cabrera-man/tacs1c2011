<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="javax.jdo.PersistenceManager"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="ar.edu.utn.tacs2011c1.servicioML.WishBean"%>
<%@ page import="ar.edu.utn.tacs2011c1.servicioML.PMF" %>
<%@ page import="javax.jdo.Query" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html bgcolor=#555555>
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
			User loggedUser = userService.getCurrentUser();
			if (loggedUser != null) {
		%>
				<p>
					Bienvenido
					<%String userName =  loggedUser.getNickname();%>
					<%=userName%>
					<a href="<%=userService.createLogoutURL(request.getRequestURI())%>"> Salir</a>
				</p>
				<div id="divisionIzquierda">
					<ul id="main_categories"></ul>
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
				<div id="divisionWishList">
					<ul id="wishList"></ul>
				</div>		
					<%
					    PersistenceManager pm = PMF.get().getPersistenceManager();
					  	String query = "select from " + WishBean.class.getName();
					    List<WishBean> wishes = (List<WishBean>) pm.newQuery(query).execute();
					    boolean emptyWishList = true;

					    for (WishBean w : wishes) {
			        		if(userName.equals(w.getUserName())){
			        			emptyWishList = false;
					%>
								
							<script type="text/javascript">
							addWishOnlyToUlTag("<%= w.getTitle()%>","<%= w.getImgURL()%>", "<%= w.getItemId()%>");
							</script>
					<%
			        		}
            			}
					    if (emptyWishList) {
					%>
					
					<script type="text/javascript">
							$("#divisionWishList").append("<p>Empty WishList</p>");</script>
					<%
					    }
    					pm.close();
					%>
		
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