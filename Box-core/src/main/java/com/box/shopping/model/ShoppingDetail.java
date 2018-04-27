package com.box.shopping.model;

import java.io.Serializable;
import java.util.Date;

public class ShoppingDetail implements Serializable {
    private String shoppingId;

    private String userId;

    private String boxId;

    private String layoutId;

    private String printPaperId;

    private String paperGramsId;

    private String printColorId;

    private String pantoneId;

    private String surfaceTreatmentId;

    private Integer isBronzing;

    private Double bronzingLength;

    private Double bronzingWidth;

    private Integer bronzingUnit;

    private Integer isConvex;

    private Double convexLength;

    private Double convexWidth;

    private Integer convexUnit;

    private Integer isUv;

    private Double uvLength;

    private Double uvWidth;

    private Integer uvUnit;

    private Integer isPvc;

    private Double pvcLength;

    private Double pvcWidth;

    private Integer pvcUnit;

    private String receiveAreaId;

    private Double paperPrice;

    private Double colorPrice;

    private Double surfacePrice;

    private Double bronzingPrice;

    private Double convexPrice;

    private Integer printNumber;

    private Double uvPrice;

    private Double pvcPrice;

    private Double taxPrice;

    private Double managementPrice;

    private Double transportPrice;

    private Double totalPrice;

    private Double unitPrice;
    
    private String excelAddress;

	private String createby;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public String getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(String shoppingId) {
        this.shoppingId = shoppingId == null ? null : shoppingId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId == null ? null : boxId.trim();
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId == null ? null : layoutId.trim();
    }

    public String getPrintPaperId() {
        return printPaperId;
    }

    public void setPrintPaperId(String printPaperId) {
        this.printPaperId = printPaperId == null ? null : printPaperId.trim();
    }

    public String getPaperGramsId() {
        return paperGramsId;
    }

    public void setPaperGramsId(String paperGramsId) {
        this.paperGramsId = paperGramsId == null ? null : paperGramsId.trim();
    }

    public String getPrintColorId() {
        return printColorId;
    }

    public void setPrintColorId(String printColorId) {
        this.printColorId = printColorId == null ? null : printColorId.trim();
    }

    public String getPantoneId() {
        return pantoneId;
    }

    public void setPantoneId(String pantoneId) {
        this.pantoneId = pantoneId == null ? null : pantoneId.trim();
    }

    public String getSurfaceTreatmentId() {
        return surfaceTreatmentId;
    }

    public void setSurfaceTreatmentId(String surfaceTreatmentId) {
        this.surfaceTreatmentId = surfaceTreatmentId == null ? null : surfaceTreatmentId.trim();
    }
    
    public String getExcelAddress() {
  		return excelAddress;
  	}

  	public void setExcelAddress(String excelAddress) {
  		this.excelAddress = excelAddress  == null ? null : excelAddress.trim();
  	}

    public Integer getIsBronzing() {
        return isBronzing;
    }

    public void setIsBronzing(Integer isBronzing) {
        this.isBronzing = isBronzing;
    }

    public Double getBronzingLength() {
        return bronzingLength;
    }

    public void setBronzingLength(Double bronzingLength) {
        this.bronzingLength = bronzingLength;
    }

    public Double getBronzingWidth() {
        return bronzingWidth;
    }

    public void setBronzingWidth(Double bronzingWidth) {
        this.bronzingWidth = bronzingWidth;
    }

    public Integer getBronzingUnit() {
        return bronzingUnit;
    }

    public void setBronzingUnit(Integer bronzingUnit) {
        this.bronzingUnit = bronzingUnit;
    }

    public Integer getIsConvex() {
        return isConvex;
    }

    public void setIsConvex(Integer isConvex) {
        this.isConvex = isConvex;
    }

    public Double getConvexLength() {
        return convexLength;
    }

    public void setConvexLength(Double convexLength) {
        this.convexLength = convexLength;
    }

    public Double getConvexWidth() {
        return convexWidth;
    }

    public void setConvexWidth(Double convexWidth) {
        this.convexWidth = convexWidth;
    }

    public Integer getConvexUnit() {
        return convexUnit;
    }

    public void setConvexUnit(Integer convexUnit) {
        this.convexUnit = convexUnit;
    }

    public Integer getIsUv() {
        return isUv;
    }

    public void setIsUv(Integer isUv) {
        this.isUv = isUv;
    }

    public Double getUvLength() {
        return uvLength;
    }

    public void setUvLength(Double uvLength) {
        this.uvLength = uvLength;
    }

    public Double getUvWidth() {
        return uvWidth;
    }

