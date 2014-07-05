package com.interzonedev.herokuspringdemo.service.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.interzonedev.zankou.dataset.DataSet;

public class UserServiceDeleteUserIntegrationTest extends AbstractUserServiceIntegrationTest {

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
    public void testDeleteUserUserWithNonExistentId() {
        log.debug("testDeleteUserUserWithNonExistentId");

        User user = new User();
        user.setId(100L);

        boolean emptyResultDataAccessExceptionThrown = false;

        try {
            userService.deleteUser(user);
        } catch (EmptyResultDataAccessException e) {
            emptyResultDataAccessExceptionThrown = true;
        }

        Assert.assertTrue(emptyResultDataAccessExceptionThrown);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testDeleteUserValid() {
        log.debug("testDeleteUserValid");

        User user = userService.getUserById(1L);

        userService.deleteUser(user);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/emptyUsersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

}
