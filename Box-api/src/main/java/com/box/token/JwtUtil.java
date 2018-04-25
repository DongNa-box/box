package com.box.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
//import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {
	
	/**
	 * 
	 * generalKey:由字符串生成加密key.
	 *
	 * @author Jay
	 * @return
	 * @since JDK 1.8
	 */
	public SecretKey generalKey(){
		String stringKey = "qweqwdwdad213233wdszczxc";
		byte[] encodedKey = Base64.decodeBase64(stringKey);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}
	
	/**
	 * 
	 * genJwtForAndroid:生成android jwt.
	 *
	 * @author Jay
	 * @param subject
	 * @return
	 * @since JDK 1.8
	 */
	public String genJwtForAndroid(String subject){
		String id ="qweqwdwdad213233wdszczxc";
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		long ttlMillis = 60*60*1000;
		JwtBuilder builder = Jwts.builder()
			.setId(id)
			.setIssuedAt(now)
			.setSubject(subject)
		    .signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	/**
	 * 
	 * parseJwtForAndroid:解析android jwt.
	 *
	 * @author Jay
	 * @param token
	 * @return
	 * @since JDK 1.8
	 */
	public JSONObject parseJwtForAndroid(String token){
		SecretKey key = generalKey();
		Claims claims = Jwts.parser()         
		   .setSigningKey(key)
		   .parseClaimsJws(token).getBody();
		String json = claims.getSubject();
		JSONObject jsonObj = JSONObject.parseObject(json);
		return jsonObj;
	}
		
	
	/**
	 * 
	 * generalSubject:生成subject信息.
	 *
	 * @author Jay
	 * @param map
	 * @return
	 * @since JDK 1.8
	 */
	public static String generalSubject(Map map){
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		return gson.toJson(map);
	}
	
}

