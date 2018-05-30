package com.grades.controller;


import com.grades.model.QueryRecord;
import com.grades.model.Student;
import com.grades.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/GradesSystem")
public class SearchController {
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @ResponseBody
    @RequestMapping(value = "/getAll")
    public List<LinkedHashMap<String,String>> getAll(@RequestBody String tableName){
        return searchService.getAll(tableName);
    }

    @ResponseBody
    @RequestMapping(value = "getOne")
    public Map<String,String> getOne(@RequestBody Map<String,String> map){
        System.out.println(map.get("tableName")+map.get("id")+map.get("name"));
        return searchService.getOne(map.get("tableName"),map.get("id"),map.get("name"));
    }

    @ResponseBody
    @RequestMapping(value = "/getTablesByQueryName")
    public QueryRecord getTables(@RequestBody String queryName){
        return searchService.getTablesByQueryName(queryName);
    }

    @ResponseBody
    @RequestMapping(value = "/checkStu")
    public Student checkStu(@RequestBody Student student){
        System.out.println(student.getId()+student.getName());
        return searchService.checkStu(student);
    }
}
