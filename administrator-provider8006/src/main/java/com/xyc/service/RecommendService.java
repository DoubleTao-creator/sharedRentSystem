package com.xyc.service;

import com.xyc.pojo.Recommend;

import java.util.List;

public interface RecommendService {

    public int add(Recommend recommend);

    public int delete(Integer id);

    public List<Recommend> get();

}
