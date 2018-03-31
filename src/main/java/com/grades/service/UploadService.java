package com.grades.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UploadService {

    String upload(CommonsMultipartFile file, HttpServletRequest request);

    String checkTableName(String talbeName);

    String fileWriteToDb(String fileName);
}
