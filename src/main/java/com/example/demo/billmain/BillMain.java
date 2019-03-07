package com.example.demo.billmain;

import com.example.demo.basedata.goods.Good;
import com.example.demo.basedata.respository.Res;
import lombok.Data;

@Data
public class BillMain {
    private String id;
    private Res res;
    private Good good;
    private Integer num;
    private String status;
}
