
 /**
 * Project Name:Box-core
 * File Name:BoxClassficationServiceImpl.java
 * Package Name:com.box.boxmanage.service.impl
 * Date:2018年4月4日上午11:04:34
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.box.boxmanage.dao.BoxClassificationMapper;
import com.box.boxmanage.model.BoxClassification;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.uums.dao.UserMapper;
import com.box.uums.service.impl.UserServiceImpl;

/**
 * ClassName:BoxClassficationServiceImpl
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2018年4月4日 上午11:04:34
 * @author   cheng
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Service("boxClassficationService")
public class BoxClassificationServiceImpl implements BoxClassficationService {

	private static final Logger logger = LoggerFactory.getLogger(BoxClassificationServiceImpl.class);
	@Resource
	BoxClassificationMapper boxClassficationMapper;
	
	@Override
	public boolean save(BoxClassification record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean update(BoxClassification record) {

		// TODO Auto-generated method stub
		return false;

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
	public boolean delete(BoxClassification record) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public BoxClassification get(String id) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public BoxClassification get(BoxClassification record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<BoxClassification> getList(BoxClassification record) {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public List<BoxClassification> getAllList() {

		// TODO Auto-generated method stub
		return null;

	}

}

