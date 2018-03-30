package com.box.framework.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.box.framework.pojo.TreeNode;

/**
 * ClassName:TreeUtil
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年7月28日 下午1:52:29
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class TreeUtil {
    public static List<TreeNode> getNodeList(Map<String, TreeNode> nodelist) {
        List<TreeNode> tnlist=new ArrayList<TreeNode>();
        for (String id : nodelist.keySet()) {
            TreeNode node = nodelist.get(id);
            if (StrUtil.isEmpty(node.getParentId())) {
                tnlist.add(node);
            } else {
                if(nodelist.get(node.getParentId()).getNodes() == null){
                    nodelist.get(node.getParentId()).setNodes(new ArrayList<TreeNode>());
                }
                nodelist.get(node.getParentId()).getNodes().add(node);
            }
        }
        return tnlist;
    }
}

