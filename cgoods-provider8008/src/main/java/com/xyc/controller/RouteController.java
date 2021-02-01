package com.xyc.controller;

import com.xyc.mapper.TypeMapper;
import com.xyc.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cgoods")
public class RouteController {

    @Autowired
    private TypeMapper typeMapper;

    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        List<Type> types = typeMapper.getAllType();
        model.addAttribute("types",types);
        return "add";
    }
}
