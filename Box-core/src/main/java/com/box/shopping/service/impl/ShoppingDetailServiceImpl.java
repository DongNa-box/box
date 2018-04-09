
 /**
 * Project Name:Box-core
 * File Name:ShoppingDetailServiceImpl.java
 * Package Name:com.box.shopping.service.impl
 * Date:2018年4月5日下午2:27:17
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

/**
 * Project Name:Box-core
 * File Name:ShoppingDetailServiceImpl.java
 * Package Name:com.box.shopping.service.impl
 * Date:2018年4月5日下午2:27:17
 * Copyright (c) 2018,Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
 */
 

package com.box.shopping.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.box.shopping.dao.ShoppingDetailMapper;
import com.box.shopping.model.ShoppingDetail;
import com.box.shopping.service.ShoppingDeatilService;

/**
 * ClassName: ShoppingDetailServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON(可选).
 * date: 2018年4月5日 下午2:27:17
 *
 * @author luowen
 * @version 
 * @since JDK 1.8
 */
@Service("shoppingDetailServiceImpl")
public class ShoppingDetailServiceImpl implements ShoppingDeatilService {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingDetailServiceImpl.class);
	@Resource
	ShoppingDetailMapper shoppingDetailMapper;
	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#save(java.lang.Object)
	*/

	@Override
	public boolean save(ShoppingDetail record) {

		int res = shoppingDetailMapper.insert(record);
		return res>0?true:false;


	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#update(java.lang.Object)
	*/

	@Override
	public boolean update(ShoppingDetail record) {
		int res = shoppingDetailMapper.updateByPrimaryKeySelective(record);
		return res>0?true:false;


	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#delete(java.io.Serializable)
	*/

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#batchDeleteById(java.util.List)
	*/

	@Override
	public boolean batchDeleteById(List<String> records) {

		// TODO Auto-generated method stub
		return false;

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#delete(java.lang.Object)
	*/

	@Override
	public boolean delete(ShoppingDetail record) {

		// TODO Auto-generated method stub
		return false;

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#get(java.io.Serializable)
	*/

	@Override
	public ShoppingDetail get(String id) {

		// TODO Auto-generated method stub
		return null;

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#get(java.lang.Object)
	*/

	@Override
	public ShoppingDetail get(ShoppingDetail record) {

		// TODO Auto-generated method stub
		return null;

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#getList(java.lang.Object)
	*/

	@Override
	public List<ShoppingDetail> getList(ShoppingDetail record) {

		// TODO Auto-generated method stub
		return null;

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.box.framework.base.service.BaseService#getAllList()
	*/

	@Override
	public List<ShoppingDetail> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}

	
	 /**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.box.shopping.service.ShoppingDeatilService#getShoppingDetailList(java.util.Map)
	 */
	 
	@Override
	public List<Map<String, Object>> getShoppingDetailList(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return shoppingDetailMapper.getShoppingDetailList(map);
		
	}

}
