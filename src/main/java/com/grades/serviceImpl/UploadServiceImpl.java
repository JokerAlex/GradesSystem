package com.grades.serviceImpl;

import com.grades.mapping.TableInfoMapper;
import com.grades.model.TableInfo;
import com.grades.service.UploadService;
import com.grades.utils.ExcelReader;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    private static List<List<String>> readResultList;
    private static int readResultRows = 0;

    private TableInfoMapper tableInfoMapper;

    public List<List<String>> getReadResultList() {
        return readResultList;
    }

    public int getReadResultRows() {
        return readResultRows;
    }

    @Autowired
    public UploadServiceImpl(TableInfoMapper tableInfoMapper){
        this.tableInfoMapper = tableInfoMapper;
    }

    public String upload(CommonsMultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty() && request != null){
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
            return "{\"uploadResult:\"true\",\"path\":"+path+fileName+"\"}";
        }
        return "{\"uploadResult:\"false\",\"path\":"+null+"\"}";
    }

    public String checkTableName(String tableName) {
        String result = null;
        if (tableName != null && !tableName.trim().equals("")){
            result = tableInfoMapper.findTable(tableName);
        }
        return result;
    }

    public String fileRead(String fileName) throws Exception {
        //获取工作表
        Workbook wb = ExcelReader.getWorkbook(fileName);
        //获取工作簿
        Sheet sheet = ExcelReader.getSheet(wb,0);
        //读取数据
        readResultList = ExcelReader.getExcelRows(sheet,-1,-1);
        if (readResultList != null){
            readResultRows = readResultList.size();
            return "{\"readResult\":\"true\",\"rows\":\""+readResultRows+"\"}";
        }
        return "{\"readResult\":\"false\",\"rows\":\""+readResultRows+"\"}";
    }

    public String fileWrite(int userId, String tableName, List<List<String>> lists) {
        boolean createResult;
        boolean insertResult = false;
        boolean updateTableInfoResult = false;
        //创建表
        List<String> listHead = lists.get(0);
        createResult = tableInfoMapper.createTable(tableName , listHead);
        if (createResult){
            //去掉头部-列名
            lists.remove(0);
            //分组插入数据，每组20条
            for (int i = 0 ; i < lists.size(); i+= 20){
                List<List<String>> temp = new ArrayList<List<String>>();
                //最后不满20条的情况
                if (i + 20 > lists.size()){
                    int remain = lists.size() % 20;
                    temp.addAll(lists.subList(lists.size() - remain,lists.size()));
                }else {
                    temp.addAll(lists.subList(i,i + 20));
                }
                insertResult = tableInfoMapper.insertData("test", temp);
                if (!insertResult){
                    break;
                }
            }
        }
        if (insertResult){
            //表信息更新
            updateTableInfoResult = tableInfoMapper.insertTableInfo(new TableInfo(0, tableName, userId, 0));
        }
        return "{\"createResult\":\""+createResult+"\",\"insertResult\":\""+insertResult+"\",\"updateTableInfoResult\":\""+updateTableInfoResult+"\"}";
    }
}
