package com.tmnt.Servlet;

import com.tmnt.DAO.ManagerDAO;
import com.tmnt.Entity.NewInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialStruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tmnt on 2015/12/28.
 */
@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //response.setHeader("Content-Type","multipart/form-data");
        PrintWriter out = response.getWriter();
//        NewInfo newInfo = new NewInfo();
//        String author = request.getParameter("author");
//        String title = request.getParameter("title");
//        String text = request.getParameter("text");
//        out.print("author  "+author+"title  "+title);
        String path =request.getRealPath("/upload");
        File file=new File(path);
        if (!file.getParentFile().exists()){
            file.mkdir();
        }

        DiskFileItemFactory factory=new DiskFileItemFactory();
        //factory.setRepository(file) ;
        ServletFileUpload fileUpload=new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("utf-8");
        fileUpload.setFileSizeMax(3*1024*1024);
        String filename=null;
        List<String>list=new ArrayList<>();
        try {
            List<FileItem> items=fileUpload.parseRequest(request);
            for (FileItem item:items)
            {
                if (item.isFormField())
                {
                    list.add(item.getString().toString());
                    //out.println(item.getString().toString());
                }
                else if (!item.isFormField()){
                    filename= item.getName();
                    item.write(new File(path+File.separator+item.getName()));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ManagerDAO dao=new ManagerDAO();
        NewInfo newInfo=new NewInfo();
        newInfo.setAuthor(list.get(0));
        newInfo.setTitle(list.get(1));
        newInfo.setText(list.get(2));
        newInfo.setImage(filename);
        if (dao.insertNew(newInfo)){
            request.setAttribute("newInfo",list);
            request.setAttribute("imagePath",filename);
            request.getRequestDispatcher("/Manager/Servlet?action=jumpToShow&pageNum=1").forward(request,response);
        }else {
            out.print("error");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
