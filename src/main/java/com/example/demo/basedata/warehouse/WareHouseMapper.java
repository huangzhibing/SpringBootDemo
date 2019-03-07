package com.example.demo.basedata.warehouse;


import com.example.demo.basedata.billType.BillType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WareHouseMapper {
    List<WareHouse> findList();

    void save(WareHouse wareHouse);

    void delete(String id);

    void updata(WareHouse wareHouse);

    WareHouse get(String id);
}
