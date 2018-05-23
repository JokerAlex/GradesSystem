package com.grades.service;

import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Map<String,String>> getAll(String tableName);

    List<Map<String,String>> getOne(String tableName, String id, String name);
}
