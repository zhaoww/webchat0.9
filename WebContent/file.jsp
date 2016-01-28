<%@page import="java.io.File"%>
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
<style type="text/css">
#big {
	width: 100%;
}

.little {
	float: left;
	background: -webkit-linear-gradient(#eee, #eee); /*谷歌浏览器*/
	background: -o-linear-gradient(#eee, #eee); /*Opera 11.1 - 12.0 */
	background: -moz-linear-gradient(#eee, #eee); /*Firefox 3.6 - 15 */
	background: linear-gradient(#eee, #eee); /*标准的语法*/
	border: 1px solid #eee;
	-webkit-border-radius: 8px;
	-moz-border-radius: 8px;
	border-radius: 8px;
	width: 24.2%;
	height: 250px;
	background-color: #000;
	vertical-align: center;
	margin: 1px;
}

img {
	max-width: 250px;
	width: expression(document.body.clientWidth > 250 ? "250px" : "auto");
	overflow: hidden;
}
</style>
<script type="text/javascript" src="script/jquery-1.11.0.min.js"></script>

</head>
<body>
	
	<div align="center">
	<form action="fileServlet?method=query&show=file&page=0" method="post">
		<input type="text" name="name" placeholder="模糊查询"> <input
			type="submit">
	</form><br><br>
		<a href="fileServlet?page=0&show=img">图像 </a>&nbsp;&nbsp;&nbsp; <a
			href="fileServlet?page=0&show=file">文件 </a><br><br>
	</div>
	<c:if test="${list.size()<=0 }">
		<div style="width: 100%; height: 400px" align="center">
			<br>此页没有内容
		</div>
	</c:if>
	<div align="center" id=big>
		<c:forEach items="${list }" var="file">
			<c:if test="${show=='img' }">
				<div align="center" class="little">${file }</div>
			</c:if>
			<c:if test="${show=='file' }">
				<div align="center">${file }</div>
				<br>
			</c:if>
		</c:forEach>
	</div>

	<div style="width: 100%;position: fixed;bottom: 0" align="center">
		<a href="fileServlet?show=${show }&page=${page-10}">前页</a>&nbsp;<a
			href="fileServlet?show=${show }&page=${page+10}">后页</a>&nbsp;
		<fmt:formatNumber type="number" value="${page/10+1}"
			maxFractionDigits="0" />
		页&nbsp;${show }类型
	</div>
	<br>
	<br>
</body>
</html>