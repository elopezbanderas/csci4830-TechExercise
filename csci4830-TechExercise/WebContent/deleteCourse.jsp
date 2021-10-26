<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Delete Course Page</title>
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
				<h2>Delete Course:</h2>
				<form action="DeleteCourse" method="POST">
	
					Course Name: <input type="text" name="course_id"> <em>ex: CSCI4830</em><br /> 
					Course Section: <input type="text" name="section"> <em>ex: 820</em><br />
					Semester: <input type="text" name="semester"> <em>ex: FALL2021, SPR2021, or SUM2021</em><br />
					<input type="hidden" name="nuid" value="<%=request.getParameter("nuid") %>"/>
		
					<input type="submit" value="Delete Course" />
				</form>
				
			</div>
		</div>
		<div id="footer">Copyright</div>
	</div>
</body>
</html>