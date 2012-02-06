package com.interzonedev.integrationtest.dataset.handler;

import java.io.File;

public interface DataSetHandler {
	public void cleanAndInsertData(File dataSetFile, Object dataSourceBean);

	public void cleanData(File dataSetFile, Object dataSourceBean);
}
