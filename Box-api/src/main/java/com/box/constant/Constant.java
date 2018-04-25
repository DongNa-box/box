
 /**
 * Project Name:PdoneCI-api
 * File Name:Constant.java
 * Package Name:com.pdone.api.constant
 * Date:2017年5月23日下午1:31:38
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.constant;

import com.box.framework.utils.PropertiesUtil;

/**
 * ClassName:Constant
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月23日 下午1:31:38
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class Constant {

	/**
	 * Jwt
	 */
	public static final int JWT_TTL = 60*60*1000;  //millisecond
	/**
	 * Vidyo Url
	 */
	public static final String httpUrl = PropertiesUtil.getValue("httpUrl");
	
	/**
	 * Query Meeting
	 */
	public static final String meeting_partic_query = "meeting_partic_query";
	
	/**
	 * Create Room
	 */
	public static final String room_main_create = "room_main_create";
	
	/**
	 * Query Room
	 */
	public static final String room_main_query = "room_main_query";
	
}

