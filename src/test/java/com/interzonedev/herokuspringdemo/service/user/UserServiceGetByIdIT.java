package com.interzonedev.herokuspringdemo.service.user;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.interzonedev.herokuspringdemo.TestUtils;
import com.interzonedev.zankou.dataset.DataSet;

public class UserServiceGetByIdIT extends AbstractUserServiceIT {
    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByIdNullId() {
        log.debug("testGetUserByIdNullId");

        userService.getUserById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByIdNonPositiveId() {
        log.debug("testGetUserByIdNonPositiveId");

        userService.getUserById(0L);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetUserByIdNonExistentId() {
        log.debug("testGetUserByIdNonExistentId");

        User user = userService.getUserById(100L);

        Assert.assertNull(user);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetUserByIdValid() throws ParseException {
        log.debug("testGetUserByIdValid");

        User user = userService.getUserById(1L);

        Date epoch = TestUtils.getEpoch();

        Assert.assertNotNull(user);
        Assert.assertEquals(Long.valueOf(1L), user.getId());
        Assert.assertEquals(0, user.getTimeCreated().compareTo(epoch));
        Assert.assertEquals(0, user.getTimeUpdated().compareTo(epoch));
        Assert.assertEquals("Gern", user.getFirstName());
        Assert.assertEquals("Blanston", user.getLastName());
        Assert.assertTrue(user.isAdmin());
    }
}
