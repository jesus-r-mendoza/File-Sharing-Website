<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello</title>
</head>
<body>

	<form action = "RenameFilesController">
			<div class="form-group">
				<label for="">Enter the new name for your file :</label>
				<textarea name="fileRename"></textarea>
			</div>
			<input type="submit" class="btn btn-primary" name="submit" value="SUBMIT" />
			
			
	</form>
</body>
</html>