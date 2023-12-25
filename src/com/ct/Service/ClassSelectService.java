package com.ct.Service;

import com.ct.Mapper.ClassSelectMapper;
import com.ct.Mapper.PageSelectMapper;
import com.ct.common.Result;

import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/23 21:30
 **/
public class ClassSelectService {
    private ClassSelectMapper ClassSelectMapper = new ClassSelectMapper();

    public List<Result> getStudents(int pageNum, int pageSize, String _class) {
        return ClassSelectMapper.getStudents(pageNum,pageSize,_class);
    }
}
