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

    private String dxf;
    
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
    
    private Integer type;

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

	public String getDxf() {
		return dxf;
	}

	public void setDxf(String dxf) {
		this.dxf = dxf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boxid == null) ? 0 : boxid.hashCode());
		result = prime * result + ((classid == null) ? 0 : classid.hashCode());
		result = prime * result + ((createby == null) ? 0 : createby.hashCode());
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((detail1 == null) ? 0 : detail1.hashCode());
		result = prime * result + ((detail2 == null) ? 0 : detail2.hashCode());
		result = prime * result + ((detail3 == null) ? 0 : detail3.hashCode());
		result = prime * result + ((dime == null) ? 0 : dime.hashCode());
		result = prime * result + ((dxf == null) ? 0 : dxf.hashCode());
		result = prime * result + ((hmax == null) ? 0 : hmax.hashCode());
		result = prime * result + ((hmin == null) ? 0 : hmin.hashCode());
		result = prime * result + ((lmax == null) ? 0 : lmax.hashCode());
		result = prime * result + ((lmin == null) ? 0 : lmin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phy == null) ? 0 : phy.hashCode());
		result = prime * result + ((pla == null) ? 0 : pla.hashCode());
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((wmax == null) ? 0 : wmax.hashCode());
		result = prime * result + ((wmin == null) ? 0 : wmin.hashCode());
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
		BoxType other = (BoxType) obj;
		if (boxid == null) {
			if (other.boxid != null)
				return false;
		} else if (!boxid.equals(other.boxid))
			return false;
		if (classid == null) {
			if (other.classid != null)
				return false;
		} else if (!classid.equals(other.classid))
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (detail1 == null) {
			if (other.detail1 != null)
				return false;
		} else if (!detail1.equals(other.detail1))
			return false;
		if (detail2 == null) {
			if (other.detail2 != null)
				return false;
		} else if (!detail2.equals(other.detail2))
			return false;
		if (detail3 == null) {
			if (other.detail3 != null)
				return false;
		} else if (!detail3.equals(other.detail3))
			return false;
		if (dime == null) {
			if (other.dime != null)
				return false;
		} else if (!dime.equals(other.dime))
			return false;
		if (dxf == null) {
			if (other.dxf != null)
				return false;
		} else if (!dxf.equals(other.dxf))
			return false;
		if (hmax == null) {
			if (other.hmax != null)
				return false;
		} else if (!hmax.equals(other.hmax))
			return false;
		if (hmin == null) {
			if (other.hmin != null)
				return false;
		} else if (!hmin.equals(other.hmin))
			return false;
		if (lmax == null) {
			if (other.lmax != null)
				return false;
		} else if (!lmax.equals(other.lmax))
			return false;
		if (lmin == null) {
			if (other.lmin != null)
				return false;
		} else if (!lmin.equals(other.lmin))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phy == null) {
			if (other.phy != null)
				return false;
		} else if (!phy.equals(other.phy))
			return false;
		if (pla == null) {
			if (other.pla != null)
				return false;
		} else if (!pla.equals(other.pla))
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (wmax == null) {
			if (other.wmax != null)
				return false;
		} else if (!wmax.equals(other.wmax))
			return false;
		if (wmin == null) {
			if (other.wmin != null)
				return false;
		} else if (!wmin.equals(other.wmin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BoxType [boxid=" + boxid + ", name=" + name + ", dime=" + dime + ", pla=" + pla + ", plan=" + plan
				+ ", phy=" + phy + ", dxf=" + dxf + ", description=" + description + ", classid=" + classid
				+ ", detail1=" + detail1 + ", detail2=" + detail2 + ", detail3=" + detail3 + ", wmin=" + wmin
				+ ", hmin=" + hmin + ", lmin=" + lmin + ", wmax=" + wmax + ", hmax=" + hmax + ", lmax=" + lmax
				+ ", unit=" + unit + ", type=" + type + ", createby=" + createby + ", createtime=" + createtime + "]";
	}

	

   
}