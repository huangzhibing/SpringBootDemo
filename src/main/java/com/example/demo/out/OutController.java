package com.example.demo.out;

import com.example.demo.account.Account;
import com.example.demo.account.AccountMapper;
import com.example.demo.basedata.goods.Good;
import com.example.demo.basedata.goods.GoodsMapper;
import com.example.demo.basedata.respository.ResMapper;
import com.example.demo.billmain.BillMain;
import com.example.demo.billmain.BillMainMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("out")
public class OutController {
    @Autowired
    ResMapper resMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BillMainMapper billMainMapper;
    @Autowired
    AccountMapper accountMapper;
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(Model model,String type,String message){
        model.addAttribute("type",type);
        model.addAttribute("goods",goodsMapper.findList());
        model.addAttribute("wares",resMapper.findList());
        if(message!=null||!"".equals(message)) model.addAttribute("message",message);
        return "out/index";
    }
    @ResponseBody
    @RequestMapping("getGood")
    public List<Good> getGood(String resId){
        return accountMapper.findGoodsByRes(resId);
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String save(BillMain billMain,RedirectAttributes redirectAttributes){
        billMainMapper.save(billMain);
        redirectAttributes.addAttribute("message","新增成功");
        return "redirect:out";
    }

    @RequestMapping("bill")
    public String bill(String flag,Model model){
        model.addAttribute("flag",flag);
        return "out/bill";
    }

    @RequestMapping("data")
    @ResponseBody
    public List<BillMain> data(BillMain billMain){
        return billMainMapper.findList(billMain);
    }

    @RequestMapping("edit")
    public String edit(String ids,String flag,Model model){
        model.addAttribute("flag","申请出库");
        for(String id:ids.split(",")){
            BillMain billMain=billMainMapper.get(id);
            if("拒绝出库".equals(flag)||accountMapper.check(billMain)>0) {
                billMainMapper.updataFlag(flag, id);
                accountMapper.updateByBillMain(billMain);
            }else {
                model.addAttribute("message",billMain.getRes().getAddress()+"库存不足");
                return "out/bill";
            }
        }
        model.addAttribute("message",flag+"成功");
        return "out/bill";
    }
}
