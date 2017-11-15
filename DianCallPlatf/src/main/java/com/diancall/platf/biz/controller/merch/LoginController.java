package com.diancall.platf.biz.controller.merch;

import com.alibaba.fastjson.JSONObject;
import com.diancall.core.constant.StringConstant;
import com.diancall.core.retcodes.ErrRetCode;
import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.core.util.ToolUtils;
import com.diancall.platf.biz.common.ReturnObject;
import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import com.diancall.platf.biz.shiro.ShiroHelper;
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

    @GetMapping(value = "/")
    @ResponseBody
    public Object index(Model model) {
        Merchuser mu = ShiroHelper.getUser();
        Set<String> roleSet = merchUserService.findRoleWithUserId(mu.getMerchuserid());
        if (ToolUtils.isEmpty(roleSet)) {
            model.addAttribute("retcode", new ErrRetCode(1, "用户权限不够"));
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
            return jsonObject.toJSONString();
        }
        return new ReturnObject<Merchuser>(new SuccRetCode(), mu);
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public Object go2Login() {
        if (ShiroHelper.getUser() != null) {
            return StringConstant.REDIRECT + "/";
        } else return new ReturnObject<Object>(new ErrRetCode(1, "未登陆"), null);
    }

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
        return StringConstant.REDIRECT + "/";
    }
}
