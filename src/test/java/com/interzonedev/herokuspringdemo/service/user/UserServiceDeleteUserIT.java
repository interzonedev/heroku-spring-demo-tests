package com.interzonedev.herokuspringdemo.service.user;

import com.interzonedev.zankou.dataset.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceDeleteUserIT extends AbstractUserServiceIT {

    @Test
    public void testDeleteUserNullUser() {
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(null));
    }

    @Test
    public void testDeleteUserUserWithNullId() {
        User user = new User();

        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(user));
    }

    @Test
    public void testDeleteUserUserWithNonPositiveId() {
        User user = new User();
        user.setId(0L);

        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(user));
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testDeleteUserUserWithNonExistentId() {
        User user = new User();
        user.setId(100L);

        boolean emptyResultDataAccessExceptionThrown = false;

        try {
            userService.deleteUser(user);
        } catch (EmptyResultDataAccessException e) {
            emptyResultDataAccessExceptionThrown = true;
        }

        assertTrue(emptyResultDataAccessExceptionThrown);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/usersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

    @Test
    @DataSet(filename = "dataset/users/usersDataSet.xml", dataSourceBeanId = "dataSource")
    public void testDeleteUserValid() {
        User user = userService.getUserById(1L);

        userService.deleteUser(user);

        dbUnitDataSetTester.compareDataSetsIgnoreColumns(dataSource, "dataset/users/emptyUsersDataSet.xml", "users",
                USERS_IGNORE_COLUMN_NAMES);
    }

}
