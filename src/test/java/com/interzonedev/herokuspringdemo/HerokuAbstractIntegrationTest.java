package com.interzonedev.herokuspringdemo;

import org.springframework.test.context.ContextConfiguration;

import com.interzonedev.integrationtest.AbstractIntegrationTest;

@ContextConfiguration(locations = { "classpath:spring/applicationContext-test.xml" })
public abstract class HerokuAbstractIntegrationTest extends AbstractIntegrationTest {
}
