package com.example.demo.basedata.respository;

import com.example.demo.basedata.goods.Good;
import com.example.demo.basedata.goods.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("res")
public class ResController {
    @Autowired
    ResMapper resMapper;

    @RequestMapping("")
    public String goods(){
        return "basedata/respository/list";
    }
    @ResponseBody
    @RequestMapping("data")
    public List<Res> data(){
        return resMapper.findList();
    }

    @RequestMapping("form")
    public String form(String id, Model model){
        Res res=resMapper.get(id);
        model.addAttribute("res",res==null?new Res():res);
        return "basedata/respository/form";
    }

    @RequestMapping("save")
    public String save(Res res){
        if(res.getId()==null||"".equals(res.getId())) {
            resMapper.save(res);
        }else {
            resMapper.updata(res);
        }
        return "basedata/respository/list";
    }

    @RequestMapping("delete")
    public String deleteAll(String ids){
        for(String id:ids.split(",")) {
            resMapper.delete(id);
        }
        return "basedata/respository/list";
    }
}
