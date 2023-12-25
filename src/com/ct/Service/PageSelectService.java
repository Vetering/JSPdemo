package com.ct.Service;
import com.ct.Mapper.PageSelectMapper;
import com.ct.common.Result;
import java.util.List;
/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/9 16:24
 **/


public class PageSelectService {
    private PageSelectMapper pageSelectMapper = new PageSelectMapper();

    public List<Result> getStudents(int pageNum, int pageSize ) {
        return pageSelectMapper.getStudents(pageNum,pageSize);
    }
    public List<Result> getStudentSelect(String username) {
        return pageSelectMapper.getStudentSelect(username);
    }
    public List<Result> getTeacherSelect(String username) {
        return pageSelectMapper.getTeacherSelect(username);
    }
}
