package com.grades.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UploadService {


    JSONObject upload(CommonsMultipartFile file, HttpServletRequest request);

    String checkTableName(String talbeName);

    void fileRead(String fileName) throws Exception;

    void fileWrite(int userId, String tableName, List<List<String>> lists);
}
