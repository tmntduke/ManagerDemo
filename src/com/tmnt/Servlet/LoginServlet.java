package com.tmnt.Servlet;

import com.tmnt.DAO.ManagerDAO;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tmnt on 2015/12/26.
 */
@javax.servlet.annotation.WebServlet(name = "LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ManagerDAO dao = new ManagerDAO();
        if (dao.queryUser(username)){
            request.getRequestDispatcher("/main.jsp").forward(request,response);
            request.getSession().setAttribute("username",username);
        }
        else {
            out.print("error");
        }
//        out.print(dao.queryUser(username));
//        out.print(username + "  " + password);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
