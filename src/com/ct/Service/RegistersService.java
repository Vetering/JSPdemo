package com.ct.Service;

import com.ct.Mapper.RegistersMapper;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/6 8:41
 **/
public class RegistersService extends RegistersMapper {
    private RegistersMapper RegistersMapper = new RegistersMapper();

    public boolean RegistersAdmin(String username1, String password1) {
        return RegistersMapper.RegistersAdmin(username1, password1);
    }
    public boolean RegistersStudent(String username2, String password2) {
        return RegistersMapper.RegistersTeach(username2, password2);
    }
    public boolean RegistersTeach(String username3, String password3) {
        return RegistersMapper.RegistersStudent(username3, password3);
    }
}