package com.ct.Service;

import com.ct.Mapper.ExportMapper;
import com.ct.common.Result;

import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/14 11:41
 **/
public class ExportService {
    private ExportMapper ExportMapper = new ExportMapper();

    public List<Result> getExports() {
        return ExportMapper.getExports();
    }
    public List<Result> getClassExports(String _class) {
        return ExportMapper.getClassExports(_class);
    }
}
