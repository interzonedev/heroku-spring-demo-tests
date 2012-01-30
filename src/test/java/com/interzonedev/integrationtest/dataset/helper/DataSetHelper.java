package com.interzonedev.integrationtest.dataset.helper;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface DataSetHelper {
	public File getDataSetFile(String dataSetFilename);

	public List<Map<String, Object>> getDataSetValues(File dataSetFile);

	public void validateDataSetValues(List<Map<String, Object>> dataSetValues);
}
