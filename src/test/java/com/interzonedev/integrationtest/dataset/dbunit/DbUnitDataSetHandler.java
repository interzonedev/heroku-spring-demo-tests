package com.interzonedev.integrationtest.dataset.dbunit;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import com.interzonedev.integrationtest.dataset.handler.DataSetHandler;

public class DbUnitDataSetHandler implements DataSetHandler {

	private Log log = LogFactory.getLog(getClass());

	@Override
	public void cleanAndInsertData(File dataSetFile, Object dataSourceBean) {
		try {
			DataSource dataSource = (DataSource) dataSourceBean;
			doDatabaseOperation(DatabaseOperation.CLEAN_INSERT, dataSource, dataSetFile);
		} catch (Throwable t) {
			String errorMessage = "cleanAndInsertData: Error setting up database";
			log.error(errorMessage, t);
			throw new RuntimeException(errorMessage, t);
		}
	}

	@Override
	public void cleanData(File dataSetFile, Object dataSourceBean) {
		try {
			DataSource dataSource = (DataSource) dataSourceBean;
			doDatabaseOperation(DatabaseOperation.DELETE_ALL, dataSource, dataSetFile);
		} catch (Throwable t) {
			String errorMessage = "cleanData: Error tearing down database";
			log.error(errorMessage, t);
			throw new RuntimeException(errorMessage, t);
		}
	}

	private void doDatabaseOperation(DatabaseOperation databaseOperation, DataSource dataSource, File dataSetFile)
			throws SQLException, DatabaseUnitException, MalformedURLException {
		IDatabaseConnection connection = getDatabaseConnection(dataSource);
		IDataSet dataSet = getDataSet(dataSetFile);
		databaseOperation.execute(connection, dataSet);
		connection.close();
	}

	private IDatabaseConnection getDatabaseConnection(DataSource dataSource) throws SQLException, DatabaseUnitException {
		Connection connection = dataSource.getConnection();

		IDatabaseConnection databaseConnection = new DatabaseConnection(connection);

		// DatabaseConfig databaseConfig = databaseConnection.getConfig();
		// databaseConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());

		return databaseConnection;
	}

	private IDataSet getDataSet(File dataSetFile) throws MalformedURLException, DataSetException {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		FlatXmlDataSet flatXmlDataSet = builder.build(dataSetFile);
		return flatXmlDataSet;
	}
}
