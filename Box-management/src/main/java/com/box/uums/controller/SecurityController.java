/**
 *Title:UserContoller.java
 *@date 2018年4月2日下午1:43:02
 */
package com.box.uums.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CalssName:UserContoller.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:43:02
 * @version
 * @since:
 * @see
 */
@Controller
@RequestMapping("/security")
public class SecurityController {

	@RequestMapping("/hasRole")
	@ResponseBody
    private boolean hasRole(String roleCode) {
        Subject subject = SecurityUtils.getSubject();
        return subject.hasRole(roleCode);
    }
	
	@RequestMapping("/isPermitted")
	@ResponseBody
    private boolean isPermitted(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(permission);
    }
	
}
