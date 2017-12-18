package com.diancall.platf.biz.controller.merch;

import com.alibaba.fastjson.JSONObject;
import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.platf.biz.common.ReturnObject;
import com.diancall.platf.biz.dao.merch.MerchUserMapper;
import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-09
 * Time: 17:56
 */
@RestController
@RequestMapping(value = "/merchuser")
public class MerchUserController {

    @Resource
    private MerchUserServiceI merchUserService;

    @Resource
    private MerchUserMapper merchUserMapper;

    @RequiresPermissions(value = "/merchuser/query")
    @GetMapping(value = "/query")
    @ResponseBody
    public ReturnObject queryMerchUserList(Model model) {
        List<Merchuser> muList = merchUserService.queryList();
        return new ReturnObject(new SuccRetCode(), muList);
    }

    @RequiresPermissions(value = "/merchuser/find")
    @GetMapping(value = "/find/{merchuserid}")
    @ResponseBody
    public ReturnObject<Merchuser> findMerchUserDetail(@PathVariable int merchuserid) {
        Merchuser mu = merchUserMapper.selectById(merchuserid);
        return new ReturnObject<Merchuser>(new SuccRetCode(), mu);
    }


}
