package com.interzonedev.herokuspringdemo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.sprintfix.AbstractIntegrationTest;
import com.interzonedev.sprintfix.dataset.dbunit.DbUnitDataSetTester;

@ContextConfiguration(locations = { "classpath:spring/applicationContext-test.xml" })
public abstract class HerokuAbstractIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	protected DataSource dataSource;

	@Autowired
	protected DbUnitDataSetTester dbUnitDataSetTester;

}
