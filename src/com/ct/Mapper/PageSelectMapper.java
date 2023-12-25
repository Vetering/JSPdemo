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
 * 日期: 2023/12/8 13:13
 **/
public class PageSelectMapper {
    public static List<Result> getStudents(int pageNum, int pageSize ) {
        List<Result> results = new ArrayList<>();
        try (var connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM student LIMIT ?, ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int offset = Math.max((pageNum - 1) * pageSize, 0);
                preparedStatement.setInt(1, offset);
                preparedStatement.setInt(2, pageSize);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String studentID = resultSet.getString("StudentID");
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        String sex = resultSet.getString("sex");
                        String _faculty = resultSet.getString("_faculty");
                        String _class = resultSet.getString("_class");
                        String Age = resultSet.getString("Age");
                        String AdmissionTime = resultSet.getString("AdmissionTime");
                        Result result = new Result();
                        result.setData(Map.of("id",id,"StudentID", studentID, "name", name, "sex", sex, "_class", _class,"AdmissionTime",AdmissionTime,"Age",Age,"_faculty",_faculty));
                        results.add(result);
                    }
                }
            }
            String countSql = "SELECT COUNT(*) FROM student";
            try (PreparedStatement countStatement = connection.prepareStatement(countSql);
                 ResultSet countResultSet = countStatement.executeQuery()) {
                if (countResultSet.next()) {
                    int totalCount = countResultSet.getInt(1);
                    Result totalResult = new Result();
                    totalResult.setData(Map.of("total", totalCount));
                    results.add(totalResult);
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
    public static List<Result> getStudentSelect(String name) {
        List<Result> results = new ArrayList<>();
        try (var connection = DatabaseConnection.getConnection()) {
            // Step 1: Execute the first SQL query to fetch paginated data
            String sql = "SELECT * FROM student where name = ?  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String studentID = resultSet.getString("StudentID");
                        String id = resultSet.getString("id");
                        String name1 = resultSet.getString("name");
                        String sex = resultSet.getString("sex");
                        String _class = resultSet.getString("_class");
                        String _faculty = resultSet.getString("_faculty");
                        String Age = resultSet.getString("Age");
                        String AdmissionTime = resultSet.getString("AdmissionTime");
                        Result result = new Result();
                        result.setData(Map.of("StudentID", studentID, "name", name1, "sex", sex, "_class", _class,"id", id,"AdmissionTime",AdmissionTime,"Age",Age,"_faculty",_faculty));
                        results.add(result);
                    }
                }
            }
            String countSql = "SELECT COUNT(*) FROM student where name = ?";
            try (PreparedStatement countStatement = connection.prepareStatement(countSql)) {
                countStatement.setString(1, name); // Set the parameter for the name condition
                try (ResultSet countResultSet = countStatement.executeQuery()) {
                    if (countResultSet.next()) {
                        int totalCount = countResultSet.getInt(1);
                        Result totalResult = new Result();
                        totalResult.setData(Map.of("total", totalCount));
                        results.add(totalResult);
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
    public static List<Result> getTeacherSelect(String username) {
        System.out.println("getTeacherSelect:"+username);
        List<Result> results = new ArrayList<>();
        try (var connection = DatabaseConnection.getConnection()) {
            // Step 1: Execute the first SQL query to fetch paginated data
            String sql = "SELECT * FROM teacher where username = ?  ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String _class = resultSet.getString("_class");
                        Result result = new Result();
                        result.setData(Map.of("_class", _class));
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
