
 /**
 * Project Name:Box-core
 * File Name:ShoppingOrderServiceImpl.java
 * Package Name:com.box.shopping.service.impl
 * Date:2018年4月5日下午2:28:27
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

import com.box.shopping.dao.ShoppingOrderMapper;
import com.box.shopping.model.ShoppingOrder;
import com.box.shopping.service.ShoppingOrderService;

/**
 * ClassName:ShoppingOrderServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月5日 下午2:28:27
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("shoppingOrderService")
public class ShoppingOrderServiceImpl implements ShoppingOrderService {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingOrderServiceImpl.class);
    @Resource
    ShoppingOrderMapper shoppingOrderMapper;
	@Override
	public boolean save(ShoppingOrder record) {
		int res = shoppingOrderMapper.insert(record);
		return res>0?true:false;

	}

	@Override
	public boolean update(ShoppingOrder record) {
		int res = shoppingOrderMapper.updateByPrimaryKeySelective(record);
		return res>0?true:false;

	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {

		int userRow = shoppingOrderMapper.batchDeleteByIds(records);
		return userRow>0?true:false;

	}

	@Override
	public boolean delete(ShoppingOrder record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public ShoppingOrder get(String id) {

		return shoppingOrderMapper.selectByPrimaryKey(id);

	}

	@Override
	public ShoppingOrder get(ShoppingOrder record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<ShoppingOrder> getList(ShoppingOrder record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<ShoppingOrder> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}
	@Override
	public List<Map<String, Object>> getShoppingOrderList(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return shoppingOrderMapper.getPantoneList(map);
		
	}

}

