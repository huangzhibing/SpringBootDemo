package com.example.demo.basedata.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("")
    public String goods(){
        return "basedata/goods/list";
    }
    @ResponseBody
    @RequestMapping("data")
    public List<Good> data(){
        return goodsMapper.findList();
    }

    @RequestMapping("form")
    public String form(String id, Model model){
        Good good=goodsMapper.get(id);
        model.addAttribute("good",good==null?new Good():good);
        return "basedata/goods/form";
    }

    @RequestMapping("save")
    public String save(Good good){
        if(good.getId()==null||"".equals(good.getId())) {
            goodsMapper.save(good);
        }else {
            goodsMapper.updata(good);
        }
        return "basedata/goods/list";
    }

    @RequestMapping("delete")
    public String deleteAll(String ids){
        for(String id:ids.split(",")) {
            goodsMapper.delete(id);
        }
        return "basedata/goods/list";
    }
}
