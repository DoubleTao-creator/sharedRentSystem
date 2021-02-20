package com.xyc.mapper;

import com.xyc.dto.SellerLoginDTO;
import com.xyc.dto.SellerModifyDTO;
import com.xyc.dto.SellerRegisterDTO;
import com.xyc.pojo.Seller;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SellerMapper {

    public Seller queryById(@Param("id") int id);

    public int getId(@Param("name") String name,@Param("email") String email);

    public int add(Seller seller);

    public Seller queryByName(SellerLoginDTO sellerLD);

    public int update(Seller seller);

    public int updatePic(@Param("pic") String pic,@Param("id") Integer id);

    public int updateLicense(@Param("license") String license,@Param("id") Integer id);

    public List<Seller> getFrozenAccount();

    public int sellerAuthenticate(@Param("id") int id);

    public int updateBalance(int income,@Param("id") int id);
}
