package com.goods.mapper;

import com.goods.entity.Goods;
import com.goods.entity.Installment;
import com.goods.entity.RentToBuy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsMapper {
    /**
     *用户查询已购买的商品
     * @param userId
     * @return
     */
    List<Goods> findOwnedGoods(Integer userId);

    /**
     * 创建以租代售订单
     * @param goods
     */
    void createInstallmentOrder(Goods goods);

    /**
     * 添加以租代售订单记录
     * @param installment
     * @return 返回添加的本记录的id
     */
    Integer addInstallmentRecode(Installment installment);

    /**
     * 更新以租代售订单记录
     * @param installment
     */
    void  updateInstallmentRecode(Installment installment);

    /**
     * 添加先租后买订单记录
     * @param rentToBuy
     * @return 返回记录id
     */
    Integer addRentToBuyRecode(RentToBuy rentToBuy);

    /**
     * 更新先租后买记录
     * @param rentToBuy
     */
    void updateRentToBuyRecode(RentToBuy rentToBuy);

    /**
     * 创建先租后订单
     * @param goods
     * @return
     */
    Integer createRentToBuyOrder(Goods goods);
}
