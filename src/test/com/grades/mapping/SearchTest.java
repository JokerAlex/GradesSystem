package com.grades.mapping;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SearchTest {

    @Autowired
    private Search search;


    private void printMap(List<Map<String,String>> list){
        for (int i = 0;i < 10;i++){
            Map map = list.get(i);
            for (Object key:map.keySet()
                    ) {
                System.out.print(map.get(key)+"\t");
            }
            System.out.println();
            if (list.size() == 1){
                break;
            }
        }
    }

    @Test
    public void getAll(){
        List<String> colNames = search.getColNames("1_bb");
        List<Map<String,String>> list = search.getAll(colNames,"1_bb");
        System.out.println("list----------->"+list.size());
        printMap(list);
    }

    @Test
    public void getOne(){
        List<String> colNames = search.getColNames("1_bb");
        List<Map<String,String>> list = search.getOne(colNames,"1_bb",colNames.get(0),"2016220401014",colNames.get(1),"赵建成");
        System.out.println("list----------->"+list.size());
        printMap(list);
    }

    @Test
    public void getColNames(){
        List<String> list = search.getColNames("1_bb");
        for (String name:list
             ) {
            System.out.print(name+"\t");
        }
    }
}
