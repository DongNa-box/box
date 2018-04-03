
 /**
 * Project Name:PdoneCI-core
 * File Name:AuthTree.java
 * Package Name:com.pdone.uums.model
 * Date:2017年8月21日下午7:50:53
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.uums.model;
/**
 * ClassName:AuthTree
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年8月21日 下午7:50:53
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class AuthTree {
    private String id;

    private String pId;

    private String name;

    private String iconSkin;
    
    private boolean open;
	
	private boolean checked;

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}

