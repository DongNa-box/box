package com.box.framework.pojo;
/**
 * ClassName:DataSyncResult
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年7月31日 下午5:50:50
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class DataSyncResult {
	private String id;
	private String devId;
	private Integer opt;
	private Integer bizType;
	private Object biz;
	
	public DataSyncResult(String id,Integer opt,Integer bizType,Object biz){
		this.id = id;
		this.opt = opt;
		this.bizType = bizType;
		this.biz = biz;
	}
	
	public DataSyncResult(String id,String devId,Integer opt,Integer bizType,Object biz){
		this.id = id;
		this.devId = devId;
		this.opt = opt;
		this.bizType = bizType;
		this.biz = biz;
	}
	
	public DataSyncResult() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public Integer getOpt() {
		return opt;
	}
	public void setOpt(Integer opt) {
		this.opt = opt;
	}
	public Integer getBizType() {
		return bizType;
	}
	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}
	public Object getBiz() {
		return biz;
	}
	public void setBiz(Object biz) {
		this.biz = biz;
	}
	
}

