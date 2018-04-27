
 /**
 * Project Name:Box-core
 * File Name:ShoppingRateServiceImpl.java
 * Package Name:com.box.shopping.service.impl
 * Date:2018年4月5日下午2:29:30
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.shopping.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.box.boxmanage.model.BoxType;
import com.box.shopping.dao.ShoppingRateMapper;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.ShoppingRateService;

/**
 * ClassName:ShoppingRateServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月5日 下午2:29:30
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("shoppingRateService")
public class ShoppingRateServiceImpl implements ShoppingRateService {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingRateServiceImpl.class);
    @Resource
    ShoppingRateMapper shoppingRateMapper;
	@Override
	public boolean save(ShoppingRate record) {
		int res = shoppingRateMapper.insert(record);
		return res>0?true:false;

	}

	@Override
	public boolean update(ShoppingRate record) {
		int res = shoppingRateMapper.updateByPrimaryKeySelective(record);
		return res>0?true:false;


	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {

		int userRow = shoppingRateMapper.batchDeleteByIds(records);
		return userRow>0?true:false;

	}

	@Override
	public boolean delete(ShoppingRate record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public ShoppingRate get(String id) {

		// TODO Auto-generated method stub
		return shoppingRateMapper.selectByPrimaryKey(id);

	}

	@Override
	public ShoppingRate get(ShoppingRate record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<ShoppingRate> getList(ShoppingRate record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<ShoppingRate> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}

	
	 /**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.box.shopping.service.ShoppingRateService#getRatelist(java.util.Map)
	 */
	 
	@Override
	public List<Map<String, Object>> getRateList(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return shoppingRateMapper.getRateList(map);
		
	}

	
	 /**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.box.shopping.service.ShoppingRateService#getByType(int)
	 */
	 
	@Override
	public ShoppingRate getByType(int type) {
		ShoppingRate shoppingRate = shoppingRateMapper.getByType(type); 
		
		return shoppingRate;
		
	}


}

