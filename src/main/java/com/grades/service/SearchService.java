package com.grades.service;

import com.grades.model.QueryRecord;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SearchService {

    List<LinkedHashMap<String,String>> getAll(String tableName);

    Map<String,String> getOne(String tableName, String id, String name);

    QueryRecord getTablesByQueryName(String queryName);
}
