
 /**
 * Project Name:Box-core
 * File Name:LayoutDetailServiceImpl.java
 * Package Name:com.box.shopping.service.impl
 * Date:2018年4月4日下午5:59:30
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

import com.box.shopping.dao.LayoutDetailMapper;
import com.box.shopping.model.LayoutDetail;
import com.box.shopping.service.LayoutDetailService;

/**
 * ClassName:LayoutDetailServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 下午5:59:30
 * @author   luowen
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("layoutDetailService")
public class LayoutDetailServiceImpl implements LayoutDetailService {
	private static final Logger logger = LoggerFactory.getLogger(LayoutDetailServiceImpl.class);

	@Resource
	LayoutDetailMapper layoutDetailMapper;
	@Override
	public boolean save(LayoutDetail record) {
		int res = layoutDetailMapper.insert(record);
		return res>0?true:false;
	}

	@Override
	public boolean update(LayoutDetail record) {

		int res = layoutDetailMapper.updateByPrimaryKeySelective(record);
		return res>0?true:false;

	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean delete(LayoutDetail record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public LayoutDetail get(String id) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public LayoutDetail get(LayoutDetail record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<LayoutDetail> getList(LayoutDetail record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<LayoutDetail> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}

	
	 /**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.box.shopping.service.LayoutDetailService#getLayoutDetailList(java.util.Map)
	 */
	 
	@Override
	public List<Map<String, Object>> getLayoutDetailList(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return layoutDetailMapper.getLayoutDetailList(map);
		
	}

}

