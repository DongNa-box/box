package com.box.framework.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.box.framework.utils.PropertiesUtil;

/**
 * 
 * ClassName: SystemFilter
 * Function: TODO ADD FUNCTION.
 * date: 2017年5月5日 上午11:09:02
 *
 * @author Jay
 * @version 
 * @since JDK 1.8
 */
public class SystemFilter implements Filter {

	private static Logger logger = Logger.getLogger(SystemFilter.class);
	private final static String apiHost  = PropertiesUtil.getValue("apiHost");
	private final static String imagesUrl  = PropertiesUtil.getValue("imagesUrl");
	private final static String softwareVersionUrl  = PropertiesUtil.getValue("softwareVersionUrl");
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info(request.getRequestURL());
        String basePath = request.getContextPath();
       
        request.setAttribute("basePath", basePath);
        request.setAttribute("imagesUrl", imagesUrl);
        request.setAttribute("apiHost", apiHost);
        request.setAttribute("softwareVersionUrl", softwareVersionUrl);
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
