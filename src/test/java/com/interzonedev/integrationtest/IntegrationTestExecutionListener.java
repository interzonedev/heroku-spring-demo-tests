package com.interzonedev.integrationtest;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class IntegrationTestExecutionListener extends AbstractTestExecutionListener {
	private ApplicationContext applicationContext;

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		applicationContext = testContext.getApplicationContext();
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
	}
}
