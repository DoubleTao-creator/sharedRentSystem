package com.xyc.mapper;

import com.xyc.pojo.Recommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecommendMapper {

    public int add(Recommend recommend);

    public int delete(@Param("id") Integer id);

    public List<Recommend> get();
}