    public void setUvWidth(Double uvWidth) {
        this.uvWidth = uvWidth;
    }

    public Integer getUvUnit() {
        return uvUnit;
    }

    public void setUvUnit(Integer uvUnit) {
        this.uvUnit = uvUnit;
    }

    public Integer getIsPvc() {
        return isPvc;
    }

    public void setIsPvc(Integer isPvc) {
        this.isPvc = isPvc;
    }

    public Double getPvcLength() {
        return pvcLength;
    }

    public void setPvcLength(Double pvcLength) {
        this.pvcLength = pvcLength;
    }

    public Double getPvcWidth() {
        return pvcWidth;
    }

    public void setPvcWidth(Double pvcWidth) {
        this.pvcWidth = pvcWidth;
    }

    public Integer getPvcUnit() {
        return pvcUnit;
    }

    public void setPvcUnit(Integer pvcUnit) {
        this.pvcUnit = pvcUnit;
    }

    public String getReceiveAreaId() {
        return receiveAreaId;
    }

    public void setReceiveAreaId(String receiveAreaId) {
        this.receiveAreaId = receiveAreaId == null ? null : receiveAreaId.trim();
    }

    public Double getPaperPrice() {
        return paperPrice;
    }

    public void setPaperPrice(Double paperPrice) {
        this.paperPrice = paperPrice;
    }

    public Double getColorPrice() {
        return colorPrice;
    }

    public void setColorPrice(Double colorPrice) {
        this.colorPrice = colorPrice;
    }

    public Double getSurfacePrice() {
        return surfacePrice;
    }

    public void setSurfacePrice(Double surfacePrice) {
        this.surfacePrice = surfacePrice;
    }

    public Double getBronzingPrice() {
        return bronzingPrice;
    }

    public void setBronzingPrice(Double bronzingPrice) {
        this.bronzingPrice = bronzingPrice;
    }

    public Double getConvexPrice() {
        return convexPrice;
    }

    public void setConvexPrice(Double convexPrice) {
        this.convexPrice = convexPrice;
    }

    public Integer getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(Integer printNumber) {
        this.printNumber = printNumber;
    }

    public Double getUvPrice() {
        return uvPrice;
    }

    public void setUvPrice(Double uvPrice) {
        this.uvPrice = uvPrice;
    }

    public Double getPvcPrice() {
        return pvcPrice;
    }

    public void setPvcPrice(Double pvcPrice) {
        this.pvcPrice = pvcPrice;
    }

    public Double getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    public Double getManagementPrice() {
        return managementPrice;
    }

    public void setManagementPrice(Double managementPrice) {
        this.managementPrice = managementPrice;
    }

