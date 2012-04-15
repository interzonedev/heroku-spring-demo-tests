package com.interzonedev.herokuspringdemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.interzonedev.herokuspringdemo.service.user.UserServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({ UserServiceTestSuite.class })
public class HerokuSpringDemoTestSuite {
}
