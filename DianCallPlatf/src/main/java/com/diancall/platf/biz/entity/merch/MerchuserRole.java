package com.diancall.platf.biz.entity.merch;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-10
 * Time: 10:26
 */
public class MerchuserRole extends Model<MerchuserRole> {
    @TableId(value = "merchuserroleid",type = IdType.AUTO)
    private Integer merchuserroleid;

    private Integer merchuserid;

    private Integer roleid;

    public Integer getMerchuserroleid() {
        return merchuserroleid;
    }

    public void setMerchuserroleid(Integer merchuserroleid) {
        this.merchuserroleid = merchuserroleid;
    }

    public Integer getMerchuserid() {
        return merchuserid;
    }

    public void setMerchuserid(Integer merchuserid) {
        this.merchuserid = merchuserid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    protected Serializable pkVal() {
        return this.merchuserroleid;
    }
}
