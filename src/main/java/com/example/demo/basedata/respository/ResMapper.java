package com.example.demo.basedata.respository;

import com.example.demo.basedata.goods.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResMapper {
    List<Res> findList();

    void save(Res ree);

    void delete(String id);

    void updata(Res res);

    Res get(String id);
}