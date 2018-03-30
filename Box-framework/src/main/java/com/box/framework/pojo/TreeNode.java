package com.box.framework.pojo;

import java.util.List;

/**
 * ClassName:TreeNode Function: TODO ADD FUNCTION. Reason: TODO ADD REASON.
 * Date: 2017年5月15日 下午1:25:46
 * 
 * @author Jay
 * @version
 * @since JDK 1.8
 * @see
 */
public class TreeNode {

	private String text;

	private String url;

	private String id;

	private String parentId;

	private String levelCode;

	private List<TreeNode> nodes;

	private String icon;

	
	public String getParentId() {

		return parentId;
	}

	public String getIcon() {

		return icon;
	}

	public void setIcon(String icon) {

		this.icon = icon;
	}

	public String getLevelCode() {

		return levelCode;
	}

	public void setLevelCode(String levelCode) {

		this.levelCode = levelCode;
	}

	public void setParentId(String parentId) {

		this.parentId = parentId;
	}

	public String getText() {

		return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public List<TreeNode> getNodes() {

		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {

		this.nodes = nodes;
	}
	
}
