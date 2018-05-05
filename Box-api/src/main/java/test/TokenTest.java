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
	  map.put("userId", "1524974962453000");
	  map.put("oldpassword", "123456");
	  map.put("newpassword", "111111");
	  //map.put("mobile", "15527698765");
	  //map.put("email", "1416732092@qq.com");
	  String subject = JwtUtil.generalSubject(map);
	  System.out.println(jwt.genJwtForAndroid(subject));
   }
}
