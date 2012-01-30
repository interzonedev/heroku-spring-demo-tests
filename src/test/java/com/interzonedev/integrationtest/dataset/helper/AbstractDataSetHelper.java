package com.interzonedev.integrationtest.dataset.helper;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

public abstract class AbstractDataSetHelper implements DataSetHelper {
	private Log log = LogFactory.getLog(getClass());

	private final String dataSetDirectory;

	@Autowired
	private ApplicationContext applicationContext;

	public AbstractDataSetHelper(String dataSetDirectory) {
		this.dataSetDirectory = dataSetDirectory;
	}

	@Override
	public final File getDataSetFile(String dataSetFilename) {
		try {
			StringBuilder dataSetPath = new StringBuilder(dataSetDirectory);
			if (!dataSetDirectory.endsWith("/")) {
				dataSetPath.append("/");
			}
			dataSetPath.append(dataSetFilename);

			Resource dataSetResource = applicationContext.getResource("classpath:" + dataSetPath.toString());
			File dataSetFile = dataSetResource.getFile();

			return dataSetFile;
		} catch (Exception e) {
			String errorMessage = "getDataSetFile: Error getting dataset file";
			log.error(errorMessage, e);
			throw new RuntimeException(errorMessage, e);
		}
	}

	public List<Map<String, Object>> getDataSetValues(String dataSetFilename, boolean validate) {
		File dataSetFile = getDataSetFile(dataSetFilename);

		List<Map<String, Object>> dataSetValues = getDataSetValues(dataSetFile);

		if (validate) {
			validateDataSetValues(dataSetValues);
		}

		return dataSetValues;
	}

	@Override
	public abstract List<Map<String, Object>> getDataSetValues(File dataSetFile);

	@Override
	public abstract void validateDataSetValues(List<Map<String, Object>> dataSetValues);
}
