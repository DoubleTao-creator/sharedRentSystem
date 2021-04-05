package com.xyc.service;

import com.xyc.dto.AdministratorLoginDTO;
import com.xyc.dto.AdministratorRegisterDTO;
import com.xyc.pojo.Administrator;
import org.springframework.stereotype.Service;

public interface AdministratorService {

    public int register(AdministratorRegisterDTO adminRD);

    public Administrator login(AdministratorLoginDTO adminLD);

}
