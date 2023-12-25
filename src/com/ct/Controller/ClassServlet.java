package com.ct.Controller;

import com.ct.Service.ClassSelectService;
import com.ct.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/23 21:45
 **/
@WebServlet("/api/ClassServlet")
public class ClassServlet extends HttpServlet {
    private ClassSelectService ClassSelectService = new ClassSelectService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pageNumStr = request.getParameter("pageNum");
        String pageSizeStr = request.getParameter("pageSize");
        String _class = request.getParameter("_class");
        request.setAttribute("_class",_class);
        try {

            if (_class != null && pageNumStr != null && pageSizeStr != null) {
                int pageNum = Integer.parseInt(pageNumStr);
                int pageSize = Integer.parseInt(pageSizeStr);
                List<Result> studentList = ClassSelectService.getStudents(pageNum, pageSize,_class);
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
}
