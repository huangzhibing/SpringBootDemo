package com.example.demo.input;

import com.example.demo.basedata.goods.Good;
import com.example.demo.basedata.respository.Res;
import lombok.Data;

@Data
public class Input {
    private String id;
    private Res res;
    private Good good;
    private String num;
    private String status;
    private String iotype;
}
