package com.ct.Service;
import com.ct.Mapper.DeleteMapper;
import com.ct.common.Result;
import com.ct.entity.MultipleSelection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 功能
 * 作者:Veter
 * 日期: 2023/12/13 8:47
 **/
public class DeleteService {
    private DeleteMapper DeleteMapper = new DeleteMapper();

    public List<Result> getDeleteId(String id ) {
        return DeleteMapper.getDeleteId(id);
    }
    public List<Result> deleteMultiple(String jsonData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        MultipleSelection[] selections = mapper.readValue(jsonData, MultipleSelection[].class);
        return DeleteMapper.deleteMultiple(selections);
    }
}
