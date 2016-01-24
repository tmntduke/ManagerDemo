<%@ page import="java.util.List" %>
<%@ page import="com.tmnt.Entity.NewInfo" %>
<%@ page import="com.tmnt.DAO.ManagerDAO" %>
<%@ page import="com.tmnt.Uilts.PageBar" %><%--
  Created by IntelliJ IDEA.
  User: tmnt
  Date: 2015/12/29
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    ManagerDAO dao=new ManagerDAO();
    //String pageA=request.getParameter("pageNum");
    //int count= Integer.parseInt(request.getParameter("count"));
    //System.out.println(count);
    List<NewInfo> list=null;
    PageBar pUtil=null;
    String proname = (String)request.getAttribute("proname");
        //int count=(int)request.getAttribute("count");
        proname = (proname==null?"":proname);
        list= (List<NewInfo>) request.getAttribute("listproduct");
        pUtil= (PageBar) request.getAttribute("pUtil");
        //System.out.println( "jsp:"+list);
%>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript">
        function updateNew(count){
            window.location.href="/updateNew.jsp?id="+count ;

        }
        function deleteNew(c){

            window.location.href="/Manager/Servlet?action=delete&count="+c;
        }
        function first(){

            window.location.href="/Manager/Servlet?action=pageBar&pageNum=1";

        }
        function forward(){

            window.location.href="<%=path%>/Manager/Servlet?action=pageBar&pageNum=<%=pUtil.getCurrentPage()-1%>";

        }
        function next(){

            window.location.href="<%=path%>/Manager/Servlet?action=pageBar&pageNum=<%=pUtil.getCurrentPage()+1%>";

        }
        function end(){

            window.location.href="<%=path%>/Manager/Servlet?action=pageBar&pageNum=<%=pUtil.getPageCount()%>";
        }
        function changePage(currentPage){
            window.location.href="<%=path%>/Manager/Servlet?action=pageBar&pageNum="+currentPage;
        }

        function selectAll(flag){
            var ids = document.getElementsByName("ids");
            for(var i=0;i<ids.length;i++){
                ids[i].checked=flag;
            }
        }

        function getSelectCount(){
            var count = 0;
            var ids = document.getElementsByName("ids");
            for(var i=0;i<ids.length;i++){
                if(ids[i].checked){
                    count++;
                }
            }
            return count;
        }
    </script>
</head>
<body>
   <table border="1" width="100%" height="100%">
       <th>author</th>
       <th>title</th>
       <th>text</th>
       <th>image</th>
       <%



           //List<NewInfo>list=dao.queryNew();
           session.setAttribute("updateNewlist",list);
           int id=0;int i=0;
           //int count=0;
           //System.out.println("newsize"+list.size());
           for (;i<=list.size()-1;i++){
       %>
       <tr>
           <td>
               <% out.print(list.get(i).getAuthor()); %>
           </td>
           <td>
               <% out.print(list.get(i).getTitle()); %>
           </td>
           <td>
               <% out.print(list.get(i).getText()); %>
           </td>
           <td>
              <img src="/upload/<%= list.get(i).getImage() %>" width="30px" height="30px"/>
           </td>
           <td>
               <a href="javascript:updateNew(<%=i%>)">修改</a>
           </td>
           <td>
               <a href="javascript:deleteNew(<%=i%>)">删除</a>
           </td>

       </tr>
       <% } %>


   </table>
   <table width="99%" border=0 cellpadding=4 cellspacing=1
          bgcolor=#0867b3>
       <!--DWLayoutTable-->
       <tbody>
       <tr bgcolor=#ffffff height=20>
           <td height="20" align="center" valign="middle" nowrap>
               <span class="STYLE7">共<%=pUtil.getPageCount()%>页</span>&nbsp;
               <a href="javascript:first();" class="dh2">首页</a>&nbsp;
               <a href="javascript:forward();" class="dh2">上一页</a>&nbsp;
               <a href="javascript:next();" class="dh2">下一页</a>&nbsp;
               <a href="javascript:end();" class="dh2">尾页</a>&nbsp;
               <span class="STYLE7">跳转到</span>
               <select name="select" class="text2" style="WIDTH: 40px" onchange="changePage(this.value)">
                   <%
                       int len = pUtil.getPageCount();
                       for(int j=1;j<=len;j++){
                   %>
                   <option value="<%=j %>" <%=(j==pUtil.getCurrentPage()?"selected":"") %>>
                       <%=j %>
                   </option>
                   <%
                       }
                   %>
               </select>
           </td>
       </tr>
       </tbody>
   </table>
</body>
</html>
