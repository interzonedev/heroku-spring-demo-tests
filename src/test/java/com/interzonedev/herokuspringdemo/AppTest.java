package com.interzonedev.herokuspringdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.interzonedev.herokuspringdemo.service.user.UserService;
import com.interzonedev.integrationtest.AbstractIntegrationTest;

public class AppTest extends AbstractIntegrationTest {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;

	@Test
	public void testApp() {
		log.debug("testApp");

		Assert.assertTrue(true);
	}
}
