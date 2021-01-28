package com.xyc;

import com.xyc.mapper.CGoodsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class test {

    @Autowired
    private CGoodsMapper cGoodsMapper;

    @Test
    public void test0128(){
        char[] s = new char[]{'0', '1', '0'};
        String ss = new String(s);
        System.out.println(cGoodsMapper.getSellModel(ss));
    }
}
