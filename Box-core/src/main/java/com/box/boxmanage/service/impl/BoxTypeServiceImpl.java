
 /**
 * Project Name:Box-core
 * File Name:BoxTypeServiceImpl.java
 * Package Name:com.box.boxmanage.service
 * Date:2018年4月4日上午11:03:47
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.box.boxmanage.dao.BoxTypeMapper;
import com.box.boxmanage.model.BoxType;
import com.box.boxmanage.service.BoxTypeService;

/**
 * ClassName:BoxTypeServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 上午11:03:47
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("boxTypeService")
public class BoxTypeServiceImpl implements BoxTypeService {
	private static final Logger logger = LoggerFactory.getLogger(BoxTypeServiceImpl.class);
	@Resource
	BoxTypeMapper boxTypeMapper;
	
	@Override
	public boolean save(BoxType record) {

		int row=boxTypeMapper.insert(record);
		return row>0?true:false;

	}

	@Override
	public boolean update(BoxType record) {
		int row=boxTypeMapper.updateByPrimaryKey(record);
		return row>0?true:false;

	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {
		int row=boxTypeMapper.batchDeleteByIds(records);
		return row>0?true:false;

	}

	@Override
	public boolean delete(BoxType record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public BoxType get(String id) {

		// TODO Auto-generated method stub
		return boxTypeMapper.selectByPrimaryKey(id);

	}

	@Override
	public BoxType get(BoxType record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<BoxType> getList(BoxType record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<BoxType> getAllList() {

		return null;

	}

	@Override
	public boolean checkBoxTypeNameExists(String name) {
		
		BoxType b=boxTypeMapper.checkBoxTypeNameExists(name);
		return b==null?false:true;

		
	}

	@Override
	public List<Map<String,Object>> boxTypeSearchList(Map<String, Object> map) {
		
		
		List<Map<String,Object>> list=boxTypeMapper.boxTypeSearchList(map);
		return list;

		
	}

	@Override
	public List<Map<String,Object>> getAllBoxTypeList() {
		
		List<Map<String,Object>> list=boxTypeMapper.getAllBoxTypeList();
		return list;

		
	}

	@Override
	public List<Map<String, Object>> getWebBoxTypeList(Map<String, Object> map) {
		
		return boxTypeMapper. getWebBoxTypeList(map);
		
	}

	@Override
	public Map<String,Object> getAllById(String boxId) {
		// TODO Auto-generated method stub
		return boxTypeMapper.getAllById(boxId);
	}

	


}

