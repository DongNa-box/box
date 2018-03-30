
 /**
 * Project Name:boxCI-core
 * File Name:TestUser.java
 * Package Name:com.box.uums.test
 * Date:2017年5月4日下午3:01:02
 * Copyright (c) 2017, Wuhan box Technology co.,LTD. All Rights Reserved.
 *
*/

package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.box.uums.model.User;
import com.box.uums.service.UserService;


/**
 * ClassName:TestUser
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月4日 下午3:01:02
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class TestUser extends BaseTest{

	@Resource
	private UserService userService;

	@Test
	public void save() {
		User user = new User();
		user.setId("3487");
		user.setName("jay");
		boolean isSave = userService.save(user);
		logger.info("==> save:" + isSave);
	}
	
//	@Test
//	public void getAllList() {
//		List<User> list = userService.getAllList();
//		logger.info("==> getAllList:"+JSON.toJSONString(list));
//	}
//	
//	@Test
//	public void get() {
//		User user = userService.get("1495094205528000");
//		logger.info("==> getAllList:"+JSON.toJSONString(user));
//	}
}

