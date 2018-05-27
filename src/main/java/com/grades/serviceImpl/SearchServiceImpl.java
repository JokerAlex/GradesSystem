package com.grades.serviceImpl;

import com.grades.mapping.QueryIdMapper;
import com.grades.mapping.Search;
import com.grades.model.QueryRecord;
import com.grades.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    private Search search;
    private QueryIdMapper queryIdMapper;

    @Autowired
    public SearchServiceImpl(Search search,QueryIdMapper queryIdMapper){
        this.search = search;
        this.queryIdMapper = queryIdMapper;
    }

    public List<LinkedHashMap<String, String>> getAll(String tableName) {
        if (tableName != null && !tableName.trim().equals("")){
            List<String> colNameList = search.getColNames(tableName);
            List<LinkedHashMap<String,String>> mapList = search.getAll(colNameList,tableName);
            return mapList;
        }
        return null;
    }

    public Map<String, String> getOne(String tableName, String id, String name) {
        boolean isTableNull = tableName != null && !tableName.trim().equals("");
        boolean isIdNull = id != null && !id.trim().equals("");
        boolean isNameNull = name != null && !name.trim().equals("");
        if (isTableNull && isIdNull && isNameNull){
            List<String> colNameList = search.getColNames(tableName);
            Map<String,String> map = search.getOne(colNameList,tableName,colNameList.get(0),id,colNameList.get(1),name);
            return map;
        }
        return null;
    }

    public QueryRecord getTablesByQueryName(String queryName){
        if (queryName == null || queryName.trim().equals("")){
            return null;
        }
        return queryIdMapper.getRecordIdAndTable(queryName);
    }
}
