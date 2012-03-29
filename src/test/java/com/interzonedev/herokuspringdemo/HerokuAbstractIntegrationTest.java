package com.interzonedev.herokuspringdemo;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.sprintfix.AbstractIntegrationTest;
import com.interzonedev.sprintfix.dataset.dbunit.DbUnitDataSetTester;

@ContextConfiguration(locations = { "classpath:com/interzonedev/herokuspringdemo/spring/applicationContext-test.xml" })
public abstract class HerokuAbstractIntegrationTest extends AbstractIntegrationTest {

	protected Log log = LogFactory.getLog(getClass());

	@Autowired
	protected DataSource dataSource;

	@Autowired
	protected DbUnitDataSetTester dbUnitDataSetTester;

}
