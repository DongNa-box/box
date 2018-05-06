package test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.box.token.JwtUtil;
import com.box.uums.model.User;

public class TokenTest {
	

	
   public static void main(String[] s){
	  JwtUtil jwt=new JwtUtil();
	  Map<String,Object> map=new HashMap<String,Object>();
	//  map.put("userId", "1495426777745000");
	  //map.put("account", "Tom");
	// map.put("oldPassword", "619a37da6984a8a76de4b61a013c04c2");
	// map.put("newPassword", "e10adc3949ba59abbe56e057f20f883e");
	  ///map.put("mobile", "15527698765");
	 // map.put("email", "1416732092@qq.com");
//	  map.put("account", "Kate");
	 // map.put("password", "619a37da6984a8a76de4b61a013c04c2");
	 map.put("code",  UUID.randomUUID().toString());
	 System.out.println(map.get("code"));
	  String subject = JwtUtil.generalSubject(map);
	  System.out.println(jwt.genJwtForAndroid(subject));
   }
}
