<%--
  Created by IntelliJ IDEA.
  User: tmnt
  Date: 2015/12/28
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
%>
<form method="post" action="/Manager/AddServlet" >
    <table>
        <tr><td>
           用户：
        </td>
            <td><input type="text" name="author"/></td>
        </tr>
        <tr>
            <td>标题：</td>
            <td><input type="text" name="title"/></td>
        </tr>
        <tr>
            <td>正文：</td>
            <td><textarea name="text" ></textarea></td>
        </tr>
        <tr>
            <td>上传图片：</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td ><input type="submit" name="submitSend"/></td>

        </tr>
    </table>
</form>
</body>
</html>
