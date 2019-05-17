<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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