package com.ct.Mapper;

import com.ct.config.DateFormat;
import com.ct.config.DateFormatEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/6 12:06
 **/
public class RegistersMapperTest {
    public static void main(String[] args) {
        RegistersMapper registersMapper = new RegistersMapper();

        // 用实际的用户名和密码替换这些值
        String testUsername = "testUser";
        String testPassword = "testPassword";
        boolean registrationResult = registersMapper.RegistersAdmin(testUsername, testPassword);

        if (registrationResult) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败！");
        }
    }
}
