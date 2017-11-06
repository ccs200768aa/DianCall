package com.diancall.platf.biz.entity.cust;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-11-02
 * Time: 14:56
 */
public class Custuser extends Model<Custuser> {
    @TableId(value = "custuserid", type = IdType.AUTO)
    private int custuserid;

    private String account;

    private String mobile;

    private String salt;

    private String password;

    private long createtime;

    public int getCustuserid() {
        return custuserid;
    }

    public void setCustuserid(int custuserid) {
        this.custuserid = custuserid;
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
        return this.custuserid;
    }
}
