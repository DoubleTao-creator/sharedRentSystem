package com.xyc.mapper;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SellerMapper {

    public int register(Seller seller);

    public Seller login(SellerLoginDTO sellerLD);
}
