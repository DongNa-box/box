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
    
    private String utilizationRate;

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

    
    public String getUtilizationRate() {
		return utilizationRate;
	}

	public void setUtilizationRate(String utilizationRate) {
		this.utilizationRate = utilizationRate;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boxHighth == null) ? 0 : boxHighth.hashCode());
		result = prime * result + ((boxId == null) ? 0 : boxId.hashCode());
		result = prime * result + ((boxLength == null) ? 0 : boxLength.hashCode());
		result = prime * result + ((boxUnit == null) ? 0 : boxUnit.hashCode());
		result = prime * result + ((boxWidth == null) ? 0 : boxWidth.hashCode());
		result = prime * result + ((createby == null) ? 0 : createby.hashCode());
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((dxfAddress == null) ? 0 : dxfAddress.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paperLength == null) ? 0 : paperLength.hashCode());
		result = prime * result + ((paperUnit == null) ? 0 : paperUnit.hashCode());
		result = prime * result + ((paperWidth == null) ? 0 : paperWidth.hashCode());
		result = prime * result + ((paperXId == null) ? 0 : paperXId.hashCode());
		result = prime * result + ((pictureAddress == null) ? 0 : pictureAddress.hashCode());
		result = prime * result + ((utilizationRate == null) ? 0 : utilizationRate.hashCode());
		result = prime * result + ((xnumber == null) ? 0 : xnumber.hashCode());
		result = prime * result + ((ynumber == null) ? 0 : ynumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LayoutDetail other = (LayoutDetail) obj;
		if (boxHighth == null) {
			if (other.boxHighth != null)
				return false;
		} else if (!boxHighth.equals(other.boxHighth))
			return false;
		if (boxId == null) {
			if (other.boxId != null)
				return false;
		} else if (!boxId.equals(other.boxId))
			return false;
		if (boxLength == null) {
			if (other.boxLength != null)
				return false;
		} else if (!boxLength.equals(other.boxLength))
			return false;
		if (boxUnit == null) {
			if (other.boxUnit != null)
				return false;
		} else if (!boxUnit.equals(other.boxUnit))
			return false;
		if (boxWidth == null) {
			if (other.boxWidth != null)
				return false;
		} else if (!boxWidth.equals(other.boxWidth))
			return false;
		if (createby == null) {
			if (other.createby != null)
				return false;
		} else if (!createby.equals(other.createby))
			return false;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		} else if (!createtime.equals(other.createtime))
			return false;
		if (dxfAddress == null) {
			if (other.dxfAddress != null)
				return false;
		} else if (!dxfAddress.equals(other.dxfAddress))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paperLength == null) {
			if (other.paperLength != null)
				return false;
		} else if (!paperLength.equals(other.paperLength))
			return false;
		if (paperUnit == null) {
			if (other.paperUnit != null)
				return false;
		} else if (!paperUnit.equals(other.paperUnit))
			return false;
		if (paperWidth == null) {
			if (other.paperWidth != null)
				return false;
		} else if (!paperWidth.equals(other.paperWidth))
			return false;
		if (paperXId == null) {
			if (other.paperXId != null)
				return false;
		} else if (!paperXId.equals(other.paperXId))
			return false;
		if (pictureAddress == null) {
			if (other.pictureAddress != null)
				return false;
		} else if (!pictureAddress.equals(other.pictureAddress))
			return false;
		if (utilizationRate == null) {
			if (other.utilizationRate != null)
				return false;
		} else if (!utilizationRate.equals(other.utilizationRate))
			return false;
		if (xnumber == null) {
			if (other.xnumber != null)
				return false;
		} else if (!xnumber.equals(other.xnumber))
			return false;
		if (ynumber == null) {
			if (other.ynumber != null)
				return false;
		} else if (!ynumber.equals(other.ynumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LayoutDetail [id=" + id + ", boxId=" + boxId + ", boxLength=" + boxLength + ", boxWidth=" + boxWidth
				+ ", boxHighth=" + boxHighth + ", boxUnit=" + boxUnit + ", paperLength=" + paperLength + ", paperWidth="
				+ paperWidth + ", paperUnit=" + paperUnit + ", paperXId=" + paperXId + ", xnumber=" + xnumber
				+ ", ynumber=" + ynumber + ", utilizationRate=" + utilizationRate + ", pictureAddress=" + pictureAddress
				+ ", dxfAddress=" + dxfAddress + ", createby=" + createby + ", createtime=" + createtime + "]";
	}

   
}