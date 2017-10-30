package com.diancall.platf.biz.controller;

import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.platf.biz.dao.MerchUserMapper;
import com.diancall.platf.biz.entity.MerchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private MerchUserMapper merchUserMapper;

    @GetMapping(path = "/q")
    public String welcome() {
        return "hello";
    }

    @GetMapping(path = "/list")
    public String list(Model model){
        List<MerchUser> muList = merchUserMapper.selectByMap(null);
        model.addAttribute("retcode",new SuccRetCode());
        model.addAttribute("muList",muList);
        return model.toString();
    }
}
