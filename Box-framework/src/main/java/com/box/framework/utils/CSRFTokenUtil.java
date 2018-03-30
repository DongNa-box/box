package com.box.framework.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 
 * ClassName: CSRFTokenUtil
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2017年5月12日 上午10:02:14
 *
 * @author Jay
 * @version 
 * @since JDK 1.8
 */
public class CSRFTokenUtil {

    public static String generate(HttpServletRequest request) {

        return UUID.randomUUID().toString();
    }

}
