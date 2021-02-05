package com.xyc.service;

import com.xyc.dto.SlidShowDTO;
import com.xyc.pojo.SlidShow;

import java.util.List;

public interface SlidShowService {
    public int add(SlidShowDTO showDTO);

    public int delete(Integer id);

    public List<SlidShow> get();
}
