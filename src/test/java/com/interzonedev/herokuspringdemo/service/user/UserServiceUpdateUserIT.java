package com.interzonedev.herokuspringdemo.service.user;

import com.interzonedev.zankou.dataset.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceUpdateUserIT extends AbstractUserServiceIT {

    @Test
    public void testUpdateUserNullUser() {
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(null));
    }

    @Test
    public void testUpdateUserNullId() {
        User user = new User();

        assertThrows(InvalidUserException.class, () -> userService.updateUser(user));

    }

    @Test
    public void testUpdateUserNonPositiveId() {
        User user = new User();
        user.setId(0L);

        assertThrows(InvalidUserException.class, () -> userService.updateUser(user));
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testUpdateUserNonExistentId() {
        User user = new User();
        user.setId(100L);

        boolean invalidUserExceptionThrown = false;

        try {
            userService.updateUser(user);
        } catch (InvalidUserException e) {
            invalidUserExceptionThrown = true;
        }

        assertTrue(invalidUserExceptionThrown);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testUpdateUserNullFirstName() {
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

        assertTrue(invalidUserExceptionThrown);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testUpdateUserNullLastName() {
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

        assertTrue(invalidUserExceptionThrown);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testUpdateUserValid() {
        User user = userService.getUserById(1L);
        user.setFirstName("Uncle");
        user.setLastName("Fester");
        user.setAdmin(false);

        userService.updateUser(user);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/updatedUserDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

}
