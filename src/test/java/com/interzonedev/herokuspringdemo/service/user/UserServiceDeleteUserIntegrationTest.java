package com.interzonedev.herokuspringdemo.service.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.interzonedev.sprintfix.dataset.DataSet;

public class UserServiceDeleteUserIntegrationTest extends AbstractUserServiceIntegrationTest {

	private Log log = LogFactory.getLog(getClass());

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteUserNullUser() {
		log.debug("testDeleteUserNullUser");

		userService.deleteUser(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteUserUserWithNullId() {
		log.debug("testDeleteUserUserWithNullId");

		User user = new User();

		userService.deleteUser(user);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteUserUserWithNonPositiveId() {
		log.debug("testDeleteUserUserWithNonPositiveId");

		User user = new User();
		user.setId(0L);

		userService.deleteUser(user);
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testDeleteUserUserWithNonExistingId() {
		log.debug("testDeleteUserUserWithNonExistingId");

		User user = new User();
		user.setId(2L);

		userService.deleteUser(user);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testDeleteUserValid() {
		log.debug("testDeleteUserValid");

		User user = userService.getById(1L);

		userService.deleteUser(user);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/emptyUsersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}

}
