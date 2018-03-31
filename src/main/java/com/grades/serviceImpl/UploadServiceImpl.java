package com.grades.serviceImpl;

import com.grades.service.UploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {
    public String upload(CommonsMultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()){
            //获取文件类型
            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String fileName = request.getSession().getAttribute("userId").toString()+System.currentTimeMillis()
                    +fileType;
            String path = request.getSession().getServletContext().getRealPath("/upload/");
            File destFile = new File(path,fileName);
            try {
                // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
            } catch (IOException e) {
                e.printStackTrace();
            }
            return path+fileName;
        }
        return "error";
    }

    public String checkTableName(String talbeName) {
        return null;
    }

    public String fileWriteToDb(String fileName) {
        return null;
    }
}
