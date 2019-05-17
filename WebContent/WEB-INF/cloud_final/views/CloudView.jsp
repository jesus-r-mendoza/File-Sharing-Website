<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		Success!
		<a href="LoginController">Sign out</a>	
		
		<div>
		
			<h4>Upload a file: </h4>
			<form action="Upload" method="post" enctype="multipart/form-data">
				File: <input type="file" name="file" /> <br>
				<input type="submit" name="upload" value="Upload" />
			</form>
		
		
		</div>
		
	</div>

</body>
</html>