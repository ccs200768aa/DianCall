package com.diancall.platf.biz.service.cust.impl;

import com.diancall.platf.biz.dao.cust.CustUserMapper;
import com.diancall.platf.biz.entity.cust.Custuser;
import com.diancall.platf.biz.service.cust.CustUserServiceI;
import com.diancall.platf.config.datasource.DataSource;
import com.diancall.platf.config.db.DBContextHolder;
import com.diancall.platf.config.db.DBTypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-06
 * Time: 18:35
 */
@Service(value = "custUserService")
public class CustUserServiceImpl implements CustUserServiceI {

    @Resource
    private CustUserMapper custUserMapper;

    @Override
    @DataSource(name = "custDataSource")
    public List<Custuser> queryList() {
        String dbType = DBContextHolder.getDBType();
        return custUserMapper.selectByMap(null);
    }
}
