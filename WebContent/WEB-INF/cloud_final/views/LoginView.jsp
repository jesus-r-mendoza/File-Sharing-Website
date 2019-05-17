<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<title>Login</title>
<style>

</style>
</head>
<body>
	<div class="container">
	<form action="LoginController" method="post">
		Username: <input type="text" name="loginName"><br>
		Password: <input type="password" name="loginPassword"><br>
		<input type="submit" name="login" value="login"><br>
	</form>
	</div>
	
	<div class="container">
		Don't have an account? Register <a href="RegisterController">here.</a>
	</div>
	
	<div class="container">
		<div style="color: #FF0000;">${loginErrorMessage}</div>
	</div>
	
</body>
</html>