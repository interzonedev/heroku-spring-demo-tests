package com.interzonedev.herokuspringdemo;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.zankou.AbstractIntegrationTest;
import com.interzonedev.zankou.dataset.dbunit.DbUnitDataSetTester;

@ContextConfiguration(locations = { "classpath:com/interzonedev/herokuspringdemo/spring/applicationContext-test.xml" })
public abstract class HerokuSpringDemoAbstractIntegrationTest extends AbstractIntegrationTest {

	protected Log log = LogFactory.getLog(getClass());

	@Inject
	protected DataSource dataSource;

	@Inject
	protected DbUnitDataSetTester dbUnitDataSetTester;

}
