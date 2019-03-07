package com.example.demo.account;

import com.example.demo.basedata.goods.Good;
import com.example.demo.basedata.respository.Res;
import lombok.Data;

@Data
public class Account {
    private Res res;
    private Good good;
    private int num;
    private String id;
}
