package com.xyc;

import com.xyc.dto.CGoodsShowDTO;
import com.xyc.service.CGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class testService {

    @Autowired
    private CGoodsService cGoodsService;

    @Test
    public void test0131(){

//        List<CGoodsShowDTO> sdList = cGoodsService.getAll();
//        List<CGoodsShowDTO> sdList = cGoodsService.fuzzySearch("f");
//        List<CGoodsShowDTO> sdList = cGoodsService.searchByTypeId(1);
        List<CGoodsShowDTO> sdList = cGoodsService.searchBySellerId(6);


        for (CGoodsShowDTO cGoodsShowDTO : sdList) {
            System.out.println(cGoodsShowDTO);
        }

    }

}
