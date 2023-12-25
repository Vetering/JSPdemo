package com.ct.Mapper;

import com.ct.common.Result;
import com.ct.entity.MultipleSelection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/13 8:47
 **/
public class DeleteMapper {
    public static List<Result> getDeleteId(String id) {
        System.out.println(id);
        List<Result> resultList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM student WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, Integer.parseInt(id));

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
    public List<Result> deleteMultiple(MultipleSelection[] selections) {
        List<Result> resultList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM student WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (MultipleSelection selection : selections) {
                    preparedStatement.setInt(1, Integer.parseInt(selection.getId()));
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        resultList.add(Result.success("Delete successful for id: " + selection.getId()));
                    } else {
                        resultList.add(Result.error(500, "Delete failed for id: " + selection.getId()));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resultList.add(Result.error(500, "Database error: " + e.getMessage()));
        }

        return resultList;
    }
}
