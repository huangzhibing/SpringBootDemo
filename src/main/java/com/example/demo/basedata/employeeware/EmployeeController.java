package com.example.demo.basedata.employeeware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("employeeware")
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @RequestMapping("")
    public String employee(){
        return "basedata/employeeware/list";
    }
    @ResponseBody
    @RequestMapping("data")
    public List<Employee> data(){
        return employeeMapper.findList();
    }

    @RequestMapping("form")
    public String form(String id, Model model){
        Employee employee = employeeMapper.get(id);
        model.addAttribute("employee",employee==null?new Employee():employee);
        return "basedata/employeeware/form";
    }

    @RequestMapping("save")
    public String save(Employee employee){
        if(employee.getId()==null) {
            employeeMapper.save(employee);
        }else {
            employeeMapper.updata(employee);
        }
        return "basedata/employeeware/list";
    }

    @RequestMapping("delete")
    public String deleteAll(String ids){
        for(String id:ids.split(",")) {
            employeeMapper.delete(id);
        }
        return "basedata/employeeware/list";
    }
}
