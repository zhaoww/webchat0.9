<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="script/jquery-1.11.0.min.js"></script>
<script src="script/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/tdcss1.css">
<style type="text/css">
td img {
	max-width: 150px;
	width: expression(document.body.clientWidth > 150 ? "150px" : "auto");
	overflow: hidden;
}
body {
	margin: 25px 12px 0 12px;
	width: 100%;
}

#divContent {
	margin-bottom: 10px;
}

#content {
	margin-bottom: 10px;
}

form {
	margin-top: 10px;
}
</style>



<title>message</title>
</head>
<body>
	<div id=divContent></div>
	<div id="content">

		<form action="<%=request.getContextPath()%>/sixinServlet" class="form-inline" role="form" method="post">
			<div class="form-group">
				<input type="text" name="day" class="form-control"
					placeholder="yyyy-MM-dd" style="width: 130px" > 
			</div>
			<button type="submit" class="btn btn-primary">查询</button>
			&nbsp; &nbsp; &nbsp; &nbsp;
		</form>
		<p style="color: red">${error }</p>
		<c:if test="${!empty day }">
		<p style="color: red"><fmt:formatDate value="${day}"
								pattern="yyyy-MM-dd" />您的私信记录</p>
		</c:if>
		<table class="table table-striped table-bordered">

			<c:if test="${listDto.size()>0 }">
				<thead>
					<tr>
						<td class="mytd1">时间</td>
						<td class="mytd2">发送人</td>
						<td class=mytd2>接收人</td>
						<td class="mytd3">内容</td>
					</tr>
				</thead>
				<tbody>
			</c:if>
			<c:forEach items="${listDto}" var="sixinDto"
				varStatus="varStatus">
				<tr>
					<td class="mytd1">
							<fmt:formatDate value="${sixinDto.created_dt }"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
					<td class="mytd2">${sixinDto.fromName }</td>
					<td class="mytd2">${sixinDto.toName }</td>
					<td class="mytd3">${sixinDto.content }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>