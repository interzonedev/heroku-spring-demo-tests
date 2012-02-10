package com.interzonedev.herokuspringdemo.service.user;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.interzonedev.herokuspringdemo.HerokuAbstractIntegrationTest;
import com.interzonedev.integrationtest.dataset.DataSet;
import com.interzonedev.integrationtest.dataset.DataSets;
import com.interzonedev.integrationtest.dataset.dbunit.DbUnitDataSetTester;

public class UserServiceIntegrationTest extends HerokuAbstractIntegrationTest {
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
	@DataSets(dataSets = { @DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource") })
	public void testCreateUser() {
		log.debug("testCreateUser");

		String firstName = "Gern";
		String lastName = "Blanston";

		User user = userService.createUser(firstName, lastName, true);

		Assert.assertNotNull(user);
		Assert.assertEquals(firstName, user.getFirstName());
		Assert.assertEquals(lastName, user.getLastName());
		Assert.assertTrue(user.isAdmin());

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}

	@Test
	@DataSets(dataSets = { @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource") })
	public void testDeleteUser() {
		log.debug("testDeleteUser");

		User user = userService.getById(1L);

		userService.deleteUser(user);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/emptyUsersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}
}