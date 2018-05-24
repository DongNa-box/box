
 /**
 * Project Name:Box-core
 * File Name:TechnologyPriceServiceImpl.java
 * Package Name:com.box.technology.service.impl
 * Date:2018年4月12日上午9:56:50
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.technology.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.box.technology.dao.TechnologyPriceMapper;
import com.box.technology.model.TechnologyPrice;
import com.box.technology.service.TechnologyPriceService;

/**
 * ClassName:TechnologyPriceServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月12日 上午9:56:50
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("technologyPriceService")
public class TechnologyPriceServiceImpl implements TechnologyPriceService {

	private static final Logger logger = LoggerFactory.getLogger(TechnologyPriceServiceImpl.class);
	@Resource
	TechnologyPriceMapper technologyPriceMapper;
	
	@Override
	public boolean save(TechnologyPrice record) {
		int row=technologyPriceMapper.insert(record);
		return row>0?true:false;

	}

	@Override
	public boolean update(TechnologyPrice record) {

		int row=technologyPriceMapper.updateByPrimaryKey(record);
		return row>0?true:false;
	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {

		int row=technologyPriceMapper.batchDeleteByIds(records);
		return row>0?true:false;

	}

	@Override
	public boolean delete(TechnologyPrice record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public TechnologyPrice get(String id) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public TechnologyPrice get(TechnologyPrice record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<TechnologyPrice> getList(TechnologyPrice record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<TechnologyPrice> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<Map<String, Object>> technologyPriceSearchList(Map<String, Object> map) {
		
		// TODO Auto-generated method stub
		return technologyPriceMapper.technologyPriceSearchList(map);
		
	}

	@Override
	public List<Map<String, Object>> getAllPriceList() {

		List<Map<String, Object>> list=technologyPriceMapper.getAllPriceList();
		return list;
	}

}

