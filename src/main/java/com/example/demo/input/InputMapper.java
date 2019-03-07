package com.example.demo.input;

import com.example.demo.billmain.BillMain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InputMapper {

    public Input get(String id);
    public List<Input> findList(Input input);
    public void insert(Input input);
    public void update(Input input);
    public void delete(String id);
}
