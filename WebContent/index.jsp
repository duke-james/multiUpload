<%
/*
 * 多文件批量上传
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>批量上传</title>
	</head>
	<body>
		<%-- 上传文件时，form 表单无法正常传递参数，但是将参数拼到 url 之后可以  --%>
		<form name="uploadForm" method="post" action="UoloadServlet?empName=111" enctype="multipart/form-data">
			<table border="1">
				<thead>
					<tr>
						<th>名称</th>
						<th>文件</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach begin="1" end="10" var="i">
						<tr>
							<td>文件${i}</td>
							<td><input type="file" name="file"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<input type="submit" name="submit" value="开始上传">
		</form>
	</body>
</html>