<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<meta charset="UTF-8">
<title>login</title>
<script type="text/javascript" charset="UTF-8"
	src="script/prefixfree.min.js"></script>
<style type="text/css">
body {
	background: #fff url("img/img.jpg") no-repeat;
	background-size: width:100%;
	min-width: 990px;
	margin: 0;
	padding: 0;
}

.content {
	width: 600px;
	height: 350px;
	margin: 160px auto 10px auto;
}

.contentHead {
	margin-bottom: 160px;
}

.login-form {
	width: 400px;
	height: 177px;
	margin: 70px auto 0;
	padding-top: 73px;
	position: relative;
	background-image: -*-linear-gradient(top, rgb(255, 255, 255),
		rgb(242, 242, 242));
	box-shadow: 0 3px 3px rgba(21, 62, 78, 0.8);
}

.login-form:before {
	content: "";
	position: absolute;
	top: -80px;
	left: 50px;
	width: 325px;
	height: 260px;
	padding: 2px;
	background: url("img/logo.png") no-repeat 2px 2px;
}

.not-registered {
	position: absolute;
	color: rgb(153, 153, 153);
	font-weight: bold;
	font-size: 13px;
	top: calc(100% + 20px);
	background-color: rgb(255, 255, 255);
	width: 400px;
	height: 46px;
	margin: 0 auto;
	line-height: 46px;
	text-align: center;
	box-shadow: 0 3px 3px rgba(21, 62, 78, 0.8);
}

.not-registered a {
	margin-left: 5px;
	text-decoration: none;
	color: rgb(52, 119, 182);
	cursor: pointer;
}

.login-form div {
	width: 216px;
	height: 28px;
	margin: 20px auto;
	position: relative;
	line-height: 28px;
	border: none;
}

.login-form .user-icon, .login-form .password-icon {
	display: inline-block;
	font-family: 'loginform-icon';
	font-size: 15px;
	text-align: center;
	line-height: 28px;
	color: rgb(153, 153, 153);
	position: absolute;
	left: 1px;
	top: 1px;
	background-color: rgb(255, 255, 255);
	border: none;
	border-right: 1px solid rgb(229, 229, 232);
	width: 30px;
	height: 28px;
	transition: all 300ms linear;
}

.login-form .username input, .login-form .password input {
	height: 100%;
	width: calc(100% - 40px);
	padding-left: 40px;
	border-radius: 2px;
	border: 1px solid;
	border-color: rgb(229, 229, 232) rgb(220, 220, 221) rgb(213, 213, 213)
		rgb(220, 220, 221);
	display: block;
	transition: all 300ms linear;
}

.login-form .icon:before, .login-form .icon:after {
	content: "";
	position: absolute;
	top: 10px;
	left: 30px;
	width: 0;
	height: 0;
	border: 4px solid transparent;
	border-left-color: rgb(255, 255, 255);
}

.login-form .icon:before {
	top: 9px;
	border: 5px solid transparent;
	border-left-color: rgb(229, 229, 232);
}

.login-form .username input:focus, .login-form .password input:focus {
	border-color: rgb(69, 153, 228);
	box-shadow: 0 0 2px 1px rgb(200, 223, 244);
}

.login-form .username input:focus+span, .login-form .password input:focus+span
	{
	background: -*-linear-gradient(top, rgb(255, 255, 255),
		rgb(245, 245, 245));
	color: rgb(51, 51, 51);
}

.login-form .username input:focus+span:after, .login-form .password input:focus+span:after
	{
	border-left-color: rgb(250, 250, 250);
}

.login-form .account-control label {
	margin-left: 24px;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	cursor: pointer;
}

.login-form button[type="submit"] {
	color: #fff;
	font-weight: bold;
	float: right;
	width: 68px;
	height: 30px;
	position: relative;
	background: -*-linear-gradient(top, rgb(74, 162, 241), rgb(52, 119, 182))
		1px 0 no-repeat,-*-linear-gradient(top, rgb(52, 118, 181),
		rgb(36, 90, 141)) left top no-repeat;
	background-size: 66px 28px, 68px 29px;
	border: none;
	border-top: 1px solid rgb(52, 118, 181);
	border-radius: 2px;
	box-shadow: inset 0 1px 0 rgb(86, 174, 251);
	text-shadow: 0 1px 1px rgb(51, 113, 173);
	transition: all 200ms linear;
}

.login-form button[type="submit"]:hover {
	text-shadow: 0 0 2px rgb(255, 255, 255);
	box-shadow: inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px
		rgba(74, 162, 241, 0.5);
}

