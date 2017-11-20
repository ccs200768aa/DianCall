package com.diancall.platf.biz.controller.merch;

import com.alibaba.fastjson.JSONObject;
import com.diancall.core.constant.StringConstant;
import com.diancall.core.retcodes.ErrRetCode;
import com.diancall.core.retcodes.RetCodeEnum;
import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.core.util.ToolUtils;
import com.diancall.platf.biz.common.ReturnObject;
import com.diancall.platf.biz.common.log.LogManager;
import com.diancall.platf.biz.common.log.LogTaskFactory;
import com.diancall.platf.biz.common.shiro.HttpHelper;
import com.diancall.platf.biz.common.shiro.ShiroHelper;
import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-14
 * Time: 16:50
 */
@Controller
public class LoginController {
    @Autowired
    private MerchUserServiceI merchUserService;

    @ApiOperation("鉴别登陆")
    @GetMapping(value = "/")
    @ResponseBody
    public Object index(Model model) {
        Merchuser mu = ShiroHelper.getUser();
        if (null == mu) return new ReturnObject<>(new ErrRetCode(RetCodeEnum.MERCH_USER_UNLOGIN));
        Set<String> roleSet = merchUserService.findRoleWithUserId(mu.getMerchuserid());
        if (ToolUtils.isEmpty(roleSet)) {
            model.addAttribute("retcode", new ErrRetCode(RetCodeEnum.MERCH_USER_ROLE_NOT_EXIST));
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
            return jsonObject.toJSONString();
        }
        return new ReturnObject<Merchuser>(new SuccRetCode(), mu);
    }

    @ApiOperation("跳转登陆")
    @GetMapping(value = "/login")
    @ResponseBody
    public Object go2Login() {
        if (ShiroHelper.getUser() != null) {
            return StringConstant.REDIRECT + "/";
        } else return new ReturnObject<>(new ErrRetCode(RetCodeEnum.MERCH_USER_UNLOGIN));
    }

    @ApiOperation("登陆校验")
    @PostMapping(value = "/login")
    public String loginVali(HttpServletRequest req, HttpServletRequest response) {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        Subject currentUser = ShiroHelper.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password.toCharArray());

        currentUser.login(token);

        Merchuser mu = ShiroHelper.getUser();
        response.getSession().setAttribute("merchuser", mu);
        response.getSession().setAttribute("merchusername", mu.getAccount());

        LogManager.me().excuteLog(LogTaskFactory.loginLog(mu.getMerchuserid(), HttpHelper.getIp()));
        return StringConstant.REDIRECT + "/";
    }

    @ApiOperation("注销")
    @GetMapping(value = "/logout")
    @ResponseBody
    public Object logOut() {
        if (ToolUtils.isEmpty(ShiroHelper.getUser())) {
            return new ReturnObject<>(new ErrRetCode(RetCodeEnum.MERCH_USER_UNLOGIN));
        }
        LogManager.me().excuteLog(LogTaskFactory.logoutLog(ShiroHelper.getUser().getMerchuserid(), HttpHelper.getIp()));
        Subject subject = ShiroHelper.getSubject();
        subject.logout();
        return new ReturnObject<>(new SuccRetCode());
    }

}
