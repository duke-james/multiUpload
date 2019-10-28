<%
/*
 * 文件预览
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty files}">
	<font color="red">您没有选择文件，或文件上传失败！</font>
</c:if>
<c:if test="${not empty files}">
  <font color="red">文件上传成功</font>
  <table border="1">
    <thead>
      <tr>
        <th>文件上传路径</th>
        <th>文件预览，仅限于图片(通过图片字节流显示图片)</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${files}" var="file">
        <tr>
          <td width="50%">${file.path}</td>
          <td width="50%" align="center">
          	<a href="<c:url value="DownLoadServlet"><c:param name="filePath" value="${file.path}"/></c:url>">
          		<img src="<c:url value="ShowImgServlet"><c:param name="imgPath" value="${file.path}"/></c:url>" width="100" height="100" alt="点击下载文件"/>
          	</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</c:if>
</body>
</html>