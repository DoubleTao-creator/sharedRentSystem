package com.xyc.mapper;

import com.xyc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    public User queryByName(@Param("name")String name);

    public User queryById(@Param("id")Integer id);

}
