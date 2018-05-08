
 /**
 * Project Name:PdoneCI-api
 * File Name:ShoppingController.java
 * Package Name:com.pdone.api.controller
 * Date:2017年6月19日下午3:27:08
 * Copyright (c) 2017, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.algriothm.Paiban;
import com.box.framework.pojo.Result;
import com.box.framework.pojo.RspCode;
import com.box.framework.utils.FileUtil;
import com.box.framework.utils.Sequence;
import com.box.shopping.model.LayoutDetail;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.LayoutDetailService;
import com.box.shopping.service.ShoppingDetailService;
import com.box.shopping.service.ShoppingRateService;
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;
import com.box.technology.service.TechnologyPriceService;
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
	
	@Resource
	TechnologyPriceService technologyPriceService;
	
	@Resource
	ShoppingRateService shoppingRateService;
	
	@Resource
	ShoppingDetailService shoppingDetailService;
	
	@Resource
	LayoutDetailService layoutDetailService;
	
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
	public Result getPrice(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
    	ShoppingDetail shoppingDetail  = JSON.parseObject(jsonObj.toString(),ShoppingDetail.class);
    	double boxlength =Double.parseDouble(jsonObj.getString("boxLength"));
    	double boxwidth = Double.parseDouble(jsonObj.getString("boxWidth"));
    	double boxheight = Double.parseDouble(jsonObj.getString("boxHeight"));
    	int type = 1;
    	String paperNumber=jsonObj.getString("papergrams");
    	//查询盒型类型，传入盒型类型
    	Map<String,Object> box=boxTypeService.getAllById(shoppingDetail.getBoxId());
    	if(box.get("classId").equals("1523178448293000")){//管式盒
    		if((box.get("detail1").equals("1523190385039003")||box.get("detail1").equals("1523190385039004"))&&
    				(box.get("detail2").equals("1523192628473001")||box.get("detail2").equals("1523192628473000"))){
    		  //盒盖前开直插，盒底后开直插	
    			type=1;
    		}
    		if((box.get("detail1").equals("1523190385039003")||box.get("detail1").equals("1523190385039004"))&&
    				(box.get("detail2").equals("1523192628473003")||box.get("detail2").equals("1523192628473004"))){
      		  //盒盖前开直插，盒底锁底
      			type=2;
      		}
    		if(box.get("detail1").equals("1523190385039005")||box.get("detail3").equals("1523192628473002")){
      		  //盒盖前开直插，后开直插	
      			type=3;
      		}
    	}else{
    		type=3;
    	}
	   	Map<String,Object> pmap=new Paiban().getResult(boxwidth, boxlength, boxheight,type);
	   	for (Entry<String, Object> entry : pmap.entrySet()) { 
	   	  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
	   	}
	   	float length=Float.parseFloat(pmap.get("X")+"")/1000;//排版后的纸张的长度
	   	float width=Float.parseFloat(pmap.get("Y")+"")/1000;//排版后的纸张的宽度
		float xl=Float.parseFloat(pmap.get("N")+"");
		float yl=Float.parseFloat(pmap.get("M")+"");
		int printnumber=shoppingDetail.getPrintNumber();//订做数量
		double c=(double)printnumber /(double)(xl * yl);
        c=(double) Math.ceil(c);
        double count=c*1.06;//根据用户定制个数和排版得出的纸张数目上加6%
        int papernumber = (int) Math.ceil(count);//纸张数量
        int cnumber=0;//印刷令数
        float pprice=0;//印刷纸质价格
        float cprice = 0;//单色价格
        float c11=0;
    	float c21=0;
	    float  sprice=0;
	   	float bf=0;
	   	float rg=0;
		float jbf = 0;
	   	float jrg=0;
	    float ucl=0;
 		float ukj=0;
 		float pvcl=0;
		float pvcr=0;
		
		float bronzprice = 0;//烫金价格
	  	float jtprice=0;//击凸价格
		float uvprice = 0;//uv价格
	    float pvcprice=0;//pvc价格
		float ysf=0;//运输费
	    
        List<Map<String, Object>> price=technologyPriceService.getAllPriceList();
        for(Map<String,Object> m:price){
        	if(m.get("tid").equals(shoppingDetail.getPrintPaperId())){
        		pprice=Float.parseFloat(m.get("price").toString());
        	}
        	if(m.get("pname").equals("CMYK")){
	        	if(m.get("style").equals(shoppingDetail.getPrintPaperId())&&m.get("tid").equals(shoppingDetail.getPrintColorId())){
	        		c11=Float.parseFloat(m.get("price").toString());
	        	}
	        	if(m.get("style").equals(shoppingDetail.getPrintPaperId())&&m.get("tname").equals(0)){
	        		c21=Float.parseFloat(m.get("price").toString());
	        	}
	        	if(c11!=0&&c21!=0){
	        		cprice=c11+c21;
	        	}
        	}else if(m.get("pname").equals("印刷颜色")){
        		if(m.get("style").equals(shoppingDetail.getPrintPaperId())&&m.get("tid").equals(shoppingDetail.getPrintColorId())){
        			cprice=Float.parseFloat(m.get("price").toString());
            	}
        	}
           if(m.get("tid").equals(shoppingDetail.getSurfaceTreatmentId())){
        		 sprice=Float.parseFloat(m.get("price").toString());
        	}
           if(shoppingDetail.getIsBronzing().equals("1")){
	     	   if(m.get("pname").equals("烫金")&&m.get("tname").equals("版费")){
	     	    	bf=Float.parseFloat(m.get("price").toString());
	     	    }
	     	   if(m.get("pname").equals("烫金")&&m.get("tname").equals("人工")){
	    	    	rg=Float.parseFloat(m.get("price").toString());
	    	    }
           }
           if(shoppingDetail.getIsConvex().equals("1")){
	     	   if(m.get("pname").equals("击凸")&&m.get("tname").equals("版费")){
	    	    	jbf=Float.parseFloat(m.get("price").toString());
	    	    }
	    	   if(m.get("pname").equals("击凸")&&m.get("tname").equals("人工")){
	   	    	    jrg=Float.parseFloat(m.get("price").toString());
	   	        }
           }
           if(shoppingDetail.getIsUv().equals("1")){
	    	   if(m.get("pname").equals("UV")&&m.get("tname").equals("开机费")){
	    		    ukj=Float.parseFloat(m.get("price").toString());
	   	       }
	   	       if(m.get("pname").equals("UV")&&m.get("tname").equals("材料费")){
	   	    	    ucl=Float.parseFloat(m.get("price").toString());
	  	       }
           }
           if(shoppingDetail.getIsPvc().equals("1")){
	   	      if(m.get("pname").equals("PVC")&&m.get("tname").equals("材料费")){
	   	    	    pvcl=Float.parseFloat(m.get("price").toString());
		       }
	   	      if(m.get("pname").equals("PVC")&&m.get("tname").equals("人工")){
	   	    	    pvcr=Float.parseFloat(m.get("price").toString());
		       }
           }
   	      if(m.get("tid").equals(shoppingDetail.getReceiveAreaId())){
   	    	    ysf=Float.parseFloat(m.get("price").toString());
           }
        }
	   	//1.计算纸张价格
   		  float papernum=Float.parseFloat(paperNumber);
   		  float  paperprice=length*width*papernum*pprice*papernumber/1000000;
   	      paperprice=getDecimal(paperprice);
   	      System.out.println("纸质价格："+pprice+"结果："+paperprice);
   	   //2.计算颜色价格
		 double c1=((double)papernumber)/500;//多少令
		 cnumber=(int) Math.ceil(c1);
		 if(cnumber<5){
			 cnumber=5;
		 }
		 float  colorprice=cprice*cnumber;
	     colorprice=getDecimal(colorprice);
	     System.out.println("颜色价格："+cprice+"结果："+colorprice);
	  //3.表面处理价格
	   	 float surfaceprice=length*width*sprice*papernumber;
		 surfaceprice=getDecimal(surfaceprice);
		 System.out.println("表面价格："+sprice+"结果："+surfaceprice);
      //4.烫金价格
	     if(shoppingDetail.getIsBronzing().equals("1")){
 	        float b0= Float.parseFloat(shoppingDetail.getBronzingLength().toString())+10;
	        float b1=Float.parseFloat(shoppingDetail.getBronzingWidth().toString())+10;
	        bronzprice=(bf+rg*papernumber)*b0*b1/1000000;   
	     }else{
	    	 bronzprice=0;
	     }
	   	 bronzprice=getDecimal(bronzprice);
	      System.out.println("烫金价格："+bf+":"+rg+"结果："+bronzprice);
	   //5.击凸价格
	    if(shoppingDetail.getIsConvex().equals("1")){
	 	    float b0= Float.parseFloat(shoppingDetail.getConvexLength().toString())+10;
		    float b1=Float.parseFloat(shoppingDetail.getConvexWidth().toString())+10;
		     jtprice=jbf*b0*b1/1000000+jrg*cnumber;  
	     }else{
	    	jtprice=0;
	     }
	    jtprice=getDecimal(jtprice);
	      System.out.println("击凸价格："+jbf+":"+jrg+"结果："+jtprice);
	    //6.UV价格
	    if(shoppingDetail.getIsUv().equals("1")){
	 	    float b0= Float.parseFloat(shoppingDetail.getUvLength().toString())+10;
		    float b1=Float.parseFloat(shoppingDetail.getUvWidth().toString())+10;
		    uvprice=ukj+ucl*b0*b1*papernumber/1000000; 
	     }else{
	    	 uvprice=0;
	     }
	    uvprice=getDecimal(uvprice);
	      System.out.println("UV价格："+ukj+":"+ucl+"结果："+uvprice);
	    //7.PVC价格
	    if(shoppingDetail.getIsPvc().equals("1")){
	 	    float b0= Float.parseFloat(shoppingDetail.getPvcLength().toString())+10;
		    float b1=Float.parseFloat(shoppingDetail.getPvcWidth().toString())+10;
		    pvcprice=pvcl*b0*b1*printnumber/1000000+pvcr; 
	     }else{
	    	 pvcprice=0;
	     }
	     pvcprice=getDecimal(uvprice);
	     System.out.println("PVC价格："+pvcl+":"+pvcr+"结果："+pvcprice);
	     //计算总价
	     System.out.println("运输费用"+ysf);
	     //先获取利率值
	     List<ShoppingRate> rateList=shoppingRateService.getAllList();
		 double taxp=0;//0管理费利率
		 double manp=0;// 1 税费利率
		 double res=0;//2总价利率 
         for(ShoppingRate rate:rateList){
        	 if(rate.getType()==0){
        		 taxp=rate.getValue()/100.00;
        	 }
        	 if(rate.getType()==1){
        		 manp=rate.getValue()/100.00;
        	 }
        	 if(rate.getType()==2){
        		 res=rate.getValue()/100.00;
        	 }
         }
		//统计费用
	    float sum=paperprice+colorprice+surfaceprice+bronzprice+jtprice+uvprice+pvcprice+ysf;
	    System.out.println("结果："+sum);
	    double tax=sum*taxp;
	    double manag=sum*manp;
	    double totalprice=(sum+ manag+ tax)*res;
	    double unitprice=totalprice/printnumber;
	    tax=getDecimal(tax);      
	    manag=getDecimal(manag); 
	    totalprice=getDecimal(totalprice); 
	    unitprice=getDecimal(unitprice);
	      Map<String,Object> mymap=new HashMap<String,Object>();
		  mymap.put("paperprice", paperprice);
		  mymap.put("colorprice", colorprice);
		  mymap.put("surfaceprice", surfaceprice);
		  mymap.put("bronzprice", bronzprice);
		  mymap.put("jtprice", jtprice);
		  mymap.put("uvprice", uvprice);
		  mymap.put("pvcprice", pvcprice);
		  mymap.put("ysf", ysf);
		  mymap.put("tax", tax);
		  mymap.put("manag", manag);
		  mymap.put("totalprice", totalprice);
		  mymap.put("unitprice", unitprice);
		  mymap.put("profitablity",pmap.get("P"));
		  mymap.put("length", pmap.get("X"));
		  mymap.put("width", pmap.get("Y"));
		  mymap.put("wnum", pmap.get("N"));
		  mymap.put("lnum",pmap.get("M"));
		  mymap.put("type",type);
         return new Result(true,mymap);
	}

	@RequestMapping(value = "/addShopping",method = RequestMethod.POST)
	@ResponseBody
	public Result addShopping(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
    	ShoppingDetail shoppingDetail  = JSON.parseObject(jsonObj.toString(),ShoppingDetail.class);
    	LayoutDetail layoutDetail= JSON.parseObject(jsonObj.toString(),LayoutDetail.class);
    	shoppingDetail.setShoppingId(Sequence.nextId());
    	layoutDetail.setId(Sequence.nextId());
    	shoppingDetail.setUvUnit(0);
    	shoppingDetail.setConvexUnit(0);
    	shoppingDetail.setPvcUnit(0);
    	shoppingDetail.setBronzingUnit(0);
    	shoppingDetail.setLayoutId(layoutDetail.getId());
    	shoppingDetail.setCreateby(shoppingDetail.getUserId());
    	shoppingDetail.setCreatetime(new Date());
    	layoutDetail.setCreateby(shoppingDetail.getUserId());
    	layoutDetail.setCreatetime(new Date());
    	layoutDetail.setBoxUnit(0);
    	layoutDetail.setPaperUnit(0);
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("shoppingDetail", shoppingDetail);
    	map.put("layoutDetail", layoutDetail);
    	boolean result=shoppingDetailService.createLayoutAndShopping(map);
    	if(result){
    		Map<String,String> m=new HashMap<String,String>();
    		m.put("layoutId", layoutDetail.getId());
    	    return new Result(true,m);
    	}else{
    		return new Result(false);
    	}
	}
	
	@RequestMapping(value = "/imageUpload",method = RequestMethod.POST)
	@ResponseBody
	public Result imageUpload(@RequestHeader HttpHeaders headers,HttpServletRequest httpRequest){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
    	MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpRequest;
		MultipartFile file=request.getFile("headImg");
		String UPLOADDIR = "previewimageaddress";
        String contentType = request.getContentType();
        if (contentType.indexOf("multipart/form-data") >= 0) {
        	String id = jsonObj.getString("layoutId"); 
			String path2=File.separator+"home"+File.separator+"ubuntu"+File.separator+"tomcat"+File.separator+"tomcat-2-8080";
        	String uploadPath=path2+File.separator+"Box-management"+File.separator+"images"+File.separator+"LayoutDetail";
    		System.out.println(uploadPath);
        	String filePath=uploadPath+File.separator+id+File.separator+UPLOADDIR;
    	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
       		Date date = new Date();
       		String time= formatter.format(date);
           	String fileName = id+"-"+time+".jpg";  
    		try {
  				FileUtil.upload(file.getInputStream(),fileName,filePath);
  				 //将图片的名称存放在数据库中
  				LayoutDetail d=new LayoutDetail();
  				d.setId(id);
  				d.setPictureAddress(fileName);
  				boolean r=layoutDetailService.updateImageByid(d);
  	    		if(r){
  					return new Result(true);						
  				}else{
  					return new Result(false,RspCode.R00000);
  				}  
  			} catch (IOException e) {
  				e.printStackTrace();	
  				return new Result(false,RspCode.R00000);
  			}
    		   
        }else{
        	return new Result(false,RspCode.R00000);
        }

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

