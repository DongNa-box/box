package com.box.framework.utils;

import java.security.MessageDigest;

/**
 * ClassName:EncryptUtil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月9日 下午5:17:28
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class EncryptUtil {

    private static final String ALGORITHM = "MD5";

    public static String encodeByMD5(String str) {
    	String digest = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);  
            byte[] digestArray = digester.digest(str.getBytes());  
            for (int i = 0; i < digestArray.length; i++) {  
                buffer.append(String.format("%02x", digestArray[i]));  
            }  
            digest = buffer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return digest;
    }

}

