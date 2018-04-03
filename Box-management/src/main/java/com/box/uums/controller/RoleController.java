/**
 *Title:RoleContoller.java
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
import com.box.framework.utils.Sequence;
import com.box.uums.model.Role;
import com.box.uums.service.RoleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * CalssName:RoleContoller.java
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
public class RoleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@Resource
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET, value = "/role")
    private String role() {
        return "uums/role";
    }
	
    @RequestMapping(method = RequestMethod.GET, value = "/rolelist")
    @ResponseBody
    public List<Role> listRole(){
    	List<Role> roleList = roleService.getAllList();
        return roleList;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/editRole")
    @ResponseBody
    private Result eidtRole(@Param(value = "params") String params){
    	Gson gson = new GsonBuilder().create();
    	Role role = gson.fromJson(params, Role.class);
    	JSONObject jsonObj = JSONObject.parseObject(params);
    	String flag = jsonObj.getString("flag");
    	boolean result;
    	switch(flag){
			case "0":
				role.setId(Sequence.nextId());
				result = roleService.save(role);
		    	return result ? new Result(true,"增加成功") : new Result(false,"增加失败");
			case "1":
				result = roleService.update(role);
				return result ? new Result(true,"修改成功") : new Result(false,"修改失败");
    	}
		return new Result(false,"操作失败");
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/deleteRole")
    @ResponseBody
    public Result deleteRole(@Param(value = "rids")String rids) {
    	List<String> list = JSON.parseArray(rids, String.class);
    	boolean result = roleService.batchDeleteById(list);
        return result ? new Result(true,"删除成功") : new Result(false,"删除失败");
    }
    
}
