package com.ct.Mapper;

import com.ct.common.Result;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/14 11:41
 **/
public class ExportMapper {
    public static List<Result> getExports() {
        List<Result> results = new ArrayList<>();
        try (var connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM student ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String studentID = resultSet.getString("StudentID");
                        String name = resultSet.getString("name");
                        String sex = resultSet.getString("sex");
                        String _faculty = resultSet.getString("_faculty");
                        String _class = resultSet.getString("_class");
                        String Age = resultSet.getString("Age");
                        String AdmissionTime = resultSet.getString("AdmissionTime");
                        Result result = new Result();
                        result.setData(Map.of("StudentID", studentID, "name", name, "sex", sex, "_class", _class,"AdmissionTime",AdmissionTime,"Age",Age,"_faculty",_faculty));
                        results.add(result);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Result errorResult = new Result();
            errorResult.setCode(Result.CODE_ERROR);
            errorResult.setMessage("系统错误！");
            results.add(errorResult);
        }
        return results;
    }
    public static List<Result> getClassExports(String _class) {
        System.out.println("ExportMapper:"+_class);
        List<Result> results = new ArrayList<>();
        try (var connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM student where _class = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1,_class);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String studentID = resultSet.getString("StudentID");
                        String name = resultSet.getString("name");
                        String sex = resultSet.getString("sex");
                        String _faculty = resultSet.getString("_faculty");
                        String _class1 = resultSet.getString("_class");
                        String Age = resultSet.getString("Age");
                        String AdmissionTime = resultSet.getString("AdmissionTime");
                        Result result = new Result();
                        result.setData(Map.of("StudentID", studentID, "name", name, "sex", sex, "_class", _class1,"AdmissionTime",AdmissionTime,"Age",Age,"_faculty",_faculty));
                        results.add(result);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Result errorResult = new Result();
            errorResult.setCode(Result.CODE_ERROR);
            errorResult.setMessage("系统错误！");
            results.add(errorResult);
        }
        return results;
    }
}
