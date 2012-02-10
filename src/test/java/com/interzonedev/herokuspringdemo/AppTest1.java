package com.interzonedev.herokuspringdemo;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.interzonedev.herokuspringdemo.service.user.UserService;
import com.interzonedev.integrationtest.dataset.DataSet;
import com.interzonedev.integrationtest.dataset.DataSets;
import com.interzonedev.integrationtest.dataset.dbunit.DbUnitDataSetTester;

@DataSets(dataSets = { @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource") })
public class AppTest1 extends HerokuAbstractIntegrationTest {
	private Log log = LogFactory.getLog(getClass());

	private static List<String> USERS_IGNORE_COLUMN_NAMES = Arrays.asList(new String[] { "id", "time_created",
			"time_updated" });

	@Autowired
	private UserService userService;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private DbUnitDataSetTester dbUnitDataSetTester;

	@Test
	@DataSets(dataSets = { @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource") })
	public void testApp1_1() {
		log.debug("testApp1_1");

		log.debug("userService = " + userService);

		Assert.assertTrue(true);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
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
