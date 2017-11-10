package com.diancall.platf.biz.entity.merch;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-09
 * Time: 17:33
 */
public class Role extends Model<Role> {

    private Integer roleid;

    private String name;

    private Integer rolelevel;

    private String description;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(Integer rolelevel) {
        this.rolelevel = rolelevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    protected Serializable pkVal() {
        return this.roleid;
    }
}
