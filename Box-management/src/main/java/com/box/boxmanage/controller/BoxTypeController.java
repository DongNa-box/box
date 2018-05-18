
 /**
 * Project Name:Box-management
 * File Name:BoxTypeController.java
 * Package Name:com.box.boxmanage.controller
 * Date:2018年4月4日上午11:03:12
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.controller;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.service.BoxTypeService;
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.FileUtil;
import com.box.framework.utils.Sequence;


/**
 * ClassName:BoxTypeController
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 上午11:03:12
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Controller
@RequestMapping(value = "/boxmanage")
public class BoxTypeController {
	   private static final Logger LOGGER = LoggerFactory.getLogger(BoxTypeController.class);
	  
	   @Resource
	   BoxTypeService boxTypeService;
	 
	   @RequestMapping(method = RequestMethod.GET, value = "/boxType")
	   protected String boxType() {
	     return "boxmanage/boxType";
       }
	   
	 /**
	  * 获取全部盒型列表
	  * boxTypeList:(这里用一句话描述这个方法的作用).
	  *
	  * @author cheng
	  * @return
	  * @since JDK 1.8
	  */
	    @RequestMapping(method = RequestMethod.GET, value = "/boxTypeList")
	   	@ResponseBody
	   	protected List<Map<String,Object>> boxTypeList(){
			List<Map<String,Object>> list = boxTypeService.getAllBoxTypeList();
	   		return list;
	   	}
	    
      /**
       * 纸盒类型的新增与修改
       * editBoxType:(这里用一句话描述这个方法的作用).
       *
       * @author cheng
       * @param params
       * @return
       * @since JDK 1.8
       */
	    @RequestMapping(method = RequestMethod.POST, value = "/editBoxType")
	    @ResponseBody
	    private Result editBoxType(HttpServletRequest httpRequest) {
	    	MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpRequest;
	    	//String uploadPath = request.getSession().getServletContext().getRealPath("/images/BoxType");  
	    	String uploadPath=System.getProperty("catalina.home")+File.separator+"images"+File.separator+"BoxType";
	    	System.out.println("上传路径:"+uploadPath);
	    	BoxType box =new BoxType();
	    	box.setBoxid(request.getParameter("boxid"));
	    	box.setClassid(request.getParameter("classId"));
	    	box.setName(request.getParameter("name"));
	    	box.setDetail1(request.getParameter("detail1"));
	    	box.setDetail2(request.getParameter("detail2"));
	    	box.setDetail3(request.getParameter("detail3"));
	    	box.setDescription(request.getParameter("description"));
	    	box.setLmax(Double.parseDouble(request.getParameter("lmax")));
	    	box.setLmin(Double.parseDouble(request.getParameter("lmin")));
	    	box.setWmax(Double.parseDouble(request.getParameter("wmax")));
	    	box.setWmin(Double.parseDouble(request.getParameter("wmin")));
	    	box.setHmax(Double.parseDouble(request.getParameter("hmax")));
	    	box.setHmin(Double.parseDouble(request.getParameter("hmin")));
	    	box.setType(Integer.parseInt(request.getParameter("type")));
	    	//设置单位,默认mm
	    	box.setUnit(Integer.parseInt(request.getParameter("unit")));
	    	if(box.getBoxid()==null || box.getBoxid().equals("")){
	    		box.setBoxid(Sequence.nextId());
	    	}
		    //获取文件表单
	        Map<String,MultipartFile> file=request.getFileMap();
	    	String flag = request.getParameter("flag");
	    	boolean result;
			String contentType = request.getContentType();
			if (contentType.indexOf("multipart/form-data") >= 0) {	
		    	MultipartFile f=file.get("file-1");
	            String fileName=setFileName(f,box.getBoxid(),uploadPath,"dime");	
	            box.setDime(fileName);
	            f=file.get("file-2");
	            fileName=setFileName(f,box.getBoxid(),uploadPath,"phy");	
	            box.setPhy(fileName);
	            f=file.get("file-3");
	            fileName=setFileName(f,box.getBoxid(),uploadPath,"plan");	
	            box.setPlan(fileName);
	            //文件存储路径
				String filePath=uploadPath+File.separator+box.getBoxid()+File.separator+"plan"+File.separator+fileName;
				//目标路径
				String outFilePath=uploadPath+File.separator+box.getBoxid()+File.separator+"pla"+File.separator+fileName;
		        //将平面展开图透明化处理
				//获取有效的尺寸
		        int[] r=FileUtil.getSize(filePath);
				//裁剪图片,获取图片的有效部分
				FileUtil.cutImage(new File(filePath),outFilePath,new Rectangle(r[0],r[1],r[2],r[3]));
				//缩放图片
				FileUtil.thumbnailImage(new File(outFilePath), outFilePath,150, 100);		
				//图片透明化处理
				FileUtil.transferAlpha(outFilePath); 
				box.setPla(fileName);
				//排版原始文件
				f=file.get("file-4");
				fileName=setFileName(f,box.getBoxid(),uploadPath,"dxf");
				box.setDxf(fileName);
				switch(flag){
				   //盒型新增
				   case "1":
				    	 box.setCreatetime(new Date());
			             box.setCreateby(SecurityUtil.getUser().getId());
						 result = boxTypeService.save(box);
				    	 return result ? new Result(true,"新增成功") : new Result(false,"新增失败");	
				    	    		
				   case "2":
					     box.setBoxid(request.getParameter("id"));
				    	 result = boxTypeService.update(box);
				    	 return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
				    			
				 }				
		    }else{
		                return new Result(false,"操作失败");	
		    }
			return new Result(false,"操作失败");	
	    	
	    }
	    
	    /**
         * 删除纸盒信息
         * deleteBoxType:(这里用一句话描述这个方法的作用).
         *
         * @author cheng
         * @param boxCids
         * @return
         * @since JDK 1.8
         */
		 @RequestMapping(method = RequestMethod.POST, value = "deleteBoxType")
		 @ResponseBody
		 private Result deleteBoxType(@Param(value = "boxCids") String boxIds) {
			  List<String> list = JSON.parseArray(boxIds, String.class);
			  //删除图片和数据
			  
			  boolean result=boxTypeService.batchDeleteById(list);
			    if(result){
			    	return new Result(true,"删除成功！");
		    	   }else{
			    		return new Result(false,"删除失败！");
		   	       } 	
		  
		  }
		 
		 /**
		  * 纸盒名称的唯一性
		  * checkBoxTypeNameExists:(这里用一句话描述这个方法的作用).
		  *
		  * @author cheng
		  * @param name
		  * @return
		  * @since JDK 1.8
		  */
		 @RequestMapping(method = RequestMethod.POST, value = "/checkBoxTypeNameExists")
		 @ResponseBody
		 private JSONObject checkBoxTypeNameExists(@RequestParam String name){
		    	boolean result = boxTypeService.checkBoxTypeNameExists(name);
		    	JSONObject jsonObj = new JSONObject();
		    	jsonObj.put("valid", (!result));
				return jsonObj;
		 }	 
		 
	     /**
	      * 查找包装盒类型依据名称的模糊查询
	      * boxTypeSearchList:(这里用一句话描述这个方法的作用).
	      *
	      * @author cheng
	      * @param name
	      * @return
	      * @since JDK 1.8
	      */
		 @RequestMapping(method = RequestMethod.GET, value = "boxTypeSearchList")
		 @ResponseBody
		 private List<Map<String,Object>> boxTypeSearchList(@RequestParam String params) {
		     JSONObject jsonObj = JSONObject.parseObject(params);
		     String name= jsonObj.getString("search-boxName");
		     String classId=jsonObj.getString("search-type");
		     Map<String,Object> map=new HashMap<String,Object>();
		     map.put("name", name);
		     map.put("classId", classId);
		     List<Map<String,Object>> list= boxTypeService.boxTypeSearchList(map);
			 return list;
		  
		  }
        /**
         * 拼接文件名称并上传图片
         * setFileName:(这里用一句话描述这个方法的作用).
         *
         * @author cheng
         * @param f
         * @param id
         * @param path
         * @param position
         * @return
         * @since JDK 1.8
         */
		public String setFileName(MultipartFile f, String id, String path,String position) {
			// 遍历上传的文件
			String orignalfilename = f.getOriginalFilename();
			String suffix = orignalfilename.substring(orignalfilename.lastIndexOf("."));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			Date date = new Date();
			String time = formatter.format(date);
			String fileName = id + "-" + time + suffix;
			// 将图片资源进行存储
			String filePath=path+File.separator+id+File.separator+position;
	        try {
				FileUtil.upload(f.getInputStream(),fileName,filePath);
			} catch (IOException e) {
				e.printStackTrace();	
			}
			return fileName;
		}
		

		 
}