.login-form button[type="submit"]:active {
	background: -*-linear-gradient(top, rgb(52, 119, 182), rgb(74, 162, 241))
		1px 0 no-repeat,-*-linear-gradient(top, rgb(52, 118, 181),
		rgb(36, 90, 141)) left top no-repeat;
}

.login-form .account-control input {
	width: 0px;
	height: 0px;
}

<!--
checkbox -->.checkboxRem label:after {
	opacity: 0.2;
	content: '';
	position: absolute;
	width: 9px;
	height: 5px;
	background: transparent;
	top: 6px;
	left: 7px;
	border: 3px solid #333;
	border-top: none;
	border-right: none;
}

.checkboxRem label:hover::after {
	opacity: 0.5;
}

.checkboxRem input[type=checkbox]:checked+label:after {
	opacity: 1;
}

.login-form label.check {
	position: absolute;
	left: 0;
	top: 50%;
	margin: -8px 0;
	display: inline-block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	border-radius: 2px;
	background: -*-linear-gradient(top, rgb(255, 255, 255),
		rgb(246, 246, 246)) 1px 1px no-repeat,-*-linear-gradient(top, rgb(227, 227, 230),
		rgb(165, 165, 165)) left top no-repeat;
	background-size: 14px 14px, 16px 16px;
}

.login-form .account-control input:checked+label.check:before {
	content: attr(data-on);
	font-family: loginform-icon;
}

@font-face {
	font-family: 'loginform-icon';
	src: url("font/loginform-icon.eot");
	src: url("font/loginform-icon.eot?#iefix") format('embedded-opentype'),
		url("font/loginform-icon.woff") format('woff'),
		url("font/loginform-icon.ttf") format('truetype'),
		url("font/loginform-icon.svg#loginform-icon") format('svg');
	font-weight: normal;
	font-style: normal;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/script/jquery-1.11.0.min.js"></script>
<script>
	window.history.forward(1);
</script>

<script type="text/javascript">
	$(function() {
		//当文本框内容发生变化的时候的事件
		$("#name").change(function() {
			val = $(this).val();
			//去除前后空格，以判断当前用户名是否为空
			val = $.trim(val);
			if (val != "") {
				//指定url和args
				var url = "${pageContext.request.contextPath}/checkServlet";
				var args = {
					"name" : val,
					"method" : "checkUserName",
					"time" : new Date()
				};
				//以post方式发送，data作为回传参数
				$.post(url, args, function(data) {
					//将回传参数显示到页面上
					$("form > p").html(data);
				});
			} else {
				var url = "${pageContext.request.contextPath}/checkServlet";
				var args = {
					"method" : "checkUserName",
					"time" : new Date()
				};
				$.post(url, args, function(data) {
					$("form > p").html(data);
				});
			}
		})
	});
</script>

<script type="text/javascript">
	function checksubmit() {
		val = $("#name").val();
		var pwd = $("#password").val();
		var remember;
		if ($("#remember").is(':checked')) {
			remember = $("#remember").val();
		} else {
			remember = null;
		}
		pwd = $.trim(pwd);
		var url = "${pageContext.request.contextPath}/checkServlet";
		var args = {
			"remember" : remember,
			"password" : pwd,
			"method" : "checkLogin",
			"name" : val
		};
		$.post(url, args, function(data) {
			if (data == "chat.jsp") {
				location.href = data;
			} else {
				$("form>p").html(data);
				return false;
			}
		})
		return false;
	}
</script>
</head>

<%
	String name = "";
	String password = "";
	Cookie[] cookies = request.getCookies();
	if (null != cookies) {
		for (int i = 0; i < cookies.length; i++) {
			if ("name".equals(cookies[i].getName())) {
				name = cookies[i].getValue();
			} else if ("password".equals(cookies[i].getName())) {
				password = cookies[i].getValue();
			}
		}
	}
%>

<body>
	<div class="contentHead"></div>
	<div class="content">
		<form method="post" class="login-form" id="formid"
			onsubmit="return checksubmit();">
			<div class="username" id="username">
				<input type="text" id="name" name="name" placeholder="staff ID"
					autocomplete="on" value="<%=name%>" /> <span
					class="user-icon icon">u</span>
			</div>
			<div class="password">
				<input type="password" name="password" id="password"
					placeholder="*******" value="<%=password%>" /> <span
					class="password-icon icon">p</span>
			</div>
			<div class="account-control">
				<input type="checkbox" name="remember me" id="remember"
					value="remember" checked="checked" /> <label for="remember"
					data-on="c" class="check"></label> <label for="remember"
					class="info">Remember me</label>
				<button type="submit">Login</button>
			</div>

			<p class="not-registered">webchat</p>
		</form>
	</div>
	<div style="text-align: center; clear: both"></div>
</body>
</html>