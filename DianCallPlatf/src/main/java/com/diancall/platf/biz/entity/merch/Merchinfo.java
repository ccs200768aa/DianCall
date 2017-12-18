package com.diancall.platf.biz.entity.merch;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Tiki
 * Date: 2017-12-18
 * Time: 15:56
 */
public class Merchinfo extends Model<Merchinfo> {
    @TableId(value = "merchid", type = IdType.AUTO)
    private int merchid;
    private String merchname;
    private String cnbusno;
    private String categoryid;
    private short type;
    private short seen;
    private short status;
    private short goodsradio;
    private String merchsign;
    private String licenceimg;
    private String province;
    private String city;
    private String busman;
    private String busqq;
    private String linkman;
    private String linkqq;
    private String linkwx;
    private String linktel;
    private String locaddress;
    private String remark;
    private long facetime;
    private long updatetime;
    private short isdelivery;
    private short isbook;
    private long createtime;

    public int getMerchid() {
        return merchid;
    }

    public void setMerchid(int merchid) {
        this.merchid = merchid;
    }

    public String getMerchname() {
        return merchname;
    }

    public void setMerchname(String merchname) {
        this.merchname = merchname;
    }

    public String getCnbusno() {
        return cnbusno;
    }

    public void setCnbusno(String cnbusno) {
        this.cnbusno = cnbusno;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short getSeen() {
        return seen;
    }

    public void setSeen(short seen) {
        this.seen = seen;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public short getGoodsradio() {
        return goodsradio;
    }

    public void setGoodsradio(short goodsradio) {
        this.goodsradio = goodsradio;
    }

    public String getMerchsign() {
        return merchsign;
    }

    public void setMerchsign(String merchsign) {
        this.merchsign = merchsign;
    }

    public String getLicenceimg() {
        return licenceimg;
    }

    public void setLicenceimg(String licenceimg) {
        this.licenceimg = licenceimg;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBusman() {
        return busman;
    }

    public void setBusman(String busman) {
        this.busman = busman;
    }

    public String getBusqq() {
        return busqq;
    }

    public void setBusqq(String busqq) {
        this.busqq = busqq;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkqq() {
        return linkqq;
    }

    public void setLinkqq(String linkqq) {
        this.linkqq = linkqq;
    }

    public String getLinkwx() {
        return linkwx;
    }

    public void setLinkwx(String linkwx) {
        this.linkwx = linkwx;
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }

    public String getLocaddress() {
        return locaddress;
    }

    public void setLocaddress(String locaddress) {
        this.locaddress = locaddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getFacetime() {
        return facetime;
    }

    public void setFacetime(long facetime) {
        this.facetime = facetime;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public short getIsdelivery() {
        return isdelivery;
    }

    public void setIsdelivery(short isdelivery) {
        this.isdelivery = isdelivery;
    }

    public short getIsbook() {
        return isbook;
    }

    public void setIsbook(short isbook) {
        this.isbook = isbook;
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
