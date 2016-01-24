package com.tmnt.Servlet;

import com.tmnt.DAO.ManagerDAO;
import com.tmnt.Entity.NewInfo;
import com.tmnt.Uilts.PageBar;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by tmnt on 2015/12/29.
 */
@WebServlet(name = "ShowServlet")
public class ShowServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        ManagerDAO dao=new ManagerDAO();
        //List<NewInfo>list=dao.queryNew();
        //request.setAttribute("showNew",list);
        String action=request.getParameter("action");

        //System.out.println("id  "+id+" action  "+action);
        if (action.equals("update")){
            updateNew(response,request);
        }
        else if (action.equals("delete")){
            deleteNew(request,response);
        }
        else if (action.equals("jumpToShow")){
            listPage(request,response);
        }
        else  if (action.equals("pageBar")){
            listPage(request,response);
        }

    }

    private void listPage(HttpServletRequest request, HttpServletResponse response) {
        //System.out.print("hello");
        ManagerDAO dao=new ManagerDAO();
        int i=dao.queryCount();
        String proname = request.getParameter("proname");
        int recordCount = dao.queryCount();// 获得记录的总条数
        int currentPage = 1;// 当前页是第一页
        String pageNum = request.getParameter("pageNum");
        if (pageNum != null) {
            currentPage = Integer.parseInt(pageNum);
        }
        PageBar pUtil = new PageBar(5, recordCount, currentPage);
        int start = pUtil.getFromIndex();
        int end = pUtil.getToIndex();
        // 已经进行分页之后的数据集合
        List<NewInfo> list=dao.pageBar(start,end);
        request.setAttribute("pUtil", pUtil);
        //HttpSession session=request.getSession();
        request.setAttribute("listproduct", list);
        request.setAttribute("count",i);
        request.setAttribute("proname", proname);
        try {
            request.getRequestDispatcher("/showNew.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("list:"+ list);
    }

    private void deleteNew(HttpServletRequest request, HttpServletResponse response) {
        ManagerDAO dao=new ManagerDAO();
        int c= Integer.parseInt(request.getParameter("count"));
        List<NewInfo>l=dao.queryNew();
        int id=dao.queryNewId(l.get(c).getAuthor(),l.get(c).getTitle());
        if (dao.deleteNew(id)){

            try {
                request.getRequestDispatcher("/Manager/Servlet?action=jumpToShow&pageNum=1").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else {
            try {
                response.getWriter().print("error");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateNew(HttpServletResponse response,HttpServletRequest request) {
        int id= Integer.parseInt(request.getParameter("id"));
            ManagerDAO dao = new ManagerDAO();
            String path = request.getRealPath("/upload");
//            File file = new File(path);
//            if (!file.getParentFile().exists()) {
//                file.mkdir();
//            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            //factory.setRepository(file) ;
            ServletFileUpload fileUpload1 = new ServletFileUpload(factory);
            fileUpload1.setHeaderEncoding("utf-8");
            fileUpload1.setFileSizeMax(3 * 1024 * 1024);
            String filename = null;
            List<String> list1 = new ArrayList<>();
            try {
                List<FileItem>items=fileUpload1.parseRequest(request);
                for (FileItem item:items){
                    if (item.isFormField()){
                        list1.add(item.getString().toString());
                        System.out.println(item.getString().toString());
                    }
                    else if (!item.isFormField()){
                        filename=item.getName();
                        item.write(new File(path+"/"+File.separator+filename));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //id= dao.queryNewId(list1.get(0),list1.get(1));
            System.out.println(" id: "+id);
            boolean check= dao.update(list1.get(0),list1.get(1),list1.get(2),filename,id);
            if (check){
                try {
                    request.getRequestDispatcher("/Manager/Servlet?action=jumpToShow&pageNum=1").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else {
                try {
                    response.getWriter().print("error");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
