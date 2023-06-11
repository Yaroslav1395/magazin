<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Spring Boot Session Example</title>
</head>
<body>
	<div>
		<form action="/persistMessage" method="post">
			<textarea name="msg" cols="40" rows="2"></textarea>
			<br> <input type="submit" value="Save Message" />
		</form>
	</div>
	<div>
		<h2>Messages</h2>
		<ul>
		<#list sessionMessages as sessionMessage>
		    <li>${sessionMessage}</li>
		</#list>
	</div>
	<div>
		<form action="/destroy" method="post">
			<input type="submit" value="Destroy Session" />
		</form>
	</div>
</body>
</html>