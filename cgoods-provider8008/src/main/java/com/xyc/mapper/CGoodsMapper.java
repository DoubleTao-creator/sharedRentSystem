package com.xyc.mapper;

import com.xyc.dto.CGoodsAddDTO;
import com.xyc.pojo.CGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CGoodsMapper {

    public CGoods queryById(@Param("id") int id);

    public int add(CGoods cGoods);

    public List<CGoods> queryAll();

    /**
     * 根据商家id得到 商品
     * @param sellerId
     * @return
     */
    public List<CGoods> queryBySellerId(@Param("sellerId") int sellerId);

    /**
     * 模糊查询 根据商家名，商品名，商品信息
     * @param info
     * @return
     */
    public List<CGoods> queryByInfo(@Param("info") String info);
    //select DISTINCT c.* from cgoods c,seller s WHERE
    //    c.name like '%c%' or
    //    c.info like '%c%' or
    //    c.sellerId in(
    //            SELECT s.id from seller WHERE s.name like '%c%'
    //    )

    /**
     * 得到某个类型的商品
     * @param typeId
     * @return
     */
    public List<CGoods> queryByTypeId(@Param("typeId") int typeId);

    public int modify(CGoods cGoods);

    /**
     * 修改照片
     * @param pic
     * @param cGoodsId
     * @return
     */
    public int modifyPic(@Param("pic") String pic,@Param("id") Integer cGoodsId);

    /**
     * 得到当前商品的id
     * @return
     */
    public int getId(@Param("sellerId") Integer sellerId
            ,@Param("name") String name);

    /**
     * 根据出售模式flag查询 出售模式
     * @param flag
     * @return
     */
    public int getSellModel(@Param("flag") String flag);

    /**
     * 查询 '待审核'的 商品
     * @return
     */
    public List<CGoods> getByStatus();

    /**
     * 更改商品的状态 '待审核'--->'正常'
     * @param id
     * @return
     */
    public int changeStatus(@Param("id") Integer id);


}
