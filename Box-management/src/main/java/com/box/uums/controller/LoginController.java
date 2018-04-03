/**
 *Title:LoginController.java
 *@date 2018年4月2日下午1:42:45
 */
package com.box.uums.controller;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.box.framework.pojo.ResultCode;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.StrUtil;
import com.box.uums.model.Login;
import com.box.uums.service.LoginService;
import com.box.uums.service.RoleService;
import com.box.uums.service.UserService;


/**
 * CalssName:LoginController.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:42:45
 * @version
 * @since:
 * @see
 */
@Controller
public class LoginController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	    private final static String MAIN_PAGE = "redirect:/main";
	    private final static String LOGIN_PAGE = "login";
	    private final static String LOGOUT_PAGE = "redirect:/login";
	    private final static String ERROR_PAGE = "error";
	    private final static String KICKOUT = "1";
	    
	    @Resource
	    private RoleService roleService;

	    @Resource
	    private UserService userService;
	    
	    @Resource
	    private LoginService loginService;
	    
	    @RequestMapping(value = "/login")
	    private String doLogin(HttpServletRequest request, Model model) {
	    	String kickout = request.getParameter("kickout");
	    	
	    	if(KICKOUT.equals(kickout)){
	    		return ERROR_PAGE;
	    	}
	        //已经登录过，直接进入主页
	        Subject subject = SecurityUtils.getSubject();
	        if (subject != null && subject.isAuthenticated()) {
	        	/**
	        	LoginLog loginLog = new LoginLog();
	        	loginLog.setSessionId(subject.getSession().getId().toString());
	        	loginLog.setUserId(SecurityUtil.getUser().getId());
	        	loginLog.setLoginTime(DateUtil.getCurrDate());
	        	try {
					loginLog.setLoginIp(NetworkUtil.getIpAddress(request));
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	loginLogService.save(loginLog);
	        	*/
	    		subject.getSession().setAttribute("user", SecurityUtil.getUser());
	            return MAIN_PAGE;
	        }
	        String userName = request.getParameter("userName");
	        System.out.println("用户名："+userName);
	        //默认首页，第一次进来
	        if (StrUtil.isEmpty(userName)) {
	            return LOGIN_PAGE;
	        }
	        String password = request.getParameter("password");
	        System.out.println("密码："+userName);
	        //密码加密+加盐
	        //password = EncryptUtil.getPassword(password, userName);
	        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
	        subject = SecurityUtils.getSubject();
	        String msg;
	        try {
	        	String shiroLoginFailure = (String)request.getAttribute("shiroLoginFailure");
	        	if("jCaptcha.error".equals(shiroLoginFailure)){
	                msg = "验证码错误！";
	                model.addAttribute("message", new ResultCode("4", msg));
	                LOGGER.error(msg);
	                return LOGIN_PAGE;
	        	}
	            subject.login(token);
	            
	            //通过认证
	            if (subject.isAuthenticated()) {
	                Set<String> roles = roleService.getRoleCodeSet(userName);
	                if (!roles.isEmpty()) {
	                    subject.getSession().setAttribute("user", SecurityUtil.getUser());
	                    return MAIN_PAGE;
	                } else {//没有授权
	                    msg = "您没有得到相应的授权！";
	                    model.addAttribute("message", new ResultCode("1", msg));
	                    LOGGER.error(msg);
	                    return LOGIN_PAGE;
	                }

	            } else {
	                return LOGIN_PAGE;
	            }
	            //0 未授权 1 账号问题 2 密码错误  3 账号密码错误
	        } catch (IncorrectCredentialsException e) {
	            msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect";
	            model.addAttribute("message", new ResultCode("2", msg));
	            LOGGER.error(msg);
	        } catch (ExcessiveAttemptsException e) {
	            msg = "登录失败次数过多";
	            model.addAttribute("message", new ResultCode("3", msg));
	            LOGGER.error(msg);
	        } catch (LockedAccountException e) {
	            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
	            model.addAttribute("message", new ResultCode("1", msg));
	            LOGGER.error(msg);
	        } catch (DisabledAccountException e) {
	            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
	            model.addAttribute("message", new ResultCode("1", msg));
	            LOGGER.error(msg);
	        } catch (ExpiredCredentialsException e) {
	            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
	            model.addAttribute("message", new ResultCode("1", msg));
	            LOGGER.error(msg);
	        } catch (UnknownAccountException e) {
	            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
	            model.addAttribute("message", new ResultCode("1", msg));
	            LOGGER.error(msg);
	        } catch (UnauthorizedException e) {
	            msg = "您没有得到相应的授权！" + e.getMessage();
	            model.addAttribute("message", new ResultCode("1", msg));
	            LOGGER.error(msg);
	        }
	        return LOGIN_PAGE;
	    }
	    
	    @RequestMapping(value = "/logout")
	    private String doLogout(Model model) {
	        Subject subject = SecurityUtils.getSubject();
	        Login login = new Login();
	        login.setLoginId(subject.getSession().getId().toString());
	        login.setLoginoutTime(DateUtil.getCurrDate());
	        loginService.update(login);
	        subject.logout();
	        return LOGOUT_PAGE;
	    }
	    
}
