
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
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxClassification;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.model.PaibanType;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.Result;
import com.box.framework.pojo.RspCode;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.FileUtil;
import com.box.framework.utils.Sequence;
import com.box.shopping.model.LayoutDetail;
import com.box.shopping.model.LayoutSize;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.LayoutDetailService;
import com.box.shopping.service.LayoutSizeService;
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
	
	@Resource
	LayoutSizeService layoutSizeService;
	
	@Resource
	BoxClassficationService boxClassficationService;
	@Resource
	TechnologyDetailService technologyDetailService;
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
    	//查找模切
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("cname", 2);
    	map.put("level", 0);
    	map.put("parentId,", "");
    	List<TechnologyDetail> result=technologyDetailService.getTechnologyByDetail(map);
    	shoppingDetail.setMoqieId(result.get(0).getId());
    	shoppingDetail.setShoppingId(Sequence.nextId());
    	
    	shoppingDetail.setLayoutId(Sequence.nextId());
    	shoppingDetail.setEnabled(1);
		shoppingDetail.setCreateby(shoppingDetail.getUserId());
		shoppingDetail.setCreatetime(DateUtil.getCurrDate());
		shoppingDetail.setBronzingUnit(0);
		shoppingDetail.setConvexUnit(0);
		shoppingDetail.setUvUnit(0);
		shoppingDetail.setPvcUnit(0);
		shoppingDetail.setZhouwenUnit(0);
		shoppingDetail.setMoshaUnit(0);
		
    	String paperNumber=jsonObj.getString("papergrams");
    	LayoutDetail layoutDetail = new LayoutDetail();
		layoutDetail.setId(shoppingDetail.getLayoutId());
		layoutDetail.setBoxId(jsonObj.getString("boxId"));
    	layoutDetail.setBoxLength(Double.parseDouble(jsonObj.getString("boxLength")));
		layoutDetail.setBoxWidth(Double.parseDouble(jsonObj.getString("boxWidth")));
		layoutDetail.setBoxHeight(Double.parseDouble(jsonObj.getString("boxHeight")));
		layoutDetail.setBoxUnit(0);
		layoutDetail.setCreateby(shoppingDetail.getUserId());
		layoutDetail.setCreatetime(DateUtil.getCurrDate());
		layoutDetail.setPaperUnit(0);
		
    	//1.查询盒型类型，传入盒型类型
    	int type=0;
		//1.获取纸张尺寸信息
    	int pantonenumber=0;
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
	sizeMap.put("boxWidth", boxwidth);
	sizeMap.put("boxLength", boxlength);
	sizeMap.put("boxHeight", boxheight);
	sizeMap.put("isBronzing", shoppingDetail.getIsBronzing());
	sizeMap.put("isConvex", shoppingDetail.getIsConvex());
	sizeMap.put("isPvc", shoppingDetail.getIsPvc());
	sizeMap.put("isUv", shoppingDetail.getIsUv());
	
	//3.查询纸盒信息，lgai，ldi，lock相关信息
	List<BoxClassification> boxClassifications = boxClassficationService.getAllList();
	BoxType boxType = boxTypeService.get(shoppingDetail.getBoxId());
	//2.读取纸盒排版模式
	type = boxType.getType();
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
		 String parentid = technologyDetailService.get(shoppingDetail.getPrintColorId()).getParentId();
		 String name = technologyDetailService.get(parentid).getName();
		 
		 if (name.equals("单色")) {
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
//					 * 烫版费：烫面积*0.3元*排版个数（长宽各加1.5cm计算）
//					 烫面积：长*宽（长宽各加1.5cm计算）
//					 计算方式：加工费用+烫版费

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
//				      （如果长*宽*0.01得出的结果少于0.05，则按0.05计算）版费+开机费+加工费
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
//				      局部UV 4.5元/㎡ ，起步价0.18元/印张
//				      开机费：180元
//				      * 计算方法：
//				      长*宽*4.5*纸张数量+开机费（如果长*宽*4.5得出的结果少于0.18，则按0.18计算）

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
//				      4.5元/㎡ 统一收费
//				      起步价0.05元/个窗
//				      例：窗口面积*3.5*窗口个数(盒子数量)=加工费用（长宽各加1.5cm计算）
//				      窗口面积（长+1.5cm）*（宽+1.5cm）*4.5元*窗口个数（如果（长+1.5cm）*（宽+1.5cm）*3.5得出的结果少于0.05，则按0.05计算
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
//				     4.5元/㎡，起步价0.25元/印张，开机费250元；
//				     计算方式：
//				     长*宽*4.5*印张数量+开机费（如果长*宽*4.5得出的结果少于0.25，则按0.25计算）
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
//				     8元/㎡，起步价0.3元/印张，开机费300元；
//				     计算方式：
//				     长*宽*8*印张数量+开机费（如果长*宽*8得出的结果少于0.3，则按0.3计算

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
//				     * 版费1000元
//				     计算方式：
//				     （印制数量*0.2+1000）如果印张数量*0.2不足300元，那就直接：300+1000
	     if (papernumber*moqiedanjia<yawenqibu) {
			yawenprice=yawenqibu+yawenbanfei;
		}else {
			yawenprice=papernumber*yawendanjia+yawenbanfei;
		}
	     System.out.println("压纹价格结果："+getDecimal(yawenprice));
	     shoppingDetail.setYawenPrice(getDecimal(Double.valueOf(yawenprice)));
//				     11.模切（啤）：
//				     0.03元/印张
//				     * 版费： 统一收费：500
//				     计算方式：
//				     0.03*印张数量+500元
	    
	     	moqieprice=papernumber*moqiedanjia+moqiebanfei;
			
		     System.out.println("模切价格结果："+getDecimal(moqieprice));
		     shoppingDetail.setMoqiePrice(getDecimal(Double.valueOf(moqieprice)));
	    
//				     12.粘盒：
//				     计算方式：
//				     0.05*用户输入的那个盒子总数
	     zhanheprice=zhanhedanjia*printnumber;
	     System.out.println("粘盒价格结果："+getDecimal(zhanheprice));
	     shoppingDetail.setZhanhePrice(getDecimal(Double.valueOf(zhanheprice)));
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
	     shoppingDetail.setBaozhuangPrice(getDecimal(Double.valueOf(baozhuangprice)));
//				     14、运输费
//				     珠三角以内200元/吨，轻货100元/m³
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
 	   Map<String, Object> cmap=new HashMap<String,Object>();
		cmap.put("layoutDetail", layoutDetail);
		cmap.put("shoppingDetail", shoppingDetail);
		boolean result1=shoppingDetailService.createLayoutAndShopping(cmap);
		 Map<String,Object> mymap=new HashMap<String,Object>();
		  mymap.put("paperprice", paperprice);
		  mymap.put("colorprice", printprice);
		  mymap.put("surfaceprice", surfaceprice);
		  mymap.put("bronzprice", bronzingprice);
		  mymap.put("jtprice", convexprice);
		  mymap.put("uvprice", uvprice);
		  mymap.put("pvcprice", pvcprice);
		  mymap.put("moshaprice",moshaprice);
		  mymap.put("zhouwenprice", zhouwenprice);
		  mymap.put("yawenprice", yawenprice);
		  mymap.put("zhanheprice", zhanheprice);
		  mymap.put("baozhuangprice", baozhuangprice);
		  mymap.put("moqieprice", moqieprice);
		  mymap.put("xid",layoutDetail.getPaperXId());
		  mymap.put("ysf", transprice);
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
		if (result1) {
			return new Result(true,mymap);
		}else {
			return new Result(false);
		}
		
		
	     
         
	}
    /**
     * 添加购物车
     * @param headers
     * @return
     */
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
    	shoppingDetail.setZhouwenUnit(0);
    	shoppingDetail.setMoshaUnit(0);
    	shoppingDetail.setLayoutId(layoutDetail.getId());
    	shoppingDetail.setEnabled(1);
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
	
	
	/**
	 * 上传排版图片
	 * @param headers
	 * @param httpRequest
	 * @return
	 */
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
	
	
	/**
	 * 查询购物车信息
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/queryShopping",method = RequestMethod.POST)
	@ResponseBody
	public Result queryShopping(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		String userId = jsonObj.getString("userId");		
		String shoppingId = jsonObj.getString("shoppingId");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		if (shoppingId.equals("00")) {
			map.put("shoppingId", null);
		}else{
			map.put("shoppingId", shoppingId);
		}
		List<Map<String, Object>> shoppingDetails=shoppingDetailService.getInfoByUserIdandShoppingId(map);
		return new Result(true,shoppingDetails);
	}
	
	
	/**
	 * 更新购物车信息
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/updateShopping",method = RequestMethod.POST)
	@ResponseBody
	public Result updateShopping(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		ShoppingDetail shoppingDetail  = JSON.parseObject(jsonObj.toString(),ShoppingDetail.class);
		LayoutDetail layoutDetail= JSON.parseObject(jsonObj.toString(),LayoutDetail.class);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("shoppingDetail", shoppingDetail);
		map.put("layoutDetail", layoutDetail);
		boolean res = shoppingDetailService.updateAllDetail(map);
		if (res) {
			return new Result(true);
		}else {
			return new Result(false);
		}
		
	}
	
	/**
	 * 删除购物车信息
	 * @param headers
	 * @return
	 */
	@RequestMapping(value = "/deleteShopping",method = RequestMethod.POST)
	@ResponseBody
	public Result deleteShopping(@RequestHeader HttpHeaders headers){
		String token = headers.getFirst("token");
		JSONObject jsonObj = jwt.parseJwtForAndroid(token);
		JSONArray arr=jsonObj.getJSONArray("shoppingId");
		List<String> list=new ArrayList<String>();
		for(int i=0;i<arr.size();i++){
			list.add(arr.getString(i));
		}

		boolean res = shoppingDetailService.updateByEnabled(list);
		if (res) {
			return new Result(true);
		}else {
			return new Result(false);
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

