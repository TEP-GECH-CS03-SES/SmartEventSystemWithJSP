<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
 session = request.getSession(); 
System.out.println(session.getAttribute("adminUser"));
if(session.getAttribute("adminUser").equals("admin")){
	
}else{
	response.sendRedirect("/logout"); 
}
%>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>

</body>
</html>