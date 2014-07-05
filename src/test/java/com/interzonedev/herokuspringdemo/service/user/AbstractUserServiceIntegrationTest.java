package com.interzonedev.herokuspringdemo.service.user;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.interzonedev.herokuspringdemo.HerokuSpringDemoAbstractIntegrationTest;
import com.interzonedev.zankou.dataset.DataSet;

@DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
public abstract class AbstractUserServiceIntegrationTest extends HerokuSpringDemoAbstractIntegrationTest {

    protected static List<String> USERS_IGNORE_COLUMN_NAMES = Arrays.asList(new String[] { "id", "time_created",
            "time_updated" });

    @Inject
    protected UserService userService;

}
