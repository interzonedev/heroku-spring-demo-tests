package com.interzonedev.integrationtest.dataset.handler;

public interface DataSetHandler {
	public void cleanAndInsertData(String dataSetFilename);

	public void cleanData();
}
