package com.xyc.mapper;

import com.xyc.dto.AdministratorLoginDTO;
import com.xyc.dto.AdministratorRegisterDTO;
import com.xyc.pojo.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdministratorMapper {

    public int add(AdministratorRegisterDTO adminRD);

    public Administrator query(AdministratorLoginDTO adminLD);

}
