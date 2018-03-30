
 /**
 * Project Name:PdoneCI-web
 * File Name:ManageableImageCaptchaService.java
 * Package Name:com.pdone.framework.captcha
 * Date:2017年8月7日下午5:45:55
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.captcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;

/**
 * ClassName:ManageableImageCaptchaService
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年8月7日 下午5:45:55
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ManageableImageCaptchaService extends DefaultManageableImageCaptchaService{
	
	public ManageableImageCaptchaService(CaptchaStore captchaStore,CaptchaEngine captchaEngine,int minGuarantedStorageDelayInSeconds,int maxCaptchaStoreSize,int captchaStoreLoadBeforeGarbageCollection){
		super(captchaStore,captchaEngine,minGuarantedStorageDelayInSeconds,maxCaptchaStoreSize,captchaStoreLoadBeforeGarbageCollection);
	}
	
	public boolean hasCapcha(String id, String userCaptchaResponse){
		return store.getCaptcha(id).validateResponse(userCaptchaResponse);
	}

}

