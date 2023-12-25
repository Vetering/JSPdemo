package com.ct.Controller;

import com.ct.Service.DeleteService;
import com.ct.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/13 8:43
 **/
@WebServlet("/api/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private DeleteService DeleteService = new DeleteService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String id = request.getParameter("id");
        if (id!= null) {
            List<Result> studentList = DeleteService.getDeleteId(id);
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
    private DeleteService deleteService = new DeleteService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String jsonData = stringBuilder.toString();
        System.out.println("从前端接收到的数据：" + jsonData);

        List<Result> resultList = deleteService.deleteMultiple(jsonData);
        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(resultList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResult);
    }
}