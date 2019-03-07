package com.example.demo.basedata.billType;

import lombok.Data;

@Data
public class BillType {
    private String id;
    private String ioType;
    private String ioFlag;
    private String ioDesc;
    private String currQty;
    private String inQty;
    private String outQty;
    private String wareId;
    private String wareName;
}
