
 /**
 * Project Name:PdoneCI-web
 * File Name:BaseTest.java
 * Package Name:com.pdone.base.test
 * Date:2017年5月15日下午4:51:36
 * Copyright (c) 2017, Wuhan Pdone Technology co.,LTD. All Rights Reserved.
 *
*/

package test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * ClassName:BaseTest
 * Function: TODO ADD FUNCTION.
 * Reason:	 TODO ADD REASON.
 * Date:     2017年5月15日 下午4:51:36
 * @author   Jay
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
public class BaseTest {
	protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);
}

