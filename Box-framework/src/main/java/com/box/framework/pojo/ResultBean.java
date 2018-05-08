/**
 * 
 */
package com.box.framework.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 
 */
public class ResultBean implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String msg;
	private String xml;
	
	private Map map;
	private List list;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public ResultBean(String errorCode, String msg) {
		super();
		this.errorCode = errorCode;
		this.msg = msg;
	}
}
