package com.xyc.service.imp;

import com.xyc.mapper.RecommendMapper;
import com.xyc.pojo.Recommend;
import com.xyc.service.RecommendService;
import entity.FTPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.PhotoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class RecommendServiceImp implements RecommendService {

    @Autowired
    private RecommendMapper recommendMapper;


    @Override
    public int add(Integer cGoodsId) {
        return recommendMapper.add(cGoodsId);
    }

    @Override
    public int delete(Integer id) {
        return recommendMapper.delete(id);
    }

    @Override
    public List<Recommend> get() {
        List<Recommend> list = recommendMapper.get();
        return list;
    }
}
