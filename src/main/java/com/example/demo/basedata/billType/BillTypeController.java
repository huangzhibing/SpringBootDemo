package com.example.demo.basedata.billType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("billType")
public class BillTypeController {
    @Autowired
    BillTypeMapper billTypeMapper;

    @RequestMapping("")
    public String billType(){
        return "basedata/billType/list";
    }
    @ResponseBody
    @RequestMapping("data")
    public List<BillType> data(){
        return billTypeMapper.findList();
    }

    @RequestMapping("form")
    public String form(String id, Model model){
        BillType billType=billTypeMapper.get(id);
        model.addAttribute("billType",billType==null?new BillType():billType);
        return "basedata/billType/form";
    }

    @RequestMapping("save")
    public String save(BillType billType){
        if(billType.getId()==null) {
            billTypeMapper.save(billType);
        }else {
            billTypeMapper.updata(billType);
        }
        return "basedata/billType/list";
    }

    @RequestMapping("delete")
    public String deleteAll(String ids){
        for(String id:ids.split(",")) {
            billTypeMapper.delete(id);
        }
        return "basedata/billType/list";
    }
}
