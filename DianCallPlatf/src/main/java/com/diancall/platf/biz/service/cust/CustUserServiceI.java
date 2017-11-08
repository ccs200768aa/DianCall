package com.diancall.platf.biz.service.cust;

import com.diancall.platf.biz.entity.cust.Custuser;

import java.io.Serializable;
import java.util.List;

public interface CustUserServiceI {

    List<Custuser> queryList();

    Custuser findById(Serializable id);
}
