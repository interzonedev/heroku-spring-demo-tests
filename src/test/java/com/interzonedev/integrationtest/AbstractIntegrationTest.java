package com.interzonedev.integrationtest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(IntegrationTestExecutionListener.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-integrationTest.xml" })
public abstract class AbstractIntegrationTest {
}
