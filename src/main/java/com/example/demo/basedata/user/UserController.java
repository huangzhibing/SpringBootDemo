package com.example.demo.basedata.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("")
    public String user(){
        return "basedata/user/list";
    }
    @ResponseBody
    @RequestMapping("data")
    public List<User> data(){
        return userMapper.findList();
    }

    @RequestMapping("form")
    public String form(String name, Model model){
        User user=userMapper.getUserByName(name);
        model.addAttribute("user",user==null?new User():user);
        model.addAttribute("roles",userMapper.findRoles());
        return "basedata/user/form";
    }

    @RequestMapping("save")
    public String save(User user){
        if(user.getId()==null||"".equals(user.getId())) {
            userMapper.save(user);
        }else {
            userMapper.updata(user);
        }
        return "basedata/user/list";
    }

    @RequestMapping("delete")
    public String deleteAll(String ids){
        for(String id:ids.split(",")) {
            userMapper.delete(id);
        }
        return "basedata/user/list";
    }
}
