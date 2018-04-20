
 /**
 * Project Name:Box-core
 * File Name:LayoutSizeServiceImpl.java
 * Package Name:com.box.shopping.service.impl
 * Date:2018年4月4日下午6:00:19
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

import com.box.shopping.dao.LayoutSizeMapper;
import com.box.shopping.model.LayoutSize;
import com.box.shopping.service.LayoutSizeService;
import com.box.uums.model.User;
import com.box.uums.service.impl.UserServiceImpl;

/**
 * ClassName:LayoutSizeServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 下午6:00:19
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("layoutSizeService")
public class LayoutSizeServiceImpl implements LayoutSizeService {
	private static final Logger logger = LoggerFactory.getLogger(LayoutSizeServiceImpl.class);
	@Resource
	LayoutSizeMapper layoutSizeMapper;
	@Override
	public boolean save(LayoutSize record) {

		int res = layoutSizeMapper.insert(record);
		return res>0?true:false;

	}

	@Override
	public boolean update(LayoutSize record) {
		int res = layoutSizeMapper.updateByPrimaryKeySelective(record);
		return res>0?true:false;

	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {

		int userRow = layoutSizeMapper.batchDeleteByIds(records);
		return userRow>0?true:false;

	}

	@Override
	public boolean delete(LayoutSize record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public LayoutSize get(String id) {

		// TODO Auto-generated method stub
		return layoutSizeMapper.selectByPrimaryKey(id);

	}

	@Override
	public LayoutSize get(LayoutSize record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<LayoutSize> getList(LayoutSize record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<LayoutSize> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}

	
	 /**
	 * TODO 查询列表.
	 * @see com.box.shopping.service.LayoutSizeService#getLayoutSizelist(java.util.Map)
	 */
	 
	@Override
	public List<Map<String, Object>> getLayoutSizelist(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return layoutSizeMapper.getLayoutSizelist(map);
		
	}

	@Override
	public boolean checkNameExists(String name) {
		LayoutSize layoutSize = layoutSizeMapper.getSizeByName(name);
		return layoutSize == null?false : true;
	}


}

