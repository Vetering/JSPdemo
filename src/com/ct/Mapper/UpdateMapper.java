package com.ct.Mapper;

import com.ct.common.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/14 0:49
 **/
public class UpdateMapper {
    public static List<Result> getUpdates(String studentID, String name, String sex, String faculty, String _class, String Age, String admissionTime) {
        int Age1 = Integer.parseInt(Age);
        List<Result> resultList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String updateQuery = "UPDATE student " + "SET name = ?, sex = ?, Age = ?, _faculty = ?, _class = ?, AdmissionTime = ? " + "WHERE StudentID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, sex);
                preparedStatement.setInt(3, Age1);
                preparedStatement.setString(4, faculty);
                preparedStatement.setString(5, _class);
                preparedStatement.setString(6, admissionTime);
                preparedStatement.setInt(7, Integer.parseInt(studentID));

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    resultList.add(Result.success("Update successful"));
                } else {
                    resultList.add(Result.error(500, "Update failed. Student with ID " + studentID + " not found."));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultList.add(Result.error(500, "Database error: " + e.getMessage()));
        }

        return resultList;
    }
}
