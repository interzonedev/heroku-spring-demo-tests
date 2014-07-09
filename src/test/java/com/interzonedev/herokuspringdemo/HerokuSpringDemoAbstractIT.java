package com.interzonedev.herokuspringdemo;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

import ch.qos.logback.classic.Logger;

import com.interzonedev.zankou.AbstractIntegrationTest;
import com.interzonedev.zankou.dataset.dbunit.DbUnitDataSetTester;

@ContextConfiguration(locations = { "classpath:com/interzonedev/herokuspringdemo/spring/applicationContext-test.xml" })
public abstract class HerokuSpringDemoAbstractIT extends AbstractIntegrationTest {

    protected final Logger log = (Logger) LoggerFactory.getLogger(getClass());

    @Inject
    protected DataSource dataSource;

    @Inject
    protected DbUnitDataSetTester dbUnitDataSetTester;

}
