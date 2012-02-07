package com.interzonedev.herokuspringdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.interzonedev.herokuspringdemo.service.user.UserService;
import com.interzonedev.integrationtest.dataset.DataSet;
import com.interzonedev.integrationtest.dataset.DataSets;

@DataSets(dataSets = { @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource") })
public class AppTest1 extends HerokuAbstractIntegrationTest {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;

	@Test
	@DataSets(dataSets = { @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource") })
	public void testApp1_1() {
		log.debug("testApp1_1");

		Assert.assertTrue(true);
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testApp1_2() {
		log.debug("testApp1_2");

		Assert.assertTrue(true);
	}

	@Test
	public void testApp1_3() {
		log.debug("testApp1_3");

		Assert.assertTrue(true);
	}
}
