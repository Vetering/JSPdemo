package com.ct.Controller;


import com.ct.Service.ImportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/17 22:06
 **/
@WebServlet("/api/import")
@MultipartConfig
public class ImportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");

        if (filePart != null) {
            InputStream fileContent = filePart.getInputStream();

            // Call a service method to handle the import logic
            ImportService importService = new ImportService();
            importService.importData(fileContent);

            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"success\", \"message\": \"Import successful\"}");
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"status\": \"error\", \"message\": \"No file uploaded\"}");
        }
    }
}

