package com.xyc.service.imp;

import com.xyc.dto.AdministratorLoginDTO;
import com.xyc.dto.AdministratorRegisterDTO;
import com.xyc.mapper.AdministratorMapper;
import com.xyc.pojo.Administrator;
import com.xyc.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.MD5Utils;

@Service
public class AdministratorServiceImp implements AdministratorService {

    @Autowired
    private AdministratorMapper adminMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(AdministratorRegisterDTO adminRD) {
        String codePwd = MD5Utils.encode(adminRD.getPassword());
        adminRD.setPassword(codePwd);
        return adminMapper.add(adminRD);
    }

    @Override
    public Administrator login(AdministratorLoginDTO adminLD) {
        String codePwd = MD5Utils.encode(adminLD.getPassword());
        adminLD.setPassword(codePwd);
        return adminMapper.query(adminLD);
    }
}
