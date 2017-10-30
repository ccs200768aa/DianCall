package com.diancall.platf.biz.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:商家版用户
 * User: Tiki
 * Date: 2017-10-30
 * Time: 20:23
 */
public class MerchUser extends Model<MerchUser> {

    @TableId(value = "merchuserid",type = IdType.AUTO)
    private int merchuserid;

    private int merchid;

    private String account;

    private String mobile;

    private String salt;

    private String password;

    private long createtime;

    public int getMerchuserid() {
        return merchuserid;
    }

    public void setMerchuserid(int merchuserid) {
        this.merchuserid = merchuserid;
    }

    public int getMerchid() {
        return merchid;
    }

    public void setMerchid(int merchid) {
        this.merchid = merchid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    @Override
    protected Serializable pkVal() {
        return this.merchid;
    }
}
