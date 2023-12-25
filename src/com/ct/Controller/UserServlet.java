package com.ct.Controller;

import com.ct.Mapper.PageSelectMapper;
import com.ct.Service.UserService;
import com.ct.common.Result;
import com.ct.entity.Admin;
import com.ct.entity.Student;
import com.ct.entity.Teacher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/5 21:34
 **/
@WebServlet("/api/LoginServlet")
public class UserServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>API接口请求GET成功！Hello Servlet!</h2>");
        out.println("</body></html>");

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session1 = request.getSession();
        session1.setAttribute("username",username);
        session1.setAttribute("password",password);
        session1.setAttribute("parameter",parameter);
        System.out.println(parameter);
        session1.setMaxInactiveInterval(1800);
        if (parameter.equals("1")) {
            Admin admin = new Admin();
            admin.setUsername(username);
            String username1 = admin.getUsername();
            admin.setPassword(password);
            String password1 = admin.getPassword();
            // 调用业务逻辑层进行用户验证
            UserService userService = new UserService();
            boolean isValidUser = userService.validateAdmin(username1, password1);
            if (isValidUser) {
                // 用户验证成功，进行其他操作，例如重定向到欢迎页面
                response.sendRedirect("/admin.jsp");
            } else {
                // 用户验证失败，返回登录页面并设置错误信息
                String errorMessage = "用户名或密码错误"; // 设置错误消息

                // 输出 JavaScript 代码到页面上
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out1 = response.getWriter();
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('" + errorMessage + "');");
                out1.println("location='/';"); // 重定向到登录页面
                out1.println("</script>");
            }
        }else if (parameter.equals("2")) {
            Teacher Teacher = new Teacher();
            Teacher.setUsername(username);
            String username2 = Teacher.getUsername();
            Teacher.setPassword(password);
            String password2 = Teacher.getPassword();
            // 调用业务逻辑层进行用户验证
            UserService userService = new UserService();
            boolean isValidUser = userService.validateTeach(username2, password2);
            if (isValidUser) {
                // 用户验证成功，进行其他操作，例如重定向到欢迎页面
                response.sendRedirect("/Teacher.jsp");
            } else {
                // 用户验证失败，返回登录页面并设置错误信息
                String errorMessage = "用户名或密码错误"; // 设置错误消息
                // 输出 JavaScript 代码到页面上
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out1 = response.getWriter();
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('" + errorMessage + "');");
                out1.println("location='/';"); // 重定向到登录页面
                out1.println("</script>");
            }
        }else if (parameter.equals("3")) {
            Student Student = new Student();
            Student.setUsername(username);
            String username3 = Student.getUsername();
            Student.setPassword(password);
            String password3 = Student.getPassword();
            // 调用业务逻辑层进行用户验证
            UserService userService = new UserService();
            boolean isValidUser = userService.validateStudent(username3, password3);
            if (isValidUser) {
                // 用户验证成功，进行其他操作，例如重定向到欢迎页面
                response.sendRedirect("/Student.jsp");
            } else {
                // 用户验证失败，返回登录页面并设置错误信息
                String errorMessage = "用户名或密码错误"; // 设置错误消息

                // 输出 JavaScript 代码到页面上
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out1 = response.getWriter();
                out1.println("<script type=\"text/javascript\">");
                out1.println("alert('" + errorMessage + "');");
                out1.println("location='/';"); // 重定向到登录页面
                out1.println("</script>");
            }
        }
    }

}