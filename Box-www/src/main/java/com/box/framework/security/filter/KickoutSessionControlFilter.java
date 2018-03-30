
 /**
 * Project Name:PdoneCI-web
 * File Name:KickoutSessionControlFilter.java
 * Package Name:com.pdone.framework.security.filter
 * Date:2017年8月7日上午10:14:29
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.framework.security.filter;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.box.uums.model.User;

/**
 * ClassName:KickoutSessionControlFilter
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年8月7日 上午10:14:29
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class KickoutSessionControlFilter extends AccessControlFilter{

	private String kickoutUrl;
	private boolean kickoutAfter = false;
	private int maxSession = 1;
	
	private SessionManager sessionManager;
	private Cache<String, Deque<Serializable>> cache;
	private static final Logger LOGGER = LoggerFactory.getLogger(KickoutSessionControlFilter.class);

	
	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setCacheManager(CacheManager cacheManager){
		this.cache = cacheManager.getCache("activeSessionCache");
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object) throws Exception {
		
		// TODO Auto-generated method stub
		return false;
		
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }
		
        Session session = subject.getSession();
        User user = (User)subject.getPrincipal();
        String loginName = user.getLoginName();
        Serializable sessionId = session.getId();
        
        // 同步控制
        Deque<Serializable> deque = cache.get(loginName);
        if(deque == null) {
            deque = new LinkedList<Serializable>();
            cache.put(loginName, deque);
        }
		
        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }
        
        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if(kickoutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst();
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
            }
        }
        
        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
            //}
        }
		return true;
	}
	
}

