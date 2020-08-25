package com.interzonedev.herokuspringdemo;

import com.interzonedev.zankou.AbstractIntegrationTest;
import com.interzonedev.zankou.dataset.dbunit.DbUnitDataSetTester;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import javax.sql.DataSource;

@ContextConfiguration(locations = {"classpath:com/interzonedev/herokuspringdemo/spring/applicationContext-test.xml"})
public abstract class HerokuSpringDemoAbstractIT extends AbstractIntegrationTest {

    @Inject
    protected DataSource dataSource;

    @Inject
    protected DbUnitDataSetTester dbUnitDataSetTester;

}
