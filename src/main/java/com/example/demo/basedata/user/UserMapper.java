package com.example.demo.basedata.user;

import com.example.demo.basedata.goods.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByName(String name);
    List<User> findList();

    void save(User user);

    void delete(String id);

    void updata(User user);

    List<String> findRoles();

    void updataRole(User user);
}
