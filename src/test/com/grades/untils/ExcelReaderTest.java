package com.grades.untils;

import com.grades.mapping.TableInfoMapper;
import com.grades.utils.ExcelReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ExcelReaderTest {

    @Autowired
    private TableInfoMapper tableInfoMapper;

    @Test
    public void dataRead() throws Exception {
        Workbook wb = ExcelReader.getWorkbook("/Users/alex/Documents/资料/z工作室/成绩发布与查询/数据/2016级学号姓名.xlsx");
        Sheet sheet = ExcelReader.getSheet(wb,0);
        List<List<String>> read = ExcelReader.getExcelRows(sheet,-1,-1);
        List<List<String>> listResult = ExcelReader.removeNull(read);
        int i = 0;
        for (List<String> rowList: listResult) {
            System.out.print(++i +"\t");
            for (String cell:rowList){
                System.out.print(cell+"\t");
            }
            System.out.println();
        }
    }

    @Test
    public void dataSearch() throws Exception {
        Workbook wb = ExcelReader.getWorkbook("D:\\资料\\学习资料\\z工作室\\成绩发布与查询\\数据\\最高成绩总表.xlsx");
        Sheet sheet = ExcelReader.getSheet(wb,0);
        List<List<String>> listResult = ExcelReader.getExcelRows(sheet,-1,-1);
        List<String> listHead = listResult.get(0);
        //创建表
        System.out.print(tableInfoMapper.createTable("test" , listHead));
        System.out.println(listResult.size());
        listResult.remove(0);
        System.out.println(listResult.size());

        //分组插入数据，每组10条
        for (int i = 0 ; i < listResult.size(); i+= 20){
            List<List<String>> temp = new ArrayList<List<String>>();
            if (i + 20 > listResult.size()){
                int remain = listResult.size() % 20;
                temp.addAll(listResult.subList(listResult.size() - remain,listResult.size()));
            }else {
                temp.addAll(listResult.subList(i,i + 20));
            }
            //System.out.println(i+"\t"+temp.size());
            System.out.print(tableInfoMapper.insertData("test", temp));
        }

    }
}
