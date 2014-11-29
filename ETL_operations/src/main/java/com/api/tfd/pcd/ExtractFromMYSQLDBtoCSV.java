package com.api.tfd.pcd;

import etl_api_tfd_1.extractfrommysqltocsv_0_1.ExtractFromMySQLtoCSV;

public class ExtractFromMYSQLDBtoCSV extends IExtractFromDBtoCSV {
	public void run(String host, String port, String dbName, String username,
			String password, String tableName, String query, String destPath,
			String statFile) {
		ExtractFromMySQLtoCSV extractFromMySQLtoCSV = new ExtractFromMySQLtoCSV();
		String[] context = new String[] { "--context_param host=" + host,
				"--context_param port=" + port,
				"--context_param username=" + username,
				"--context_param password=" + password,
				"--context_param dbName=" + dbName,
				"--context_param tableName=" + tableName,
				"--context_param query=" + query,
				"--context_param destPath=" + destPath };
		extractFromMySQLtoCSV.runJob(context);
	}

	public void run(DBProperties dbprop, String table, String requete,
			String fileDest, String statFile) {
		run(dbprop.getHost(), dbprop.getPort(), dbprop.getDbName(),
				dbprop.getUsername(), dbprop.getPassword(), table, requete,
				fileDest, statFile);
	}
}
