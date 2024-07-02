<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Contact Information Page</title>
</head>
<body>

<h1><% 

String error = (String) session.getAttribute("error");
if(error != null){
	out.print(error);
	session.setAttribute("error",null);
} %></h1>

<div>
<form action="contactinfo" method="post">
		Address : <input type="text" name="address" ><br>
		City : <input type="text" name="city" ><br>
		State : <input type="text" name="state" ><br>
		Country : <input type="text" name="country" ><br>
		Phone : <input type="text" name="phone" ><br>
		<input type="submit" value="Submit">
	</form>
</div>
</body>
</html>