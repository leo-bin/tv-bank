package com.ccsu.jc.tvbank.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class one
 */
public class One extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("11");

        //request.getRequestDispatcher("http://127.0.0.1:888/bilibili/static/adminjs/index.html").forward(request,response);
        //System.out.println("测试");
        request.getRequestDispatcher("logoone.sf").forward(request, response);
    }


}
