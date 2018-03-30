package com.box.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;


/**
 * ClassName:ImageUtil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年12月9日 下午5:35:18
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ImageUtil {

	public static boolean GenerateImage(String imgStr, String filePath, String fileName) {
	    try {
	        if (imgStr == null) {
	            return false;
	        }
	        //BASE64Decoder decoder = new BASE64Decoder();
	        byte[] b = Base64.decodeBase64(imgStr);

	        File file = new File(filePath);
	        if (!file.exists()) {
	            file.mkdirs();
	        }
	        OutputStream out = new FileOutputStream(filePath + fileName);
	        out.write(b);
	        out.flush();
	        out.close();
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public static String getImageStr(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    return new String(Base64.encodeBase64(data));
	}
}

