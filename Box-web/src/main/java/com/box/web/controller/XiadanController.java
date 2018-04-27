package com.box.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.TreeNode;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.ExcelUtil;
import com.box.framework.utils.PropertiesUtil;
import com.box.framework.utils.Sequence;
import com.box.shopping.model.LayoutDetail;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.service.LayoutDetailService;
import com.box.shopping.service.ShoppingDeatilService;
import com.box.shopping.service.ShoppingRateService;
import com.box.technology.service.TechnologyDetailService;

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
	 ShoppingDeatilService shoppingDeatilService;
	 @Resource
	 LayoutDetailService layoutDetailService;
	 @Resource
	 ShoppingRateService shoppingRateService;
	 
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
		layoutDetail.setBoxWidth(Double.parseDouble(jsonObj.getString("boxHighth")));
		layoutDetail.setBoxUnit(Integer.valueOf(jsonObj.getString("boxUnit")));
		layoutDetail.setCreateby(SecurityUtil.getUser().getId());
		layoutDetail.setCreatetime(DateUtil.getCurrDate());
		
		ShoppingDetail shoppingDetail = new ShoppingDetail();
		shoppingDetail.setShoppingId(Sequence.nextId());
		shoppingDetail.setBoxId(jsonObj.getString("boxId"));
		shoppingDetail.setLayoutId(layoutDetail.getId());
		shoppingDetail.setUserId(SecurityUtil.getUser().getId());
		shoppingDetail.setCreateby(SecurityUtil.getUser().getId());
		shoppingDetail.setCreatetime(DateUtil.getCurrDate());
		shoppingDetail.setPrintPaperId(jsonObj.getString("printPaperId"));
		shoppingDetail.setPaperGramsId(jsonObj.getString("paperGramsId"));
		shoppingDetail.setPrintColorId(jsonObj.getString("printColorId"));
		//0单色 1多色
		if (jsonObj.getString("colorFlag")!="") {
			if (jsonObj.getString("colorFlag").equals("0")) {
				shoppingDetail.setPantoneId(jsonObj.getString("pantoneId"));
			}
			
		}
		shoppingDetail.setSurfaceTreatmentId(jsonObj.getString("surfaceTreatmentId"));
		if (jsonObj.getString("isBronzing")!=null&jsonObj.getString("isBronzing")!="") {
			shoppingDetail.setIsBronzing(1);
			shoppingDetail.setBronzingLength(Double.parseDouble(jsonObj.getString("bronzingLength")));
			shoppingDetail.setBronzingWidth(Double.parseDouble(jsonObj.getString("bronzingWidth")));
			shoppingDetail.setBronzingUnit(Integer.valueOf(jsonObj.getString("bronzingUnit")));
		}else {
			shoppingDetail.setIsBronzing(0);
		}
			
		if (jsonObj.getString("isConvex")!=null&jsonObj.getString("isConvex")!="") {
			shoppingDetail.setIsConvex(1);
			shoppingDetail.setConvexLength(Double.parseDouble(jsonObj.getString("convexLength")));
			shoppingDetail.setConvexWidth(Double.parseDouble(jsonObj.getString("convexWidth")));
			shoppingDetail.setConvexUnit(Integer.valueOf(jsonObj.getString("convexUnit")));
		}else{
			shoppingDetail.setIsConvex(0);
		} if (jsonObj.getString("isPvc")!=null&jsonObj.getString("isPvc")!="") {
			shoppingDetail.setIsPvc(1);
			shoppingDetail.setPvcLength(Double.parseDouble(jsonObj.getString("pvcLength")));
			shoppingDetail.setPvcWidth(Double.parseDouble(jsonObj.getString("pvcWidth")));
			shoppingDetail.setPvcUnit(Integer.valueOf(jsonObj.getString("pvcUnit")));
		}else{
			shoppingDetail.setIsPvc(0);
		} if (jsonObj.getString("isUv")!=null&jsonObj.getString("isUv")!="") {
			shoppingDetail.setIsUv(1);
			shoppingDetail.setUvLength(Double.parseDouble(jsonObj.getString("uvLength")));
			shoppingDetail.setUvWidth(Double.parseDouble(jsonObj.getString("uvWidth")));
			shoppingDetail.setUvUnit(Integer.valueOf(jsonObj.getString("uvUnit")));
		}else {
			shoppingDetail.setIsUv(0);
		}
		shoppingDetail.setReceiveAreaId(jsonObj.getString("receiveAreaId"));
		shoppingDetail.setPrintNumber(Integer.valueOf(jsonObj.getString("printNumber")));
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("layoutDetail", layoutDetail);
		map.put("shoppingDetail", shoppingDetail);
		boolean result=shoppingDeatilService.createLayoutAndShopping(map);
		//排版（生成排版图片，dxf文件上传）
		//计算价格
		//生成excel：查询数据，写入数据，生成excel
		String uploadPath = request.getSession().getServletContext().getRealPath("/res/excel/");
		System.out.println("uploadpath1:"+uploadPath);  
		File file = new File(uploadPath+"ExcelDemo.xls");
	     String[][] result1;
	     boolean flag;
		try {
			result1 = ExcelUtil.getData(file, 1);
			 int rowLength = result1.length;
	       String[][] body = writeData(result1, shoppingDeatilService.get(shoppingDetail.getShoppingId()),
	    		   layoutDetailService.get(layoutDetail.getId()));
	       /* for(int i=0;i<rowLength;i++) {
           for(int j=0;j<result[i].length;j++) {
              System.out.print(result[i][j]+"\t\t");
           }
           System.out.println();
       		}*/
	       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date date = new Date();
			String time = formatter.format(date);
	        String filePath = uploadPath+File.separator
	        		+shoppingDetail.getShoppingId()+File.separator+shoppingDetail.getShoppingId()+
	        		"-"+time+".xls";
	        System.err.println("filepath::"+filePath);
	        ExcelUtil testJxl = ExcelUtil.getInstance();
	        flag = testJxl.createTable(body, filePath,rowLength);
	        if (flag) {
	            System.out.println("表格创建成功！");
	        }
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("文件不存在！");
			
		} catch (IOException e1) {
		
			e1.printStackTrace();
			System.out.println("EXCEL创建失败！");
		}
		if (result) {
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
	public String[][] writeData(String[][] list,ShoppingDetail sd,LayoutDetail la) {
		int rowLength = list.length;
		BoxType boxType = boxTypeService.get(la.getBoxId());
	 for(int i=0;i<rowLength;i++) {
        for(int j=0;j<list[i].length;j++) {
        	if (i<=20) {
        		  if (j==1) {
        				list[0][j]=boxType.getName();
        				list[1][j]=boxClassficationService.get(boxType.getClassid()).getName();
        				list[2][j]=String.valueOf(sd.getPrintNumber());
        				list[4][j]=technologyDetailService.get(sd.getPrintPaperId()).getName();
        				list[5][j]=technologyDetailService.get(sd.getPrintColorId()).getName();
        				if (sd.getPantoneId()!=null&sd.getPantoneId()!="") {
        					list[6][j]=technologyDetailService.get(sd.getPantoneId()).getName();
        				}
        				list[7][j]=technologyDetailService.get(sd.getSurfaceTreatmentId()).getName();
        				list[16][j]=String.valueOf((shoppingRateService.getByType(1).getValue())*100)+"%";//0管理费利率 1 税费利率 2总价利率 
        				list[17][j]=String.valueOf((shoppingRateService.getByType(0).getValue())*100)+"%";
        				list[18][j]=String.valueOf(sd.getTotalPrice());
        				list[19][j]=String.valueOf((shoppingRateService.getByType(2).getValue())*100)+"%";
        				list[20][j]=String.valueOf(sd.getUnitPrice());
        			}else if (i==4) {
        				list[i][2]=String.valueOf(la.getPaperLength());
        				list[i][4]=String.valueOf(la.getPaperWidth());
        			}else if (i==7) {
        				list[i][2]=String.valueOf(la.getPaperLength());
        				list[i][4]=String.valueOf(la.getPaperWidth());
        			}else if (sd.getIsBronzing()==1) {
        				list[8][2]=String.valueOf(sd.getBronzingLength());
        				list[8][4]=String.valueOf(sd.getBronzingWidth());
        				list[8][5]=unit(sd.getBronzingUnit());
        				list[8][6]=String.valueOf(sd.getBronzingPrice());
        			}else if (sd.getIsConvex()==1) {
        				list[10][2]=String.valueOf(sd.getConvexLength());
        				list[10][4]=String.valueOf(sd.getConvexWidth());
        				list[10][5]=unit(sd.getConvexUnit());
        				list[10][6]=String.valueOf(sd.getColorPrice());
        			}else if (sd.getIsUv()==1) {
        				list[9][2]=String.valueOf(sd.getUvLength());
        				list[9][4]=String.valueOf(sd.getUvWidth());
        				list[9][5]=unit(sd.getUvUnit());
        				list[9][6]=String.valueOf(sd.getUvPrice());
        			}else if (sd.getIsPvc()==1) {
        				list[11][2]=String.valueOf(sd.getPvcLength());
        				list[11][4]=String.valueOf(sd.getPvcWidth());
        				list[11][5]=unit(sd.getPvcUnit());
        				list[11][6]=String.valueOf(sd.getPvcPrice());
        			}else if (j==6) {
        				list[4][j]=String.valueOf(sd.getPaperPrice());
        				list[5][j]=String.valueOf(sd.getColorPrice());
        				list[6][j]="";
        				list[7][j]=String.valueOf(sd.getSurfacePrice());
        				list[15][j]=String.valueOf(sd.getTransportPrice());
        				list[16][j]=String.valueOf(sd.getTaxPrice());
        				list[17][j]=String.valueOf(sd.getManagementPrice());
        				list[18][j]=String.valueOf(sd.getTotalPrice()/shoppingRateService.getByType(2).getValue());
        				list[19][j]=String.valueOf(sd.getTotalPrice());
        				list[20][j]=String.valueOf(sd.getUnitPrice());
        				
        			}
			}else {
				 if (j==1) {
     				list[20][j]=boxType.getName();
     				list[21][j]=boxClassficationService.get(boxType.getClassid()).getName();
     				list[22][j]=String.valueOf(sd.getPrintNumber());
     				list[24][j]=technologyDetailService.get(sd.getPrintPaperId()).getName();
     				list[25][j]=technologyDetailService.get(sd.getPrintColorId()).getName();
     				if (sd.getPantoneId()!=null&sd.getPantoneId()!="") {
     					list[26][j]=technologyDetailService.get(sd.getPantoneId()).getName();
     				}
     				list[27][j]=technologyDetailService.get(sd.getSurfaceTreatmentId()).getName();
     				list[36][j]=String.valueOf((shoppingRateService.getByType(1).getValue())*100)+"%";//0管理费利率 1 税费利率 2总价利率 
     				list[37][j]=String.valueOf((shoppingRateService.getByType(0).getValue())*100)+"%";
     				list[38][j]=String.valueOf(sd.getTotalPrice());
     				list[39][j]=String.valueOf((shoppingRateService.getByType(2).getValue())*100)+"%";
     				list[40][j]=String.valueOf(sd.getUnitPrice());
     			}else if (i==24) {
     				list[i][2]=String.valueOf(la.getPaperLength());
     				list[i][4]=String.valueOf(la.getPaperWidth());
     			}else if (i==27) {
     				list[i][2]=String.valueOf(la.getPaperLength());
     				list[i][4]=String.valueOf(la.getPaperWidth());
     			}else if (sd.getIsBronzing()==1) {
     				list[28][2]=String.valueOf(sd.getBronzingLength());
     				list[28][4]=String.valueOf(sd.getBronzingWidth());
     				list[28][5]=unit(sd.getBronzingUnit());
     				list[28][6]=String.valueOf(sd.getBronzingPrice());
     			}else if (sd.getIsConvex()==1) {
     				list[30][2]=String.valueOf(sd.getConvexLength());
     				list[30][4]=String.valueOf(sd.getConvexWidth());
     				list[30][5]=unit(sd.getConvexUnit());
     				list[30][6]=String.valueOf(sd.getColorPrice());
     			}else if (sd.getIsUv()==1) {
     				list[29][2]=String.valueOf(sd.getUvLength());
     				list[29][4]=String.valueOf(sd.getUvWidth());
     				list[29][5]=unit(sd.getUvUnit());
     				list[29][6]=String.valueOf(sd.getUvPrice());
     			}else if (sd.getIsPvc()==1) {
     				list[31][2]=String.valueOf(sd.getPvcLength());
     				list[31][4]=String.valueOf(sd.getPvcWidth());
     				list[31][5]=unit(sd.getPvcUnit());
     				list[31][6]=String.valueOf(sd.getPvcPrice());
     			}else if (j==6) {
     				list[24][j]=String.valueOf(sd.getPaperPrice());
     				list[25][j]=String.valueOf(sd.getColorPrice());
     				list[26][j]="";
     				list[27][j]=String.valueOf(sd.getSurfacePrice());
     				list[35][j]=String.valueOf(sd.getTransportPrice());
     				list[36][j]=String.valueOf(sd.getTaxPrice());
     				list[37][j]=String.valueOf(sd.getManagementPrice());
     				list[38][j]=String.valueOf(sd.getTotalPrice()/shoppingRateService.getByType(2).getValue());
     				list[39][j]=String.valueOf(sd.getTotalPrice());
     				list[40][j]=String.valueOf(sd.getUnitPrice());
     				
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
	 * checkBoxHighth:(这里用一句话描述这个方法的作用).
	 *
	 * @author luowen
	 * @param BoxHighth
	 * @param BoxId
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/checkBoxHighth")
    @ResponseBody
    private JSONObject checkBoxHighth(HttpServletRequest request){
    	boolean result =true;
    	BoxType boxType= boxTypeService.get(request.getParameter("BoxId"));
    	double len=Double.parseDouble(request.getParameter("boxHighth"));
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
}

