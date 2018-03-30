
package com.box.framework.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:RspCode
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月26日 下午3:41:38
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class RspCode {

	public static final String R00000 = "00000";
	public static final String R00001 = "00001";
	public static final String R00002 = "00002";
	public static final String R00003 = "00003";
	public static final String R10000 = "10000";
	public static final String R10001 = "10001";
	public static final String R10002 = "10002";
	public static final String R10003 = "10003";
	public static final String R10004 = "10004";
	public static final String R20000 = "20000";
	public static final String R20001 = "20001";
	public static final String R30000 = "30000";
	public static final String R30001 = "30001";
	public static final String R30002 = "30002";
	public static final String R30003 = "30003";
	public static final String R40000 = "40000";
	public static final String R50000 = "50000";

		
	static Map<String, String> map = new HashMap<String, String>();
	static {

		map.put(R00000, "服务器异常，请稍后重试");
		map.put(R00001, "暂无记录");
		map.put(R00002, "网络异常，请稍后重试");
		map.put(R00003, "文件上传失败");
		map.put(R10000, "该手机号未注册，请先注册或使用已注册的手机号登录");
		map.put(R10001, "密码错误，请重新填写");
		map.put(R10002, "验证码错误，请重新填写");
		map.put(R10003, "该手机号已注册，您可直接使用该手机号登陆");
		map.put(R10004, "该手机号已注册，您可在登陆页面找回该手机号对应的密码");
		map.put(R20000, "已为该用户授权，您可以直接修改对该用户的授权");
		map.put(R20001, "您要授权的用户不是注册用户，您可以通过个人中心->推荐给朋友，邀请该用户注册");
		map.put(R30000, "参数解析失败");
		map.put(R30001, "广告素材不存在");
		map.put(R30002, "广告素材下载失败");
		map.put(R30003, "无权限访问广告素材资源");
		map.put(R40000, "社区ID非法");
		map.put(R50000, "设备离线，请稍后再试");
		
	}
	
	public static String getMsg(String bizCode) {
		return map.get(bizCode);
	}
	
}

