package com.box.boxmanage.model;

import java.io.Serializable;
import java.util.Date;

public class BoxType implements Serializable {
    private String boxid;

    private String name;

    private String dime;

    private String pla;

    private String plan;

    private String phy;

    private String description;

    private String classid;

    private String detail1;

    private String detail2;

    private String detail3;

    private Double wmin;

    private Double hmin;

    private Double lmin;

    private Double wmax;

    private Double hmax;

    private Double lmax;

    private Integer unit;

    private String createby;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getBoxid() {
        return boxid;
    }

    public void setBoxid(String boxid) {
        this.boxid = boxid == null ? null : boxid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDime() {
        return dime;
    }

    public void setDime(String dime) {
        this.dime = dime == null ? null : dime.trim();
    }

    public String getPla() {
        return pla;
    }

    public void setPla(String pla) {
        this.pla = pla == null ? null : pla.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public String getPhy() {
        return phy;
    }

    public void setPhy(String phy) {
        this.phy = phy == null ? null : phy.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid == null ? null : classid.trim();
    }

    public String getDetail1() {
        return detail1;
    }

    public void setDetail1(String detail1) {
        this.detail1 = detail1 == null ? null : detail1.trim();
    }

    public String getDetail2() {
        return detail2;
    }

    public void setDetail2(String detail2) {
        this.detail2 = detail2 == null ? null : detail2.trim();
    }

    public String getDetail3() {
        return detail3;
    }

    public void setDetail3(String detail3) {
        this.detail3 = detail3 == null ? null : detail3.trim();
    }

    public Double getWmin() {
        return wmin;
    }

    public void setWmin(Double wmin) {
        this.wmin = wmin;
    }

    public Double getHmin() {
        return hmin;
    }

    public void setHmin(Double hmin) {
        this.hmin = hmin;
    }

    public Double getLmin() {
        return lmin;
    }

    public void setLmin(Double lmin) {
        this.lmin = lmin;
    }

    public Double getWmax() {
        return wmax;
    }

    public void setWmax(Double wmax) {
        this.wmax = wmax;
    }

    public Double getHmax() {
        return hmax;
    }

    public void setHmax(Double hmax) {
        this.hmax = hmax;
    }

    public Double getLmax() {
        return lmax;
    }

    public void setLmax(Double lmax) {
        this.lmax = lmax;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BoxType other = (BoxType) that;
        return (this.getBoxid() == null ? other.getBoxid() == null : this.getBoxid().equals(other.getBoxid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDime() == null ? other.getDime() == null : this.getDime().equals(other.getDime()))
            && (this.getPla() == null ? other.getPla() == null : this.getPla().equals(other.getPla()))
            && (this.getPlan() == null ? other.getPlan() == null : this.getPlan().equals(other.getPlan()))
            && (this.getPhy() == null ? other.getPhy() == null : this.getPhy().equals(other.getPhy()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getClassid() == null ? other.getClassid() == null : this.getClassid().equals(other.getClassid()))
            && (this.getDetail1() == null ? other.getDetail1() == null : this.getDetail1().equals(other.getDetail1()))
            && (this.getDetail2() == null ? other.getDetail2() == null : this.getDetail2().equals(other.getDetail2()))
            && (this.getDetail3() == null ? other.getDetail3() == null : this.getDetail3().equals(other.getDetail3()))
            && (this.getWmin() == null ? other.getWmin() == null : this.getWmin().equals(other.getWmin()))
            && (this.getHmin() == null ? other.getHmin() == null : this.getHmin().equals(other.getHmin()))
            && (this.getLmin() == null ? other.getLmin() == null : this.getLmin().equals(other.getLmin()))
            && (this.getWmax() == null ? other.getWmax() == null : this.getWmax().equals(other.getWmax()))
            && (this.getHmax() == null ? other.getHmax() == null : this.getHmax().equals(other.getHmax()))
            && (this.getLmax() == null ? other.getLmax() == null : this.getLmax().equals(other.getLmax()))
            && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
            && (this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoxid() == null) ? 0 : getBoxid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDime() == null) ? 0 : getDime().hashCode());
        result = prime * result + ((getPla() == null) ? 0 : getPla().hashCode());
        result = prime * result + ((getPlan() == null) ? 0 : getPlan().hashCode());
        result = prime * result + ((getPhy() == null) ? 0 : getPhy().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getClassid() == null) ? 0 : getClassid().hashCode());
        result = prime * result + ((getDetail1() == null) ? 0 : getDetail1().hashCode());
        result = prime * result + ((getDetail2() == null) ? 0 : getDetail2().hashCode());
        result = prime * result + ((getDetail3() == null) ? 0 : getDetail3().hashCode());
        result = prime * result + ((getWmin() == null) ? 0 : getWmin().hashCode());
        result = prime * result + ((getHmin() == null) ? 0 : getHmin().hashCode());
        result = prime * result + ((getLmin() == null) ? 0 : getLmin().hashCode());
        result = prime * result + ((getWmax() == null) ? 0 : getWmax().hashCode());
        result = prime * result + ((getHmax() == null) ? 0 : getHmax().hashCode());
        result = prime * result + ((getLmax() == null) ? 0 : getLmax().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getCreateby() == null) ? 0 : getCreateby().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", boxid=").append(boxid);
        sb.append(", name=").append(name);
        sb.append(", dime=").append(dime);
        sb.append(", pla=").append(pla);
        sb.append(", plan=").append(plan);
        sb.append(", phy=").append(phy);
        sb.append(", description=").append(description);
        sb.append(", classid=").append(classid);
        sb.append(", detail1=").append(detail1);
        sb.append(", detail2=").append(detail2);
        sb.append(", detail3=").append(detail3);
        sb.append(", mmin=").append(wmin);
        sb.append(", hmin=").append(hmin);
        sb.append(", lmin=").append(lmin);
        sb.append(", mmax=").append(wmax);
        sb.append(", hmax=").append(hmax);
        sb.append(", lmax=").append(lmax);
        sb.append(", unit=").append(unit);
        sb.append(", createby=").append(createby);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}