package com.interzonedev.herokuspringdemo.service.user;

import org.junit.Assert;
import org.junit.Test;

import com.interzonedev.zankou.dataset.DataSet;

public class UserServiceUpdateUserIntegrationTest extends AbstractUserServiceIntegrationTest {
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateUserNullUser() {
		log.debug("testUpdateUserNullUser");

		userService.updateUser(null);
	}

	@Test(expected = InvalidUserException.class)
	public void testUpdateUserNullId() {
		log.debug("testUpdateUserNullId");

		User user = new User();

		userService.updateUser(user);
	}

	@Test(expected = InvalidUserException.class)
	public void testUpdateUserNonPositiveId() {
		log.debug("testUpdateUserNonPositiveId");

		User user = new User();
		user.setId(0L);

		userService.updateUser(user);
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testUpdateUserNonExistentId() {
		log.debug("testUpdateUserNonExistentId");

		User user = new User();
		user.setId(100L);

		boolean invalidUserExceptionThrown = false;

		try {
			userService.updateUser(user);
		} catch (InvalidUserException e) {
			invalidUserExceptionThrown = true;
		}

		Assert.assertTrue(invalidUserExceptionThrown);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testUpdateUserNullFirstName() {
		log.debug("testUpdateUserNullFirstName");

		User user = userService.getUserById(1L);
		user.setFirstName(null);
		user.setLastName("Fester");
		user.setAdmin(false);

		boolean invalidUserExceptionThrown = false;

		try {
			userService.updateUser(user);
		} catch (InvalidUserException e) {
			invalidUserExceptionThrown = true;
		}

		Assert.assertTrue(invalidUserExceptionThrown);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testUpdateUserNullLastName() {
		log.debug("testUpdateUserNullLastName");

		User user = userService.getUserById(1L);
		user.setFirstName("Uncle");
		user.setLastName(null);
		user.setAdmin(false);

		boolean invalidUserExceptionThrown = false;

		try {
			userService.updateUser(user);
		} catch (InvalidUserException e) {
			invalidUserExceptionThrown = true;
		}

		Assert.assertTrue(invalidUserExceptionThrown);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}

	@Test
	@DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
	public void testUpdateUserValid() {
		log.debug("testUpdateUserValid");

		User user = userService.getUserById(1L);
		user.setFirstName("Uncle");
		user.setLastName("Fester");
		user.setAdmin(false);

		userService.updateUser(user);

		dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/updatedUserDataSet.xml", "users",
				USERS_IGNORE_COLUMN_NAMES);
	}
}
