<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="get-data">Get data</a>
<br>
<form action="/post-data">
    <label for="id">ID:</label><br>
    <input type="text" id="id" name="id" value=""><br>
    <label for="name">name:</label><br>
    <input type="text" id="name" name="name" value="Doe"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>