//package com.xyc;
//
//import com.xyc.dto.GoodsShowDTO;
//import com.xyc.mapper.CGoodsMapper;
//import com.xyc.mapper.GoodsMapper;
//import com.xyc.mapper.TypeMapper;
//import com.xyc.pojo.Type;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class test {
//
//    @Autowired
//    private CGoodsMapper cGoodsMapper;
//
//    @Autowired
//    private TypeMapper typeMapper;
//
//    @Autowired
//    private GoodsMapper goodsMapper;
//
//    @Test
//    public void test0202(){
//        List<GoodsShowDTO> list = goodsMapper.getGoodsByCGoodsId(5);
//        for (GoodsShowDTO goodsShowDTO : list) {
//            System.out.println(goodsShowDTO);
//        }
//    }
//
//    @Test
//    public void test0131(){
//        List<Type> list = typeMapper.getAllType();
//        for (Type type : list) {
//            System.out.println(type);
//        }
//    }
//
//    @Test
//    public void test0128(){
//        char[] s = new char[]{'0', '1', '0'};
//        String ss = new String(s);
//        System.out.println(cGoodsMapper.getSellModel(ss));
//    }
//
//}
