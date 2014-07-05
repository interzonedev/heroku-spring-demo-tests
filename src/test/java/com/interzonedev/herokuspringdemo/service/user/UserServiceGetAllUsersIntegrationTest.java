package com.interzonedev.herokuspringdemo.service.user;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.interzonedev.zankou.dataset.DataSet;

public class UserServiceGetAllUsersIntegrationTest extends AbstractUserServiceIntegrationTest {
    @Test
    @DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetAllUsersNoUsers() {
        log.debug("testGetAllUsersNoUsers");

        List<User> allUsers = userService.getAllUsers();

        Assert.assertNotNull(allUsers);
        Assert.assertTrue(allUsers.isEmpty());
    }

    @Test
    @DataSet(filename = "dataset/users/multiUsersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetAllUsersWithUsers() {
        log.debug("testGetAllUsersWithUsers");

        List<User> allUsers = userService.getAllUsers();

        Assert.assertNotNull(allUsers);
        Assert.assertEquals(2, allUsers.size());

        User expectedUser1 = userService.getUserById(1L);
        User expectedUser2 = userService.getUserById(2L);

        User actualUser1 = allUsers.get(0);
        User actualUser2 = allUsers.get(1);

        Assert.assertEquals(expectedUser1, actualUser1);
        Assert.assertEquals(expectedUser2, actualUser2);
    }
}
