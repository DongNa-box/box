
 /**
 * Project Name:PdoneCI-api
 * File Name:AuthController.java
 * Package Name:com.pdone.api.controller
 * Date:2017年5月19日下午3:27:08
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.service.BoxTypeService;
import com.box.dao.rowmapper.PriceRowMapper;
import com.box.dao.rowmapper.RateRowMapper;
import com.box.domain.PriceVO;
import com.box.domain.RateVo;
import com.box.framework.algriothm.Paiban;
import com.box.framework.pojo.Result;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.model.ShoppingRate;
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;
import com.box.token.JwtUtil;



/**
 * ClassName:PricePaibanResultController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月19日 下午3:27:08
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value="/shopping")
public class ShoppingController {
	
	@Resource
	BoxTypeService boxTypeService;
	
	
	@Autowired
	JwtUtil jwt;
	
	
	private static final Logger logger = Logger.getLogger(ShoppingController.class);
	
	/**
	 * 
	 * 获取计算价格结果和排版结果
	 *
	 * @author Cheng
	 * @param token
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/getPrice",method = RequestMethod.POST)
	@ResponseBody
	public Result login(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
    	ShoppingDetail shoppingDetail  = JSON.parseObject(jsonObj.toString(),ShoppingDetail.class);
    	double boxlength =Double.parseDouble(jsonObj.getString("boxlength"));
    	double boxwidth = Double.parseDouble(jsonObj.getString("boxwidth"));
    	double boxheight = Double.parseDouble(jsonObj.getString("boxheight"));
    	int type = Integer.parseInt(jsonObj.getString("type"));
    	//查询盒型类型，传入盒型类型
	   	Map<String,Object> pmap=new Paiban().getResult(boxwidth, boxlength, boxheight,type);
	   	float length=Float.parseFloat(pmap.get("X")+"")/1000;//排版后的纸张的长度
	   	float width=Float.parseFloat(pmap.get("Y")+"")/1000;//排版后的纸张的宽度
		float xl=Float.parseFloat(pmap.get("N")+"");
		float yl=Float.parseFloat(pmap.get("M")+"");
		int printnumber=shoppingDetail.getPrintNumber();//订做数量
		double c=(double)printnumber /(double)(xl * yl);
        c=(double) Math.ceil(c);
        double count=c*1.06;//根据用户定制个数和排版得出的纸张数目上加6%
        int papernumber = (int) Math.ceil(count);//纸张数量
	   	//计算纸张价格
   		  String p=one.getPrice();
   		  float pprice=Float.parseFloat(p);;
   		  float papernum=Float.parseFloat(map.get("p2"));
   		  float  paperprice=length*width*papernum*pprice*papernumber/1000000;
   	      paperprice=getDecimal(paperprice);

		int cnumber=0;//印刷令数

		float colorprice=0;//颜色价格
		float surfaceprice;//表面处理价格	
		float bronzprice = 0;//烫金价格
	  	float jtprice=0;//击凸价格
		float uvprice = 0;//uv价格
	    float pvcprice=0;//pvc价格
		float ysf=0;//运输费
		float sum=0;
	    double tax=0;
	    double manag=0;
	    double totalprice=0;
	    double unitprice=0;
	    float result;//利用率
    	
        return new Result(true,result);
	}
	//最终价格
    public Map<String,String> getAllPrice(Map<String, String> hmap){
	
		  SetColorPrice();
		  SetBmclPrice();
		  SetTjPrice();
		  SetJtPrice();
		  SetUvPrice();
		  SetPvcPrice();
		  SetYsPrice();
		  getTotalPrice();
		  Map<String,String> mymap=new HashMap<String,String>();
		  mymap.put("paperprice", paperprice+"");
		  mymap.put("colorprice", colorprice+"");
		  mymap.put("surfaceprice", surfaceprice+"");
		  mymap.put("bronzprice", bronzprice+"");
		  mymap.put("jtprice", jtprice+"");
		  mymap.put("uvprice", uvprice+"");
		  mymap.put("pvcprice", pvcprice+"");
		  mymap.put("ysf", ysf+"");
		  mymap.put("tax", tax+"");
		  mymap.put("manag", manag+"");
		  mymap.put("totalprice", totalprice+"");
		  mymap.put("unitprice", unitprice+"");
		  mymap.put("profitablity",result+"");
		  return mymap;
	}

	   //计算颜色价格
	   @SuppressWarnings("unchecked")
		public void SetColorPrice(){
	   	 float cprice;
		    double c=((double)papernumber)/500;
		    cnumber=  (int) Math.ceil(c);
//		    System.out.println("cnumber-->"+cnumber+"");
			String tid=map.get("p3");
	   	String style=map.get("p1");
	   	String shade=map.get("p4");
		    if(map.get("p3").equals("CMYK")){
		    	float c11=0;
		    	float c21=0;
//				System.out.print("传递过来的参数"+tid);
			   	String sql = "";
			   	sql = "select a.* from price_all a where a.tid=? and a.style=? and a.shade='0' and a.enabled='1'";
			   	Object[] param = null;
			   	param = new Object[] {tid,style};
			   	List list = this.query(sql, param, new PriceRowMapper());
		    	if(list!=null&&list.size()>0){
		    		  //获取纸质的价格和单位   	
		    		  PriceVO one=(PriceVO) list.get(0);
		    		  String c1=one.getPrice();
		    		  c11=Float.parseFloat(c1);;
//		    	      System.out.println("价格：="+c11);
		    	}
		    	String sql1 = "";
			   	sql1 = "select a.* from price_all a where a.tid=? and a.style=? and a.shade=? and a.enabled='1'";
			   	Object[] param1 = null;
			   	param1 = new Object[] {tid,style,shade};
			   	List list1 = this.query(sql1, param1, new PriceRowMapper());
		    	if(list1!=null&&list1.size()>0){
		    		  //获取纸质的价格和单位   	
		    		  PriceVO one=(PriceVO) list.get(0);
		    		  String c2=one.getPrice();
		    		  c21=Float.parseFloat(c2);;
//		    	      System.out.println("价格：="+c21);
		    	}
		         cprice=c11+c21;
		     }else{
		    	 float c30=0;
		    	 String sql = "";
				 sql = "select a.* from price_all a where a.tid=? and a.style=? and a.enabled='1'";
				   	Object[] param = null;
				   	param = new Object[] {tid,style};
				   	List list = this.query(sql, param, new PriceRowMapper());
			    	if(list!=null&&list.size()>0){
			    		  //获取纸质的价格和单位   	
			    		  PriceVO one=(PriceVO) list.get(0);
			    		  String c3=one.getPrice();
			    		  c30=Float.parseFloat(c3);;
//			    	      System.out.println("价格：="+c30);
			    	}
		         cprice=c30;  
		     }
		    
//		     System.out.println("cprice-->"+cprice);
		     colorprice=cprice*cnumber;
//		     System.out.println("计算得到的颜色价格："+colorprice);
		     colorprice=getDecimal(colorprice);
//		     System.out.println("颜色价格："+colorprice);
	   }
	   //3.表面处理价格
	   @SuppressWarnings("unchecked")
		public void SetBmclPrice(){
	   	String tid=map.get("p5");
	   	String sql = "";
	   	float sprice=0;
		   	sql = "select a.* from price_all a where a.tid=? and a.parentid=?";
		   	Object[] param = null;
		   	param = new Object[] {tid,"BMCL"};
		   	List list = this.query(sql, param, new PriceRowMapper());
	   	if(list!=null&&list.size()>0){
	   		  //获取纸质的价格和单位   	
	   		  PriceVO one=(PriceVO) list.get(0);
	   		  String s=one.getPrice();    		
	   		  sprice=Float.parseFloat(s);;
//	   	      System.out.println("价格：="+sprice);
	   	}
		     surfaceprice=length*width*sprice*papernumber;
//		     System.out.println("计算得到表面处理价格："+surfaceprice);
		     surfaceprice=getDecimal(surfaceprice);
//		     System.out.println("处理后表面处理价格："+surfaceprice);
	   }
	   //4.烫金价格
	   @SuppressWarnings("unchecked")
		public void SetTjPrice(){
	   	float bf1;
	   	float rg1;
	   	String tjlength=map.get("p7");
	   	String tjwidth=map.get("p8");
	   	if(map.get("p6").equals("1")){
	   	String sql = "";
		   	sql = "select a.* from price_all a where  a.parentid=? order by tid";
		   	Object[] param = null;
		   	param = new Object[] {"TJ"};
		   	List list = this.query(sql, param, new PriceRowMapper());
	   	if(list!=null&&list.size()>0){
	   		  //获取纸质的价格和单位   	
	   		  PriceVO one=(PriceVO) list.get(0);
	   		  PriceVO two=(PriceVO) list.get(1);
	   		  String bf=one.getPrice();
	   		  String rg=two.getPrice();
//	   		  System.out.println(bf);
//	   		  System.out.println(rg);
	   		  bf1=Float.parseFloat(bf);
	 	          rg1=Float.parseFloat(rg);
	 	        float b0= Float.parseFloat(tjlength)+10;
		        float b1=Float.parseFloat(tjwidth)+10;
		        bronzprice=(bf1+rg1*papernumber)*b0*b1/1000000;  
	   	  }
	   	}else{
		    	 bronzprice=0;
		     }
//	   	System.out.println("计算得到的烫金价格="+bronzprice);
	   	bronzprice=getDecimal(bronzprice);
//		     System.out.println("处理后的烫金价格="+bronzprice);
	   }
	   //5.击凸价格
	   @SuppressWarnings("unchecked")
		public void SetJtPrice(){
	   	 float bf1=0;
	   	 float rg1=0;
	   	 String convexlength=map.get("p10");
	   	 String convexwidth=map.get("p11");
	   	 if(map.get("p9").equals("1")){
	   		 String sql = "";
	   		 sql = "select a.* from price_all a where  a.parentid=? order by tid";
	   		 Object[] param = null;
	   		 param = new Object[] {"JT"};
	   		 List list = this.query(sql, param, new PriceRowMapper());
	   	    	if(list!=null&&list.size()>0){
	   	    		  //获取纸质的价格和单位   	
	   	    		  PriceVO one=(PriceVO) list.get(0);
	   	    		  PriceVO two=(PriceVO) list.get(1);
	   	    		  String jbf=one.getPrice();
	   	    		  String jrg=two.getPrice();
//	   		          System.out.println(jbf);//0.00004
//	   		          System.out.println(jrg);//550
	   		          bf1=Float.parseFloat(jbf);
	   		   	      rg1=Float.parseFloat(jrg);
	   	    	}
	  	    	float b0= Float.parseFloat(convexlength);
	  	        float b1=Float.parseFloat(convexwidth);   
	  	        jtprice=bf1*b0*b1/1000000+rg1*cnumber;     
	  	     }else{
	  	    	 jtprice=0;
	  	     }
//	     System.out.println("计算得到的击凸价格="+jtprice);	
	   	 jtprice=getDecimal(jtprice);
	//   System.out.println("处理后的击凸价格="+jtprice);	
	   }
	   //6.UV价格
	   @SuppressWarnings("unchecked")
		public void SetUvPrice(){
	   	 String uvlength=map.get("p13");
	   	 String uvwidth=map.get("p14");
		     if(map.get("p12").equals("1")){
		    	 String sql = "";
	   		 sql = "select a.* from price_all a where  a.parentid=? order by tid";
	   		 Object[] param = null;
	   		 param = new Object[] {"UV"};
	   		 List list = this.query(sql, param, new PriceRowMapper());
	   	    	if(list!=null&&list.size()>0){
	   	    		  PriceVO one=(PriceVO) list.get(0);
	   	    		  PriceVO two=(PriceVO) list.get(1);
	   	    		  String ucl=one.getPrice();
	   	    		  String ukj=two.getPrice();
//	   	    		  System.out.println(ucl);//0.7
//	   	 	   	      System.out.println(ukj);//100
				       float b0= Float.parseFloat(uvlength);
					   float b1=Float.parseFloat(uvwidth);  
					   float bf1=Float.parseFloat(ucl);
					   float rg1=Float.parseFloat(ukj);
					   uvprice=rg1+bf1*b0*b1*papernumber/1000000; 
		       }
		     }else{
		    	 uvprice=0;
		     }
//		     System.out.println("计算得到的UV价格="+uvprice);
		     uvprice=getDecimal(uvprice);
//		     System.out.println("处理后的UV价格="+uvprice);
	   }
	   //7.PVC价格
	   @SuppressWarnings("unchecked")
		public void SetPvcPrice(){
	 
	       String pvclength=map.get("p16");
	       String pvcwidth=map.get("p17");
	     if(map.get("p15").equals("1")){
	   	  String sql = "";
			  sql = "select a.* from price_all a where  a.parentid=? order by tid";
			  Object[] param = null;
			  param = new Object[] {"PVC"};
			  List list = this.query(sql, param, new PriceRowMapper());
		    	if(list!=null&&list.size()>0){
		    		  PriceVO one=(PriceVO) list.get(0);
		    		  PriceVO two=(PriceVO) list.get(1);
		    		  String pvcl=one.getPrice();
		    		  String pvcr=two.getPrice();
//		   	     System.out.println(pvcl);//3.5
//		   	     System.out.println(pvcr);//0.03
		    	float b0= Float.parseFloat(pvclength);
		        float b1=Float.parseFloat(pvcwidth);  
		        float bf1=Float.parseFloat(pvcl);
		        float bf2=Float.parseFloat(pvcr);
		        pvcprice=bf1*b0*b1*printnumber/1000000+bf2; 
		       }
		     }else{
		    	 pvcprice=0;
		     }
//	     System.out.println("计算得到的PVC价格="+pvcprice);
	        pvcprice=getDecimal(pvcprice);
//		     System.out.println("处理后的PVC价格="+pvcprice);
	   }
	   //8运输费
	   @SuppressWarnings("unchecked")
		public void SetYsPrice(){
	   
	   	String tid=map.get("p18");
	   	String sql = "";
			  sql = "select a.* from price_all a where  a.tid=? ";
			  Object[] param = null;
			  param = new Object[] {tid};
			  List list = this.query(sql, param, new PriceRowMapper());
		    	if(list!=null&&list.size()>0){
		    	  PriceVO one=(PriceVO) list.get(0);
	     		  String yf=one.getPrice();
//		          System.out.println(yf);
		          ysf= Float.parseFloat(yf);	        
		    	}
//		    System.out.println("运输费价格="+ysf);//500
	   }
	   //9计算总价
		@SuppressWarnings("unchecked")
		public void getTotalPrice(){
			   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			   createtime=df.format(new Date());
			
				Map<String,String> mymap=new HashMap<String,String>();
			   List<RateVo> vo=new ArrayList<RateVo>();
				double taxp=0;
			    double manp=0;
			    String sql = "";
			   	sql = "select a.* from rate a ";
			   	List list = this.query(sql, new RateRowMapper());
		    	if(list!=null&&list.size()>0){
		   		  //获取纸质的价格和单位   	
		    		for(int i=0;i<list.size();i++){
		   		    RateVo one=(RateVo) list.get(i);
		   		    String p=one.getValue();
		   		    mymap.put(i+"", p);
		   		    vo.add(one);
		    		}
		   	    }
		    	String ss=mymap.get("0");
		    	String sa=mymap.get("1");
		    	if(ss.contains("%")){
		    		ss=ss.replaceAll("%","");
		    		taxp=Double.valueOf(ss);
		    		//System.out.println("获取字符串的值"+String.format("%.3f", taxp));
		    		taxp=getDouble(taxp);
		    		taxp=taxp/100.0000;
//		    		System.out.println("获取字符串的值"+String.format("%.3f", taxp));	
		    	}else{
		    		taxp=Double.parseDouble(mymap.get("0"));	
		    	}
		    	if(sa.contains("%")){
			    	//taxp=(new Double(mymap.get("0").substring(0,mymap.get("0").indexOf("%"))))/100;
			    	 sa=sa.replaceAll("%","");
			    	 manp=Double.valueOf(sa);
			    		//System.out.println("获取字符串的值"+String.format("%.3f", manp));
			    	 manp=getDouble(manp);
			    	 manp=manp/100.0000; 
			    	//	System.out.println("获取字符串的值"+String.format("%.3f", manp));
				 	//manp=(new Double(mymap.get("1").substring(0,mymap.get("1").indexOf("%"))))/100;
			     } else{
	 	    	   manp=Double.parseDouble(mymap.get("1"));
		    	 }
		        //将利润为110%转为小数
				   //1.如果是用户的话，map.get("p20").equals(00)就从数据库调用数据    	
				if(map.get("p20").equals("00")){
//					System.out.println("用户");
					if(mymap.get("2").contains("%")){
						result=new Float(mymap.get("2").substring(0,mymap.get("2").indexOf("%")))/100;
			    	}else{
			    		result=Float.parseFloat(mymap.get("2"));
			    	}
			    		
				}else{
					 //2.如果是内部人员的话就要先存储到数据库，然后再进行计算
			    	 //插入利率表 
//					System.out.println("内部使用的");
					if(map.get("p20").contains("%")){
						result=new Float(map.get("p20").substring(0,map.get("p20").indexOf("%")))/100;
			    	}else{
					result=Float.parseFloat(map.get("p20"));
					
			    	}
				}
				taxp=0.07;
				manp=0.075;
//			   System.out.println("最终结果："+taxp);
//			   System.out.println("最终结果："+manp);
//			   System.out.println("最终结果："+result);
			   //统计费用
			     sum=paperprice+colorprice+surfaceprice+bronzprice+jtprice+uvprice+pvcprice+ysf;
			     //System.out.println("1-8的总价："+sum);
			    
			     tax=sum*taxp;
			     manag=sum*manp;
			     totalprice=(sum+ manag+ tax)*result;
			     unitprice=totalprice/printnumber;
//			     System.out.println("税费："+tax);
//			     System.out.println("管理费："+manag);
//			     System.out.println("总价："+totalprice);
//			     System.out.println("单价："+unitprice);
			     tax=getDecimal(tax);      
			     manag=getDecimal(manag); 
			     totalprice=getDecimal(totalprice); 
			     unitprice=getDecimal(unitprice); 
//			     System.out.println("税费："+tax);
//			     System.out.println("管理费："+manag);
//			     System.out.println("总价："+totalprice);
//			     System.out.println("单价："+unitprice);
		   }
		public Float getDecimal(float p){
		    	float a=0;
		    	 DecimalFormat decimalFormat=new DecimalFormat(".00");
			     a=Float.parseFloat(decimalFormat.format(p));//format 返回的是字符串
		        return a;
		    }
		public Double getDecimal(double p){
	    	double a=0;
	    	 DecimalFormat decimalFormat=new DecimalFormat(".00");
		     a=Double.parseDouble(decimalFormat.format(p));//format 返回的是字符串
	        return a;
	    }
		public Double getDouble(double p){
	    	double a=0;
	    	 DecimalFormat decimalFormat=new DecimalFormat(".0000");
		     a=Double.parseDouble(decimalFormat.format(p));//format 返回的是字符串
	        return a;
	    }	
}

