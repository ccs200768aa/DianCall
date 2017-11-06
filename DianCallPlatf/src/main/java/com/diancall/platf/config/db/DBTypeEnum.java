package com.diancall.platf.config.db;

public enum DBTypeEnum {
    CUST_DATA_SOURCE("custdatasource"),MERCH_DATA_SOURCE("merchdatasource");

    private String value;

    DBTypeEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return  this.value;
    }
}
