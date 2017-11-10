package com.diancall.platf.biz.entity.merch;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-10
 * Time: 10:26
 */
public class RolePermission extends Model<RolePermission> {
    private Integer rolepermissionid;

    private Integer roleid;

    private Integer permissionid;

    public Integer getRolepermissionid() {
        return rolepermissionid;
    }

    public void setRolepermissionid(Integer rolepermissionid) {
        this.rolepermissionid = rolepermissionid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(Integer permissionid) {
        this.permissionid = permissionid;
    }

    @Override
    protected Serializable pkVal() {
        return this.rolepermissionid;
    }
}
