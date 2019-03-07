package com.example.demo.basedata.billType;


import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BillTypeMapper{
    List<BillType> findList();

    void save(BillType billType);

    void delete(String id);

    void updata(BillType billType);

    BillType get(String id);
}
