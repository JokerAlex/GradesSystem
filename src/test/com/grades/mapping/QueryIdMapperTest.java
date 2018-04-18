package com.grades.mapping;


import com.grades.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class QueryIdMapperTest {
    @Autowired
    private QueryIdMapper queryIdMapper;

    @Test
    public void getAllRecords(){

        List<QueryRecord> queryRecordList = queryIdMapper.getRecordIdAndTables(1);
        for (QueryRecord qr:queryRecordList
             ) {
            for (TableStatus ta:qr.getTableStatus()
                 ) {
                System.out.println(qr.getQueryRecordId()+"\t"+qr.getQueryIdName()+"\t"+ta.getTableName()+"\t"+ta.getTableStatus());
            }
        }
    }
}
