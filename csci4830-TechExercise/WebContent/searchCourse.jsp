<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Course Page</title>
<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Student Academic Tracker</h1>
		</div>
		<div id="content">
			<div id="nav">
				<h3>Navigation</h3>
				<ul>
					<li><a href="/csci4830-TechExercise/main.html">Home Page</a> <br></li>
					<li><a href="/csci4830-TechExercise/LogInStudent?nuid=<%=request.getParameter("nuid") %>">Back</a> <br>
						<br></li>
				</ul>
			</div>
			<div id="main">
				<h2>Search Course:</h2>
				<p>Search course by final grade:</p>
				<form action="SearchCourse" method="POST">
	
					Final Grade: <input type="text" name="grade"> <em>ex: B+</em><br />
					<input type="hidden" name="nuid" value="<%=request.getParameter("nuid") %>"/>
		
					<input type="submit" value="Search Course" />
				</form>
				
			</div>
		</div>
		<div id="footer">Copyright</div>
	</div>
</body>
</html>