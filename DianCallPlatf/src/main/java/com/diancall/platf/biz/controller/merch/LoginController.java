package com.diancall.platf.biz.controller.merch;

import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-14
 * Time: 16:50
 */
@RestController
public class LoginController {
    @Autowired
    private MerchUserServiceI merchUserService;


}
