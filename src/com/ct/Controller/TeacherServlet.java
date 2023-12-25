package com.ct.Controller;

import com.ct.Service.PageSelectService;
import com.ct.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/24 17:59
 **/
@WebServlet("/api/TeacherServlet")
public class TeacherServlet extends HttpServlet {
    private PageSelectService pageSelectService = new PageSelectService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        System.out.println("TeacherServlet:"+username);
        try {
            if (username != null) {
                List<Result> teacherSelect = pageSelectService.getTeacherSelect(username);
                Result result;
                if (teacherSelect != null && !teacherSelect.isEmpty()) {
                    result = Result.success(teacherSelect);
                } else {
                    result = Result.error(Result.CODE_ERROR, "未找到学生信息");
                }

                ObjectMapper mapper = new ObjectMapper();
                String jsonResult = mapper.writeValueAsString(result);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResult);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid pageNum or pageSize");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}