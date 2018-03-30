
 /**
 * Project Name:PdoneCI-web
 * File Name:JCaptcha.java
 * Package Name:com.pdone.framework.captcha
 * Date:2017年8月7日下午5:52:35
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.captcha;

import javax.servlet.http.HttpServletRequest;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;

/**
 * ClassName:JCaptcha
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年8月7日 下午5:52:35
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class JCaptcha {
	public static final ManageableImageCaptchaService captchaService = new ManageableImageCaptchaService(new FastHashMapCaptchaStore(),new CaptchaEngine(),180,100000,75000);

	public static boolean validateResponse(HttpServletRequest request,String userCaptchaResponse){
		if(request.getSession(false) == null) return false;
		boolean validated = false;
		try{
			String id = request.getSession().getId();
			validated = captchaService.validateResponseForID(id, userCaptchaResponse).booleanValue();
		}catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		return validated;
	}
	
	public static boolean hasCaptcha(HttpServletRequest request, String userCaptchaResponse) {
        if (request.getSession(false) == null) return false;
        boolean validated = false;
        try {
            String id = request.getSession().getId();
            validated = captchaService.hasCapcha(id, userCaptchaResponse);
        } catch (CaptchaServiceException e) {
            e.printStackTrace();
        }
        return validated;
    }
}

