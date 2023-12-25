package com.ct.Service;

import com.ct.Mapper.InsertMapper;
import com.ct.common.Result;

import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/12 18:18
 **/
public class InsertService {
    private InsertMapper InsertMapper = new InsertMapper();

    public List<Result> getInserts(String StudentID,String name,String sex,String _faculty,String _class,String Age,String AdmissionTime ) {
        return InsertMapper.getInserts(StudentID,name,sex,_faculty,_class,Age,AdmissionTime);
    }
}
