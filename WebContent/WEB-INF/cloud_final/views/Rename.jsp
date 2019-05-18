<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hello</title>
</head>
<body>



	<form action = "RenameFilesController" method = "post">
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