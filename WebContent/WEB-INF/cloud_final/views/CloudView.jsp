<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<title>Home Page</title>
</head>
<body>
	<div class="container"> 
	
		<div class="jumbotron">
			<h1 class="display-3">Welcome to your Cloud</h1>
		</div>
		
		<div class="d-flex justify-content-between">
			<h3>These are your stored files:</h3>
			<a href="LoginController">Sign Out</a>
		</div>
		
		<div>
			<h4>Upload a file: </h4>
			<form action="Upload" method="post" enctype="multipart/form-data">
				File: <input type="file" name="file" /> <br>
				<input type="submit" name="upload" value="Upload" />
			</form>
		</div>
		
		
		<c:forEach items="${files}" var="f">
			<h5>${f.file.name}</h5>
			<h5>${f.path}</h5>
			<h5>${f.id}</h5>
			<hr>
		</c:forEach>
		
	</div>

</body>

</html>