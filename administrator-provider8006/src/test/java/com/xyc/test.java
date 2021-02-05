package com.xyc;

import com.xyc.mapper.RecommendMapper;
import com.xyc.mapper.SlidShowMapper;
import com.xyc.pojo.Recommend;
import com.xyc.pojo.SlidShow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class test {

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private SlidShowMapper slidShowMapper;

    @Test
    public void test0205(){
        SlidShow slidShow = new SlidShow(3,"...");

        System.out.println(slidShowMapper.add(slidShow));

        List<SlidShow> list = slidShowMapper.get();
        for (SlidShow show : list) {
            System.out.println(show);
        }
    }

    @Test
    public void test0204(){
        List<Recommend> list = recommendMapper.get();

        for (Recommend recommend : list) {
            System.out.println(recommend);
        }
    }
}
