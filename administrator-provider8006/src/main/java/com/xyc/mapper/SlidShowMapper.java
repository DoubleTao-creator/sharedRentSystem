package com.xyc.mapper;

import com.xyc.pojo.SlidShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SlidShowMapper {

    public SlidShow getById(@Param("id") Integer id);

    public int add(SlidShow slidShow);

    public int delete(@Param("cgoodsId") Integer cgoodsId);

    public List<SlidShow> get();
}
