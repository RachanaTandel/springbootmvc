<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank Information Page</title>
</head>
<body>

<h1><% 

String error = (String) session.getAttribute("error");
if(error != null){
	out.print(error);
	session.setAttribute("error",null);
} %></h1>

<form action="bankinfo" method="post">
		Bank Name : <input type="text" name="bankName"><br>
		Account Number : <input type="number" name="accountNumber" ><br>
		SSN : <input type="number" name="ssn" ><br>
		<input type="submit"  value="Submit">
	</form>
</body>
</html>