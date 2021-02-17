package com.xyc.controller;

import com.xyc.dto.CGoodsShowDTO;
import com.xyc.mapper.CGoodsMapper;
import com.xyc.mapper.TypeMapper;
import com.xyc.pojo.CGoods;
import com.xyc.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cgoods")
public class RouteController {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private CGoodsMapper cGoodsMapper;

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        List<Type> types = typeMapper.getAllType();
        model.addAttribute("types",types);
        return "add";
    }

    @GetMapping("/toModify/{cGoodsId}")
    public String toModify(Model model,@PathVariable("cGoodsId") int id){
        CGoods cGoods = cGoodsMapper.queryById(id);
        CGoodsShowDTO cGoodsSD = getCGoodsSD(cGoods);
        model.addAttribute("cGoodSD",cGoodsSD);

        List<Type> types = typeMapper.getAllType();
        model.addAttribute("types",types);

        return "modify";
    }

    @GetMapping("/toModifyPic/{cGoodsId}")
    public String toModifyPic(Model model,@PathVariable("cGoodsId") int id){
        CGoods cGoods = cGoodsMapper.queryById(id);
        CGoodsShowDTO cGoodsSD = getCGoodsSD(cGoods);
        model.addAttribute("cGoodSD",cGoodsSD);

        return "modifyPic";
    }

    private CGoodsShowDTO getCGoodsSD(CGoods cGoods){
        CGoodsShowDTO cGoodsSD = new CGoodsShowDTO();

        cGoodsSD.setId(cGoods.getId());
        cGoodsSD.setSellerId(cGoods.getSellerId());
        cGoodsSD.setName(cGoods.getName());
        cGoodsSD.setType(getType(cGoods.getTypeId()));
        cGoodsSD.setRepertory(cGoods.getRepertory());
        cGoodsSD.setPic(cGoods.getPic());
        cGoodsSD.setInfo(cGoods.getInfo());
        cGoodsSD.setSellModels(getSellModels(cGoods.getSellModel()));
        cGoodsSD.setPrice(cGoods.getPrice());
        cGoodsSD.setRental(cGoods.getRental());
        cGoodsSD.setDeposit(cGoods.getDeposit());
        cGoodsSD.setStatus(cGoods.getStatus());

        return cGoodsSD;
    }

    private String[] getSellModels(int sellModel){
        String[] models;
        switch(sellModel){
            case 1 :
                models = new String[]{"以租代售"};
                break;
            case 2 :
                models = new String[]{"先租后买"};
                break;
            case 3 :
                models = new String[]{"先租后买","以租代售"};
                break;
            case 4 :
                models = new String[]{"共享租赁"};
                break;
            case 5 :
                models = new String[]{"共享租赁","以租代售"};
                break;
            case 6 :
                models = new String[]{"共享租赁","先租后买"};
                break;
            case 7 :
                models = new String[]{"共享租赁","先租后买","以租代售"};
                break;
            default: models = null;
        }
        return models;
    }

    private String getType(int typeId){
        String s = "";
        switch(typeId){
            case 1 :
                s = "电摩公社";
                break;
            case 2 :
                s = "电子产品";
                break;
            case 3 :
                s = "摄影航拍";
                break;
            case 4 :
                s = "家居生活";
                break;
            case 5 :
                s = "游戏酷玩";
                break;
            case 6 :
                s = "服饰潮鞋";
                break;
        }
        return s;
    }


}
