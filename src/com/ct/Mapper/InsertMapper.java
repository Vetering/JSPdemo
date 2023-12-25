package com.ct.Mapper;

import com.ct.common.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/12 18:19
 **/
public class InsertMapper {
    public static List<Result> getInserts(String studentID, String name, String sex, String faculty, String _class, String Age, String admissionTime) {
        List<Result> resultList = new ArrayList<>();
        int Age1 = Integer.parseInt(Age);
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO student (StudentID, name, sex, Age, _faculty, _class, AdmissionTime) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentID);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, sex);
                preparedStatement.setInt(4, Age1);
                preparedStatement.setString(5, faculty);
                preparedStatement.setString(6, _class);
                preparedStatement.setString(7, admissionTime);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    resultList.add(Result.success("Insert successful"));
                } else {
                    resultList.add(Result.error(500, "Insert failed"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultList.add(Result.error(500, "Database error: " + e.getMessage()));
        }

        return resultList;
    }
}
