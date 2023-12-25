package com.ct.Controller;

import com.ct.Service.RegistersService;
import com.ct.entity.Admin;
import com.ct.entity.Student;
import com.ct.entity.Teacher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/6 8:26
 **/
@WebServlet("/api/RegistersServlet")
public class RegistersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>注册API接口GET请求成功！Hello RegistersServlet!</h2>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parameter = request.getParameter("role");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
            if (parameter.equals("1")) {
                Admin admin = new Admin();
                admin.setUsername(username);
                String username1 = admin.getUsername();
                admin.setPassword(password);
                String password1 = admin.getPassword();
                // 调用业务逻辑层进行用户验证
                RegistersService RegisterService = new RegistersService();
                boolean isValidUser = RegisterService.RegistersAdmin(username1, password1);
                if (isValidUser) {
                    // 用户验证成功，进行其他操作，例如重定向到登录页面
                    // 用户验证失败，返回登录页面并设置错误信息
                    String successMessage = "用户名注册成功！"; // 设置注册消息
                    // 输出 JavaScript 代码到页面上
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out1 = response.getWriter();
                    out1.println("<script type=\"text/javascript\">");
                    out1.println("alert('" + successMessage + "');");
                    out1.println("</script>");
                    response.sendRedirect("/login.jsp");
                } else {
                    // 用户验证失败，返回登录页面并设置错误信息
                    String errorMessage = "用户名已存在,请重新注册！"; // 设置错误消息

                    // 输出 JavaScript 代码到页面上
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out1 = response.getWriter();
                    out1.println("<script type=\"text/javascript\">");
                    out1.println("alert('" + errorMessage + "');");
                    out1.println("location='/register.jsp';"); // 重定向到登录页面
                    out1.println("</script>");
                }
            } else if (parameter.equals("2")) {
                Student Student = new Student();
                Student.setUsername(username);
                String username2 = Student.getUsername();
                Student.setPassword(password);
                String password2 = Student.getPassword();
                // 调用业务逻辑层进行用户验证
                RegistersService RegisterService = new RegistersService();
                boolean isValidUser = RegisterService.RegistersTeach(username2, password2);
                if (isValidUser) {
                    // 用户验证成功，进行其他操作，例如重定向到登录页面
                    // 用户验证失败，返回登录页面并设置错误信息
                    String successMessage = "用户名注册成功！"; // 设置注册消息

                    // 输出 JavaScript 代码到页面上
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out1 = response.getWriter();
                    out1.println("<script type=\"text/javascript\">");
                    out1.println("alert('" + successMessage + "');");
                    out1.println("location='/login.jsp';"); // 重定向到登录页面
                    out1.println("</script>");
                } else {
                    // 用户验证失败，返回登录页面并设置错误信息
                    String errorMessage = "用户名已存在,请重新注册！"; // 设置错误消息

                    // 输出 JavaScript 代码到页面上
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out1 = response.getWriter();
                    out1.println("<script type=\"text/javascript\">");
                    out1.println("alert('" + errorMessage + "');");
                    out1.println("location='/register.jsp';"); // 重定向到登录页面
                    out1.println("</script>");
                }
            } else if (parameter.equals("3")) {
                Teacher Teacher = new Teacher();
                Teacher.setUsername(username);
                String username3 = Teacher.getUsername();
                Teacher.setPassword(password);
                String password3 = Teacher.getPassword();
                // 调用业务逻辑层进行用户验证
                RegistersService RegisterService = new RegistersService();
                boolean isValidUser = RegisterService.RegistersStudent(username3, password3);
                if (isValidUser) {
                    // 用户验证成功，进行其他操作，例如重定向到登录页面
                    // 用户验证失败，返回登录页面并设置错误信息
                    String successMessage = "用户名注册成功！"; // 设置注册消息

                    // 输出 JavaScript 代码到页面上
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out1 = response.getWriter();
                    out1.println("<script type=\"text/javascript\">");
                    out1.println("alert('" + successMessage + "');");
                    out1.println("location='/login.jsp';"); // 重定向到登录页面
                    out1.println("</script>");
                } else {
                    // 用户验证失败，返回登录页面并设置错误信息
                    String errorMessage = "用户名已存在,请重新注册！"; // 设置错误消息

                    // 输出 JavaScript 代码到页面上
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out1 = response.getWriter();
                    out1.println("<script type=\"text/javascript\">");
                    out1.println("alert('" + errorMessage + "');");
                    out1.println("location='/register.jsp';"); // 重定向到登录页面
                    out1.println("</script>");
                }
            }
        }//aa
    }//aa