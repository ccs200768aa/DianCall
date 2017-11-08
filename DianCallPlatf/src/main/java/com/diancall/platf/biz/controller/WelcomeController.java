package com.diancall.platf.biz.controller;

import com.alibaba.fastjson.JSONObject;
import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.platf.biz.entity.cust.Custuser;
import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.service.cust.CustUserServiceI;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-10-30
 * Time: 18:09
 */
@RestController
@RequestMapping(value = "w")
public class WelcomeController {

    @Autowired
    private MerchUserServiceI merchUserService;

    @Autowired
    private CustUserServiceI custUserService;

    @GetMapping(path = "/q")
    public String welcome() {
        return "hello";
    }

    @GetMapping(path = "/querycustuser")
    public String querycustuser(Model model) {
        List<Custuser> muList = custUserService.queryList();
        model.addAttribute("retcode", new SuccRetCode());
        model.addAttribute("muList", muList);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
        return jsonObject.toJSONString();
    }

    @GetMapping(path = "/findcustuser/{id}")
    public String findcustuser(@PathVariable("id") int id, Model model) {
        Custuser custuser = custUserService.findById(id);
        model.addAttribute("retcode", new SuccRetCode());
        model.addAttribute("muList", custuser);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
        return jsonObject.toJSONString();
    }

    @GetMapping(path = "/querymerchuser")
    public String querymerchuser(Model model) {
        List<Merchuser> muList = merchUserService.queryList();
        model.addAttribute("retcode", new SuccRetCode());
        model.addAttribute("muList", muList);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
        return jsonObject.toJSONString();
    }
}
