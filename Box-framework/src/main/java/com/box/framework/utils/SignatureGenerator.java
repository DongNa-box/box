package com.box.framework.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ClassName:SignatureGenerator
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月16日 下午12:59:06
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class SignatureGenerator {
	   public static String generate(String urlResourcePart, Map<String, String> params, String secretKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	        //对参数按名称排序(升序)
	        List<Map.Entry<String, String>> parameters = new LinkedList<Map.Entry<String, String>>(params.entrySet());
	        Collections.sort(parameters, new Comparator<Map.Entry<String, String>>() {
	            @Override
	            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
	                return o1.getKey().compareTo(o2.getKey());
	            }
	        });
	        //形成参数字符串, 并把SecretKey加在末尾（salt）
	        StringBuilder sb = new StringBuilder();
	        sb.append(urlResourcePart).append("_");
	        for (Map.Entry<String, String> param : parameters) {
	            sb.append(param.getKey()).append("=").append(param.getValue()).append("_");
	        }
	        sb.append(secretKey);
	        String baseString = URLEncoder.encode(sb.toString(), "UTF-8");
	        return EncryptUtil.encodeByMD5(baseString);
	    }
	   
	    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("appkey", PropertiesUtil.getValue("appkey"));
	        params.put("params1", "123456");
	        params.put("params2", "abcdef");
	        String urlResourcePart = "uums/user/login";
	        String sign = generate(urlResourcePart, params, PropertiesUtil.getValue("secretKey"));
	        System.out.println(sign);
	    }
}

