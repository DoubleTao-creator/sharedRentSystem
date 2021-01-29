package com.xyc.mapper;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SellerMapper {

    public Seller queryById(@Param("id") int id);

    public int register(Seller seller);

    public Seller login(SellerLoginDTO sellerLD);

    public int update(Seller seller);

    public int updateBalance(int income,@Param("sellerId") int sellerId);
}
