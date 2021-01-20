package com.xtt.mapper;
import com.xtt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author xtt
 * @date
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 用户注册
     * @param user
     * @return
     */
    Integer userLogin(User user);
}
