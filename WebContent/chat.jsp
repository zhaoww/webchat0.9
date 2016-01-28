<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE htmll>
<html>
<head>
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>chat</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="css/bootstrap-theme.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/site.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/script/jquery-1.11.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/bootstrap.min.js"></script>
<script type="text/javascript">
	var socketUrl = "ws://" + window.location.host
			+ "${pageContext.request.contextPath}/chatSocket";
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/script/chat.js">
</script>
<script type="text/javascript">
	function clickHref(href) {
		// 选择好表情后，隐藏表情框
		$(".content").hide();
		var message = $("#message").val();
		message = $.trim(message);
		var mark = href.split("=")[1];
		mark = mark.split("&")[0];
		var method = href.split("=")[2];
		var url = "${pageContext.request.contextPath}/expressServlet";
		var args = {
			"message" : message,
			"mark" : mark,
			"method" : method
		};
		$.post(url, args, function(data) {
			$("#message").val(data);
			$("#message").focus();
		})
	}
	$(function() {
		$(".pic").hide();
		$(".content").hide();
		$(".head").click(function() {
			$(".pic").hide();
			$(".content").slideToggle();
		});
		$(".head1").click(function() {
			$(".content").hide();
			$(".pic").slideToggle();
		});

		$("#remove").click(function() {
			$("#history").hide();
		});
		$("#message").keyup(function() {
			var message = $("#message").val();
			if (message.length >= 140) {
				$("#message").val(message.substr(0, 140));
			}
		});

	})
</script>
<script type="text/javascript" src="script/upload.js"></script>
</head>

<body style="font-family: 微软雅黑; 
	background: #fff url('img/img3.jpg') no-repeat; 
	background-size: width:100%;">

	<div class="htmleaf-container">
		<div style="position: absolute; right: 50px; top: 20px;">
			<!-- 功能选项div-->
			<c:if test="${user.code==123 }">
				<a class="a2"
					href="<%=request.getContextPath()%>/managerServlet?method=showLimitUsers"
					target="_blank">用户管理</a>
			</c:if>
			<a class="a2" href="sixinServlet" target="_blank">私信记录</a> <a
				class="a2"
				href="
				<%=request.getContextPath()%>/messageServlet?method=showAllMessages"
				target="_blank">群聊记录</a> <a class="a2" href="avatar.html">更换头像</a>
				<a class="a2" href="fileServlet" target="_blank">文件共享</a>
				 <a
				class="a2" href="updatepwd.jsp">修改密码</a> <a class="a2" id="logOut"
				href="<%=request.getContextPath()%>/managerServlet?method=LogOut">注销登录</a>

		</div>
		<br> <br> <br>
		<div style="text-align: center; clear: both"></div>
		<div class="container mp30">
			<div class="row">
				<div class="twowindows">
					<div class="col-md-2"
						style="background-color: #e8f4f4; border-radius: 10px;">
						<div
							style="padding: 20px; text-align: center; height: 50px; background-color:; font-family: Helvetica; font-size: 20px; color: #000; font-weight: 700;">
							在线列表</div>
						<br>
						<div id="onlineLists"style="overflow-y: auto; height: 592px">
						</div>
					</div>

					<div class="col-md-5" style="">
						<div class="panel panel-default">
							<div id="showHistory" class="panel-heading"
								style="cursor: pointer; font-family: Helvetica; font-size: 18px; text-align: center;">
								<span class="glyphicon glyphicon-list-alt"></span><b id="title1">查询群聊记录</b>
							</div>
							<div class="panel-body">
								<div class="row">

									<div class="col-xs-12" style="overflow-y: auto; height: 436px">
										<ul class="demo1">

										</ul>
									</div>
								</div>
							</div>

							<div class="tools" style="border-top: 1px solid #DDD;">

								<img alt="" src="img/face.gif" style="cursor: pointer;"
									class="head"> <img alt="" src="img/pic.png"
									style="cursor: pointer;" class="head1">
								<div class="pic">
									<form id="form1" enctype="multipart/form-data" method="post"
										action="upload">
										<input type="file" name="fileToUpload" id="fileToUpload" /> 
										<input
											type="button" id="upload" value="上传文件"
											
											onclick="clickHref(this.href);return false;" /> <span
											id="fileSize"></span>&nbsp; <span id="fileType"></span>&nbsp;
										<span id="progressNumber"></span>&nbsp;
									</form>
								</div>
								<div class="content">
									<%
										for (int i = 1; i <= 75; i++) {
									%>
									<a
										href="<%=request.getContextPath()%>/expressSe2rvlet?i=<%=i%>&method=doShow"
										style="border: none;"
										onclick="clickHref(this.href);return false;"> <img alt=""
										src=<%="img/face/" + i + ".gif"%> style="border: none;">
									</a>
									<%
										}
									%>
								</div>

							</div>
							<textarea id="message" class="textarea"
								placeholder="Enter to send"
								style="width: 100%; height: 80px; font-size: 14px; line-height: 18px; border: none;"></textarea>
							<div align="center" id="sendButton" class="panel-footer"
								style="cursor: pointer; font-family: Helvetica; font-size: 18px;">
								<b>发送</b>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-5" id="history">
					<div class="panel panel-default" style="width: 100%;">
						<div class="panel-heading"
							style="font-family: Helvetica; font-size: 18px; text-align: center;">
							<b>群聊记录 </b> <label style="cursor: pointer;"> <span
								id="remove" class="glyphicon glyphicon-remove"
								style="position: absolute; right: 25px; top: 10px;"></span>
							</label>
						</div>
						<div style="overflow-y: auto; height: 610px">
							<ul class="demo1"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" id="userCode"
		value="<%=session.getAttribute("userCode")%>">
	<script type="text/javascript">
		function sixin(src) {
			//console.log($(src).children(":last").text());
			$("#message").val("(私信" +"-"+ $(src).children(":last").text() + ")\r\n");
			$("#message").focus();
		}
	</script>
</body>
</html>