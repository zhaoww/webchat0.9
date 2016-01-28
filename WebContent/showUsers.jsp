<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>用户管理</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="script/jquery-1.11.0.min.js"></script>
<script src="script/bootstrap.min.js"></script>

<style type="text/css">
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

</head>
<body>
	<div id=divContent></div>
	<div id="content">
		<form
			action="<%=request.getContextPath()%>/managerServlet?method=showAllUsers"
			method="post" class="form-inline" role="form">
			<!--                  <input type="text" name="query" value=${query }> 
     <input type="submit" value="Query">
       -->
			<div class="form-group">
				<label class="sr-only" for="query">query</label> <input type="text"
					class="form-control" name="query" id="query" placeholder="姓名/工号...">
			</div>

			<button type="submit" class="btn btn-primary">查询</button>
		</form>

		<c:choose>
			<c:when test="${users != null }">
				<br>
				<form action="#" method="post">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<td>编号</td>
								<td>姓名</td>
								<td>工号</td>
								<td>密码</td>
								<td>状态</td>
								<td>禁言</td>
								<td>更新</td>
							</tr>
						</thead>
						<c:forEach items="${users }" var="user">
							<tr>
								<td>${user.id }</td>
								<td>${user.name }</td>
								<td>${user.code }</td>
								<td>${user.pwd }</td>
								<td>${user.state }</td>
								<td>${user.flag }</td>
								<td><a href="update.jsp?user=${user }" style="color:#327681">update</a></td>
							</tr>
						</c:forEach>
					</table>
				</form>
				<c:if test="${flag=='T'}">
					<a style="color:#327681"
						href="<%=request.getContextPath()%>/managerServlet?method=showLimitUsers&currentRecord=${page.currentRecord}&type=prev">前一页</a>
					/<a style="color:#327681"
						href="<%=request.getContextPath()%>/managerServlet?method=showLimitUsers&currentRecord=${page.currentRecord}&type=next"> 后一页</a>
				</c:if>
			</c:when>
			<c:otherwise>
				<h2>Sorry,no data had queried!</h2>
			</c:otherwise>
		</c:choose>

	</div>
</body>
</html>