package com.tmnt.Servlet;

import com.tmnt.DAO.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tmnt on 2015/12/27.
 */
@WebServlet(name = "CheckServlet")
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String username =request.getParameter("username");
        ManagerDAO dao=new ManagerDAO();
        if (dao.queryUser(username)){
            out.print("error");
        }
        else {
            out.print("ok");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
