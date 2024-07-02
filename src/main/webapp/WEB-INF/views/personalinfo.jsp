<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Personal Information Page</title>
</head>
<body>

	
<h1><% 

String error = (String) session.getAttribute("error");
if(error != null){
	out.print(error);
	session.setAttribute("error",null);
} %></h1>

	<form action="personalinfo" method="post">
		First Name : <input type="text" name="firstName" ><br>
		Last Name : <input type="text" name="lastName" ><br>
		Middle Name : <input type="text" name="middleName" ><br>
		Gender: <br> <input type="radio" id="male" name ="gender" value="Male">
		<label for="male">Male</label><br>
		<input type="radio" id="female" name ="gender" value="Female">
		<label for="female">Female</label><br>
		<input type="submit" value="Submit">
	</form>
	
	
	
</body>
</html>