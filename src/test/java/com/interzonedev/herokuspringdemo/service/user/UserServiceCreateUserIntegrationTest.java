package com.interzonedev.herokuspringdemo.service.user;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.interzonedev.zankou.dataset.DataSet;

public class UserServiceCreateUserIntegrationTest extends AbstractUserServiceIntegrationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserNullFirstName() {
        log.debug("testCreateUserNullFirstName");

        userService.createUser(null, "test", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserEmptyFirstName() {
        log.debug("testCreateUserEmptyFirstName");

        userService.createUser("", "test", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserBlankFirstName() {
        log.debug("testCreateUserBlankFirstName");

        userService.createUser("  ", "test", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserNullLastName() {
        log.debug("testCreateUserNullLastName");

        userService.createUser("test", null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserEmptyLastName() {
        log.debug("testCreateUserEmptyLastName");

        userService.createUser("test", "", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserBlankLastName() {
        log.debug("testCreateUserBlankLastName");

        userService.createUser("test", "  ", true);
    }

    @Test
    @DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testCreateUserValid() {
        log.debug("testCreateUserValid");

        String firstName = "Gern";
        String lastName = "Blanston";

        Date now = new Date();

        User user = userService.createUser(firstName, lastName, true);

        Assert.assertNotNull(user);
        Assert.assertTrue(user.getId() > 0);
        Assert.assertEquals(1, user.getTimeCreated().compareTo(now));
        Assert.assertEquals(1, user.getTimeUpdated().compareTo(now));
        Assert.assertEquals(firstName, user.getFirstName());
        Assert.assertEquals(lastName, user.getLastName());
        Assert.assertTrue(user.isAdmin());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

}
