package com.interzonedev.herokuspringdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.interzonedev.herokuspringdemo.service.user.UserService;
import com.interzonedev.integrationtest.dataset.DataSet;

@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
public class AppTest2 extends HerokuAbstractIntegrationTest {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;

	@Test
	public void testApp2_1() {
		log.debug("testApp2_1");

		Assert.assertTrue(true);
	}
}
