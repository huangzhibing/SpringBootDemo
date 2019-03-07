//package com.example.demo.basedata.employeeware;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//@Controller
//@RequestMapping("employeeware")
//public class EmployeewareController {
//    @Autowired
//    EmployeewareMapper employeewareMapper;
//
//    @RequestMapping("")
//    public String employee(){
//        return "basedata/employeeware/list";
//    }
//    @ResponseBody
//    @RequestMapping("data")
//    public List<Employeeware> data(){
//        return employeewareMapper.findList();
//    }
//
//    @RequestMapping("form")
//    public String form(String wareId, Model model){
//        Employeeware employeeware = employeewareMapper.get(wareId);
//        model.addAttribute("employeeware",employeeware==null?new Employeeware():employeeware);
//        return "basedata/employeeware/form";
//    }
//
//    @RequestMapping("save")
//    public String save(Employeeware employeeware){
//        if(employeeware.getWareId()==null) {
//            employeewareMapper.save(employeeware);
//        }else {
//            employeewareMapper.updata(employeeware);
//        }
//        return "basedata/employeeware/list";
//    }
//
//    @RequestMapping("delete")
//    public String deleteAll(String ids){
//        for(String wareId:ids.split(",")) {
//            employeewareMapper.delete(wareId);
//        }
//        return "basedata/employeeware/list";
//    }
//}
