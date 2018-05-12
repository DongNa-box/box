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
	// map.put("userId", "1525657483518000");
	  //map.put("account", "Tom");
	// map.put("oldPassword", "e10adc3949ba59abbe56e057f20f883e");
	// map.put("newPassword", "e10adc3949ba59abbe56e057f20f883e");
	  ///map.put("mobile", "15527698765");
	 // map.put("email", "1416732092@qq.com");
//	  map.put("account", "Kate");
	 // map.put("password", "619a37da6984a8a76de4b61a013c04c2");
	 //map.put("code",  UUID.randomUUID().toString());
	 //System.out.println(map.get("code"));
	 // map.put("msg", "1524627135192000");
//	  map.put("cname", "2");
//  	  map.put("level", "2");
//  	  map.put("description", "");
  	  map.put("parentId", "1523523977642007");
	  map.put("printPaperId", "1523521257758003");
  	  map.put("paperGramsId", "1523611171534008");
      map.put("papergrams", "300");
  	  map.put("printColorId", "1523523977642004");
  	  map.put("surfaceTreatmentId", "1523609723044008");
 	  map.put("isBronzing", 0);
 	  map.put("bronzingLength",0 );
 	  map.put("bronzingWidth",0);
 	  map.put("isConvex", 0);
 	  map.put("convexlength", 0);
 	  map.put("convexWidth", 0);
 	  map.put("isUv", 0);
 	  map.put("uvLength", 0);
 	  map.put("uvWidth",0);
 	  map.put("isPvc", 0);
 	  map.put("pvcLength",0);
 	  map.put("pvcWidth", 0);
 	  map.put("receiveAreaId","1523531798224000");
 	  map.put("printNumber", 1000);
// 	  
// 	  map.put("managementPrice","23");
// 	  map.put("taxPrice","12");	
// 	  map.put("totalPrice","2300");
// 	  map.put("unitPrice","2.3");
// 	  map.put("paperprice","45");
// 	  map.put("colorPrice","30");
// 	  map.put("surfacePrice","45");
// 	  map.put("bronzingPrice","0");
// 	  map.put("convexPrice","0");
// 	  map.put("uvPrice","0");
// 	  map.put("pvcprice","0");
// 	  map.put("transportPrice","1500");
// 	  
 	  map.put("userId", "1525657483518000");
	  map.put("boxLength", "30");
	  map.put("boxWidth", "40");
	  map.put("boxHeight", "50");
	  map.put("boxId", "1525858321220000");
// 	map.put("paperLength","456");
// 	map.put("paperWidth","789");
// 	map.put("xnumber","5");
// 	map.put("ynumber","6");
// 	map.put("utilizationRate","70.87");
	  //map.put("userId", "1525657483518000");
	 // map.put("shoppingId", "00");
	  String subject = JwtUtil.generalSubject(map);
	  System.out.println(jwt.genJwtForAndroid(subject));
   }
}
