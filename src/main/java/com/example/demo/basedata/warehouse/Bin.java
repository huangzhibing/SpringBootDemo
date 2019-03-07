package com.example.demo.basedata.warehouse;

import lombok.Data;

@Data
public class Bin {
    private WareHouse wareId;
    private String binId;
    private String binDesc;
    private String note;
}
