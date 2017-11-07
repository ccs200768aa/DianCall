package com.diancall.platf.biz.service.merch.impl;

import com.diancall.platf.biz.dao.merch.MerchUserMapper;
import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import com.diancall.platf.config.datasource.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-10-31
 * Time: 17:56
 */
@Service(value = "merchUserService")
public class MerchUserServiceImpl implements MerchUserServiceI {

    @Autowired
    private MerchUserMapper merchUserMapper;

    @Override
    @DataSource(name = "merchDataSource")
    public List<Merchuser> queryList() {
        return merchUserMapper.selectByMap(null);
    }
}
