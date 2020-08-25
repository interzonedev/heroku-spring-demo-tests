package com.interzonedev.herokuspringdemo.service.user;

import com.interzonedev.herokuspringdemo.TestUtils;
import com.interzonedev.zankou.dataset.DataSet;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceGetByIdIT extends AbstractUserServiceIT {

    @Test
    public void testGetUserByIdNullId() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(null));
    }

    @Test
    public void testGetUserByIdNonPositiveId() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(0L));
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetUserByIdNonExistentId() {
        User user = userService.getUserById(100L);

        assertNull(user);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testGetUserByIdValid() throws ParseException {
        User user = userService.getUserById(1L);

        Date epoch = TestUtils.getEpoch();

        assertNotNull(user);
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals(0, user.getTimeCreated().compareTo(epoch));
        assertEquals(0, user.getTimeUpdated().compareTo(epoch));
        assertEquals("Gern", user.getFirstName());
        assertEquals("Blanston", user.getLastName());
        assertTrue(user.isAdmin());
    }
}
