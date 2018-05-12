package com.grades.serciceImpl;


import com.grades.serviceImpl.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UploadServiceImplTest {

    @Autowired
    private UploadServiceImpl uploadService;

    @Test
    public void upload(){

        try {
            uploadService.fileRead("/Users/alex/Documents/资料/z工作室/成绩发布与查询/数据/最高成绩总表.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
        uploadService.fileWrite(1,"zzz",uploadService.getReadResultList());
    }
}
