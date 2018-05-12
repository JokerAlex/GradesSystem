package com.grades.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UploadService {


    String upload(String tableName, CommonsMultipartFile file, HttpServletRequest request);

    String checkTableName(String talbeName);

    String fileRead(String fileName) throws Exception;

    String fileWrite(int userId, String tableName, List<List<String>> lists);
}
