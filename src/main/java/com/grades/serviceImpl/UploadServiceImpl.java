package com.grades.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.grades.mapping.TableInfoMapper;
import com.grades.model.*;
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
    private List<List<String>> readResultList = new ArrayList<List<String>>();//读取结果
    private int readResultRows = 0;//总行数
    private int errorCode = 1;
    private String errorInfo = "";

    private TableInfoMapper tableInfoMapper;

    public List<List<String>> getReadResultList() {
        return this.readResultList;
    }

    public String getFilePath(){
        return this.filePath;
    }

    public int getErrorCode(){
        return this.errorCode;
    }

    public String getErrorInfo(){
        return this.errorInfo;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
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
    public JSONObject upload(CommonsMultipartFile file, String tableName, HttpServletRequest request) {
        setErrorCode(1);
        setErrorInfo("");
        User user = new User();
        boolean isDelExcel = false;

        //文件上传

        if (!file.isEmpty() && request != null){
            //获取文件类型
            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            String sessionId = request.getSession().getId();
            user = (User) request.getSession().getAttribute(sessionId);
            String fileName = user.getId() + System.currentTimeMillis() + fileType;
            String path = request.getSession().getServletContext().getRealPath("/upload/");
            File destFile = new File(path,fileName);
            try {
                // FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下
            } catch (IOException e) {
                setErrorCode(-1);
                setErrorInfo("文件上传时错误");
                e.printStackTrace();
            }
            this.filePath = path+fileName;
        } else {
            setErrorCode(-1);
            setErrorInfo("文件上传时错误");
        }

        if (new File(filePath).length() > 2*1024*1024){
            setErrorCode(-8);
            setErrorInfo("文件大小超过2M");
        }

        //文件读取

        if (errorCode == 1){
            try {
                this.fileRead(filePath);
            } catch (Exception e) {
                setErrorCode(-2);
                setErrorInfo("读取文件时发生错误");
            }
        }else {
           isDelExcel = delExcel(filePath);
        }

        //文件写库

        if (errorCode == 1){
            this.fileWrite(user.getId(),user.getId()+"_"+tableName,this.readResultList);
        } else {
            if (isDelExcel != true){
                isDelExcel = delExcel(filePath);
            }
        }

        if (isDelExcel != true){
            isDelExcel = delExcel(filePath);
        }
        //返回信息
        //System.out.println("isDelExcel--------->"+isDelExcel+filePath);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode",getErrorCode());
        jsonObject.put("errorInfo",getErrorInfo());
        return jsonObject;
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
        if (result == null){
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
    public void fileRead(String fileName) throws Exception {
        //存储单元初始化
        this.readResultList.clear();
        this.readResultRows = 0;
        //判断文件格式是否正确
        String fileType = fileName.substring(fileName.indexOf(".")+1);
        if (fileName.equals("xls") || fileType.equals("xlsx")){
            //获取工作表
            Workbook wb = ExcelReader.getWorkbook(fileName);
            //获取工作簿
            Sheet sheet = ExcelReader.getSheet(wb,0);
            //获取总行数
            readResultRows = ExcelReader.getRowNumber(sheet);
            //读取数据
            readResultList = ExcelReader.getExcelRows(sheet,-1,-1);
            if (readResultList.size() != readResultRows){
                setErrorCode(-2);
                setErrorInfo("读取文件时发生错误");
            }

            //过滤空列
            ExcelReader.removeNull(readResultList);

        } else {
            setErrorCode(-7);
            setErrorInfo("请上传Excel文件");
        }

    }

    /**
     * excel写入数据库,写入进度
     * @param userId
     * @param tableName
     * @param lists
     * @return "{"createResult":"false/true","insertResult":"false/true","updateTableInfoResult":"false/true"}
     */
    //@Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public void fileWrite(int userId, String tableName, List<List<String>> lists) {
        int groupSize = 20;//分组大小
        //boolean insertResult = false;
        //创建表
        List<String> listHead = lists.get(0);
        try {
            tableInfoMapper.createTable(tableName, listHead);
        } catch (Exception e){
            setErrorCode(-3);
            setErrorInfo("创建数据表时错误，尝试将数据部分粘贴到新的Excel文件后重试");
        }

        if (getErrorCode() == 1){
            //去掉头部-列名
            lists.remove(0);
            //分组插入数据
            for (int i = 0 ; i < lists.size(); i+= groupSize){
                List<List<String>> temp = new ArrayList<List<String>>();
                //最后不满20条的情况
                try {
                    if (i + groupSize > lists.size()){
                        int remain = lists.size() % groupSize;
                        temp.addAll(lists.subList(lists.size() - remain,lists.size()));
                    }else {
                        temp.addAll(lists.subList(i,i + groupSize));
                    }
                    tableInfoMapper.insertData(tableName, temp);
                } catch (Exception e){
                    tableInfoMapper.dropTable(tableName);
                    setErrorCode(-4);
                    setErrorInfo("插入数据时错误");
                }
            }
        }

        if (getErrorCode() == 1){
            try {
                tableInfoMapper.insertTableInfo(new TableInfo(tableName, userId));
            } catch (Exception e){
                setErrorCode(-5);
                setErrorInfo("数据库内部错误：更新数据表信息时异常");
            }
        }
    }

    public boolean delExcel(String path){
        File file = new File(path);
        if(file.isFile() && file.exists()){
            file.delete();
            return true;
        }else{
            return false;
        }
    }
}
