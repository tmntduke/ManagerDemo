<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
%>
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
    <TITLE>后台管理系统</TITLE>
    <META http-equiv=content-type content="text/html; charset=gb2312">
</HEAD>
<FRAMESET
        rows=105,* cols="*" bordercolor="04376E">
    <FRAME name=ads marginWidth=0 marginHeight=0
           src="<%=path %>/top.jsp" frameBorder=0 noResize scrolling=no
           longDesc="">
    <FRAMESET rows=675 cols=198,* frameborder="yes">
        <FRAME name=list marginWidth=0
               marginHeight=0
               src="<%=path %>/left.jsp"
               frameBorder=0 noResize scrolling=yes longDesc="">
        <FRAME name="main" marginWidth=0
               marginHeight=0 src="<%=path %>/postdata.jsp" frameBorder=0 scrolling=yes
               longDesc="">
    </FRAMESET>
    <NOFRAMES>
        <body></body>
    </NOFRAMES>
</FRAMESET>
</HTML>

