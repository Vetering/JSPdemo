package com.ct.Service;

import com.ct.Mapper.InsertMapper;
import com.ct.Mapper.UpdateMapper;
import com.ct.common.Result;

import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/14 0:50
 **/
public class UpdateService {
    private UpdateMapper UpdateMapper = new UpdateMapper();

    public List<Result> getUpdates(String StudentID, String name, String sex, String _faculty, String _class, String Age, String AdmissionTime ) {
        return UpdateMapper.getUpdates(StudentID,name,sex,_faculty,_class,Age,AdmissionTime);
    }
}
