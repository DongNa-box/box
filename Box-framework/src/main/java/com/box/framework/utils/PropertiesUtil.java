package com.box.framework.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * ClassName:PropertiesUtil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月9日 上午11:16:06
 * @author   lucky
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class PropertiesUtil {

	public final static Properties props=new Properties();

	static{
		PropertiesUtil.read("config.properties");
	}


	public static void read(String path) {
		try {
			InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			props.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getValue(String key) {
		try {
			String returnStr = "";
			returnStr = props.getProperty(key);
			return returnStr;
		} catch (Exception e) {
			return "";
		}

	}

}

