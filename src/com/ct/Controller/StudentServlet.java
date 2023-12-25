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
 * 日期: 2023/12/9 20:04
 **/
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private PageSelectService pageSelectService = new PageSelectService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pageNumStr = request.getParameter("pageNum");
        String pageSizeStr = request.getParameter("pageSize");
        String username = request.getParameter("name");
        try {

            if (username != null) {
                int pageNum = Integer.parseInt(pageNumStr);
                int pageSize = Integer.parseInt(pageSizeStr);
                System.out.println(username);
                System.out.println(pageNum);
                System.out.println(pageSize);
                List<Result> studentSelect = pageSelectService.getStudentSelect(username);
                Result result;
                if (studentSelect != null && !studentSelect.isEmpty()) {
                    result = Result.success(studentSelect);
                } else {
                    result = Result.error(Result.CODE_ERROR, "未找到学生信息");
                }

                ObjectMapper mapper = new ObjectMapper();
                String jsonResult = mapper.writeValueAsString(result);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonResult);
            } else {
                int pageNum = Integer.parseInt(pageNumStr);
                int pageSize = Integer.parseInt(pageSizeStr);
                List<Result> studentList = pageSelectService.getStudents(pageNum, pageSize);
                Result result;
                if (studentList != null && !studentList.isEmpty()) {
                    result = Result.success(studentList);
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
            // Handle the case where pageNum or pageSize is not a valid integer
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid pageNum or pageSize");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Your existing doPost logic here (if applicable)
    }
}
