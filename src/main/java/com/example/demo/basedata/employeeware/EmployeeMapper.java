package com.example.demo.basedata.employeeware;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> findList();

    void save(Employee employee);

    void delete(String id);

    void updata(Employee employee);

    Employee get(String id);
}
