package com.box.shopping.model;

import java.io.Serializable;
import java.util.Date;

public class LayoutDetail implements Serializable {
    private String id;

    private String boxId;

    private Double boxLength;

    private Double boxWidth;

    private Double boxHighth;

    private Integer boxUnit;

    private Double paperLength;

    private Double paperWidth;

    private Integer paperUnit;

    private String paperXId;

    private Integer xnumber;

    private Integer ynumber;

    private String pictureAddress;

    private String dxfAddress;

    private String createby;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId == null ? null : boxId.trim();
    }

    public Double getBoxLength() {
        return boxLength;
    }

    public void setBoxLength(Double boxLength) {
        this.boxLength = boxLength;
    }

    public Double getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(Double boxWidth) {
        this.boxWidth = boxWidth;
    }

    public Double getBoxHighth() {
        return boxHighth;
    }

    public void setBoxHighth(Double boxHighth) {
        this.boxHighth = boxHighth;
    }

    public Integer getBoxUnit() {
        return boxUnit;
    }

    public void setBoxUnit(Integer boxUnit) {
        this.boxUnit = boxUnit;
    }

    public Double getPaperLength() {
        return paperLength;
    }

    public void setPaperLength(Double paperLength) {
        this.paperLength = paperLength;
    }

    public Double getPaperWidth() {
        return paperWidth;
    }

    public void setPaperWidth(Double paperWidth) {
        this.paperWidth = paperWidth;
    }

    public Integer getPaperUnit() {
        return paperUnit;
    }

    public void setPaperUnit(Integer paperUnit) {
        this.paperUnit = paperUnit;
    }

    public String getPaperXId() {
        return paperXId;
    }

    public void setPaperXId(String paperXId) {
        this.paperXId = paperXId == null ? null : paperXId.trim();
    }

    public Integer getXnumber() {
        return xnumber;
    }

    public void setXnumber(Integer xnumber) {
        this.xnumber = xnumber;
    }

    public Integer getYnumber() {
        return ynumber;
    }

    public void setYnumber(Integer ynumber) {
        this.ynumber = ynumber;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress == null ? null : pictureAddress.trim();
    }

    public String getDxfAddress() {
        return dxfAddress;
    }

    public void setDxfAddress(String dxfAddress) {
        this.dxfAddress = dxfAddress == null ? null : dxfAddress.trim();
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
        LayoutDetail other = (LayoutDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBoxId() == null ? other.getBoxId() == null : this.getBoxId().equals(other.getBoxId()))
            && (this.getBoxLength() == null ? other.getBoxLength() == null : this.getBoxLength().equals(other.getBoxLength()))
            && (this.getBoxWidth() == null ? other.getBoxWidth() == null : this.getBoxWidth().equals(other.getBoxWidth()))
            && (this.getBoxHighth() == null ? other.getBoxHighth() == null : this.getBoxHighth().equals(other.getBoxHighth()))
            && (this.getBoxUnit() == null ? other.getBoxUnit() == null : this.getBoxUnit().equals(other.getBoxUnit()))
            && (this.getPaperLength() == null ? other.getPaperLength() == null : this.getPaperLength().equals(other.getPaperLength()))
            && (this.getPaperWidth() == null ? other.getPaperWidth() == null : this.getPaperWidth().equals(other.getPaperWidth()))
            && (this.getPaperUnit() == null ? other.getPaperUnit() == null : this.getPaperUnit().equals(other.getPaperUnit()))
            && (this.getPaperXId() == null ? other.getPaperXId() == null : this.getPaperXId().equals(other.getPaperXId()))
            && (this.getXnumber() == null ? other.getXnumber() == null : this.getXnumber().equals(other.getXnumber()))
            && (this.getYnumber() == null ? other.getYnumber() == null : this.getYnumber().equals(other.getYnumber()))
            && (this.getPictureAddress() == null ? other.getPictureAddress() == null : this.getPictureAddress().equals(other.getPictureAddress()))
            && (this.getDxfAddress() == null ? other.getDxfAddress() == null : this.getDxfAddress().equals(other.getDxfAddress()))
            && (this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBoxId() == null) ? 0 : getBoxId().hashCode());
        result = prime * result + ((getBoxLength() == null) ? 0 : getBoxLength().hashCode());
        result = prime * result + ((getBoxWidth() == null) ? 0 : getBoxWidth().hashCode());
        result = prime * result + ((getBoxHighth() == null) ? 0 : getBoxHighth().hashCode());
        result = prime * result + ((getBoxUnit() == null) ? 0 : getBoxUnit().hashCode());
        result = prime * result + ((getPaperLength() == null) ? 0 : getPaperLength().hashCode());
        result = prime * result + ((getPaperWidth() == null) ? 0 : getPaperWidth().hashCode());
        result = prime * result + ((getPaperUnit() == null) ? 0 : getPaperUnit().hashCode());
        result = prime * result + ((getPaperXId() == null) ? 0 : getPaperXId().hashCode());
        result = prime * result + ((getXnumber() == null) ? 0 : getXnumber().hashCode());
        result = prime * result + ((getYnumber() == null) ? 0 : getYnumber().hashCode());
        result = prime * result + ((getPictureAddress() == null) ? 0 : getPictureAddress().hashCode());
        result = prime * result + ((getDxfAddress() == null) ? 0 : getDxfAddress().hashCode());
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
        sb.append(", id=").append(id);
        sb.append(", boxId=").append(boxId);
        sb.append(", boxLength=").append(boxLength);
        sb.append(", boxWidth=").append(boxWidth);
        sb.append(", boxHighth=").append(boxHighth);
        sb.append(", boxUnit=").append(boxUnit);
        sb.append(", paperLength=").append(paperLength);
        sb.append(", paperWidth=").append(paperWidth);
        sb.append(", paperUnit=").append(paperUnit);
        sb.append(", paperXId=").append(paperXId);
        sb.append(", xnumber=").append(xnumber);
        sb.append(", ynumber=").append(ynumber);
        sb.append(", pictureAddress=").append(pictureAddress);
        sb.append(", dxfAddress=").append(dxfAddress);
        sb.append(", createby=").append(createby);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}