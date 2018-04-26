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

    private String filePath;//文件保存位置
    private String tableName;//设置的表名
    private List<List<String>> readResultList;//读取结果
    private int readResultRows = 0;//总行数
    private int writeProgress = 0;//写入进度

    private TableInfoMapper tableInfoMapper;

    public List<List<String>> getReadResultList() {
        return readResultList;
    }

    public String getTableName(){
        return tableName;
    }

    public String getFilePath(){
        return filePath;
    }

    public float getReadProgress(){
        float result = (float)readResultRows/(float)readResultList.size();
        return result;
    }

    public float getWriteProgress(){
        float result = (float)writeProgress/(float)readResultList.size();
        return result;
    }

    @Autowired
    public UploadServiceImpl(TableInfoMapper tableInfoMapper){
        this.tableInfoMapper = tableInfoMapper;
    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @return {"uploadResult:"false/true"}
     */
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
            filePath = path+fileName;
            return "{\"uploadResult:\"true\"";
        }
        return "{\"uploadResult:\"false\"}";
    }

    /**
     * 检测表名是否可用
     * @param tableName
     * @return {"isAvailable":"false/true"}
     */
    public String checkTableName(String tableName) {
        String result = null;
        if (tableName != null && !tableName.trim().equals("")){
            result = tableInfoMapper.findTable(tableName);
        }
        if (result != null){
            this.tableName = tableName;
            return "{\"isAvailable\":\"true\"}";
        }
        return "{\"isAvailable\":\"false\"}";
    }

    /**
     * 读取文件内容，同时获取总行数
     * @param fileName
     * @return {"readResult":"false/true"}
     * @throws Exception
     */
    public String fileRead(String fileName) throws Exception {
        //获取工作表
        Workbook wb = ExcelReader.getWorkbook(fileName);
        //获取工作簿
        Sheet sheet = ExcelReader.getSheet(wb,0);
        //获取总行数
        readResultRows = ExcelReader.getRowNumber(sheet);
        //读取数据
        readResultList = ExcelReader.getExcelRows(sheet,-1,-1);
        if (readResultList.size() == readResultRows){
            return "{\"readResult\":\"true\"}";
        }
        return "{\"readResult\":\"false\"}";
    }

    /**
     * excel写入数据库,写入进度
     * @param userId
     * @param tableName
     * @param lists
     * @return "{"createResult":"false/true","insertResult":"false/true","updateTableInfoResult":"false/true"}
     */
    public String fileWrite(int userId, String tableName, List<List<String>> lists) {
        int groupSize = 20;//分组大小
        String tempTableName = userId+"_"+tableName;
        boolean createResult;
        boolean insertResult = false;
        boolean updateTableInfoResult = false;
        //创建表
        List<String> listHead = lists.get(0);
        createResult = tableInfoMapper.createTable(tempTableName, listHead);
        if (createResult){
            //去掉头部-列名
            lists.remove(0);
            //分组插入数据
            for (int i = 0 ; i < lists.size(); i+= groupSize){
                List<List<String>> temp = new ArrayList<List<String>>();
                //最后不满20条的情况
                if (i + groupSize > lists.size()){
                    int remain = lists.size() % groupSize;
                    temp.addAll(lists.subList(lists.size() - remain,lists.size()));
                }else {
                    temp.addAll(lists.subList(i,i + groupSize));
                }
                insertResult = tableInfoMapper.insertData(tempTableName, temp);
                writeProgress += groupSize;
                if (!insertResult){
                    break;
                }
            }
        }
        if (insertResult){
            //表信息更新
            updateTableInfoResult = tableInfoMapper.insertTableInfo(new TableInfo(0, tableName, userId, 0));
        }
        writeProgress = 0;
        return "{\"createResult\":\""+createResult+"\",\"insertResult\":\""+insertResult+"\",\"updateTableInfoResult\":\""+updateTableInfoResult+"\"}";
    }
}
