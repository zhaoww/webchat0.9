<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
	window.history.forward(1);
</script>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style>
form{
	width: 20%;
	margin: 40px auto ;
}

</style>
</head>
<body>
<form  role="form"; action="<%= request.getContextPath() %>/managerServlet?method=UpdatePwd" method="post">
	<div class="form-group">
    <label>Old Password</label>
    <input type="password" class="form-control input-lg" name="oldPwd"  placeholder="Enter Old Password">
  </div>
  
  <div class="form-group">
    <label>New Password</label>
    <input type="password" class="form-control input-lg" name="newPwd1" placeholder="Enter New Password">
  </div>
  
  <div class="form-group">
    <label>Repeat Password</label>
    <input type="password" class="form-control input-lg" name="newPwd2" placeholder="Repeat New Password">
  </div>
	
    <div>${sessionScope.show_1 }</div>

    <div>${sessionScope.show_2 }</div>
	
	<button type="submit" class="btn btn-info btn-lg btn-block">Submit</button>
	
	
</form>

</body>
</html>