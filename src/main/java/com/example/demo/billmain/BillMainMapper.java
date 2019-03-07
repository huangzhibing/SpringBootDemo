package com.example.demo.billmain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BillMainMapper {
    void save(BillMain billMain);
    void updata(BillMain billMain);
    void delete(String id);
    void updataFlag(@Param("status") String status,@Param("id") String id);
    BillMain get(String id);
    List<BillMain> findList(BillMain billMain);
}
