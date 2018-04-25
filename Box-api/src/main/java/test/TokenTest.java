package test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.box.token.JwtUtil;
import com.box.uums.model.User;

public class TokenTest {
	

	
   public static void main(String[] s){
	  JwtUtil jwt=new JwtUtil();
	  Map<String,Object> map=new HashMap<String,Object>();
	  map.put("account", "kate");
	  map.put("password", "123456");
	  String subject = JwtUtil.generalSubject(map);
	  System.out.println(jwt.genJwtForAndroid(subject));
   }
}
