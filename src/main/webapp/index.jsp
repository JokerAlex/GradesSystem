<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2018/3/28
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>上传附件</h1>
<%
    session.setAttribute("userId","1");
%>
<form method="post" action="/GradesSystem/upload.do" enctype="multipart/form-data">
    <input type="file" name="uploadFile"/>
    <button type="submit" >提交</button>
</form>
</body>
</html>
