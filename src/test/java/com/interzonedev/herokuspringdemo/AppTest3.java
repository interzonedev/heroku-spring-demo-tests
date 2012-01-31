package com.interzonedev.herokuspringdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.interzonedev.herokuspringdemo.service.user.UserService;
import com.interzonedev.integrationtest.dataset.DataSet;

public class AppTest3 extends HerokuAbstractIntegrationTest {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;

	@Test
	@DataSet(filename = "")
	public void testApp3_1() {
		log.debug("testApp3_1");

		Assert.assertTrue(true);
	}

	@Test
	public void testApp3_2() {
		log.debug("testApp3_2");

		Assert.assertTrue(true);
	}
}
