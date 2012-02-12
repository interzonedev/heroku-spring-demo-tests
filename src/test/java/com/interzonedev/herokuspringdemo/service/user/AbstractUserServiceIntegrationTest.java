package com.interzonedev.herokuspringdemo.service.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.interzonedev.herokuspringdemo.HerokuAbstractIntegrationTest;
import com.interzonedev.sprintfix.dataset.DataSet;

@DataSet(filename = "dataset/users/emptyUsersDataSet.xml", dataSourceBeanId = "dataSource")
public abstract class AbstractUserServiceIntegrationTest extends HerokuAbstractIntegrationTest {

	protected static List<String> USERS_IGNORE_COLUMN_NAMES = Arrays.asList(new String[] { "id", "time_created",
			"time_updated" });

	@Autowired
	protected UserService userService;

}
