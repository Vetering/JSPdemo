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
 * 日期: 2023/12/23 21:31
 **/
public class ClassSelectMapper {
    public static List<Result> getStudents(int pageNum, int pageSize, String _class) {
        List<Result> results = new ArrayList<>();
        System.out.println("mapper:"+_class);
        try (var connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM  student where _class= ? LIMIT ?, ? ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int offset = Math.max((pageNum - 1) * pageSize, 0);
                preparedStatement.setString(1, _class);
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, pageSize);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String studentID = resultSet.getString("StudentID");
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        String sex = resultSet.getString("sex");
                        String _faculty = resultSet.getString("_faculty");
                        String _Class = resultSet.getString("_class");
                        String Age = resultSet.getString("Age");
                        String AdmissionTime = resultSet.getString("AdmissionTime");
                        Result result = new Result();
                        result.setData(Map.of("id",id,"StudentID", studentID, "name", name, "sex", sex, "_class", _Class,"AdmissionTime",AdmissionTime,"Age",Age,"_faculty",_faculty));
                        results.add(result);
                    }
                }
            }
            String countSql = "SELECT COUNT(*) FROM student WHERE _class = ?";
            try (PreparedStatement countStatement = connection.prepareStatement(countSql)) {
                countStatement.setString(1, _class);  // 修改这里，将索引改为 1
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
}
