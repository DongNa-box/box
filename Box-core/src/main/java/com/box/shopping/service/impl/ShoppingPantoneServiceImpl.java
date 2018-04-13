
 /**
 * Project Name:Box-core
 * File Name:ShoppingPantoneServiceImpl.java
 * Package Name:com.box.shopping.service.impl
 * Date:2018年4月8日上午10:17:18
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

import com.box.shopping.dao.ShoppingPantoneMapper;
import com.box.shopping.dao.ShoppingRateMapper;
import com.box.shopping.model.ShoppingPantone;
import com.box.shopping.model.ShoppingRate;
import com.box.shopping.service.ShoppingPantoneService;

/**
 * ClassName:ShoppingPantoneServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月8日 上午10:17:18
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("shoppingPantoneService")
public class ShoppingPantoneServiceImpl implements ShoppingPantoneService {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingPantoneServiceImpl.class);
    @Resource
    ShoppingPantoneMapper shoppingPantoneMapper;
	@Override
	public boolean save(ShoppingPantone record) {
		int res = shoppingPantoneMapper.insert(record);
		return res>0?true:false;

	}

	@Override
	public boolean update(ShoppingPantone record) {
		int res = shoppingPantoneMapper.updateByPrimaryKeySelective(record);
		return res>0?true:false;

	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {
		int userRow = shoppingPantoneMapper.batchDeleteByIds(records);
		return userRow>0?true:false;
	}

	@Override
	public boolean delete(ShoppingPantone record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public ShoppingPantone get(String id) {

		// TODO Auto-generated method stub
		return shoppingPantoneMapper.selectByPrimaryKey(id);

	}

	@Override
	public ShoppingPantone get(ShoppingPantone record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<ShoppingPantone> getList(ShoppingPantone record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<ShoppingPantone> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}
	@Override
	public List<Map<String, Object>> getPantoneList(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return shoppingPantoneMapper.getPantoneList(map);
		
	}
}

