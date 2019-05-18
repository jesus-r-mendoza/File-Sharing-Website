<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Hello</title>
</head>
<body>
	<div class="jumbotron">
			<h1 class="display-3">Welcome to your Cloud</h1>
		</div>
		
		<div class="d-flex justify-content-between">
			<a href="home">Return</a>
			<a href="LoginController">Sign Out</a>
	</div>


	<form action = "renamefiles" method = "post">
			<div class="form-group">
				<label for="">Enter the new name for your file :</label>
				<input type="text" name="fileRename"/><span>${extension}</span>
			</div>
			<input type = "hidden" name="userId" value= "${param.userId}"/>
			<input type = "hidden" name="fileId" value= "${param.fileId}"/>
			<input type="submit" class="btn btn-primary" name="submit" value="SUBMIT" />
			
	</form>
</body>
</html>