<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/sign-up">
Name :<input type="text" name="name" id="name" placeholder="enter the name"><br>
Address :<input type="text" name="address" id="address" placeholder="enter the address"><br>
Mobile N0:<input type="text" name="mobileNo" id="mobileno" placeholder="enter the Mobile Number"><br>
Gender :<input type="radio" name="gender" id="gender" value="male">Male <input type="radio" name="gender" value="female">Female<br>
Username :<input type="text" name="username" id="username" placeholder="enter the username"><br>
Password :<input type="password" name="paswsword" id="password" placeholder="enter the password"><br>
<input type="submit" value="submit">
</form>

</body>
</html>