package com.diancall.platf.config.db;

public enum DBTypeEnum {
    CUST_DATA_SOURCE("custDataSource"),MERCH_DATA_SOURCE("merchDataSource");

    private String value;

    DBTypeEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return  this.value;
    }
}
