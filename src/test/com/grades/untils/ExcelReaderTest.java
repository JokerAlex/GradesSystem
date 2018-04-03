package com.grades.untils;

import com.grades.utils.ExcelReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ExcelReaderTest {

    @Test
    public void dataRead() throws Exception {
        Workbook wb = ExcelReader.getWorkbook("D:\\资料\\学习资料\\z工作室\\成绩发布与查询\\数据\\最高成绩总表.xlsx");
        Sheet sheet = ExcelReader.getSheet(wb,0);
        List<List<String>> listResult = ExcelReader.getExcelRows(sheet,-1,-1);
        int i = 0;
        for (List<String> rowList: listResult) {
            System.out.print(i++);
            for (String cell:rowList){
                System.out.print(cell+"\t");
            }
            System.out.println();
        }
    }
}
