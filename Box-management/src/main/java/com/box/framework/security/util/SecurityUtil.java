
 /**
 * Project Name:PdoneCI-web
 * File Name:SecurityUtil.java
 * Package Name:com.pdone.framework.util
 * Date:2017年5月26日上午8:46:54
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.security.util;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.box.uums.model.User;

/**
 * ClassName:SecurityUtil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月26日 上午8:46:54
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class SecurityUtil {
	
	public static final int HTTP_STATUS_SESSION_EXPIRE = 403;
	
	
	/**
	 * 
	 * getSession:获取session.
	 *
	 * @author Jay
	 * @return
	 * @since JDK 1.8
	 */
    public static Session getSession() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
    }
	
	/**
	 * 
	 * getUser:获取当前登录用户.
	 *
	 * @author Jay
	 * @return
	 * @since JDK 1.8
	 */
    public static User getUser(){
        return (User)SecurityUtils.getSubject().getPrincipal();
    }
 
    /**
     * 
     * isAjax:判断ajax请求.
     *
     * @author Jay
     * @param request
     * @return
     * @since JDK 1.8
     */
	public static boolean isAjax(HttpServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}
}

