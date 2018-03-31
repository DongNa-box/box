
 /**
 * Project Name:PdoneCI-framework
 * File Name:SystemAuthorizingRealm.java
 * Package Name:com.pdone.framework.filter
 * Date:2017年5月9日上午9:15:14
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.security;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.box.framework.utils.EncryptUtil;
import com.box.framework.utils.PropertiesUtil;
import com.box.web.uums.controller.LoginController;
import com.box.uums.model.User;
import com.box.uums.service.RoleService;
import com.box.uums.service.UserService;

/**
 * ClassName:SystemAuthorizingRealm
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月9日 上午9:15:14
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class SystemAuthorizingRealm extends AuthorizingRealm{

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemAuthorizingRealm.class);
	@Resource
	private UserService userService;
	
	@Resource 
	private RoleService roleService;

	 /**
     * 提供账户信息返回认证信息
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        if (authToken == null)
            throw new AuthenticationException("parameter token is null");
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        // 校验用户名密码
        String password=String.copyValueOf(token.getPassword());
        User user= userService.getUserForLogin(token.getUsername());
        if (user!=null) {
            if(!EncryptUtil.encodeByMD5(password).equals(user.getPassword())&& isNeedPassword()){
                throw new IncorrectCredentialsException();
            }
            // 注意此处的返回值没有使用加盐方式,如需要加盐，可以在密码参数上加
            return new SimpleAuthenticationInfo(user, token.getPassword(), token.getUsername());
        }
        throw new UnknownAccountException();		
	}
	/**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        if (principal == null) {
            throw new AuthorizationException("parameters principals is null");
        }
        //获取已认证的用户名（登录名）
        String username=(String)super.getAvailablePrincipal(principal);
        Set<String> roleCodes=roleService.getRoleCodeSet(username);
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleCodes);
        //authorizationInfo.setStringPermissions(functionCodes);
        return authorizationInfo;
    }
	
    //是否需要校验密码登录，用于开发环境 0默认为开发环境，其他为正式环境（1，或者不配）
    public boolean isNeedPassword(){
         String version=PropertiesUtil.getValue("system.version");
        if("0".equals(version))
            return false;
        else
            return true;
    }
}

