package com.diancall.platf.biz.controller.merch;

import com.alibaba.fastjson.JSONObject;
import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.entity.merch.Permission;
import com.diancall.platf.biz.entity.merch.Role;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private RoleServiceI roleService;

    @Resource
    private PermissionServiceI permissionService;

    @GetMapping(value = "/querymerchuser")
    public String queryMerchUserList(Model model) {
        List<Merchuser> muList = merchUserService.queryList();
        model.addAttribute("retcode", new SuccRetCode());
        model.addAttribute("muList", muList);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/querymerchuserrole")
    public String queryMerchUserRoleList(Model model) {
        List<Role> murList = roleService.queryRoleList(null);
        model.addAttribute("retcode", new SuccRetCode());
        model.addAttribute("murList", murList);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/querymerchuserpermission")
    public String queryMerchUserPermissionList(Model model) {
        List<Permission> mupList = permissionService.queryPermissionList(null);
        model.addAttribute("retcode", new SuccRetCode());
        model.addAttribute("mupList", mupList);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(model);
        return jsonObject.toJSONString();
    }
}
