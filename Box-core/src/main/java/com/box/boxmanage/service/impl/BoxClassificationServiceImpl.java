
 /**
 * Project Name:Box-core
 * File Name:BoxClassficationServiceImpl.java
 * Package Name:com.box.boxmanage.service.impl
 * Date:2018年4月4日上午11:04:34
 * Copyright (c) 2018, Wuhan CCNU Technology co.,LTD. All Rights Reserved.
 *
*/

package com.box.boxmanage.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.box.boxmanage.dao.BoxClassificationMapper;
import com.box.boxmanage.model.BoxClassification;
import com.box.boxmanage.service.BoxClassficationService;
import com.box.framework.pojo.TreeNode;
import com.box.framework.utils.TreeUtil;
import com.box.uums.dao.UserMapper;
import com.box.uums.model.Function;
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

		int row=boxClassficationMapper.insert(record);
		return row>0?true:false;

	}

	@Override
	public boolean update(BoxClassification record) {
		int row=boxClassficationMapper.updateByPrimaryKey(record);
		return row>0?true:false;

	}

	@Override
	public boolean delete(String id) {

		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean batchDeleteById(List<String> records) {

		int row=boxClassficationMapper.batchDeleteByIds(records);
		return row>0?true:false;

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

		List<BoxClassification> list=boxClassficationMapper.selectAll();
		return list;

	}

	@Override
	public List<BoxClassification> getBoxClassificaionByLevel(String level) {
		
		List<BoxClassification> boxCl=boxClassficationMapper.getBoxClassificaionByLevel(level);
		return boxCl;
		
	}

	@Override
	public List<BoxClassification> getBoxClassificaionByGroupLevel(Map<String, Object> map) {

		List<BoxClassification> boxCl=boxClassficationMapper.getBoxClassificaionByGroupLevel(map);
		return boxCl;
		
	}

	@Override
	public BoxClassification getBoxClassificaionByGroupid(String groupid) {
		BoxClassification boxCl=boxClassficationMapper.getBoxClassificaionByGroupid(groupid);
		return boxCl;
		
	}

	@Override
	public List<BoxClassification> boxClassificationSearchList(String name) {
		List<BoxClassification> boxCl=boxClassficationMapper.boxClassificationSearchList(name);
		return boxCl;

	}

	
	 /**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see com.box.boxmanage.service.BoxClassficationService#getWebTree()
	 */
	 
	@Override
	public List<TreeNode> getWebTree() {
		
		List<BoxClassification> list = boxClassficationMapper.selectAll();
		Map<String, TreeNode> nodelist = new LinkedHashMap<String, TreeNode>();
		for (BoxClassification func : list) {
            TreeNode node = new TreeNode();
            node.setText(func.getName());
            node.setId(func.getId());
            node.setParentId(func.getGroupid());
            node.setLevelCode(String.valueOf(func.getLevel()));
            node.setIcon("fa fa-circle-o");
            node.setUrl("/web/boxType");
            nodelist.put(node.getId(), node);
        }
		return TreeUtil.getNodeList(nodelist);
		
	}

	@Override
	public List<Map<String, Object>> getAllDetailList() {
		List<Map<String, Object>> list=boxClassficationMapper.getAllDetailList();
		return list;

	}

}

