package com.grades.mapping;


import com.grades.model.Student;
import com.grades.utils.ExcelReader;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void insertTest(){
        Workbook wb = null;
        try {
            wb = ExcelReader.getWorkbook("/Users/alex/Documents/资料/z工作室/成绩发布与查询/数据/2016级学号姓名.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sheet sheet = ExcelReader.getSheet(wb,0);
        List<List<String>> listResult = ExcelReader.getExcelRows(sheet,-1,-1);
        //List<Student> studentList = new ArrayList<Student>();
        for (List<String> list:listResult){
            for (String s:list){
                System.out.print(s+"\t");
            }
            System.out.println();
        }
        /*for (int i = 0; i < listResult.size(); i++) {
            if (i == 0) {
                continue;
            }
            Student student = new Student(listResult.get(i).get(0), listResult.get(i).get(1), listResult.get(i).get(2));
            studentList.add(student);
        }

        for (Student stu : studentList) {
            System.out.println(stu.getId() + stu.getName() + stu.getGrade());
        }

        int result = studentMapper.insertStuBatch(studentList);

        System.out.println(result == studentList.size());*/
    }

    @Test
    public void getStu(){
        List<Student> studentList = studentMapper.getAllStu(null);
        for (Student stu : studentList){
            System.out.println(stu.getId()+"------>"+stu.getName());
        }
        System.out.println(studentList.size());
    }

    @Test
    public void getStuOne(){
        Student student = studentMapper.getStu("2016220401014","赵建成");
        /*for (Student stu : studentList){
            System.out.println(stu.getId()+"------>"+stu.getName());
        }*/
        System.out.println(student == null);
    }
}
