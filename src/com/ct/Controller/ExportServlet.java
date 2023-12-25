// ExportServlet.java
package com.ct.Controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ct.Service.ExportService;
import com.ct.common.Result;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/ExportServlet")
public class ExportServlet extends HttpServlet {
    private ExportService exportService = new ExportService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Result> results = exportService.getExports();

        ExcelWriter writer = ExcelUtil.getWriter(true);

        List<Map<String, Object>> data = new ArrayList<>();

        // 调整数据列的顺序
        List<String> columnOrder = List.of("id", "StudentID", "name", "sex", "Age", "_faculty", "_class", "AdmissionTime");

        // 添加中文表头别名
        writer.addHeaderAlias("id", "编号");
        writer.addHeaderAlias("StudentID", "学生编号");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("Age", "年龄");
        writer.addHeaderAlias("_faculty", "学院");
        writer.addHeaderAlias("_class", "班级");
        writer.addHeaderAlias("AdmissionTime", "入学时间");

        for (Result result : results) {
            // 从 Result 对象中提取数据
            Map<String, Object> rowData = (Map<String, Object>) result.getData();

            List<Object> adjustedRowData = new ArrayList<>();
            for (String columnName : columnOrder) {
                adjustedRowData.add(rowData.get(columnName));
            }

            // 将调整后的数据添加到 data 列表中
            Map<String, Object> rowMap = new LinkedHashMap<>(); // 使用 LinkedHashMap 保持插入顺序
            for (int i = 0; i < columnOrder.size(); i++) {
                rowMap.put(columnOrder.get(i), adjustedRowData.get(i));
            }

            data.add(rowMap);
        }

        // 写入 Excel 表格
        writer.write(data, true);

        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户信息表", "UTF-8") + ".xlsx");

        // 写入 ServletOutputStream
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }
}
