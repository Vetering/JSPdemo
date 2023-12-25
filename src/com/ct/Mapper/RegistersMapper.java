package com.ct.Mapper;

import com.ct.config.DateFormat;
import com.ct.config.DateFormatEnum;

import java.sql.*;
import java.util.Date;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/6 8:37
 **/
public class RegistersMapper {
    public boolean RegistersAdmin(String username, String password) {
        String dateFormat = DateFormat.getDateFormat(new Date(), DateFormatEnum.DATE_FORMAT_DATETIME.getPattern());
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO admin (username, password,datetime) VALUES (?, ?, ?)";
            System.out.println("打印的SQL: " + sql); // 打印生成的 SQL 语句
            System.out.println("打印的dateFormat: " + dateFormat); // 打印生成的 dateFormat 语句
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, dateFormat);
                // 查询表结构
                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet resultSet = metaData.getColumns(null, null, "admin", null);
                System.out.println("表结构:");
                while (resultSet.next()) {
                    System.out.println("属性名: " + resultSet.getString("COLUMN_NAME"));
                    System.out.println("数据类型: " + resultSet.getString("TYPE_NAME"));
                }

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }




    public boolean RegistersStudent(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO admin(username, password) values(?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // 如果有匹配的用户，返回true
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean RegistersTeach(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO admin values(?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // 如果有匹配的用户，返回true
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
