package com.interzonedev.integrationtest;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.interzonedev.integrationtest.dataset.DataSet;
import com.interzonedev.integrationtest.dataset.DataSets;
import com.interzonedev.integrationtest.dataset.handler.DataSetHandler;
import com.interzonedev.integrationtest.dataset.handler.Handler;
import com.interzonedev.integrationtest.dataset.helper.DataSetHelper;

public class IntegrationTestExecutionListener extends AbstractTestExecutionListener {
	private Log log = LogFactory.getLog(getClass());

	private DataSets classDataSets;

	private DataSet classDataSet;

	private ApplicationContext applicationContext;

	private DataSetHelper dataSetHelper;

	private enum DatabaseOp {
		SETUP, TEARDOWN;
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		log.debug("beforeTestClass");

		Class<?> testClass = testContext.getTestClass();

		if (testClass.isAnnotationPresent(DataSets.class)) {
			classDataSets = (DataSets) testClass.getAnnotation(DataSets.class);
		} else if (testClass.isAnnotationPresent(DataSet.class)) {
			classDataSet = (DataSet) testClass.getAnnotation(DataSet.class);
		}

		applicationContext = testContext.getApplicationContext();

		dataSetHelper = (DataSetHelper) applicationContext.getBean("dataSetHelper");
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		log.debug("beforeTestMethod");

		doDatabaseOpertions(DatabaseOp.SETUP, testContext);
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		log.debug("afterTestMethod");

		doDatabaseOpertions(DatabaseOp.TEARDOWN, testContext);
	}

	private List<DataSet> getTestDataSets(TestContext testContext) {
		List<DataSet> testDataSets = new ArrayList<DataSet>();

		Method method = testContext.getTestMethod();

		if (method.isAnnotationPresent(DataSets.class)) {
			DataSets methodDataSets = (DataSets) method.getAnnotation(DataSets.class);
			testDataSets.addAll(Arrays.asList(methodDataSets.dataSets()));
		} else if (method.isAnnotationPresent(DataSet.class)) {
			DataSet methodDataSet = (DataSet) method.getAnnotation(DataSet.class);
			testDataSets.add(methodDataSet);
		}

		if (testDataSets.isEmpty()) {
			if (null != classDataSets) {
				testDataSets.addAll(Arrays.asList(classDataSets.dataSets()));
			} else if (null != classDataSet) {
				testDataSets.add(classDataSet);
			}
		}

		return testDataSets;
	}

	private void doDatabaseOpertions(DatabaseOp databaseOp, TestContext testContext) {
		List<DataSet> testDataSets = getTestDataSets(testContext);

		if (testDataSets.isEmpty()) {
			StringBuilder errorMessage = new StringBuilder("doDatabaseOpertions: ");
			errorMessage.append("The test method ").append(testContext.getTestMethod().getName());
			errorMessage.append(" on the test class ").append(testContext.getTestClass().getName());
			errorMessage.append(" does not have a DataSets or DataSet annotation");
			errorMessage.append(" on either class or the method level.");
			throw new RuntimeException(errorMessage.toString());
		}

		for (DataSet dataSet : testDataSets) {
			Handler handler = dataSet.handler();
			String handlerBeanId = dataSet.handlerBeanId();

			if (null != handler) {
				handlerBeanId = handler.handlerBeanId();
			}

			if (StringUtils.isBlank(handlerBeanId)) {
				StringBuilder errorMessage = new StringBuilder("doDatabaseOpertions: ");
				errorMessage.append("The test method ").append(testContext.getTestMethod().getName());
				errorMessage.append(" on the test class ").append(testContext.getTestClass().getName());
				errorMessage.append(" does not specify a handler bean id.");
				throw new RuntimeException(errorMessage.toString());
			}

			DataSetHandler dataSetHandler = (DataSetHandler) applicationContext.getBean(handlerBeanId);

			String dataSetFilename = dataSet.filename();

			File dataSetFile = dataSetHelper.getDataSetFile(dataSetFilename);

			String dataSourceBeanId = dataSet.dataSourceBeanId();

			Object dataSourceBean = applicationContext.getBean(dataSourceBeanId);

			switch (databaseOp) {
				case SETUP:
					dataSetHandler.cleanAndInsertData(dataSetFile, dataSourceBean);
					break;
				case TEARDOWN:
					dataSetHandler.cleanData(dataSetFile, dataSourceBean);
					break;
			}
		}
	}
}
