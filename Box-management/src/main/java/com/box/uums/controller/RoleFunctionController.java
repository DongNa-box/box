package com.box.uums.controller;

import com.alibaba.fastjson.JSONArray;
import com.box.framework.pojo.Result;
import com.box.framework.security.util.SecurityUtil;
import com.box.framework.utils.DateUtil;
import com.box.framework.utils.Sequence;
import com.box.uums.model.AuthTree;
import com.box.uums.model.RoleFunction;
import com.box.uums.service.FunctionService;
import com.box.uums.service.RoleService;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/uums")
public class RoleFunctionController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleFunctionController.class);
	
	@Resource
	private FunctionService functionService;
	
	@Resource
	private RoleService roleService;
	
    @RequestMapping(method = RequestMethod.POST, value = "/authTree")
    @ResponseBody
    public List<AuthTree> authTree(@Param(value = "roleId") String roleId) {
    	List<AuthTree> authTree = functionService.getAuthTree(roleId);
        return authTree;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authRole")
    @ResponseBody
    public Result authRole(@Param(value = "auths") String auths,@Param(value = "roleId") String roleId){
    	roleService.removeAuth(roleId);
    	JSONArray authsArray = JSONArray.parseArray(auths);
    	List<RoleFunction> authsList = new ArrayList<RoleFunction>();
    	for(Object auth:authsArray){
    		RoleFunction roleFunc = new RoleFunction();
    		roleFunc.setId(Sequence.nextId());
    		roleFunc.setRoleId(roleId);
    		roleFunc.setFunctionId(auth.toString());
    		roleFunc.setCreateby(SecurityUtil.getUser().getId());
    		roleFunc.setCreatetime(DateUtil.getCurrDate());
    		authsList.add(roleFunc);
    	}
    	boolean result = roleService.authRole(authsList);
    	return result ? new Result(true,"授权成功") : new Result(false,"授权失败");
    }
}
