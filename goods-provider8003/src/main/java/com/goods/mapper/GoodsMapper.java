package com.goods.mapper;

import com.goods.entity.Goods;
import com.goods.entity.Installment;
import com.goods.entity.RentToBuy;
import com.goods.entity.ShareRent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GoodsMapper {
    /**
     * 通过ID查询订单记录
     * @param goodsId
     * @return
     */
    Goods findGoodsById(@Param("goodsId") Integer goodsId);
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
     * 创建先租后买订单
     * @param goods
     * @return
     */
    Integer createRentToBuyOrder(Goods goods);

    /**
     * 添加共享租赁订单记录
     * @param shareRent
     * @return
     */
    Integer addShareRentRecode(ShareRent shareRent);

    /**
     * 更新共享租赁记录
     * @param shareRent
     * @return
     */
    Integer updateShareRentRecode(ShareRent shareRent);

    /**
     * 创建共享租赁订单
     * @param goods
     * @return
     */
    Integer createShareRentOrder(Goods goods);

    /**
     * 用户直接购买商品(未体验）
     * @param cgoodsId 商品类ID
     * @param userId 用户ID
     * @return
     */
    Integer purchaseGoods(@Param("cgoodsId") Integer cgoodsId, @Param("userId") Integer userId);

    /**
     * 续租以租代售订单(续租一个月)
     * @param goodsId
     * @return
     */
    Integer rerentGoods(@Param("goodsId") Integer goodsId);

    /**
     * 根据商品id查询以租代售
     * @param goodsId
     * @return
     */
    Installment findInstallmentByGoodsId(@Param("goodsId") Integer goodsId);

    /**
     * 查询以租代售订单两个时间相差几个月
     * @param goodsId 商品id
     * @return 相差的月数
     */
    Integer selectDifferMonth(@Param("goodsId") Integer goodsId);

    /**
     * 对先租后买订单选择购买
     * @param goodsId
     * @return
     */
    Integer purchaseRentToBuy(@Param("goodsId") Integer goodsId);

    /**
     * 查询先租后买记录
     * @param goodsId 订单ID
     * @return 先租后买记录
     */
    RentToBuy findRentToBuyById(Integer goodsId);

    /**
     * 退租（退商品不退钱）
     * @param goodsId
     * @param userId
     * @return
     */
    Integer refundRent(@Param("goodsId") Integer goodsId,@Param("userId") Integer userId);
}
