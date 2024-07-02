<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.apex.beans.PersonalInfoBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success Page</title>
</head>
<body>

<h1>User Data</h1>

<% 
    Map<String, List<Map<String, Object>>> userTablesData = (Map<String, List<Map<String, Object>>>) request.getAttribute("userTablesData");
%>

<% if (userTablesData != null) { %>
    <% for (String table : userTablesData.keySet()) { %>
        <h2><%= table %></h2>
        <table border="1">
            <thead>
                <tr>
                    <% 
                        List<Map<String, Object>> tableData = userTablesData.get(table);
                        if (!tableData.isEmpty()) {
                            for (String column : tableData.get(0).keySet()) { 
                    %>
                        <th><%= column %></th>
                    <% 
                            } 
                        } 
                    %>
                </tr>
            </thead>
            <tbody>
                <% for (Map<String, Object> row : tableData) { %>
                    <tr>
                        <% for (Object value : row.values()) { %>
                            <td><%= value %></td>
                        <% } %>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <br>
    <% } %>
<% } else { %>
    <p>No data found for the current user.</p>
<% } %>

</body>
</html>