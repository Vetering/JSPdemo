package com.ct.Mapper;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/mis");
            config.setUsername("root");
            config.setPassword("123456");
            config.setMaximumPoolSize(10);  // 设置连接池的最大连接数
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataSource = new HikariDataSource(config);
        } catch (Throwable t) {
            t.printStackTrace();
            try {
                throw t;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