    public Double getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(Double transportPrice) {
        this.transportPrice = transportPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingDetail other = (ShoppingDetail) obj;
		if (boxId == null) {
			if (other.boxId != null)
				return false;
		} else if (!boxId.equals(other.boxId))
			return false;
		if (bronzingLength == null) {
			if (other.bronzingLength != null)
				return false;
		} else if (!bronzingLength.equals(other.bronzingLength))
			return false;
		if (bronzingPrice == null) {
			if (other.bronzingPrice != null)
				return false;
		} else if (!bronzingPrice.equals(other.bronzingPrice))
			return false;
		if (bronzingUnit == null) {
			if (other.bronzingUnit != null)
				return false;
		} else if (!bronzingUnit.equals(other.bronzingUnit))
			return false;
		if (bronzingWidth == null) {
			if (other.bronzingWidth != null)
				return false;
		} else if (!bronzingWidth.equals(other.bronzingWidth))
			return false;
		if (colorPrice == null) {
			if (other.colorPrice != null)
				return false;
		} else if (!colorPrice.equals(other.colorPrice))
			return false;
		if (convexLength == null) {
			if (other.convexLength != null)
				return false;
		} else if (!convexLength.equals(other.convexLength))
			return false;
		if (convexPrice == null) {
			if (other.convexPrice != null)
				return false;
		} else if (!convexPrice.equals(other.convexPrice))
			return false;
		if (convexUnit == null) {
			if (other.convexUnit != null)
				return false;
		} else if (!convexUnit.equals(other.convexUnit))
			return false;
		if (convexWidth == null) {
			if (other.convexWidth != null)
				return false;
		} else if (!convexWidth.equals(other.convexWidth))
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
		if (excelAddress == null) {
			if (other.excelAddress != null)
				return false;
		} else if (!excelAddress.equals(other.excelAddress))
			return false;
		if (isBronzing == null) {
			if (other.isBronzing != null)
				return false;
		} else if (!isBronzing.equals(other.isBronzing))
			return false;
		if (isConvex == null) {
			if (other.isConvex != null)
				return false;
		} else if (!isConvex.equals(other.isConvex))
			return false;
		if (isPvc == null) {
			if (other.isPvc != null)
				return false;
		} else if (!isPvc.equals(other.isPvc))
			return false;
		if (isUv == null) {
			if (other.isUv != null)
				return false;
		} else if (!isUv.equals(other.isUv))
			return false;
		if (layoutId == null) {
			if (other.layoutId != null)
				return false;
		} else if (!layoutId.equals(other.layoutId))
			return false;
		if (managementPrice == null) {
			if (other.managementPrice != null)
				return false;
		} else if (!managementPrice.equals(other.managementPrice))
			return false;
		if (pantoneId == null) {
			if (other.pantoneId != null)
				return false;
		} else if (!pantoneId.equals(other.pantoneId))
			return false;
		if (paperGramsId == null) {
			if (other.paperGramsId != null)
				return false;
		} else if (!paperGramsId.equals(other.paperGramsId))
			return false;
		if (paperPrice == null) {
			if (other.paperPrice != null)
				return false;
		} else if (!paperPrice.equals(other.paperPrice))
			return false;
		if (printColorId == null) {
			if (other.printColorId != null)
				return false;
		} else if (!printColorId.equals(other.printColorId))
			return false;
		if (printNumber == null) {
			if (other.printNumber != null)
				return false;
		} else if (!printNumber.equals(other.printNumber))
			return false;
		if (printPaperId == null) {
			if (other.printPaperId != null)
				return false;
		} else if (!printPaperId.equals(other.printPaperId))
			return false;
		if (pvcLength == null) {
			if (other.pvcLength != null)
				return false;
		} else if (!pvcLength.equals(other.pvcLength))
			return false;
		if (pvcPrice == null) {
			if (other.pvcPrice != null)
				return false;
		} else if (!pvcPrice.equals(other.pvcPrice))
			return false;
		if (pvcUnit == null) {
			if (other.pvcUnit != null)
				return false;
		} else if (!pvcUnit.equals(other.pvcUnit))
			return false;
		if (pvcWidth == null) {
			if (other.pvcWidth != null)
				return false;
		} else if (!pvcWidth.equals(other.pvcWidth))
			return false;
		if (receiveAreaId == null) {
			if (other.receiveAreaId != null)
				return false;
		} else if (!receiveAreaId.equals(other.receiveAreaId))
			return false;
		if (shoppingId == null) {
			if (other.shoppingId != null)
				return false;
		} else if (!shoppingId.equals(other.shoppingId))
			return false;
		if (surfacePrice == null) {
			if (other.surfacePrice != null)
				return false;
		} else if (!surfacePrice.equals(other.surfacePrice))
			return false;
		if (surfaceTreatmentId == null) {
			if (other.surfaceTreatmentId != null)
				return false;
		} else if (!surfaceTreatmentId.equals(other.surfaceTreatmentId))
			return false;
		if (taxPrice == null) {
			if (other.taxPrice != null)
				return false;
		} else if (!taxPrice.equals(other.taxPrice))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		if (transportPrice == null) {
			if (other.transportPrice != null)
				return false;
		} else if (!transportPrice.equals(other.transportPrice))
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (uvLength == null) {
			if (other.uvLength != null)
				return false;
		} else if (!uvLength.equals(other.uvLength))
			return false;
		if (uvPrice == null) {
			if (other.uvPrice != null)
				return false;
		} else if (!uvPrice.equals(other.uvPrice))
			return false;
		if (uvUnit == null) {
			if (other.uvUnit != null)
				return false;
		} else if (!uvUnit.equals(other.uvUnit))
			return false;
		if (uvWidth == null) {
			if (other.uvWidth != null)
				return false;
		} else if (!uvWidth.equals(other.uvWidth))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boxId == null) ? 0 : boxId.hashCode());
		result = prime * result + ((bronzingLength == null) ? 0 : bronzingLength.hashCode());
		result = prime * result + ((bronzingPrice == null) ? 0 : bronzingPrice.hashCode());
		result = prime * result + ((bronzingUnit == null) ? 0 : bronzingUnit.hashCode());
		result = prime * result + ((bronzingWidth == null) ? 0 : bronzingWidth.hashCode());
		result = prime * result + ((colorPrice == null) ? 0 : colorPrice.hashCode());
		result = prime * result + ((convexLength == null) ? 0 : convexLength.hashCode());
		result = prime * result + ((convexPrice == null) ? 0 : convexPrice.hashCode());
		result = prime * result + ((convexUnit == null) ? 0 : convexUnit.hashCode());
		result = prime * result + ((convexWidth == null) ? 0 : convexWidth.hashCode());
		result = prime * result + ((createby == null) ? 0 : createby.hashCode());
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((excelAddress == null) ? 0 : excelAddress.hashCode());
		result = prime * result + ((isBronzing == null) ? 0 : isBronzing.hashCode());
		result = prime * result + ((isConvex == null) ? 0 : isConvex.hashCode());
		result = prime * result + ((isPvc == null) ? 0 : isPvc.hashCode());
		result = prime * result + ((isUv == null) ? 0 : isUv.hashCode());
		result = prime * result + ((layoutId == null) ? 0 : layoutId.hashCode());
		result = prime * result + ((managementPrice == null) ? 0 : managementPrice.hashCode());
		result = prime * result + ((pantoneId == null) ? 0 : pantoneId.hashCode());
		result = prime * result + ((paperGramsId == null) ? 0 : paperGramsId.hashCode());
		result = prime * result + ((paperPrice == null) ? 0 : paperPrice.hashCode());
		result = prime * result + ((printColorId == null) ? 0 : printColorId.hashCode());
		result = prime * result + ((printNumber == null) ? 0 : printNumber.hashCode());
		result = prime * result + ((printPaperId == null) ? 0 : printPaperId.hashCode());
		result = prime * result + ((pvcLength == null) ? 0 : pvcLength.hashCode());
		result = prime * result + ((pvcPrice == null) ? 0 : pvcPrice.hashCode());
		result = prime * result + ((pvcUnit == null) ? 0 : pvcUnit.hashCode());
		result = prime * result + ((pvcWidth == null) ? 0 : pvcWidth.hashCode());
		result = prime * result + ((receiveAreaId == null) ? 0 : receiveAreaId.hashCode());
		result = prime * result + ((shoppingId == null) ? 0 : shoppingId.hashCode());
		result = prime * result + ((surfacePrice == null) ? 0 : surfacePrice.hashCode());
		result = prime * result + ((surfaceTreatmentId == null) ? 0 : surfaceTreatmentId.hashCode());
		result = prime * result + ((taxPrice == null) ? 0 : taxPrice.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((transportPrice == null) ? 0 : transportPrice.hashCode());
		result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((uvLength == null) ? 0 : uvLength.hashCode());
		result = prime * result + ((uvPrice == null) ? 0 : uvPrice.hashCode());
		result = prime * result + ((uvUnit == null) ? 0 : uvUnit.hashCode());
		result = prime * result + ((uvWidth == null) ? 0 : uvWidth.hashCode());
		return result;
	}

    @Override
	public String toString() {
		return "ShoppingDetail [shoppingId=" + shoppingId + ", userId=" + userId + ", boxId=" + boxId + ", layoutId="
				+ layoutId + ", printPaperId=" + printPaperId + ", paperGramsId=" + paperGramsId + ", printColorId="
				+ printColorId + ", pantoneId=" + pantoneId + ", surfaceTreatmentId=" + surfaceTreatmentId
				+ ", isBronzing=" + isBronzing + ", bronzingLength=" + bronzingLength + ", bronzingWidth="
				+ bronzingWidth + ", bronzingUnit=" + bronzingUnit + ", isConvex=" + isConvex + ", convexLength="
				+ convexLength + ", convexWidth=" + convexWidth + ", convexUnit=" + convexUnit + ", isUv=" + isUv
				+ ", uvLength=" + uvLength + ", uvWidth=" + uvWidth + ", uvUnit=" + uvUnit + ", isPvc=" + isPvc
				+ ", pvcLength=" + pvcLength + ", pvcWidth=" + pvcWidth + ", pvcUnit=" + pvcUnit + ", receiveAreaId="
				+ receiveAreaId + ", paperPrice=" + paperPrice + ", colorPrice=" + colorPrice + ", surfacePrice="
				+ surfacePrice + ", bronzingPrice=" + bronzingPrice + ", convexPrice=" + convexPrice + ", printNumber="
				+ printNumber + ", uvPrice=" + uvPrice + ", pvcPrice=" + pvcPrice + ", taxPrice=" + taxPrice
				+ ", managementPrice=" + managementPrice + ", transportPrice=" + transportPrice + ", totalPrice="
				+ totalPrice + ", unitPrice=" + unitPrice + ", excelAddress=" + excelAddress + ", createby=" + createby
				+ ", createtime=" + createtime + "]";
	}
}