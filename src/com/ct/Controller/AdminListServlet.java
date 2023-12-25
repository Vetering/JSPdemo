package com.ct.Controller;

import com.ct.Service.ExportService;
import com.ct.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminListServlet")
public class AdminListServlet extends HttpServlet {
    private ExportService ExportService = new ExportService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Result> studentList = ExportService.getExports();
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
}