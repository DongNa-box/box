package com.box.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxClassification;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.model.PaibanType;
import com.box.boxmanage.model.PaibanTypeL;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.algriothm.MergePicture;
import com.box.framework.algriothm.Paiban;
import com.box.framework.algriothm.PaibanResult;

import com.box.framework.pojo.TreeNode;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.ExcelUtil;
import com.box.framework.utils.Sequence;
import com.box.shopping.model.LayoutDetail;
import com.box.shopping.model.LayoutSize;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.model.ShoppingPantone;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.LayoutDetailService;
import com.box.shopping.service.LayoutSizeService;
import com.box.shopping.service.ShoppingDetailService;
import com.box.shopping.service.ShoppingPantoneService;
import com.box.shopping.service.ShoppingRateService;
import com.box.technology.model.TechnologyDetail;
import com.box.technology.service.TechnologyDetailService;
import com.box.technology.service.TechnologyPriceService;

/**
 * Project Name:Box-web
 * File Name:PictureController.java
 * Package Name:
 * Date:2018年4月12日上午10:27:20
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

@Controller
@RequestMapping(value = "/web")
public class XiadanController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(XiadanController.class);
	
	 @Resource
	 BoxClassficationService boxClassficationService;
	 @Resource
	 BoxTypeService boxTypeService;
	 @Resource
	 TechnologyDetailService technologyDetailService;
	 @Resource 
	 ShoppingDetailService shoppingDetailService;
	 @Resource
	 LayoutDetailService layoutDetailService;
	 @Resource
	 ShoppingRateService shoppingRateService;
	 @Resource
	 LayoutSizeService layoutSizeService;
	 @Resource
	TechnologyPriceService technologyPriceService;
	 @Resource
	 ShoppingPantoneService shoppingPantoneService;
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/xiadan")
	    private String xiadan() {
		 
	        return "web/xiadan";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/xiadan2")
	    private String xiadan2() {
	        return "web/xiadan2";
	    }
	 @RequestMapping(method = RequestMethod.GET, value = "/xiadan3")
	    private String xiadan3() {
	        return "web/xiadan3";
	    }
	/**
	 * 查询该盒型相关信息
	 * xiadanBox:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/xiadanBox")
    @ResponseBody
    public BoxType xiadanBox(@Param(value = "params") String params){
		JSONObject jsonObj = JSONObject.parseObject(params);
		List<Map<String, Object>> list = new ArrayList<>();
		String boxId=jsonObj.getString("boxId");
		BoxType boxType = boxTypeService.get(boxId);
		return boxType;
		
	}
	/**
	 * 查询所有工艺
	 * technology:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/technology")
    @ResponseBody
    public List<TreeNode> technology(){
		List<TreeNode> technologymenu = technologyDetailService.getTechnologyTree();
		return technologymenu;
	}
	/**
	 * 实时计算价格
	 * calculatePrice:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @param request
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/calculatePrice")
    @ResponseBody
    public String calculatePrice(@Param(value = "params") String params,HttpServletRequest request){
		JSONObject jsonObj = JSONObject.parseObject(params);
		//判断是否添加工艺
		int isBronzing=0,isConvex=0,isPvc=0,isUv=0,isMosha=0,isZhouwen=0;
		if (jsonObj.getString("isBronzing")!=null&jsonObj.getString("isBronzing")!="") {
			isBronzing=1;
		}else {
			isBronzing=0;
		}
			
		if (jsonObj.getString("isConvex")!=null&jsonObj.getString("isConvex")!="") {
			isConvex=1;
		}else{
			isConvex=0;
		} if (jsonObj.getString("isPvc")!=null&jsonObj.getString("isPvc")!="") {
			isPvc=1;

		}else{
			isPvc=0;
		} if (jsonObj.getString("isUv")!=null&jsonObj.getString("isUv")!="") {
			isUv=1;

		}else {
			isUv=0;
		}if (jsonObj.getString("isMosha")!=null&jsonObj.getString("isMosha")!="") {
			isMosha=1;
		}else {
			isMosha=0;
		}if (jsonObj.getString("isZhouwen")!=null&jsonObj.getString("isZhouwen")!="") {
			isZhouwen=1;
		}else {
			isZhouwen=0;
		}
		//0单色 1多色
		int pantonenumber=0;
		if (jsonObj.getString("colorFlag")!=""&&jsonObj.getString("colorFlag").equals("0")) {
				if (technologyDetailService.get(jsonObj.getString("printColorId"))!=null) {
					if (technologyDetailService.get(jsonObj.getString("printColorId")).getName().equals("专色")) {	
						pantonenumber=1;
					}else{						
						pantonenumber=0;
					}
					
				}											
		}
		//1.获取纸张尺寸信息
		int T=0;
    	List<LayoutSize> sizeList=layoutSizeService.getAllList();
    	Map<String,Object> sizeMap=new HashMap<String,Object>();
    	List<Integer> paperSize=new ArrayList<Integer>();	
    	for(LayoutSize size:sizeList){
    		if(size.getType()==0&&size.getName().equals("7")){
    			if (size.getSize()>420&size.getSize()<1000) {
    				paperSize.add(size.getSize());
				}
    			if(size.getSize()/2>420&size.getSize()/2<1000){
    				paperSize.add(size.getSize()/2);//表示纸张尺寸可2开	
    				
    			}
    			if (size.getSize()/3>420&size.getSize()/3<1000) {
    				paperSize.add(size.getSize()/3);//表示纸张尺寸可2开	
    				
				}
    		
    		}
    		if(size.getName().equals("0")){
    			sizeMap.put("zhjj", size.getSize());
    		}
    		if(size.getName().equals("1")){
    			sizeMap.put("xd", size.getSize());
    		}
    		if(size.getName().equals("2")){
    			sizeMap.put("yd", size.getSize());
    		}
    		if(size.getName().equals("3")){
    			sizeMap.put("tmin", size.getSize());
    		}
    		if(size.getName().equals("4")){
    			sizeMap.put("tmax", size.getSize());
    		}
    		if(size.getName().equals("5")){
    			sizeMap.put("gmin", size.getSize());
    		}
    		if(size.getName().equals("6")){
    			sizeMap.put("gmax", size.getSize());
    		}
    		if(size.getName().equals("8")){
    			sizeMap.put("zhjjmin", size.getSize());
    		}
    		if(size.getName().equals("9")){
    			sizeMap.put("xdmin", size.getSize());
    		}
    		if(size.getName().equals("10")){
    			sizeMap.put("ydmin", size.getSize());
    		}
    		if(size.getName().equals("11")){
    			sizeMap.put("t", size.getSize());
    			T=size.getSize();
    		}
    		if(size.getName().equals("12")){
    			sizeMap.put("g", size.getSize());
    		}
    	}
    	sizeMap.put("boxWidth", jsonObj.getString("boxWidth"));
    	sizeMap.put("boxLength", jsonObj.getString("boxLength"));
    	sizeMap.put("boxHeight", jsonObj.getString("boxHeight"));
    	sizeMap.put("isBronzing", isBronzing);
    	sizeMap.put("isConvex", isConvex);
    	sizeMap.put("isPvc", isPvc);
    	sizeMap.put("isUv",isUv);
    	//2.读取纸盒排版模式
    	int type = boxTypeService.get(jsonObj.getString("boxId")).getType();
    	//3.查询纸盒信息，lgai，ldi，lock相关信息
    	List<BoxClassification> boxClassifications = boxClassficationService.getAllList();
    	BoxType boxType = boxTypeService.get(jsonObj.getString("boxId"));
    	//4.进行排版预估
    	Map<String,Object> pmap=new PaibanType(paperSize,boxClassifications,boxType,type,sizeMap).getResult();
	   	for (Entry<String, Object> entry : pmap.entrySet()) { 
	   	  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
	   	}
		int length=Integer.parseInt(pmap.get("X")+"");//排版后的纸张的长度
	   	int width=Integer.parseInt(pmap.get("Y")+"");//排版后的纸张的宽度
		int xl=Integer.parseInt(pmap.get("M")+"");
		int yl=Integer.parseInt(pmap.get("N")+"");
		float utilizationRate=Float.parseFloat((pmap.get("P")+""));
		//5.查询size的x的id
		
		//6.存数据库
		System.out.println(length+"&&"+Double.valueOf(length)+"&&"+getDecimal(Double.valueOf(length))+
				"&&"+Double.valueOf(getDecimal(length)));
		
		//7.价格初始化
		int printnumber=Integer.parseInt(jsonObj.getString("printNumber"));//订做数量
		double c=(double)printnumber /(double)(xl * yl);
        c=(double) Math.ceil(c);
        double count=c*1.06;//根据用户定制个数和排版得出的纸张数目上加6%
        int papernumber = (int) Math.ceil(count);//纸张数量
        int cnumber=0;//印刷令数
        float pprice=0;//印刷纸质价格
        
       float colorbanfei=0;
       float colordanjia=0;
       float danprice=0;
       float zhuanprice=0;
       	
	    float  sprice=0;
	   	float bronzingdanjia=0;
	   	float bronzingkaiji=0;
	   	float bronzingqibu=0;
	   	float bronzingprice=0;
		float convexbanfei = 0;
		float convexdanjia=0;
	   	float convexKaiji=0;
	   	float convexprice=0;
	    float uvkaiji=0;
 		float uvqibu=0;
 		float uvdanjia=0;
 		float uvprice=0;
 		
		float pvcqibu=0;
		float pvcdanjia=0;
		float pvcprice=0;
		float moshakaiji=0;
		float moshaqibu=0;
		float moshadanjia=0;
		float moshaprice=0;
		float zhouwenkaiji=0;
		float zhouwenqibu=0;
		float zhouwendanjia=0;
		float zhouwenprice=0;
		
		float yawenqibu=0;
		float yawendanjia=0;
		float yawenbanfei=0;
		float yawenprice=0;
		float moqiebanfei=0;
		float moqieprice=0;
		
		float moqiedanjia=0;
		float zhanhedanjia=0;
		float zhanheprice=0;
		float baozhuangk=0;
		float baozhuang4k=0;
		float baozhuang9k=0;
		float baozhuang18k=0;
		float baozhuangdanjia=0;
		float transprice=0;
		float transdanjia=0;
		float baozhuangprice=0;//运输费
		String mid=null;
		  List<Map<String, Object>> price=technologyPriceService.getAllPriceList();
	        for(Map<String,Object> m:price){
	        	if(m.get("tid").equals(jsonObj.getString("printPaperId"))){
	        		pprice=Float.parseFloat(m.get("price").toString());
	        		mid=m.get("mid").toString();
	        	}
	        	 if(m.get("pname").equals("印刷颜色")){
	        		if(m.get("style").equals(mid)){
	        			if (m.get("tname").equals("单价")) {
		        			colordanjia=Float.parseFloat(m.get("price").toString());//25/50
						}else if (m.get("tname").equals("单色价格")) {
							danprice=Float.parseFloat(m.get("price").toString());//1(一个颜色40)
						}else if (m.get("tname").equals("专色价格")) {
							zhuanprice=Float.parseFloat(m.get("price").toString());//1.3(一个颜色40)
						}
	            	}if (m.get("tname").equals("版费")) {
						colorbanfei=Float.parseFloat(m.get("price").toString());//40(一个颜色40)
					}
	        	}
	           if(m.get("tid").equals(jsonObj.getString("surfaceTreatmentId"))){
	        		 sprice=Float.parseFloat(m.get("price").toString());
	        	}
	           if(m.get("pname").equals("烫金")){
		     	   if(m.get("tname").equals("单价")){
		     	    	bronzingdanjia=Float.parseFloat(m.get("price").toString());//开机费
		     	    }
		     	  if(m.get("tname").equals("起步价")){
		     	    	bronzingqibu=Float.parseFloat(m.get("price").toString());//开机费
		     	    }
		     	   if(m.get("tname").equals("开机费")){
		    	    	bronzingkaiji=Float.parseFloat(m.get("price").toString());
		    	    }
		     	  
	           }
	           if(m.get("pname").equals("击凸")){
		     	   if(m.get("tname").equals("版费")){
		    	    	convexbanfei=Float.parseFloat(m.get("price").toString());
		    	    }
		    	   if(m.get("tname").equals("开机费")){
		   	    	    convexKaiji=Float.parseFloat(m.get("price").toString());
		   	        }
		    	   if(m.get("tname").equals("单价")){
		   	    	    convexdanjia=Float.parseFloat(m.get("price").toString());
		   	        }
		    	  
	           }
	           if(m.get("pname").equals("UV")){
		    	   if(m.get("tname").equals("开机费")){
		    		    uvkaiji=Float.parseFloat(m.get("price").toString());
		   	       }
		   	       if(m.get("tname").equals("起步价")){
		   	    	    uvqibu=Float.parseFloat(m.get("price").toString());
		  	       }
		   	    if(m.get("tname").equals("单价")){
	   	    	    uvdanjia=Float.parseFloat(m.get("price").toString());
	  	       }
	           }
	           if(m.get("pname").equals("PVC")){
		   	      if(m.get("tname").equals("起步价")){
		   	    	    pvcqibu=Float.parseFloat(m.get("price").toString());
			       }
		   	   if(m.get("tname").equals("单价")){
	   	    	    pvcdanjia=Float.parseFloat(m.get("price").toString());
		       }
	           }
	           if(m.get("pname").equals("磨砂")){
	        	   if(m.get("tname").equals("开机费")){
		   	    	    moshakaiji=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("单价")){
		   	    	    moshadanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("起步价")){
		   	    	    moshaqibu=Float.parseFloat(m.get("price").toString());
			       }
			}
	           if(m.get("pname").equals("皱纹")){
	        	   if(m.get("tname").equals("开机费")){
		   	    	    zhouwenkaiji=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("单价")){
		   	    	    zhouwendanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("起步价")){
		   	    	    zhouwenqibu=Float.parseFloat(m.get("price").toString());
			       }
			}
	           if(m.containsKey("mid")&&m.get("mid").equals(jsonObj.getString("yawenId"))){
	        	   if(m.get("mname").equals("压普纹")){
	        			if(m.get("tname").equals("起步价")){
		   	    	    yawenqibu=Float.parseFloat(m.get("price").toString());
			       }if(m.get("tname").equals("版费")){
		   	    	    yawenbanfei=Float.parseFloat(m.get("price").toString());
			       }if(m.get("tname").equals("单价")){
		   	    	    yawendanjia=Float.parseFloat(m.get("price").toString());
			       }}
	        	   if(m.get("mname").equals("压光纹")){
	        		   if(m.get("tname").equals("起步价")){
			   	    	    yawenqibu=Float.parseFloat(m.get("price").toString());
				       }if(m.get("tname").equals("版费")){
			   	    	    yawenbanfei=Float.parseFloat(m.get("price").toString());
				       }if(m.get("tname").equals("单价")){
			   	    	    yawendanjia=Float.parseFloat(m.get("price").toString());
				       }
				       }
	        	}
	           if (m.get("pid").equals(jsonObj.getString("moqieId"))) {
	        	   
	        	   if(m.get("pname").equals("模切")){
	        			if(m.get("tname").equals("版费")){
		   	    	    moqiebanfei=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("单价")){
		   	    	    moqiedanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   }
			}
	           if (m.containsKey("mid")&&m.get("mid").equals(jsonObj.getString("zhanheId"))) {
	        	  
	        	   if(m.get("mname").equals("机器粘盒")){
	        			   if(m.get("tname").equals("单价")){
		   	    	    zhanhedanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   }if(m.get("mname").equals("人工粘盒")){
        			   if(m.get("tname").equals("单价")){
	   	    	    zhanhedanjia=Float.parseFloat(m.get("price").toString());
        			   }
	        	   }
			}
	           if (m.containsKey("mid")&&m.get("mid").equals(jsonObj.getString("baozhuangId"))) {
	        	   if(m.get("mname").equals("牛皮纸")){
	        		   if(m.get("tname").equals("单价")){
			   	    	    baozhuangdanjia=Float.parseFloat(m.get("price").toString());
				       }  
	        	   }
	        	   if(m.get("mname").equals("纸箱")){
	        		   if(m.get("tname").equals("单价")){
			   	    	    baozhuangdanjia=Float.parseFloat(m.get("price").toString());
				       }  
	        	   }
	           }
	        	   if(m.get("pname").equals("包装")){
	        		   if (m.get("tname").equals("4k")) {
						baozhuang4k=Float.parseFloat(m.get("price").toString());
					}
	        		   if (m.get("tname").equals("9k")) {
							baozhuang9k=Float.parseFloat(m.get("price").toString());
						}
	        		   if (m.get("tname").equals("18k")) {
							baozhuang18k=Float.parseFloat(m.get("price").toString());
						}
		   	    	 
			       
	        	  
			}
	   	      if(m.get("tid").equals(jsonObj.getString("receiveAreaId"))){
	   	    	if(m.get("tname").equals("珠三角以内")){
	   	    		
	   	    	    transdanjia=Float.parseFloat(m.get("price").toString());
	   	    		
		       }if(m.get("tname").equals("珠三角以外")){ 
	   	    	    transdanjia=Float.parseFloat(m.get("price").toString());
		         
	           }
	   	    
	   	      }
	   }
		
	    	//1.计算纸张价格
	          TechnologyDetail t = technologyDetailService.get(jsonObj.getString("paperGramsId"));
	   		  float papernum=Float.parseFloat(t.getName());
	   		  float  paperprice=length*width*papernum*pprice/1000000*papernumber/1000000;
	   	      
	   	      System.out.println("纸质价格"+paperprice);
		
	   	      //2.印刷费
//			   	   正一个颜色就40元
//			   	普通纸：25元/色令
//			   	特殊纸：50元/色令
//			   	CMYK算4个颜色，1个专色按1.3来算。

	   	   float c1=papernumber*length*width/787/1092/500;//正度纸/500=总令数
			 cnumber=(int) Math.ceil(c1);
			 if(cnumber<5){
				 cnumber=5;
			 }
			 float printprice=0;
			 
			 if (jsonObj.getString("colorFlag").equals("0")) {
					//0单色1多色
			   		printprice=(float) (colorbanfei*1+colordanjia*cnumber*(1*danprice+zhuanprice*pantonenumber));
				}else {					
					printprice=(float) (colorbanfei*(4+pantonenumber)+colordanjia*cnumber*(4*danprice+zhuanprice*pantonenumber));
				}			
		     System.out.println("印刷价格："+getDecimal(printprice));
	   	  
		  //3.表面处理价格
		   	 float surfaceprice=length*width*sprice*papernumber/1000000;
			 
			 System.out.println("表面价格："+getDecimal(surfaceprice));
	      //4.烫金价格：加工费用：长*宽*排版个数*6*纸张数量+开机费（如果长*宽*6得出的结果少于0.15，则按0.15计算）
//					 * 烫版费：烫面积*0.3元*排版个数（长宽各加1.5cm计算）
//					 烫面积：长*宽（长宽各加1.5cm计算）
//					 计算方式：加工费用+烫版费

		     if(isBronzing==1){
	 	        
		    	float a0= Float.parseFloat(jsonObj.getString("bronzingLength"))+15;
		    	float a1=Float.parseFloat(jsonObj.getString("bronzingWidth"))+15;
		    	if (a0*a1*bronzingdanjia/1000000<bronzingqibu) {//
		        	 bronzingprice=(float) (papernumber*bronzingqibu*(xl*yl)+bronzingkaiji+a0*a1/1000000*0.3*(xl*yl)); 
				}else {
					bronzingprice=(float) (papernumber*a0*a1*bronzingdanjia*(xl*yl)/1000000+bronzingkaiji+a0*a1/1000000*0.3*(xl*yl)); 
				}
		         
		     }else{
		    	 bronzingprice=0;
		     }
		      System.out.println("烫金价格:"+getDecimal(bronzingprice));
		   //5.击凸价格:（长+1.5cm）*(宽+1.5cm)*0.3元*2*排版个数+150+0.05*印张数量 
//				      （如果长*宽*0.01得出的结果少于0.05，则按0.05计算）版费+开机费+加工费
		    if(isConvex==1){
		 	    float b0= Float.parseFloat(jsonObj.getString("convexLength"))+15;
		 	    float b1=Float.parseFloat(jsonObj.getString("convexWidth"))+15;
		 	    convexprice=(float) (b0*b1*convexbanfei*(xl*yl)*2/1000000+convexKaiji+convexdanjia*papernumber);
			   
		     }else{
		    	 convexprice=0;
		     }
		    
		      System.out.println("击凸价格:"+getDecimal(convexprice));
		    //6.UV价格
//				      局部UV 4.5元/㎡ ，起步价0.18元/印张
//				      开机费：180元
//				      * 计算方法：
//				      长*宽*4.5*纸张数量+开机费（如果长*宽*4.5得出的结果少于0.18，则按0.18计算）

		    if(isUv==1){
		 	    float c0= Float.parseFloat(jsonObj.getString("uvLength"));
		 	    float c2=Float.parseFloat(jsonObj.getString("uvWidth"));
		 	    if (c0*c2*uvdanjia/1000000<uvqibu) {
			    	uvprice=uvqibu*papernumber+uvkaiji;
				}else {
					uvprice=c0*c2*uvdanjia*papernumber/1000000+uvkaiji;
				}
			    
		     }else{
		    	 uvprice=0;
		     }
		   
		      System.out.println("UV价格结果："+getDecimal(uvprice));
		    //7.PVC价格
//				      4.5元/㎡ 统一收费
//				      起步价0.05元/个窗
//				      例：窗口面积*3.5*窗口个数(盒子数量)=加工费用（长宽各加1.5cm计算）
//				      窗口面积（长+1.5cm）*（宽+1.5cm）*4.5元*窗口个数（如果（长+1.5cm）*（宽+1.5cm）*3.5得出的结果少于0.05，则按0.05计算
		    if(isPvc==1){
		 	    float d0= Float.parseFloat(jsonObj.getString("pvcLength"))+15;			 
		 	    float d1=Float.parseFloat(jsonObj.getString("pvcWidth"))+15;
		 	    if (d0*d1*pvcdanjia/1000000<pvcqibu) {
			    	 pvcprice=pvcqibu*printnumber;
				}else {
					pvcprice=d0*d1*pvcdanjia*printnumber/1000000;
				}
			    
		     }else{
		    	 pvcprice=0;
		     }
		     
		     System.out.println("PVC价格结果："+getDecimal(pvcprice));
		     //8.磨砂
//				     4.5元/㎡，起步价0.25元/印张，开机费250元；
//				     计算方式：
//				     长*宽*4.5*印张数量+开机费（如果长*宽*4.5得出的结果少于0.25，则按0.25计算）
		     if(isMosha==1){
			 	    float e0= Float.parseFloat(jsonObj.getString("moshaLength"));
				    float e1=Float.parseFloat(jsonObj.getString("moshaWidth"));
				    if (e0*e1*moshadanjia/1000000<moshaqibu) {
				    	 moshaprice=moshaqibu*papernumber+moshakaiji;
					}else {
						moshaprice=e0*e1*moshadanjia*papernumber/1000000+moshakaiji;
					}
				    
			     }else{
			    	 moshaprice=0;
			     }
		     System.out.println("磨砂价格结果："+getDecimal(moshaprice));
		     //9.皱纹
//				     8元/㎡，起步价0.3元/印张，开机费300元；
//				     计算方式：
//				     长*宽*8*印张数量+开机费（如果长*宽*8得出的结果少于0.3，则按0.3计算

		     if(isZhouwen==1){
			 	    float f0= Float.parseFloat(jsonObj.getString("zhouwenLength"));
				    float f1=Float.parseFloat(jsonObj.getString("zhouwenWidth"));
				    if (f0*f1*zhouwendanjia/1000000<zhouwenqibu) {
				    	 zhouwenprice=zhouwenqibu*papernumber+zhouwenkaiji;
					}else {
						zhouwenprice=f0*f1*zhouwendanjia*papernumber/1000000+zhouwenkaiji;
					}
				    
			     }else{
			    	 zhouwenprice=0;
			     }
		     System.out.println("磨砂价格结果："+getDecimal(zhouwenprice));
		     //10.压纹
//				     * 版费1000元
//				     计算方式：
//				     （印制数量*0.2+1000）如果印张数量*0.2不足300元，那就直接：300+1000
		     if (papernumber*moqiedanjia<yawenqibu) {
				yawenprice=yawenqibu+yawenbanfei;
			}else {
				yawenprice=papernumber*yawendanjia+yawenbanfei;
			}
		     System.out.println("压纹价格结果："+getDecimal(yawenprice));
//				     11.模切（啤）：
//				     0.03元/印张
//				     * 版费： 统一收费：500
//				     计算方式：
//				     0.03*印张数量+500元
		    
		     	moqieprice=papernumber*moqiedanjia+moqiebanfei;
				
			     System.out.println("模切价格结果："+getDecimal(moqieprice));
		    
//				     12.粘盒：
//				     计算方式：
//				     0.05*用户输入的那个盒子总数
		     zhanheprice=zhanhedanjia*printnumber;
		     System.out.println("粘盒价格结果："+getDecimal(zhanheprice));
//				     13.包装：
//				     牛皮纸包装1元/包（4K盒或以下50个/包，9K盒200个/包，18K盒600个/包）
//				     纸箱6元/箱（数量同牛皮纸包装）
//				     计算方式：(纸张数量*盒子数量（每印张）/50)*1（4K盒以下）
//				     (纸张数量*盒子数量（每印张）/200)*1 （4k-9K盒）
//				     (纸张数量*盒子数量（每印张）/600)*1 （9k-18k）
		     int bao=0;
		     if (xl*yl<=4) {	
				baozhuangk=baozhuang4k;
			}else if (xl*yl>4&xl*yl<=9) {	
				baozhuangk=baozhuang9k;
			}else if (xl*yl>9&xl*yl<=18) {	
				baozhuangk=baozhuang18k;
			}
		     bao=(int) (printnumber/baozhuangk);
		     baozhuangprice=(papernumber*xl*yl/baozhuangk)*baozhuangdanjia;
		     System.out.println("包装价格结果："+getDecimal(baozhuangprice));
//				     14、运输费
//				     珠三角以内200元/吨，轻货100元/m³
		     transprice=transdanjia*papernumber*papernum/1000000;
		     System.out.println("运输费价格结果："+getDecimal(transprice));
		     //先获取利率值
		     List<ShoppingRate> rateList=shoppingRateService.getAllList();
			 double manp=0;//0管理费利率
			 double taxp=0;// 1 税费利率
			 double res=0;//2总价利率 
	         for(ShoppingRate rate:rateList){
	        	 if(rate.getType()==0){
	        		 manp=rate.getValue()/100.00;
	        		
	        	 }
	        	 if(rate.getType()==1){
	        		 taxp=rate.getValue()/100.00;
	        		
	        	 }
	        	 if(rate.getType()==2){
	        		 res=rate.getValue()/100.00;
	        	 }
	         }
	         
	       //统计费用
	 	    float sum=paperprice+printprice+surfaceprice+bronzingprice+convexprice+uvprice+pvcprice+
	 	    		moshaprice+zhouwenprice+yawenprice+moqieprice+baozhuangprice+transprice;
	 	    System.out.println("结果："+sum);
	 	    double tax=sum*taxp;
	 	    double manag=sum*manp;
	 	    double totalprice=(sum+ manag+ tax)*res;
	 	    double unitprice=totalprice/printnumber;
	 	   JSONObject jsonObject=new JSONObject();
	 	   jsonObject.put("totalprice", getDecimal(totalprice));
	 	   jsonObject.put("unitprice", getDecimal(unitprice));
	 	   return jsonObject.toJSONString();
	}
	/**
	 * 生成订单，排版
	 * createShoppingandLayout:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param params
	 * @return
	 * @since JDK 1.8
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.POST, value = "/createShoppingandLayout")
    @ResponseBody
    public String createShoppingandLayout(@Param(value = "params") String params,HttpServletRequest request){
		JSONObject jsonObj = JSONObject.parseObject(params);
		/*MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpRequest;*/
    	LayoutDetail layoutDetail = new LayoutDetail();
		layoutDetail.setId(Sequence.nextId());
		layoutDetail.setBoxId(jsonObj.getString("boxId"));
    	layoutDetail.setBoxLength(Double.parseDouble(jsonObj.getString("boxLength")));
		layoutDetail.setBoxWidth(Double.parseDouble(jsonObj.getString("boxWidth")));
		layoutDetail.setBoxHeight(Double.parseDouble(jsonObj.getString("boxHeight")));
		layoutDetail.setBoxUnit(0);
		layoutDetail.setCreateby(SecurityUtil.getUser().getId());
		layoutDetail.setCreatetime(DateUtil.getCurrDate());
		layoutDetail.setPaperUnit(0);
		
		ShoppingDetail shoppingDetail = new ShoppingDetail();
		shoppingDetail.setShoppingId(Sequence.nextId());
		shoppingDetail.setBoxId(jsonObj.getString("boxId"));
		shoppingDetail.setLayoutId(layoutDetail.getId());
		shoppingDetail.setUserId(SecurityUtil.getUser().getId());
		shoppingDetail.setEnabled(1);
		shoppingDetail.setCreateby(SecurityUtil.getUser().getId());
		shoppingDetail.setCreatetime(DateUtil.getCurrDate());
		shoppingDetail.setPrintPaperId(jsonObj.getString("printPaperId"));
		shoppingDetail.setPaperGramsId(jsonObj.getString("paperGramsId"));
		
		
		ShoppingPantone shoppingPantone=new ShoppingPantone();
		//0单色 1多色
		int pantonenumber=0;
		if (jsonObj.getString("colorFlag")!="") {
			if (jsonObj.getString("colorFlag").equals("0")) {
				shoppingDetail.setPrintColorId(jsonObj.getString("printColorId"));
				if (technologyDetailService.get(shoppingDetail.getPrintColorId())!=null) {
					if (technologyDetailService.get(shoppingDetail.getPrintColorId()).getName().equals("专色")) {
						shoppingPantone.setId(Sequence.nextId());
						shoppingPantone.setColorNum("1");
						shoppingPantone.setAttr1(jsonObj.getString("attr1"));
						shoppingPantoneService.save(shoppingPantone);
						shoppingDetail.setPantoneId(shoppingPantone.getId());
						pantonenumber=Integer.parseInt(shoppingPantone.getColorNum());
					}else{
						
						pantonenumber=0;
					}
					
				}
				
				
				
			}else{
				shoppingDetail.setPrintColorId(jsonObj.getString("printColor"));
				shoppingPantone.setId(Sequence.nextId());
				shoppingPantone.setColorNum(jsonObj.getString("colorNumber"));
				shoppingPantone.setAttr1(jsonObj.getString("attr1"));
				shoppingPantone.setAttr1(jsonObj.getString("attr2"));
				shoppingPantone.setAttr1(jsonObj.getString("attr3"));
				shoppingPantoneService.save(shoppingPantone);
				shoppingDetail.setPantoneId(shoppingPantone.getId());
			}
			
		}
		shoppingDetail.setSurfaceTreatmentId(jsonObj.getString("surfaceTreatmentId"));
		if (jsonObj.getString("isBronzing")!=null&jsonObj.getString("isBronzing")!="") {
			shoppingDetail.setIsBronzing(1);
			shoppingDetail.setBronzingLength(Double.parseDouble(jsonObj.getString("bronzingLength")));
			shoppingDetail.setBronzingWidth(Double.parseDouble(jsonObj.getString("bronzingWidth")));
			shoppingDetail.setBronzingUnit(0);
		}else {
			shoppingDetail.setIsBronzing(0);
			shoppingDetail.setBronzingLength(0.0);
			shoppingDetail.setBronzingWidth(0.0);
			shoppingDetail.setBronzingUnit(0);
		}
			
		if (jsonObj.getString("isConvex")!=null&jsonObj.getString("isConvex")!="") {
			shoppingDetail.setIsConvex(1);
			shoppingDetail.setConvexLength(Double.parseDouble(jsonObj.getString("convexLength")));
			shoppingDetail.setConvexWidth(Double.parseDouble(jsonObj.getString("convexWidth")));
			//shoppingDetail.setConvexUnit(Integer.valueOf(jsonObj.getString("convexUnit")));
			shoppingDetail.setConvexUnit(0);
		}else{
			shoppingDetail.setIsConvex(0);
			shoppingDetail.setConvexLength(0.0);
			shoppingDetail.setConvexWidth(0.0);
			shoppingDetail.setConvexUnit(0);
		} if (jsonObj.getString("isPvc")!=null&jsonObj.getString("isPvc")!="") {
			shoppingDetail.setIsPvc(1);
			shoppingDetail.setPvcLength(Double.parseDouble(jsonObj.getString("pvcLength")));
			shoppingDetail.setPvcWidth(Double.parseDouble(jsonObj.getString("pvcWidth")));
			//shoppingDetail.setPvcUnit(Integer.valueOf(jsonObj.getString("pvcUnit")));
			shoppingDetail.setPvcUnit(0);

		}else{
			shoppingDetail.setIsPvc(0);
			shoppingDetail.setPvcLength(0.0);
			shoppingDetail.setPvcWidth(0.0);
			shoppingDetail.setPvcUnit(0);
		} if (jsonObj.getString("isUv")!=null&jsonObj.getString("isUv")!="") {
			shoppingDetail.setIsUv(1);
			shoppingDetail.setUvLength(Double.parseDouble(jsonObj.getString("uvLength")));
			shoppingDetail.setUvWidth(Double.parseDouble(jsonObj.getString("uvWidth")));
			//shoppingDetail.setUvUnit(Integer.valueOf(jsonObj.getString("uvUnit")));
			shoppingDetail.setUvUnit(0);

		}else {
			shoppingDetail.setIsUv(0);
			shoppingDetail.setUvLength(0.0);
			shoppingDetail.setUvWidth(0.0);
			shoppingDetail.setUvUnit(0);
		}if (jsonObj.getString("isMosha")!=null&jsonObj.getString("isMosha")!="") {
			shoppingDetail.setIsMosha(1);
			shoppingDetail.setMoshaLength(Double.parseDouble(jsonObj.getString("moshaLength")));
			shoppingDetail.setMoshaWidth(Double.parseDouble(jsonObj.getString("moshaWidth")));
			//shoppingDetail.setMoshaUnit(Integer.valueOf(jsonObj.getString("moshaUnit")));
			shoppingDetail.setMoshaUnit(0);
		}else {
			shoppingDetail.setIsMosha(0);
			shoppingDetail.setMoshaLength(0.0);
			shoppingDetail.setMoshaWidth(0.0);
			shoppingDetail.setMoshaUnit(0);
		}if (jsonObj.getString("isZhouwen")!=null&jsonObj.getString("isZhouwen")!="") {
			shoppingDetail.setIsZhouwen(1);
			shoppingDetail.setZhouwenLength(Double.parseDouble(jsonObj.getString("zhouwenLength")));
			shoppingDetail.setZhouwenWidth(Double.parseDouble(jsonObj.getString("zhouwenWidth")));
			shoppingDetail.setZhouwenUnit(0);
		}else {
			shoppingDetail.setIsZhouwen(0);
			shoppingDetail.setZhouwenLength(0.0);
			shoppingDetail.setZhouwenWidth(0.0);
			shoppingDetail.setZhouwenUnit(0);
		}
		shoppingDetail.setBaozhuangId(jsonObj.getString("baozhuangId"));
		shoppingDetail.setYawenId(jsonObj.getString("yawenId"));
		shoppingDetail.setZhanheId(jsonObj.getString("zhanheId"));
		shoppingDetail.setMoqieId(jsonObj.getString("moqieId"));
		shoppingDetail.setReceiveAreaId(jsonObj.getString("receiveAreaId"));
		shoppingDetail.setPrintNumber(Integer.valueOf(jsonObj.getString("printNumber")));
		
		//排版（生成排版图片，dxf文件上传）
		//1.获取纸张尺寸信息
		int T=0;
    	List<LayoutSize> sizeList=layoutSizeService.getAllList();
    	Map<String,Object> sizeMap=new HashMap<String,Object>();
    	List<Integer> paperSize=new ArrayList<Integer>();	
    	for(LayoutSize size:sizeList){
    		if(size.getType()==0&&size.getName().equals("7")){
    			if (size.getSize()>420&size.getSize()<1000) {
    				paperSize.add(size.getSize());
				}
    			if(size.getSize()/2>420&size.getSize()/2<1000){
    				paperSize.add(size.getSize()/2);//表示纸张尺寸可2开	
    				
    			}
    			if (size.getSize()/3>420&size.getSize()/3<1000) {
    				paperSize.add(size.getSize()/3);//表示纸张尺寸可2开	
    				
				}
    		
    		}
    		if(size.getName().equals("0")){
    			sizeMap.put("zhjj", size.getSize());
    		}
    		if(size.getName().equals("1")){
    			sizeMap.put("xd", size.getSize());
    		}
    		if(size.getName().equals("2")){
    			sizeMap.put("yd", size.getSize());
    		}
    		if(size.getName().equals("3")){
    			sizeMap.put("tmin", size.getSize());
    		}
    		if(size.getName().equals("4")){
    			sizeMap.put("tmax", size.getSize());
    		}
    		if(size.getName().equals("5")){
    			sizeMap.put("gmin", size.getSize());
    		}
    		if(size.getName().equals("6")){
    			sizeMap.put("gmax", size.getSize());
    		}
    		if(size.getName().equals("8")){
    			sizeMap.put("zhjjmin", size.getSize());
    		}
    		if(size.getName().equals("9")){
    			sizeMap.put("xdmin", size.getSize());
    		}
    		if(size.getName().equals("10")){
    			sizeMap.put("ydmin", size.getSize());
    		}
    		if(size.getName().equals("11")){
    			sizeMap.put("t", size.getSize());
    			T=size.getSize();
    		}
    		if(size.getName().equals("12")){
    			sizeMap.put("g", size.getSize());
    		}
    	}
    	sizeMap.put("boxWidth", layoutDetail.getBoxWidth());
    	sizeMap.put("boxLength", layoutDetail.getBoxLength());
    	sizeMap.put("boxHeight", layoutDetail.getBoxHeight());
    	sizeMap.put("isBronzing", shoppingDetail.getIsBronzing());
    	sizeMap.put("isConvex", shoppingDetail.getIsConvex());
    	sizeMap.put("isPvc", shoppingDetail.getIsPvc());
    	sizeMap.put("isUv", shoppingDetail.getIsUv());
    	//2.读取纸盒排版模式
    	int type = boxTypeService.get(layoutDetail.getBoxId()).getType();
    	//3.查询纸盒信息，lgai，ldi，lock相关信息
    	List<BoxClassification> boxClassifications = boxClassficationService.getAllList();
    	BoxType boxType = boxTypeService.get(layoutDetail.getBoxId());
    	//4.进行排版预估
    	Map<String,Object> pmap=new PaibanType(paperSize,boxClassifications,boxType,type,sizeMap).getResult();
	   	for (Entry<String, Object> entry : pmap.entrySet()) { 
	   	  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
	   	}
		int length=Integer.parseInt(pmap.get("X")+"");//排版后的纸张的长度
	   	int width=Integer.parseInt(pmap.get("Y")+"");//排版后的纸张的宽度
		int xl=Integer.parseInt(pmap.get("M")+"");
		int yl=Integer.parseInt(pmap.get("N")+"");
		float utilizationRate=Float.parseFloat((pmap.get("P")+""));
		//5.查询size的x的id
		for(LayoutSize size:sizeList){
			if (size.getSize()==length||size.getSize()/2==length||
					size.getSize()/3==length) {
				layoutDetail.setPaperXId(size.getId());
			}
		}
		//6.存数据库
		System.out.println(length+"&&"+Double.valueOf(length)+"&&"+getDecimal(Double.valueOf(length))+
				"&&"+Double.valueOf(getDecimal(length)));
		layoutDetail.setPaperLength(getDecimal(Double.valueOf(length)));
		layoutDetail.setPaperWidth(getDecimal(Double.valueOf(width)));
		layoutDetail.setBoxUnit(0);
		layoutDetail.setUtilizationRate(String.valueOf(utilizationRate));
		layoutDetail.setXnumber(xl);
		layoutDetail.setYnumber(yl);
		//7.价格初始化
		int printnumber=shoppingDetail.getPrintNumber();//订做数量
		double c=(double)printnumber /(double)(xl * yl);
        c=(double) Math.ceil(c);
        double count=c*1.06;//根据用户定制个数和排版得出的纸张数目上加6%
        int papernumber = (int) Math.ceil(count);//纸张数量
        int cnumber=0;//印刷令数
        float pprice=0;//印刷纸质价格
        
       float colorbanfei=0;
       float colordanjia=0;
       float danprice=0;
       float zhuanprice=0;
       	
	    float  sprice=0;
	   	float bronzingdanjia=0;
	   	float bronzingkaiji=0;
	   	float bronzingqibu=0;
	   	float bronzingprice=0;
		float convexbanfei = 0;
		float convexdanjia=0;
	   	float convexKaiji=0;
	   	float convexprice=0;
	    float uvkaiji=0;
 		float uvqibu=0;
 		float uvdanjia=0;
 		float uvprice=0;
 		
		float pvcqibu=0;
		float pvcdanjia=0;
		float pvcprice=0;
		float moshakaiji=0;
		float moshaqibu=0;
		float moshadanjia=0;
		float moshaprice=0;
		float zhouwenkaiji=0;
		float zhouwenqibu=0;
		float zhouwendanjia=0;
		float zhouwenprice=0;
		
		float yawenqibu=0;
		float yawendanjia=0;
		float yawenbanfei=0;
		float yawenprice=0;
		float moqiebanfei=0;
		float moqieprice=0;
		
		float moqiedanjia=0;
		float zhanhedanjia=0;
		float zhanheprice=0;
		float baozhuangk=0;
		float baozhuang4k=0;
		float baozhuang9k=0;
		float baozhuang18k=0;
		float baozhuangdanjia=0;
		float transprice=0;
		float transdanjia=0;
		float baozhuangprice=0;//运输费
		String mid=null;
		  List<Map<String, Object>> price=technologyPriceService.getAllPriceList();
	        for(Map<String,Object> m:price){
	        	if(m.get("tid").equals(shoppingDetail.getPrintPaperId())){
	        		pprice=Float.parseFloat(m.get("price").toString());
	        		mid=m.get("mid").toString();
	        	}
	        	 if(m.get("pname").equals("印刷颜色")){
	        		if(m.get("style").equals(mid)){
	        			if (m.get("tname").equals("单价")) {
		        			colordanjia=Float.parseFloat(m.get("price").toString());//25/50
						}else if (m.get("tname").equals("单色价格")) {
							danprice=Float.parseFloat(m.get("price").toString());//1(一个颜色40)
						}else if (m.get("tname").equals("专色价格")) {
							zhuanprice=Float.parseFloat(m.get("price").toString());//1.3(一个颜色40)
						}
	            	}if (m.get("tname").equals("版费")) {
						colorbanfei=Float.parseFloat(m.get("price").toString());//40(一个颜色40)
					}
	        	}
	           if(m.get("tid").equals(shoppingDetail.getSurfaceTreatmentId())){
	        		 sprice=Float.parseFloat(m.get("price").toString());
	        	}
	           if(m.get("pname").equals("烫金")){
		     	   if(m.get("tname").equals("单价")){
		     	    	bronzingdanjia=Float.parseFloat(m.get("price").toString());//开机费
		     	    }
		     	  if(m.get("tname").equals("起步价")){
		     	    	bronzingqibu=Float.parseFloat(m.get("price").toString());//开机费
		     	    }
		     	   if(m.get("tname").equals("开机费")){
		    	    	bronzingkaiji=Float.parseFloat(m.get("price").toString());
		    	    }
		     	  
	           }
	           if(m.get("pname").equals("击凸")){
		     	   if(m.get("tname").equals("版费")){
		    	    	convexbanfei=Float.parseFloat(m.get("price").toString());
		    	    }
		    	   if(m.get("tname").equals("开机费")){
		   	    	    convexKaiji=Float.parseFloat(m.get("price").toString());
		   	        }
		    	   if(m.get("tname").equals("单价")){
		   	    	    convexdanjia=Float.parseFloat(m.get("price").toString());
		   	        }
		    	  
	           }
	           if(m.get("pname").equals("UV")){
		    	   if(m.get("tname").equals("开机费")){
		    		    uvkaiji=Float.parseFloat(m.get("price").toString());
		   	       }
		   	       if(m.get("tname").equals("起步价")){
		   	    	    uvqibu=Float.parseFloat(m.get("price").toString());
		  	       }
		   	    if(m.get("tname").equals("单价")){
	   	    	    uvdanjia=Float.parseFloat(m.get("price").toString());
	  	       }
	           }
	           if(m.get("pname").equals("PVC")){
		   	      if(m.get("tname").equals("起步价")){
		   	    	    pvcqibu=Float.parseFloat(m.get("price").toString());
			       }
		   	   if(m.get("tname").equals("单价")){
	   	    	    pvcdanjia=Float.parseFloat(m.get("price").toString());
		       }
	           }
	           if(m.get("pname").equals("磨砂")){
	        	   if(m.get("tname").equals("开机费")){
		   	    	    moshakaiji=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("单价")){
		   	    	    moshadanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("起步价")){
		   	    	    moshaqibu=Float.parseFloat(m.get("price").toString());
			       }
			}
	           if(m.get("pname").equals("皱纹")){
	        	   if(m.get("tname").equals("开机费")){
		   	    	    zhouwenkaiji=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("单价")){
		   	    	    zhouwendanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("起步价")){
		   	    	    zhouwenqibu=Float.parseFloat(m.get("price").toString());
			       }
			}
	           if(m.containsKey("mid")&&m.get("mid").equals(shoppingDetail.getYawenId())){
	        	   if(m.get("mname").equals("压普纹")){
	        			if(m.get("tname").equals("起步价")){
		   	    	    yawenqibu=Float.parseFloat(m.get("price").toString());
			       }if(m.get("tname").equals("版费")){
		   	    	    yawenbanfei=Float.parseFloat(m.get("price").toString());
			       }if(m.get("tname").equals("单价")){
		   	    	    yawendanjia=Float.parseFloat(m.get("price").toString());
			       }}
	        	   if(m.get("mname").equals("压光纹")){
	        		   if(m.get("tname").equals("起步价")){
			   	    	    yawenqibu=Float.parseFloat(m.get("price").toString());
				       }if(m.get("tname").equals("版费")){
			   	    	    yawenbanfei=Float.parseFloat(m.get("price").toString());
				       }if(m.get("tname").equals("单价")){
			   	    	    yawendanjia=Float.parseFloat(m.get("price").toString());
				       }
				       }
	        	}
	           if (m.get("pid").equals(shoppingDetail.getMoqieId())) {
	        	   
	        	   if(m.get("pname").equals("模切")){
	        			if(m.get("tname").equals("版费")){
		   	    	    moqiebanfei=Float.parseFloat(m.get("price").toString());
			       }
	        	   if(m.get("tname").equals("单价")){
		   	    	    moqiedanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   }
			}
	           if (m.containsKey("mid")&&m.get("mid").equals(shoppingDetail.getZhanheId())) {
	        	  
	        	   if(m.get("mname").equals("机器粘盒")){
	        			   if(m.get("tname").equals("单价")){
		   	    	    zhanhedanjia=Float.parseFloat(m.get("price").toString());
			       }
	        	   }if(m.get("mname").equals("人工粘盒")){
        			   if(m.get("tname").equals("单价")){
	   	    	    zhanhedanjia=Float.parseFloat(m.get("price").toString());
        			   }
	        	   }
			}
	           if (m.containsKey("mid")&&m.get("mid").equals(shoppingDetail.getBaozhuangId())) {
	        	   if(m.get("mname").equals("牛皮纸")){
	        		   if(m.get("tname").equals("单价")){
			   	    	    baozhuangdanjia=Float.parseFloat(m.get("price").toString());
				       }  
	        	   }
	        	   if(m.get("mname").equals("纸箱")){
	        		   if(m.get("tname").equals("单价")){
			   	    	    baozhuangdanjia=Float.parseFloat(m.get("price").toString());
				       }  
	        	   }
	           }
	        	   if(m.get("pname").equals("包装")){
	        		   if (m.get("tname").equals("4k")) {
						baozhuang4k=Float.parseFloat(m.get("price").toString());
					}
	        		   if (m.get("tname").equals("9k")) {
							baozhuang9k=Float.parseFloat(m.get("price").toString());
						}
	        		   if (m.get("tname").equals("18k")) {
							baozhuang18k=Float.parseFloat(m.get("price").toString());
						}
		   	    	 
			       
	        	  
			}
	   	      if(m.get("tid").equals(shoppingDetail.getReceiveAreaId())){
	   	    	if(m.get("tname").equals("珠三角以内")){
	   	    		
	   	    	    transdanjia=Float.parseFloat(m.get("price").toString());
	   	    		
		       }if(m.get("tname").equals("珠三角以外")){ 
	   	    	    transdanjia=Float.parseFloat(m.get("price").toString());
		         
	           }
	   	    
	   	      }
	   }
		
	    	//1.计算纸张价格
	          TechnologyDetail t = technologyDetailService.get(shoppingDetail.getPaperGramsId());
	   		  float papernum=Float.parseFloat(t.getName());
	   		  float  paperprice=length*width*papernum*pprice/1000000*papernumber/1000000;
	   	      
	   	      System.out.println("纸质价格"+paperprice);
	   	      shoppingDetail.setPaperPrice(getDecimal(Double.valueOf(paperprice)));
		
	   	      //2.印刷费
//	   	   正一个颜色就40元
//	   	普通纸：25元/色令
//	   	特殊纸：50元/色令
//	   	CMYK算4个颜色，1个专色按1.3来算。

	   	   float c1=papernumber*length*width/787/1092/500;//正度纸/500=总令数
			 cnumber=(int) Math.ceil(c1);
			 if(cnumber<5){
				 cnumber=5;
			 }
			 float printprice=0;
			 
			 if (jsonObj.getString("colorFlag").equals("0")) {
					//0单色1多色
			   		printprice=(float) (colorbanfei*1+colordanjia*cnumber*(1*danprice+zhuanprice*pantonenumber));
				}else {					
					printprice=(float) (colorbanfei*(4+pantonenumber)+colordanjia*cnumber*(4*danprice+zhuanprice*pantonenumber));
				}			
		     System.out.println("印刷价格："+getDecimal(printprice));
		     shoppingDetail.setColorPrice(getDecimal(Double.valueOf(printprice)));
	   	  
		  //3.表面处理价格
		   	 float surfaceprice=length*width*sprice*papernumber/1000000;
			 
			 System.out.println("表面价格："+getDecimal(surfaceprice));
			 shoppingDetail.setSurfacePrice(getDecimal(Double.valueOf(surfaceprice)));
	      //4.烫金价格：加工费用：长*宽*排版个数*6*纸张数量+开机费（如果长*宽*6得出的结果少于0.15，则按0.15计算）
//			 * 烫版费：烫面积*0.3元*排版个数（长宽各加1.5cm计算）
//			 烫面积：长*宽（长宽各加1.5cm计算）
//			 计算方式：加工费用+烫版费

		     if(shoppingDetail.getIsBronzing().equals(1)){
	 	        
		    	float a0= Float.parseFloat(shoppingDetail.getBronzingLength().toString())+15;
		    	float a1=Float.parseFloat(shoppingDetail.getBronzingWidth().toString())+15;
		    	if (a0*a1*bronzingdanjia/1000000<bronzingqibu) {//
		        	 bronzingprice=(float) (papernumber*bronzingqibu*(xl*yl)+bronzingkaiji+a0*a1/1000000*0.3*(xl*yl)); 
				}else {
					bronzingprice=(float) (papernumber*a0*a1*bronzingdanjia*(xl*yl)/1000000+bronzingkaiji+a0*a1/1000000*0.3*(xl*yl)); 
				}
		         
		     }else{
		    	 bronzingprice=0;
		     }
		      System.out.println("烫金价格:"+getDecimal(bronzingprice));
		      shoppingDetail.setBronzingPrice(getDecimal(Double.valueOf(bronzingprice)));
		   //5.击凸价格:（长+1.5cm）*(宽+1.5cm)*0.3元*2*排版个数+150+0.05*印张数量 
//		      （如果长*宽*0.01得出的结果少于0.05，则按0.05计算）版费+开机费+加工费
		    if(shoppingDetail.getIsConvex().equals(1)){
		 	    float b0= Float.parseFloat(shoppingDetail.getConvexLength().toString())+15;
		 	    float b1=Float.parseFloat(shoppingDetail.getConvexWidth().toString())+15;
		 	    convexprice=(float) (b0*b1*convexbanfei*(xl*yl)*2/1000000+convexKaiji+convexdanjia*papernumber);
			   
		     }else{
		    	 convexprice=0;
		     }
		    
		      System.out.println("击凸价格:"+getDecimal(convexprice));
		      shoppingDetail.setConvexPrice(getDecimal(Double.valueOf(convexprice)));
		    //6.UV价格
//		      局部UV 4.5元/㎡ ，起步价0.18元/印张
//		      开机费：180元
//		      * 计算方法：
//		      长*宽*4.5*纸张数量+开机费（如果长*宽*4.5得出的结果少于0.18，则按0.18计算）

		    if(shoppingDetail.getIsUv().equals(1)){
		 	    float c0= Float.parseFloat(shoppingDetail.getUvLength().toString());
		 	    float c2=Float.parseFloat(shoppingDetail.getUvWidth().toString());
		 	    if (c0*c2*uvdanjia/1000000<uvqibu) {
			    	uvprice=uvqibu*papernumber+uvkaiji;
				}else {
					uvprice=c0*c2*uvdanjia*papernumber/1000000+uvkaiji;
				}
			    
		     }else{
		    	 uvprice=0;
		     }
		   
		      System.out.println("UV价格结果："+getDecimal(uvprice));
		      shoppingDetail.setUvPrice(getDecimal(Double.valueOf(uvprice)));
		    //7.PVC价格
//		      4.5元/㎡ 统一收费
//		      起步价0.05元/个窗
//		      例：窗口面积*3.5*窗口个数(盒子数量)=加工费用（长宽各加1.5cm计算）
//		      窗口面积（长+1.5cm）*（宽+1.5cm）*4.5元*窗口个数（如果（长+1.5cm）*（宽+1.5cm）*3.5得出的结果少于0.05，则按0.05计算
		    if(shoppingDetail.getIsPvc().equals(1)){
		 	    float d0= Float.parseFloat(shoppingDetail.getPvcLength().toString())+15;			 
		 	    float d1=Float.parseFloat(shoppingDetail.getPvcWidth().toString())+15;
		 	    if (d0*d1*pvcdanjia/1000000<pvcqibu) {
			    	 pvcprice=pvcqibu*printnumber;
				}else {
					pvcprice=d0*d1*pvcdanjia*printnumber/1000000;
				}
			    
		     }else{
		    	 pvcprice=0;
		     }
		     
		     System.out.println("PVC价格结果："+getDecimal(pvcprice));
		     shoppingDetail.setPvcPrice(getDecimal(Double.valueOf(pvcprice)));
		     //8.磨砂
//		     4.5元/㎡，起步价0.25元/印张，开机费250元；
//		     计算方式：
//		     长*宽*4.5*印张数量+开机费（如果长*宽*4.5得出的结果少于0.25，则按0.25计算）
		     if(shoppingDetail.getIsMosha().equals(1)){
			 	    float e0= Float.parseFloat(shoppingDetail.getMoshaLength().toString());
				    float e1=Float.parseFloat(shoppingDetail.getMoshaWidth().toString());
				    System.out.println(e0+"*"+e1);
				    if (e0*e1*moshadanjia/1000000<moshaqibu) {
				    	 moshaprice=moshaqibu*papernumber+moshakaiji;
					}else {
						moshaprice=e0*e1*moshadanjia*papernumber/1000000+moshakaiji;
					}
				    
			     }else{
			    	 moshaprice=0;
			     }
		     System.out.println("磨砂价格结果："+getDecimal(moshaprice));
		     shoppingDetail.setMoshaPrice(getDecimal(Double.valueOf(moshaprice)));
		     //9.皱纹
//		     8元/㎡，起步价0.3元/印张，开机费300元；
//		     计算方式：
//		     长*宽*8*印张数量+开机费（如果长*宽*8得出的结果少于0.3，则按0.3计算

		     if(shoppingDetail.getIsZhouwen().equals(1)){
			 	    float f0= Float.parseFloat(shoppingDetail.getZhouwenLength().toString());
				    float f1=Float.parseFloat(shoppingDetail.getZhouwenWidth().toString());
				    if (f0*f1*zhouwendanjia/1000000<zhouwenqibu) {
				    	 zhouwenprice=zhouwenqibu*papernumber+zhouwenkaiji;
					}else {
						zhouwenprice=f0*f1*zhouwendanjia*papernumber/1000000+zhouwenkaiji;
					}
				    
			     }else{
			    	 zhouwenprice=0;
			     }
		     System.out.println("磨砂价格结果："+getDecimal(zhouwenprice));
		     shoppingDetail.setZhouwenPrice(getDecimal(Double.valueOf(zhouwenprice)));
		     //10.压纹
//		     * 版费1000元
//		     计算方式：
//		     （印制数量*0.2+1000）如果印张数量*0.2不足300元，那就直接：300+1000
		     if (papernumber*moqiedanjia<yawenqibu) {
				yawenprice=yawenqibu+yawenbanfei;
			}else {
				yawenprice=papernumber*yawendanjia+yawenbanfei;
			}
		     System.out.println("压纹价格结果："+getDecimal(yawenprice));
		     shoppingDetail.setYawenPrice(getDecimal(Double.valueOf(yawenprice)));
//		     11.模切（啤）：
//		     0.03元/印张
//		     * 版费： 统一收费：500
//		     计算方式：
//		     0.03*印张数量+500元
		    
		     	moqieprice=papernumber*moqiedanjia+moqiebanfei;
				
			     System.out.println("模切价格结果："+getDecimal(moqieprice));
			     shoppingDetail.setMoqiePrice(getDecimal(Double.valueOf(moqieprice)));
		    
//		     12.粘盒：
//		     计算方式：
//		     0.05*用户输入的那个盒子总数
		     zhanheprice=zhanhedanjia*printnumber;
		     System.out.println("粘盒价格结果："+getDecimal(zhanheprice));
		     shoppingDetail.setZhanhePrice(getDecimal(Double.valueOf(zhanheprice)));
//		     13.包装：
//		     牛皮纸包装1元/包（4K盒或以下50个/包，9K盒200个/包，18K盒600个/包）
//		     纸箱6元/箱（数量同牛皮纸包装）
//		     计算方式：(纸张数量*盒子数量（每印张）/50)*1（4K盒以下）
//		     (纸张数量*盒子数量（每印张）/200)*1 （4k-9K盒）
//		     (纸张数量*盒子数量（每印张）/600)*1 （9k-18k）
		     int bao=0;
		     if (xl*yl<=4) {	
				baozhuangk=baozhuang4k;
			}else if (xl*yl>4&xl*yl<=9) {	
				baozhuangk=baozhuang9k;
			}else if (xl*yl>9&xl*yl<=18) {	
				baozhuangk=baozhuang18k;
			}
		     bao=(int) (printnumber/baozhuangk);
		     baozhuangprice=(papernumber*xl*yl/baozhuangk)*baozhuangdanjia;
		     System.out.println("包装价格结果："+getDecimal(baozhuangprice));
		     shoppingDetail.setBaozhuangPrice(getDecimal(Double.valueOf(baozhuangprice)));
//		     14、运输费
//		     珠三角以内200元/吨，轻货100元/m³
		     transprice=transdanjia*papernumber*papernum/1000000;
		     System.out.println("运输费价格结果："+getDecimal(transprice));
		     shoppingDetail.setTransportPrice(getDecimal(Double.valueOf(transprice)));
		     //先获取利率值
		     List<ShoppingRate> rateList=shoppingRateService.getAllList();
			 double manp=0;//0管理费利率
			 double taxp=0;// 1 税费利率
			 double res=0;//2总价利率 
	         for(ShoppingRate rate:rateList){
	        	 if(rate.getType()==0){
	        		 manp=rate.getValue()/100.00;
	        		
	        	 }
	        	 if(rate.getType()==1){
	        		 taxp=rate.getValue()/100.00;
	        		
	        	 }
	        	 if(rate.getType()==2){
	        		 res=rate.getValue()/100.00;
	        	 }
	         }
	         
	       //统计费用
	 	    float sum=paperprice+printprice+surfaceprice+bronzingprice+convexprice+uvprice+pvcprice+
	 	    		moshaprice+zhouwenprice+yawenprice+moqieprice+baozhuangprice+transprice;
	 	    System.out.println("结果："+sum);
	 	    double tax=sum*taxp;
	 	    double manag=sum*manp;
	 	    double totalprice=(sum+ manag+ tax)*res;
	 	    double unitprice=totalprice/printnumber;
	 	    shoppingDetail.setManagementPrice(getDecimal(manag));
	 	    shoppingDetail.setTaxPrice(getDecimal(tax));
	 	    shoppingDetail.setTotalPrice(getDecimal(totalprice));
	 	    shoppingDetail.setUnitPrice(getDecimal(unitprice));
	 	 //生成订单记录和排版记录
	 	   Map<String, Object> map=new HashMap<String,Object>();
			map.put("layoutDetail", layoutDetail);
			map.put("shoppingDetail", shoppingDetail);
			boolean result=shoppingDetailService.createLayoutAndShopping(map);
			String uploadfile=System.getProperty("catalina.home")+File.separator+"images"
			+File.separator;

			//7.生成图片
			//获取原图照片
			String file1=null;
			String targetFile=null;
			//BoxType boxType = boxTypeService.get(shoppingDetail.getBoxId());
			if (boxType!=null) {
				file1=uploadfile+"BoxType"+File.separator+boxType.getBoxid()+File.separator+
						"pla"+File.separator+boxType.getPla();
				
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date date1 = new Date();
			String time1 = formatter.format(date1);
			/*targetFile= request.getSession().getServletContext().getRealPath("/res/picture")+File.separator
		        		+layoutDetail.getId()+File.separator+layoutDetail.getId()+
		        		"-"+time1+".jpg";*/
			targetFile=uploadfile+"LayoutDetail"+File.separator+layoutDetail.getId()+File.separator+
					"previewimageadress"+File.separator+layoutDetail.getId()+
	        		"-"+time1+".jpg";
			
			Map<String,Object> pictureMap=new HashMap<String,Object>();
			pictureMap.put("type", boxType.getType());
			pictureMap.put("m", xl);
			pictureMap.put("n", yl);
			pictureMap.put("file", file1);
			pictureMap.put("targetFile", targetFile);
			MergePicture mergePicture = new MergePicture(pictureMap);
			boolean update=false;
			if (mergePicture.mergeImage()) {
				layoutDetail.setPictureAddress(layoutDetail.getId()+
		        		"-"+time1+".jpg");
				if (layoutDetailService.update(layoutDetail)) {
					update=true;
				}else {
					update=false;
				}
			}
			
		//8.生成excel：查询数据，写入数据，生成excel
		//String uploadPath = request.getSession().getServletContext().getRealPath("/res/excel/");
		//System.out.println("uploadpath1:"+uploadPath);  
		File file = new File(System.getProperty("catalina.home")+File.separator+"images"
				+File.separator+"LayoutDetail"+File.separator+"ExcelDemo.xls");
	     String[][] result1;
	     boolean flag=false;
	     Map<String, Object> excelMap=new HashMap<String,Object>();
	     excelMap.put("ling", cnumber);
	     excelMap.put("ge", printnumber);
	     excelMap.put("bao", bao);
	     excelMap.put("fang", layoutDetail.getBoxLength()*layoutDetail.getBoxHeight()*layoutDetail.getBoxWidth()/1000000000);
		try {
			result1 = ExcelUtil.getData(file, 1);
			 int rowLength = result1.length;
	       String[][] body = writeData(result1, shoppingDetailService.get(shoppingDetail.getShoppingId()),
	    		   layoutDetailService.get(layoutDetail.getId()),excelMap);
	       /* for(int i=0;i<rowLength;i++) {
           for(int j=0;j<result[i].length;j++) {
              System.out.print(result[i][j]+"\t\t");
           }
           System.out.println();
       		}*/
			Date date = new Date();
			String time = formatter.format(date);
	        String filePath = uploadfile+"LayoutDetail"+File.separator+layoutDetail.getId()+File.separator+
					"excel"+File.separator+shoppingDetail.getShoppingId()+
	        		"-"+time+".xls";
	        System.out.println("filepath::"+filePath);
	        ExcelUtil testJxl = ExcelUtil.getInstance();
	        
	        flag = testJxl.createTable(body, filePath,rowLength);
	        if (flag) {
	            System.out.println("表格创建成功！");
	            shoppingDetail.setExcelAddress(shoppingDetail.getShoppingId()+
	        		"-"+time+".xls");
	            if (shoppingDetailService.update(shoppingDetail)) {
					flag=true;
				}else {
					flag=false;
				}
	        }
	       
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("文件不存在！");
			
		} catch (IOException e1) {
		
			e1.printStackTrace();
			System.out.println("EXCEL创建失败！");
		}
		if (result&&flag&&update) {
			return shoppingDetail.getShoppingId();
		}else {
			return null;
		}
	}
	
	/**
	 * 修改excel参数
	 * writeData:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param list
	 * @param sd
	 * @param la
	 * @return
	 * @since JDK 1.8
	 */
	public String[][] writeData(String[][] list,ShoppingDetail sd,LayoutDetail la,Map<String, Object> map) {
		int rowLength = list.length;
 		BoxType boxType = boxTypeService.get(la.getBoxId());
 	 for(int i=0;i<rowLength;i++) {
         for(int j=0;j<list[i].length;j++) {
         	if (i<=20) {
         		  if (j==1) {
         				list[0][j]=boxType.getName();
         				if (boxClassficationService.get(boxType.getClassid())!=null) {
         					list[1][j]=boxClassficationService.get(boxType.getClassid()).getName();
 						}else {
 							list[1][j]="";
 						}
         				
         				list[2][j]=String.valueOf(sd.getPrintNumber());
         				
         				list[4][j]=isExist(sd.getPrintPaperId())+isExist(sd.getPaperGramsId());
         				list[5][j]=isExist(sd.getPrintColorId());
         				list[6][j]=isExist(sd.getPantoneId());
         				
         				list[7][j]=isExist(sd.getSurfaceTreatmentId());
         				list[16][j]=String.valueOf((shoppingRateService.getByType(1).getValue()))+"%";//0管理费利率 1 税费利率 2总价利率 
         				list[17][j]=String.valueOf((shoppingRateService.getByType(0).getValue()))+"%";
         				list[18][j]=null;
         				list[19][j]=String.valueOf((shoppingRateService.getByType(2).getValue()))+"%";
         				list[20][j]=null;
         			}else if (j==2) {
          				list[12][j]=map.get("ling").toString();
          				list[13][j]=map.get("ge").toString();
          				list[14][j]=map.get("bao").toString();
          				list[15][j]=map.get("fang").toString();
          			}else if (j==6) {
         				list[4][j]=String.valueOf(sd.getPaperPrice());
         				list[5][j]=String.valueOf(sd.getColorPrice());
         				list[6][j]="";
         				list[7][j]=String.valueOf(sd.getSurfacePrice());
         				list[12][j]=String.valueOf(sd.getMoqiePrice());
          				list[13][j]=String.valueOf(sd.getZhanhePrice());
          				list[14][j]=String.valueOf(sd.getBaozhuangPrice());
         				list[15][j]=String.valueOf(sd.getTransportPrice());
         				list[16][j]=String.valueOf(sd.getTaxPrice());
         				list[17][j]=String.valueOf(sd.getManagementPrice());
         				list[18][j]=String.valueOf(sd.getTotalPrice()/shoppingRateService.getByType(2).getValue()*100);
         				list[19][j]=String.valueOf(sd.getTotalPrice());
         				list[20][j]=String.valueOf(sd.getUnitPrice());
         				
         			}
         		  if (i==4) {
         				list[i][2]=String.valueOf(la.getPaperLength());
         				list[i][4]=String.valueOf(la.getPaperWidth());
         			}else if (i==7) {
         				list[i][2]=String.valueOf(la.getPaperLength());
         				list[i][4]=String.valueOf(la.getPaperWidth());
         			}
         		  if (sd.getIsBronzing()!=null) {
         				list[8][2]=String.valueOf(sd.getBronzingLength());
         				list[8][4]=String.valueOf(sd.getBronzingWidth());
         				list[8][5]=unit(sd.getBronzingUnit());
         				list[8][6]=String.valueOf(sd.getBronzingPrice());
         			}
         		  if (sd.getIsConvex()!=null) {
         				list[10][2]=String.valueOf(sd.getConvexLength());
         				list[10][4]=String.valueOf(sd.getConvexWidth());
         				list[10][5]=unit(sd.getConvexUnit());
         				list[10][6]=String.valueOf(sd.getConvexPrice());
         			}
         		  if (sd.getIsUv()!=null) {
         				list[9][2]=String.valueOf(sd.getUvLength());
         				list[9][4]=String.valueOf(sd.getUvWidth());
         				list[9][5]=unit(sd.getUvUnit());
         				list[9][6]=String.valueOf(sd.getUvPrice());
         			}
         		  if (sd.getIsPvc()!=null) {
         				list[11][2]=String.valueOf(sd.getPvcLength());
         				list[11][4]=String.valueOf(sd.getPvcWidth());
         				list[11][5]=unit(sd.getPvcUnit());
         				list[11][6]=String.valueOf(sd.getPvcPrice());
         			}
 			}else {
 				 if (j==1) {
      				list[21][j]=boxType.getName();
      				if (boxClassficationService.get(boxType.getClassid())!=null) {
      					list[22][j]=boxClassficationService.get(boxType.getClassid()).getName();	
 					}else {
 						list[22][j]="";
 					}
      				
      				list[23][j]=String.valueOf(sd.getPrintNumber());
      				list[25][j]=isExist(sd.getPrintPaperId())+isExist(sd.getPaperGramsId());
      				list[26][j]=isExist(sd.getPrintColorId());
      				list[27][j]=isExist(sd.getPantoneId());
      				
      				list[28][j]=isExist(sd.getSurfaceTreatmentId());
      				list[35][j]=isExist(sd.getBaozhuangId());
      				list[37][j]=String.valueOf((shoppingRateService.getByType(1).getValue()))+"%";//0管理费利率 1 税费利率 2总价利率 
      				
      				list[38][j]=String.valueOf((shoppingRateService.getByType(0).getValue()))+"%";
      				
      			}else if (j==2) {
      				list[33][j]=map.get("ling").toString();
      				list[34][j]=map.get("ge").toString();
      				list[35][j]=map.get("bao").toString();
      				list[36][j]=map.get("fang").toString();
      			}else if (j==6) {
      				list[25][j]=String.valueOf(sd.getPaperPrice());
      				list[26][j]=String.valueOf(sd.getColorPrice());
      				list[27][j]="";
      				list[28][j]=String.valueOf(sd.getSurfacePrice());
      				list[33][j]=String.valueOf(sd.getMoqiePrice());
      				list[34][j]=String.valueOf(sd.getZhanhePrice());
      				list[35][j]=String.valueOf(sd.getBaozhuangPrice());
      				list[36][j]=String.valueOf(sd.getTransportPrice());
      				list[37][j]=String.valueOf(sd.getTaxPrice());
      				list[38][j]=String.valueOf(sd.getManagementPrice());
      				
      				list[39][j]=String.valueOf(sd.getUnitPrice());
      				
      			}
 				 if (i==25) {
      				list[i][2]=String.valueOf(la.getPaperLength());
      				list[i][4]=String.valueOf(la.getPaperWidth());
      			}else if (i==28) {
      				list[i][2]=String.valueOf(la.getPaperLength());
      				list[i][4]=String.valueOf(la.getPaperWidth());
      			}
 				 if (sd.getIsBronzing()!=null) {
      				list[29][2]=String.valueOf(sd.getBronzingLength());
      				list[29][4]=String.valueOf(sd.getBronzingWidth());
      				list[29][5]=unit(sd.getBronzingUnit());
      				list[29][6]=String.valueOf(sd.getBronzingPrice());
      			}
 				 if (sd.getIsConvex()!=null) {
      				list[31][2]=String.valueOf(sd.getConvexLength());
      				list[31][4]=String.valueOf(sd.getConvexWidth());
      				list[31][5]=unit(sd.getConvexUnit());
      				list[31][6]=String.valueOf(sd.getColorPrice());
      			}
 				 if (sd.getIsUv()!=null) {
      				list[30][2]=String.valueOf(sd.getUvLength());
      				list[30][4]=String.valueOf(sd.getUvWidth());
      				list[30][5]=unit(sd.getUvUnit());
      				list[30][6]=String.valueOf(sd.getUvPrice());
      			}
 				 if (sd.getIsPvc()!=null) {
      				list[32][2]=String.valueOf(sd.getPvcLength());
      				list[32][4]=String.valueOf(sd.getPvcWidth());
      				list[32][5]=unit(sd.getPvcUnit());
      				list[32][6]=String.valueOf(sd.getPvcPrice());
      			}
 			}
          
         }
     }
 		return list;
 		
	}
	/**
	 * 单位转换
	 * unit:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param type
	 * @return
	 * @since JDK 1.8
	 */
	
	public String unit(int type) {
		if (type==0) {
			return "mm";
		}else if (type==1) {
			return "dm";
		}else if (type==2) {
			return "cm";
		}else if (type==3) {
			return "m";
		}
		return "";
	}
	/**
	 * 判断长度是否在范围内
	 * checkPhoneExists:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param BoxLength
	 * @param BoxId
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/checkBoxLength")
    @ResponseBody
    private JSONObject checkBoxLength(HttpServletRequest request){
		boolean result =true;
   	BoxType boxType= boxTypeService.get(request.getParameter("BoxId"));
    	double len=Double.parseDouble(request.getParameter("boxLength"));
    	if (Double.doubleToLongBits(len)<Double.doubleToLongBits(boxType.getLmin())||
    			Double.doubleToLongBits(len)>Double.doubleToLongBits(boxType.getLmax())) {
			result=false;
		}else {
			result=true;
		}
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("valid", result);
		return jsonObj;
    }
	/**
	 * 判断纸盒宽度
	 * checkBoxWidth:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param BoxWidth
	 * @param BoxId
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/checkBoxWidth")
    @ResponseBody
    private JSONObject checkBoxWidth(HttpServletRequest request){
    	boolean result =true;
    	BoxType boxType= boxTypeService.get(request.getParameter("BoxId"));
    	double len=Double.parseDouble(request.getParameter("boxWidth"));
    	if (Double.doubleToLongBits(len)<Double.doubleToLongBits(boxType.getWmin())||
    			Double.doubleToLongBits(len)>Double.doubleToLongBits(boxType.getWmax())) {
			result=false;
		}else {
			result=true;
		}
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("valid", result);
		return jsonObj;
    }
	/**
	 * 判断纸盒高度
	 * checkBoxHeight:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param boxHeight
	 * @param BoxId
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/checkBoxHeight")
    @ResponseBody
    private JSONObject checkboxHeight(HttpServletRequest request){
    	boolean result =true;
    	BoxType boxType= boxTypeService.get(request.getParameter("BoxId"));
    	double len=Double.parseDouble(request.getParameter("boxHeight"));
    	if (Double.doubleToLongBits(len)<Double.doubleToLongBits(boxType.getHmin())||
    			Double.doubleToLongBits(len)>Double.doubleToLongBits(boxType.getHmax())) {
			result=false;
		}else {
			result=true;
		}
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("valid", result);
		return jsonObj;
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
private String isExist(String id) {
	if (id!=null&&id!="") {
		TechnologyDetail technologyDetail=technologyDetailService.get(id);
		if (technologyDetail!=null) {
			return technologyDetail.getName();
		}else {
			return "";
		}
	}else {
		return "";
	}
	
}
}

