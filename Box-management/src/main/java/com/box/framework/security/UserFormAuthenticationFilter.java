
 /**
 * Project Name:PdoneCI-web
 * File Name:PdoneFormAuthenticationFilter.java
 * Package Name:com.pdone.framework.captcha
 * Date:2017年8月8日上午9:58:13
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:PdoneFormAuthenticationFilter
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年8月8日 上午9:58:13
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class UserFormAuthenticationFilter extends FormAuthenticationFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if(request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		return super.onAccessDenied(request, response);
	}
	
}

