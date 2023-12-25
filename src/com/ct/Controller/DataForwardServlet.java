package com.ct.Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/25 12:25
 **/
@WebServlet("/api/DataForwardServlet")
public class DataForwardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String _class = request.getParameter("_class");
        HttpSession session = request.getSession();
        session.setAttribute("_class", _class);
        session.setMaxInactiveInterval(1800);
        System.out.println("DataForwardServlet:" + _class);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(_class);
    }
}