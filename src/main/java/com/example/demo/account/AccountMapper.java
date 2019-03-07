package com.example.demo.account;

import com.example.demo.basedata.goods.Good;
import com.example.demo.basedata.respository.Res;
import com.example.demo.billmain.BillMain;
import com.example.demo.input.Input;
import org.apache.ibatis.annotations.Mapper;

import javax.naming.spi.ResolveResult;
import java.util.List;

@Mapper
public interface AccountMapper {
    List <Account> findList();
    void save(Account account);
    void updateByBillMain(BillMain billMain);
    void updateByInPut(Input input);
    int check(BillMain billMain);
    List<Good> findGoodsByRes(String resId);
    void insertByBillMain(Input input);
    Integer preinsert (Input input);
    Account get(String id);
}
