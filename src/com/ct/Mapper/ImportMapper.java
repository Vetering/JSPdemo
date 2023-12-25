package com.ct.Mapper;
import com.ct.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/17 22:10
 **/
public class ImportMapper {
    public void insertStudents(List<Student> students) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO student (StudentID, name, sex, Age, _faculty, _class, AdmissionTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (Student student : students) {
                    preparedStatement.setString(1, student.getStudentID());
                    preparedStatement.setString(2, student.getName());
                    preparedStatement.setString(3, student.getSex());
                    preparedStatement.setInt(4, student.getAge());
                    preparedStatement.setString(5, student.get_faculty());
                    preparedStatement.setString(6, student.get_class());
                    preparedStatement.setString(7, student.getAdmissionTime());
                    preparedStatement.addBatch();
                }

                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
