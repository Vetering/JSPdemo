package com.ct.Service;

import com.ct.Mapper.ImportMapper;
import com.ct.entity.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImportService {
    public void importData(InputStream fileContent) {
        try (Workbook workbook = new XSSFWorkbook(fileContent)) {
            Sheet sheet = workbook.getSheetAt(0); // 假设数据在第一个表单

            Iterator<Row> rowIterator = sheet.iterator();
            List<Student> students = new ArrayList<>();

            // 跳过表头行
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // 假设列的顺序是：学生ID、姓名、性别、年龄、学院、班级、入学时间
                String studentID = getCellValueAsString(row.getCell(0));
                String name = getCellValueAsString(row.getCell(1));
                String originalSex = getCellValueAsString(row.getCell(2));
                String convertSex = convertSex(originalSex);  // 转换性别的值
                String age = getCellValueAsString(row.getCell(3));
                String faculty = getCellValueAsString(row.getCell(4));
                String clazz = getCellValueAsString(row.getCell(5));
                String admissionTime = getCellValueAsString(row.getCell(6));

                // 创建一个学生对象并将其添加到列表中
                Student student = new Student();
                student.setStudentID(studentID);
                student.setName(name);
                student.setSex(convertSex);
                student.setAge(Integer.parseInt(age));
                student.set_faculty(faculty);
                student.set_class(clazz);
                student.setAdmissionTime(admissionTime);
                students.add(student);
            }

            // 调用 Mapper 中的方法将数据插入到数据库中
            ImportMapper importMapper = new ImportMapper();
            importMapper.insertStudents(students);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将性别值转换为"W"（男）或"M"（女）
    private String convertSex(String originalSex) {
        if ("男".equals(originalSex)) {
            return "W";
        } else if ("女".equals(originalSex)) {
            return "M";
        } else {
            return originalSex;
        }
    }

    // 获取单元格的字符串值
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return null;
        }
    }
}
