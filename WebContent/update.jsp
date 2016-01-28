<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ctrl.vo.User" %>
<%@ page import="com.ctrl.utils.UserStringUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户更新</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="script/jquery-1.11.0.min.js"></script>
<script src="script/bootstrap.min.js"></script>
<style>
form{
	width: 20%;
	margin: 40px auto ;
}

</style>
</head>
<body>

    <%
       User user = new User();
       String url = request.getParameter("user");
       user = UserStringUtils.getUser(url);
       session.setAttribute("up_user", user);
    %>
    
	<form role="form" action="<%= request.getContextPath() %>/managerServlet?method=updateUser" method="post">
	<div class="form-group">
		<label for="exampleInputEmail1">Name</label>
		<input type="text" class="form-control" name="name" disabled="disabled" value="${up_user.name }">
	</div>
	
	<div class="form-group">
		<label for="exampleInputEmail1">Code</label>
		<input type="text" class="form-control" name="code" disabled="disabled" value="${up_user.code }">
	</div>
	
	
	<div class="form-group">
		<label for="exampleInputEmail1">State</label>
		<select type="text" class="form-control" name="state" value="${up_user.state }">
		<option>T</option>
  		<option>F</option>
  		</select>
		
	</div>
	
	<div class="form-group">
		<label for="exampleInputEmail1">Flag</label>
		<select type="text" class="form-control" name="flag" value="${up_user.flag }">
		<option>T</option>
  		<option>F</option>
  		</select>
	</div>
	
	<button type="submit" class="btn btn-info btn-block">Submit</button>

	  
		  
	</form>

</body>
</html>