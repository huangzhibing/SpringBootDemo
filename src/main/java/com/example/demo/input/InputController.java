package com.example.demo.input;

import com.example.demo.account.Account;
import com.example.demo.account.AccountMapper;
import com.example.demo.basedata.goods.Good;
import com.example.demo.basedata.goods.GoodsMapper;
import com.example.demo.basedata.respository.Res;
import com.example.demo.basedata.respository.ResMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping(value = "input")
public class InputController {

    @Autowired
    InputMapper inputMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ResMapper resMapper;
    @Autowired
    AccountMapper accountMapper;

    @ModelAttribute
    public Input get(@RequestParam(required=false) String id){
        Input entity = null;
        if(id == null || "".equals(id)){
            Good good = new Good();
            Res res = new Res();
            entity = new Input();
            entity.setGood(good);
            entity.setRes(res);
        }else {
            entity = inputMapper.get(id);
        }
        return entity;
    }

    @RequestMapping(value = "list")
    public String ListLuru(String flag,Model model){
        model.addAttribute("flag",flag);
        return "input/list";
    }

    @ResponseBody
    @RequestMapping(value = "data")
    public List<Input> data(Input input){
        return inputMapper.findList(input);
    }

    @RequestMapping(value = "form")
    public String Form(String flag,Input input, Model model){
        input = get(input.getId());
        model.addAttribute("input",input);
        model.addAttribute("flag",flag);
        model.addAttribute("good",goodsMapper.findList());
        model.addAttribute("ware",resMapper.findList());
        return "input/form";
    }

    @RequestMapping(value = "yanshou")
    public String yanshou(String ids,Model model,RedirectAttributes redirectAttributes){
        for(String id:ids.split(",")){
            Input input = inputMapper.get(id);
            input.setStatus("yanshou");
            inputMapper.update(input);
        }
        model.addAttribute("flag","yanshou");
        model.addAttribute("message","验收成功！");
        return "input/list";
    }

    @RequestMapping(value = "tuihuo")
    public String tuihuo(String ids,Model model){
        for(String id:ids.split(",")){
            Input input = inputMapper.get(id);
            input.setStatus("yituihuo");
            inputMapper.update(input);
        }
        model.addAttribute("flag","tuihuo");
        model.addAttribute("message","退货成功！");

        return "input/list";
    }

    //

    //

    @ResponseBody
    @RequestMapping(value = "check")
    public String check(String type,String id,Model model,RedirectAttributes redirectAttributes){
        Input input = inputMapper.get(id);

        if("hege".equals(type)){
            input.setStatus("chaxun");
            inputMapper.update(input);

            if(accountMapper.preinsert(input)>0){
                accountMapper.updateByInPut(input);
            }
            else
                accountMapper.insertByBillMain(input);

            return "检验合格，正在登记入库单..." ;
        }
        else{
            Input badinput = new Input();
            badinput.setGood(input.getGood());
            badinput.setRes(input.getRes());
            badinput.setNum(input.getNum());
            badinput.setIotype("IT");
            badinput.setStatus("tuihuo");
            inputMapper.insert(badinput);
            input.setStatus("daibuhuo");
            inputMapper.update(input);
            return "检验不合格，正在生成退货单..." ;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "save")
    public String Save(Input input,String status,String hege,String buhege,Model model,RedirectAttributes attributes){
        if("luru".equals(status)) {
            if ("".equals(input.getId())||input.getId() == null) {
                input.setIotype("I");
                inputMapper.insert(input);
            } else {
                inputMapper.update(input);
            }
            model.addAttribute("message","提交入库申请成功！");
        }
        if("yanshou".equals(status)){
            if("true".equals(hege)){
                model.addAttribute("message","检验合格，正在登记入库单...");
            }else if("true".equals(buhege)){
                model.addAttribute("message","检验不合格，正在生成退货单...");
            }
        }
        model.addAttribute("flag",input.getStatus());
        return "/input/list";
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "delete")
    public String deletaAll(String ids,Model model){
        for(String id:ids.split(",")){
            inputMapper.delete(id);
        }
        model.addAttribute("message","删除成功！");
        model.addAttribute("flag","luru");
        return "/input/list";
    }

}
