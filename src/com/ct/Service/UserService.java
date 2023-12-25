package com.ct.Service;

import com.ct.Mapper.UserMapper;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/4 22:21
 **/
public class UserService extends UserMapper {
    private UserMapper UserMapper = new UserMapper();
    public boolean validateAdmin(String username1, String password1) {
        return UserMapper.validateAdmin(username1, password1);
    }
    public boolean validateTeach(String username2, String password2) {
        return UserMapper.validateTeach(username2, password2);
    }
    public boolean validateStudent(String username3, String password3) {
        return UserMapper.validateStudent(username3, password3);
    }



}
