package com.xyc.mapper;

import com.xyc.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeMapper {

    public List<Type> getAllType();

}
