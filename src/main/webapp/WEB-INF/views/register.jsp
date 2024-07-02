<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<h1>
		<%
		out.println(request.getAttribute("message"));
		%>
	</h1>
	<form action="addUser" method="post">
		FirstName : <input type="text" name="firstName"> <br> 
		LastName : <input type="text" name="lastName"> <br> 
		UserName : <input type="text" name="username"> <br> 
		Password : <input type="password" name="password"> <br> 
		<input type="submit">
	</form>
</body>
</html>