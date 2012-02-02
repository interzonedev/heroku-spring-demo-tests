package com.interzonedev.integrationtest.dataset.helper;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

public class DataSetHelperImpl implements DataSetHelper {
	private Log log = LogFactory.getLog(getClass());

	private final String dataSetDirectory;

	@Autowired
	private ApplicationContext applicationContext;

	public DataSetHelperImpl(String dataSetDirectory) {
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
}
