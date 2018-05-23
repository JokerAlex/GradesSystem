package com.grades.controller;


import com.grades.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public List<Map<String,String>> getAll(@RequestBody String tableName){
        return null;
    }
}
