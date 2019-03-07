package com.example.demo.basedata.goods;

import com.example.demo.basedata.goods.Good;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Good> findList();

    void save(Good good);

    void delete(String id);

    void updata(Good good);

    Good get(String id);
}