package com.diancall.platf.biz.controller.merch;

import com.diancall.core.retcodes.ErrRetCode;
import com.diancall.core.retcodes.RetCodes;
import com.diancall.core.retcodes.SuccRetCode;
import com.diancall.platf.biz.common.ReturnObject;
import com.diancall.platf.biz.dao.merch.MerchUserRoleMapper;
import com.diancall.platf.biz.dao.merch.PermissionMapper;
import com.diancall.platf.biz.dao.merch.RoleMapper;
import com.diancall.platf.biz.dao.merch.RolePermissionMapper;
import com.diancall.platf.biz.entity.merch.MerchuserRole;
import com.diancall.platf.biz.entity.merch.Permission;
import com.diancall.platf.biz.entity.merch.Role;
import com.diancall.platf.biz.entity.merch.RolePermission;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-16
 * Time: 14:25
 */
@RestController
@RequestMapping(value = "/merchrole")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MerchUserRoleMapper merchUserRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private MerchUserServiceI merchUserServiceI;

    @GetMapping(value = "/queryrolelist")
    @RequiresRoles(value = "admin")
    public Object queryRoleList(@RequestParam(required = false) Map<String, Object> map) {
        List<Role> roleList = roleMapper.selectByMap(map);
        return new ReturnObject<List<Role>>(new SuccRetCode(), roleList);
    }

    @PostMapping(value = "/updaterole")
    @RequiresRoles("admin")
    public RetCodes updateRole(@Valid Role role) {
        Role r = roleMapper.selectById(role.getRoleid());
        if (r != null) {
            roleMapper.updateById(role);
        } else {
            role.setRoleid(null);
            roleMapper.insert(role);
        }
        return new SuccRetCode();
    }

    @GetMapping(value = "/assignrole")
    @RequiresRoles("admin")
    public Object assignRole(@RequestParam("roleid") Integer roleid, @RequestParam("merchuserid") Integer merchuserid) {
        Role role = roleMapper.selectById(roleid);
        if (null == role) return new ErrRetCode(1, "角色信息不存在");
        MerchuserRole mur = new MerchuserRole();
        mur.setMerchuserid(merchuserid);
        mur.setRoleid(roleid);
        merchUserRoleMapper.insert(mur);
        return new SuccRetCode();
    }

    @GetMapping(value = "/assignpermission")
    @RequiresRoles("admin")
    public Object assignPermission(@RequestParam("roleid") Integer roleid, @RequestParam("permissionid") Integer permissionid) {
        Permission permission = permissionMapper.selectById(permissionid);
        if (null == permission) return new ErrRetCode(1, "权限信息不存在");
        RolePermission rp = new RolePermission();
        rp.setPermissionid(permissionid);
        rp.setRoleid(roleid);
        rolePermissionMapper.insert(rp);
        return new SuccRetCode();
    }

}
