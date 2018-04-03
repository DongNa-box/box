/**
 *Title:FunctionController.java
 *@date 2018年4月2日下午1:43:02
 */
package com.box.uums.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.box.framework.pojo.Result;
import com.box.framework.pojo.TreeNode;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.Sequence;
import com.box.uums.model.Function;
import com.box.uums.model.UserRole;
import com.box.uums.service.FunctionService;
import com.box.uums.service.UserRoleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * CalssName:FunctionController.java
 * Function:TODO
 * Reason:TODO
 * @author luowen
 * @date 2018年4月2日下午1:43:02
 * @version
 * @since:
 * @see
 */
@Controller
@RequestMapping(value = "/uums")
public class FunctionController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionController.class);
	
    @Resource
    private FunctionService functionService;
    
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping(method = RequestMethod.POST, value = "/menuTree")
    @ResponseBody
    public List<TreeNode> getMenuTree() {
    	UserRole userRole = userRoleService.getUserRoleByUserId(SecurityUtil.getUser().getId());
    	List<TreeNode> menu = functionService.getMenuTree(userRole.getRoleId());
        return menu;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/function")
    private String function() {
        return "uums/function";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/functionlist")
    @ResponseBody
    public List<Function> listFunction(){
    	
    	
    	List<Function> functionList = functionService.getAllList();
        return functionList;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/parentMenu")
    @ResponseBody
    public List<Function> getParentMenu(){
    	List<Function> parentMenuList = functionService.getParentMenu();
        return parentMenuList;
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/deleteFunction")
    @ResponseBody
    private Result deleteFunction(@Param(value = "fids")String fids) {
    	List<String> list = JSON.parseArray(fids, String.class);
    	boolean result = functionService.batchDeleteById(list);
    	return result ? new Result(true,"删除成功") : new Result(false,"删除失败");
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editFunction")
    @ResponseBody
    private Result editFunction(@Param(value = "params") String params) {
    	Gson gson = new GsonBuilder().create();
    	Function func = gson.fromJson(params, Function.class);
    	JSONObject jsonObj = JSONObject.parseObject(params);
    	String flag = jsonObj.getString("flag");
    	boolean result;
    	switch(flag){
			case "0":
				func.setId(Sequence.nextId());
				result = functionService.save(func);
				return result ? new Result(true,"增加成功") : new Result(false,"增加失败");
			case "1":
				result = functionService.update(func);
				return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
    	}
    	return new Result(false,"操作失败");
    }
    
}
