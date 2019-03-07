package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountMapper accountMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String get(){
        return "account/list";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String post(){
        return "account/list";
    }

    @ResponseBody
    @RequestMapping(value = "data")
    public List<Account> data(){
        return accountMapper.findList();
    }
}
