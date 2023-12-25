package com.ct.Controller;

import com.ct.Service.InsertService;
import com.ct.Service.UpdateService;
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
 * 日期: 2023/12/11 23:46
 *
 **/
@WebServlet("/api/updateServlet")
public class UpdateServlet extends HttpServlet {
    private UpdateService UpdateService = new UpdateService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String name = request.getParameter("name");
        String StudentID = request.getParameter("StudentID");
        String sex = request.getParameter("sex");
        String _class = request.getParameter("_class");
        String Age = request.getParameter("Age");
        String _faculty = request.getParameter("_faculty");
        String AdmissionTime = request.getParameter("AdmissionTime");
        if (StudentID != null || sex != null || _class != null  || Age != null || _faculty != null || AdmissionTime != null) {
            List<Result> studentList = UpdateService.getUpdates(StudentID,name,sex,_faculty,_class,Age,AdmissionTime);
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
}
