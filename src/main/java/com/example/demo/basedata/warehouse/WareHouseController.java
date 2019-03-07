package com.example.demo.basedata.warehouse;

import com.example.demo.basedata.billType.BillType;
import com.example.demo.basedata.billType.BillTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("warehouse")
public class WareHouseController {
    @Autowired
    WareHouseMapper wareHouseMapper;

    @RequestMapping("")
    public String warehouse(){
        return "basedata/warehouse/list";
    }
    @ResponseBody
    @RequestMapping("data")
    public List<WareHouse> data(){
        return wareHouseMapper.findList();
    }

    @RequestMapping("form")
    public String form(String id, Model model){
        WareHouse wareHouse=wareHouseMapper.get(id);
        model.addAttribute("warehouse",wareHouse==null?new WareHouse():wareHouse);
        return "basedata/warehouse/form";
    }

    @RequestMapping("save")
    public String save(WareHouse wareHouse){
        if(wareHouse.getId()==null||"".equals(wareHouse.getId())) {
            wareHouseMapper.save(wareHouse);
        }else {
            wareHouseMapper.updata(wareHouse);
        }
        return "basedata/warehouse/list";
    }

    @RequestMapping("delete")
    public String deleteAll(String ids){
        for(String id:ids.split(",")) {
            wareHouseMapper.delete(id);
        }
        return "basedata/warehouse/list";
    }
}
