package com.interzonedev.herokuspringdemo.service.user;

import com.interzonedev.zankou.dataset.DataSet;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceCreateUserIT extends AbstractUserServiceIT {

    @Test
    public void testCreateUserNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(null, "test", true));
    }

    @Test
    public void testCreateUserEmptyFirstName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("", "test", true));
    }

    @Test
    public void testCreateUserBlankFirstName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("  ", "test", true));
    }

    @Test
    public void testCreateUserNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("test", null, true));
    }

    @Test
    public void testCreateUserEmptyLastName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("test", "", true));
    }

    @Test
    public void testCreateUserBlankLastName() {
        assertThrows(IllegalArgumentException.class, () -> userService.createUser("test", "  ", true));
    }

    @Test
    @DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testCreateUserValid() {
        String firstName = "Gern";
        String lastName = "Blanston";

        Date now = new Date();

        User user = userService.createUser(firstName, lastName, true);

        assertNotNull(user);
        assertTrue(user.getId() > 0);
        assertEquals(1, user.getTimeCreated().compareTo(now));
        assertEquals(1, user.getTimeUpdated().compareTo(now));
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertTrue(user.isAdmin());

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

}
