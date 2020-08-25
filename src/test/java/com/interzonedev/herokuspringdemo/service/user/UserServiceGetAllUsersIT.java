package com.interzonedev.herokuspringdemo.service.user;

import com.interzonedev.zankou.dataset.DataSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceGetAllUsersIT extends AbstractUserServiceIT {

    @Test
    @DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetAllUsersNoUsers() {
        List<User> allUsers = userService.getAllUsers();

        assertNotNull(allUsers);
        assertTrue(allUsers.isEmpty());
    }

    @Test
    @DataSet(filename = "dataset/users/multiUsersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetAllUsersWithUsers() {
        List<User> allUsers = userService.getAllUsers();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());

        User expectedUser1 = userService.getUserById(1L);
        User expectedUser2 = userService.getUserById(2L);

        User actualUser1 = allUsers.get(0);
        User actualUser2 = allUsers.get(1);

        assertEquals(expectedUser1, actualUser1);
        assertEquals(expectedUser2, actualUser2);
    }

}
