package com.tmnt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tmnt on 2015/12/27.
 */
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String path=request.getContextPath();
        String   uri   =  request.getRequestURI();
        String username=(String) request.getSession().getAttribute("username");

        // 判断如果没有取到用户信息,就跳转到登陆页面
        if (username != null) {

            chain.doFilter(request, response);
        } else {
            // 跳转到登陆页面
//            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//            dispatcher.forward(request, response);
            //response.sendRedirect("/index.jsp");
              request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
