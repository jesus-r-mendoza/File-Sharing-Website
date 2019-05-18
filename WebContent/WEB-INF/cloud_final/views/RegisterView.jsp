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
<title>Register</title>
<style>

</style>
</head>
<body>
	<div class="jumbotron">
			<h1 class="display-3">Welcome to your Cloud</h1>
		</div>
		<div class="d-flex justify-content-between">
	</div>
	
	<div class="container">
		<form action="register" method="post">
			username: <input type="text" name="registerName"><br>
			password: <input type="password" name="registerPassword"><br>
			<input type="submit" name="register" value="register">
		</form>
	</div>
	
	<div class="container">
		<div style="color: #FF0000;">${registerErrorMessage}</div>
	</div>
	
</body>