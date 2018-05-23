package com.grades.serviceImpl;

import com.grades.mapping.Search;
import com.grades.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    private Search search;

    @Autowired
    public SearchServiceImpl(Search search){
        this.search = search;
    }

    public List<Map<String, String>> getAll(String tableName) {
        if (tableName != null && !tableName.trim().equals("")){
            List<String> colNameList = search.getColNames(tableName);
            List<Map<String,String>> mapList = search.getAll(colNameList,tableName);
            return mapList;
        }
        return null;
    }

    public List<Map<String, String>> getOne(String tableName, String id, String name) {
        boolean isTableNull = tableName != null && !tableName.trim().equals("");
        boolean isIdNull = id != null && !id.trim().equals("");
        boolean isNameNull = name != null && !name.trim().equals("");
        if (isTableNull && isIdNull && isNameNull){
            List<String> colNameList = search.getColNames(tableName);
            List<Map<String,String>> mapList = search.getOne(colNameList,tableName,colNameList.get(0),id,colNameList.get(1),name);
            return mapList;
        }
        return null;
    }
}
