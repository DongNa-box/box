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

    private Integer isMosha;

    private Double moshaLength;

    private Double moshaWidth;

    private Integer moshaUnit;

    private Integer isZhouwen;

    private Double zhouwenLength;

    private Double zhouwenWidth;

    private Integer zhouwenUnit;

    private String yawenId;

    private String moqieId;

    private String zhanheId;

    private String baozhuangId;

    private String receiveAreaId;

    private Double paperPrice;

    private Double colorPrice;

    private Double surfacePrice;

    private Double bronzingPrice;

    private Double convexPrice;

    private Integer printNumber;

    private Double uvPrice;

    private Double pvcPrice;

    private Double moshaPrice;

    private Double zhouwenPrice;

    private Double yawenPrice;

    private Double moqiePrice;

    private Double zhanhePrice;

    private Double baozhuangPrice;

    private Double taxPrice;

    private Double managementPrice;

    private Double transportPrice;

    private Double totalPrice;

    private Double unitPrice;

    private String excelAddress;

    private Integer enabled;

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

    public Integer getIsMosha() {
        return isMosha;
    }

    public void setIsMosha(Integer isMosha) {
        this.isMosha = isMosha;
    }

    public Double getMoshaLength() {
        return moshaLength;
    }

    public void setMoshaLength(Double moshaLength) {
        this.moshaLength = moshaLength;
    }

    public Double getMoshaWidth() {
        return moshaWidth;
    }

    public void setMoshaWidth(Double moshaWidth) {
        this.moshaWidth = moshaWidth;
    }

    public Integer getMoshaUnit() {
        return moshaUnit;
    }

    public void setMoshaUnit(Integer moshaUnit) {
        this.moshaUnit = moshaUnit;
    }

    public Integer getIsZhouwen() {
        return isZhouwen;
    }

    public void setIsZhouwen(Integer isZhouwen) {
        this.isZhouwen = isZhouwen;
    }

    public Double getZhouwenLength() {
        return zhouwenLength;
    }

    public void setZhouwenLength(Double zhouwenLength) {
        this.zhouwenLength = zhouwenLength;
    }

    public Double getZhouwenWidth() {
        return zhouwenWidth;
    }

    public void setZhouwenWidth(Double zhouwenWidth) {
        this.zhouwenWidth = zhouwenWidth;
    }

    public Integer getZhouwenUnit() {
        return zhouwenUnit;
    }

    public void setZhouwenUnit(Integer zhouwenUnit) {
        this.zhouwenUnit = zhouwenUnit;
    }

    public String getYawenId() {
        return yawenId;
    }

    public void setYawenId(String yawenId) {
        this.yawenId = yawenId == null ? null : yawenId.trim();
    }

    public String getMoqieId() {
        return moqieId;
    }

    public void setMoqieId(String moqieId) {
        this.moqieId = moqieId == null ? null : moqieId.trim();
    }

    public String getZhanheId() {
        return zhanheId;
    }

    public void setZhanheId(String zhanheId) {
        this.zhanheId = zhanheId == null ? null : zhanheId.trim();
    }

    public String getBaozhuangId() {
        return baozhuangId;
    }

    public void setBaozhuangId(String baozhuangId) {
        this.baozhuangId = baozhuangId == null ? null : baozhuangId.trim();
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

    public Double getMoshaPrice() {
        return moshaPrice;
    }

    public void setMoshaPrice(Double moshaPrice) {
        this.moshaPrice = moshaPrice;
    }

    public Double getZhouwenPrice() {
        return zhouwenPrice;
    }

    public void setZhouwenPrice(Double zhouwenPrice) {
        this.zhouwenPrice = zhouwenPrice;
    }

    public Double getYawenPrice() {
        return yawenPrice;
    }

    public void setYawenPrice(Double yawenPrice) {
        this.yawenPrice = yawenPrice;
    }

    public Double getMoqiePrice() {
        return moqiePrice;
    }

    public void setMoqiePrice(Double moqiePrice) {
        this.moqiePrice = moqiePrice;
    }

    public Double getZhanhePrice() {
        return zhanhePrice;
    }

    public void setZhanhePrice(Double zhanhePrice) {
        this.zhanhePrice = zhanhePrice;
    }

    public Double getBaozhuangPrice() {
        return baozhuangPrice;
    }

    public void setBaozhuangPrice(Double baozhuangPrice) {
        this.baozhuangPrice = baozhuangPrice;
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

    public String getExcelAddress() {
        return excelAddress;
    }

    public void setExcelAddress(String excelAddress) {
        this.excelAddress = excelAddress == null ? null : excelAddress.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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
        ShoppingDetail other = (ShoppingDetail) that;
        return (this.getShoppingId() == null ? other.getShoppingId() == null : this.getShoppingId().equals(other.getShoppingId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBoxId() == null ? other.getBoxId() == null : this.getBoxId().equals(other.getBoxId()))
            && (this.getLayoutId() == null ? other.getLayoutId() == null : this.getLayoutId().equals(other.getLayoutId()))
            && (this.getPrintPaperId() == null ? other.getPrintPaperId() == null : this.getPrintPaperId().equals(other.getPrintPaperId()))
            && (this.getPaperGramsId() == null ? other.getPaperGramsId() == null : this.getPaperGramsId().equals(other.getPaperGramsId()))
            && (this.getPrintColorId() == null ? other.getPrintColorId() == null : this.getPrintColorId().equals(other.getPrintColorId()))
            && (this.getPantoneId() == null ? other.getPantoneId() == null : this.getPantoneId().equals(other.getPantoneId()))
            && (this.getSurfaceTreatmentId() == null ? other.getSurfaceTreatmentId() == null : this.getSurfaceTreatmentId().equals(other.getSurfaceTreatmentId()))
            && (this.getIsBronzing() == null ? other.getIsBronzing() == null : this.getIsBronzing().equals(other.getIsBronzing()))
            && (this.getBronzingLength() == null ? other.getBronzingLength() == null : this.getBronzingLength().equals(other.getBronzingLength()))
            && (this.getBronzingWidth() == null ? other.getBronzingWidth() == null : this.getBronzingWidth().equals(other.getBronzingWidth()))
            && (this.getBronzingUnit() == null ? other.getBronzingUnit() == null : this.getBronzingUnit().equals(other.getBronzingUnit()))
            && (this.getIsConvex() == null ? other.getIsConvex() == null : this.getIsConvex().equals(other.getIsConvex()))
            && (this.getConvexLength() == null ? other.getConvexLength() == null : this.getConvexLength().equals(other.getConvexLength()))
            && (this.getConvexWidth() == null ? other.getConvexWidth() == null : this.getConvexWidth().equals(other.getConvexWidth()))
            && (this.getConvexUnit() == null ? other.getConvexUnit() == null : this.getConvexUnit().equals(other.getConvexUnit()))
            && (this.getIsUv() == null ? other.getIsUv() == null : this.getIsUv().equals(other.getIsUv()))
            && (this.getUvLength() == null ? other.getUvLength() == null : this.getUvLength().equals(other.getUvLength()))
            && (this.getUvWidth() == null ? other.getUvWidth() == null : this.getUvWidth().equals(other.getUvWidth()))
            && (this.getUvUnit() == null ? other.getUvUnit() == null : this.getUvUnit().equals(other.getUvUnit()))
            && (this.getIsPvc() == null ? other.getIsPvc() == null : this.getIsPvc().equals(other.getIsPvc()))
            && (this.getPvcLength() == null ? other.getPvcLength() == null : this.getPvcLength().equals(other.getPvcLength()))
            && (this.getPvcWidth() == null ? other.getPvcWidth() == null : this.getPvcWidth().equals(other.getPvcWidth()))
            && (this.getPvcUnit() == null ? other.getPvcUnit() == null : this.getPvcUnit().equals(other.getPvcUnit()))
            && (this.getIsMosha() == null ? other.getIsMosha() == null : this.getIsMosha().equals(other.getIsMosha()))
            && (this.getMoshaLength() == null ? other.getMoshaLength() == null : this.getMoshaLength().equals(other.getMoshaLength()))
            && (this.getMoshaWidth() == null ? other.getMoshaWidth() == null : this.getMoshaWidth().equals(other.getMoshaWidth()))
            && (this.getMoshaUnit() == null ? other.getMoshaUnit() == null : this.getMoshaUnit().equals(other.getMoshaUnit()))
            && (this.getIsZhouwen() == null ? other.getIsZhouwen() == null : this.getIsZhouwen().equals(other.getIsZhouwen()))
            && (this.getZhouwenLength() == null ? other.getZhouwenLength() == null : this.getZhouwenLength().equals(other.getZhouwenLength()))
            && (this.getZhouwenWidth() == null ? other.getZhouwenWidth() == null : this.getZhouwenWidth().equals(other.getZhouwenWidth()))
            && (this.getZhouwenUnit() == null ? other.getZhouwenUnit() == null : this.getZhouwenUnit().equals(other.getZhouwenUnit()))
            && (this.getYawenId() == null ? other.getYawenId() == null : this.getYawenId().equals(other.getYawenId()))
            && (this.getMoqieId() == null ? other.getMoqieId() == null : this.getMoqieId().equals(other.getMoqieId()))
            && (this.getZhanheId() == null ? other.getZhanheId() == null : this.getZhanheId().equals(other.getZhanheId()))
            && (this.getBaozhuangId() == null ? other.getBaozhuangId() == null : this.getBaozhuangId().equals(other.getBaozhuangId()))
            && (this.getReceiveAreaId() == null ? other.getReceiveAreaId() == null : this.getReceiveAreaId().equals(other.getReceiveAreaId()))
            && (this.getPaperPrice() == null ? other.getPaperPrice() == null : this.getPaperPrice().equals(other.getPaperPrice()))
            && (this.getColorPrice() == null ? other.getColorPrice() == null : this.getColorPrice().equals(other.getColorPrice()))
            && (this.getSurfacePrice() == null ? other.getSurfacePrice() == null : this.getSurfacePrice().equals(other.getSurfacePrice()))
            && (this.getBronzingPrice() == null ? other.getBronzingPrice() == null : this.getBronzingPrice().equals(other.getBronzingPrice()))
            && (this.getConvexPrice() == null ? other.getConvexPrice() == null : this.getConvexPrice().equals(other.getConvexPrice()))
            && (this.getPrintNumber() == null ? other.getPrintNumber() == null : this.getPrintNumber().equals(other.getPrintNumber()))
            && (this.getUvPrice() == null ? other.getUvPrice() == null : this.getUvPrice().equals(other.getUvPrice()))
            && (this.getPvcPrice() == null ? other.getPvcPrice() == null : this.getPvcPrice().equals(other.getPvcPrice()))
            && (this.getMoshaPrice() == null ? other.getMoshaPrice() == null : this.getMoshaPrice().equals(other.getMoshaPrice()))
            && (this.getZhouwenPrice() == null ? other.getZhouwenPrice() == null : this.getZhouwenPrice().equals(other.getZhouwenPrice()))
            && (this.getYawenPrice() == null ? other.getYawenPrice() == null : this.getYawenPrice().equals(other.getYawenPrice()))
            && (this.getMoqiePrice() == null ? other.getMoqiePrice() == null : this.getMoqiePrice().equals(other.getMoqiePrice()))
            && (this.getZhanhePrice() == null ? other.getZhanhePrice() == null : this.getZhanhePrice().equals(other.getZhanhePrice()))
            && (this.getBaozhuangPrice() == null ? other.getBaozhuangPrice() == null : this.getBaozhuangPrice().equals(other.getBaozhuangPrice()))
            && (this.getTaxPrice() == null ? other.getTaxPrice() == null : this.getTaxPrice().equals(other.getTaxPrice()))
            && (this.getManagementPrice() == null ? other.getManagementPrice() == null : this.getManagementPrice().equals(other.getManagementPrice()))
            && (this.getTransportPrice() == null ? other.getTransportPrice() == null : this.getTransportPrice().equals(other.getTransportPrice()))
            && (this.getTotalPrice() == null ? other.getTotalPrice() == null : this.getTotalPrice().equals(other.getTotalPrice()))
            && (this.getUnitPrice() == null ? other.getUnitPrice() == null : this.getUnitPrice().equals(other.getUnitPrice()))
            && (this.getExcelAddress() == null ? other.getExcelAddress() == null : this.getExcelAddress().equals(other.getExcelAddress()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
            && (this.getCreateby() == null ? other.getCreateby() == null : this.getCreateby().equals(other.getCreateby()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShoppingId() == null) ? 0 : getShoppingId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getBoxId() == null) ? 0 : getBoxId().hashCode());
        result = prime * result + ((getLayoutId() == null) ? 0 : getLayoutId().hashCode());
        result = prime * result + ((getPrintPaperId() == null) ? 0 : getPrintPaperId().hashCode());
        result = prime * result + ((getPaperGramsId() == null) ? 0 : getPaperGramsId().hashCode());
        result = prime * result + ((getPrintColorId() == null) ? 0 : getPrintColorId().hashCode());
        result = prime * result + ((getPantoneId() == null) ? 0 : getPantoneId().hashCode());
        result = prime * result + ((getSurfaceTreatmentId() == null) ? 0 : getSurfaceTreatmentId().hashCode());
        result = prime * result + ((getIsBronzing() == null) ? 0 : getIsBronzing().hashCode());
        result = prime * result + ((getBronzingLength() == null) ? 0 : getBronzingLength().hashCode());
        result = prime * result + ((getBronzingWidth() == null) ? 0 : getBronzingWidth().hashCode());
        result = prime * result + ((getBronzingUnit() == null) ? 0 : getBronzingUnit().hashCode());
        result = prime * result + ((getIsConvex() == null) ? 0 : getIsConvex().hashCode());
        result = prime * result + ((getConvexLength() == null) ? 0 : getConvexLength().hashCode());
        result = prime * result + ((getConvexWidth() == null) ? 0 : getConvexWidth().hashCode());
        result = prime * result + ((getConvexUnit() == null) ? 0 : getConvexUnit().hashCode());
        result = prime * result + ((getIsUv() == null) ? 0 : getIsUv().hashCode());
        result = prime * result + ((getUvLength() == null) ? 0 : getUvLength().hashCode());
        result = prime * result + ((getUvWidth() == null) ? 0 : getUvWidth().hashCode());
        result = prime * result + ((getUvUnit() == null) ? 0 : getUvUnit().hashCode());
        result = prime * result + ((getIsPvc() == null) ? 0 : getIsPvc().hashCode());
        result = prime * result + ((getPvcLength() == null) ? 0 : getPvcLength().hashCode());
        result = prime * result + ((getPvcWidth() == null) ? 0 : getPvcWidth().hashCode());
        result = prime * result + ((getPvcUnit() == null) ? 0 : getPvcUnit().hashCode());
        result = prime * result + ((getIsMosha() == null) ? 0 : getIsMosha().hashCode());
        result = prime * result + ((getMoshaLength() == null) ? 0 : getMoshaLength().hashCode());
        result = prime * result + ((getMoshaWidth() == null) ? 0 : getMoshaWidth().hashCode());
        result = prime * result + ((getMoshaUnit() == null) ? 0 : getMoshaUnit().hashCode());
        result = prime * result + ((getIsZhouwen() == null) ? 0 : getIsZhouwen().hashCode());
        result = prime * result + ((getZhouwenLength() == null) ? 0 : getZhouwenLength().hashCode());
        result = prime * result + ((getZhouwenWidth() == null) ? 0 : getZhouwenWidth().hashCode());
        result = prime * result + ((getZhouwenUnit() == null) ? 0 : getZhouwenUnit().hashCode());
        result = prime * result + ((getYawenId() == null) ? 0 : getYawenId().hashCode());
        result = prime * result + ((getMoqieId() == null) ? 0 : getMoqieId().hashCode());
        result = prime * result + ((getZhanheId() == null) ? 0 : getZhanheId().hashCode());
        result = prime * result + ((getBaozhuangId() == null) ? 0 : getBaozhuangId().hashCode());
        result = prime * result + ((getReceiveAreaId() == null) ? 0 : getReceiveAreaId().hashCode());
        result = prime * result + ((getPaperPrice() == null) ? 0 : getPaperPrice().hashCode());
        result = prime * result + ((getColorPrice() == null) ? 0 : getColorPrice().hashCode());
        result = prime * result + ((getSurfacePrice() == null) ? 0 : getSurfacePrice().hashCode());
        result = prime * result + ((getBronzingPrice() == null) ? 0 : getBronzingPrice().hashCode());
        result = prime * result + ((getConvexPrice() == null) ? 0 : getConvexPrice().hashCode());
        result = prime * result + ((getPrintNumber() == null) ? 0 : getPrintNumber().hashCode());
        result = prime * result + ((getUvPrice() == null) ? 0 : getUvPrice().hashCode());
        result = prime * result + ((getPvcPrice() == null) ? 0 : getPvcPrice().hashCode());
        result = prime * result + ((getMoshaPrice() == null) ? 0 : getMoshaPrice().hashCode());
        result = prime * result + ((getZhouwenPrice() == null) ? 0 : getZhouwenPrice().hashCode());
        result = prime * result + ((getYawenPrice() == null) ? 0 : getYawenPrice().hashCode());
        result = prime * result + ((getMoqiePrice() == null) ? 0 : getMoqiePrice().hashCode());
        result = prime * result + ((getZhanhePrice() == null) ? 0 : getZhanhePrice().hashCode());
        result = prime * result + ((getBaozhuangPrice() == null) ? 0 : getBaozhuangPrice().hashCode());
        result = prime * result + ((getTaxPrice() == null) ? 0 : getTaxPrice().hashCode());
        result = prime * result + ((getManagementPrice() == null) ? 0 : getManagementPrice().hashCode());
        result = prime * result + ((getTransportPrice() == null) ? 0 : getTransportPrice().hashCode());
        result = prime * result + ((getTotalPrice() == null) ? 0 : getTotalPrice().hashCode());
        result = prime * result + ((getUnitPrice() == null) ? 0 : getUnitPrice().hashCode());
        result = prime * result + ((getExcelAddress() == null) ? 0 : getExcelAddress().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
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
        sb.append(", shoppingId=").append(shoppingId);
        sb.append(", userId=").append(userId);
        sb.append(", boxId=").append(boxId);
        sb.append(", layoutId=").append(layoutId);
        sb.append(", printPaperId=").append(printPaperId);
        sb.append(", paperGramsId=").append(paperGramsId);
        sb.append(", printColorId=").append(printColorId);
        sb.append(", pantoneId=").append(pantoneId);
        sb.append(", surfaceTreatmentId=").append(surfaceTreatmentId);
        sb.append(", isBronzing=").append(isBronzing);
        sb.append(", bronzingLength=").append(bronzingLength);
        sb.append(", bronzingWidth=").append(bronzingWidth);
        sb.append(", bronzingUnit=").append(bronzingUnit);
        sb.append(", isConvex=").append(isConvex);
        sb.append(", convexLength=").append(convexLength);
        sb.append(", convexWidth=").append(convexWidth);
        sb.append(", convexUnit=").append(convexUnit);
        sb.append(", isUv=").append(isUv);
        sb.append(", uvLength=").append(uvLength);
        sb.append(", uvWidth=").append(uvWidth);
        sb.append(", uvUnit=").append(uvUnit);
        sb.append(", isPvc=").append(isPvc);
        sb.append(", pvcLength=").append(pvcLength);
        sb.append(", pvcWidth=").append(pvcWidth);
        sb.append(", pvcUnit=").append(pvcUnit);
        sb.append(", isMosha=").append(isMosha);
        sb.append(", moshaLength=").append(moshaLength);
        sb.append(", moshaWidth=").append(moshaWidth);
        sb.append(", moshaUnit=").append(moshaUnit);
        sb.append(", isZhouwen=").append(isZhouwen);
        sb.append(", zhouwenLength=").append(zhouwenLength);
        sb.append(", zhouwenWidth=").append(zhouwenWidth);
        sb.append(", zhouwenUnit=").append(zhouwenUnit);
        sb.append(", yawenId=").append(yawenId);
        sb.append(", moqieId=").append(moqieId);
        sb.append(", zhanheId=").append(zhanheId);
        sb.append(", baozhuangId=").append(baozhuangId);
        sb.append(", receiveAreaId=").append(receiveAreaId);
        sb.append(", paperPrice=").append(paperPrice);
        sb.append(", colorPrice=").append(colorPrice);
        sb.append(", surfacePrice=").append(surfacePrice);
        sb.append(", bronzingPrice=").append(bronzingPrice);
        sb.append(", convexPrice=").append(convexPrice);
        sb.append(", printNumber=").append(printNumber);
        sb.append(", uvPrice=").append(uvPrice);
        sb.append(", pvcPrice=").append(pvcPrice);
        sb.append(", moshaPrice=").append(moshaPrice);
        sb.append(", zhouwenPrice=").append(zhouwenPrice);
        sb.append(", yawenPrice=").append(yawenPrice);
        sb.append(", moqiePrice=").append(moqiePrice);
        sb.append(", zhanhePrice=").append(zhanhePrice);
        sb.append(", baozhuangPrice=").append(baozhuangPrice);
        sb.append(", taxPrice=").append(taxPrice);
        sb.append(", managementPrice=").append(managementPrice);
        sb.append(", transportPrice=").append(transportPrice);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", excelAddress=").append(excelAddress);
        sb.append(", enabled=").append(enabled);
        sb.append(", createby=").append(createby);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}