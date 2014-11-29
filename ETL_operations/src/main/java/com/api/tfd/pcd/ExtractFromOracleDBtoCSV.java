package com.api.tfd.pcd;

import etl_api_tfd_1.extractandmap_0_1.extractAndMap;

public class ExtractFromOracleDBtoCSV extends IExtractFromDBtoCSV {
	public void run(String hote, String port, String nomDB, String utilisateur,
			String motDePasse, String table, String requete, String fileDest,
			String statFile) {
		// TODO Auto-generated method stub
		System.out.println("in class");
		extractAndMap talendExtractAndMap = new extractAndMap();

		String[] context = new String[] { "--context_param hote=" + hote,
				"--context_param port=" + port,
				"--context_param utilisateur=" + utilisateur,
				"--context_param motDePasse=" + motDePasse,
				"--context_param nomDB=" + nomDB,
				"--context_param table=" + table,
				"--context_param requete=" + requete,
				"--context_param fileDest=" + fileDest,
				"--context_param statFile=" + statFile };
		talendExtractAndMap.runJob(context);

	}

	public void run(DBProperties dbprop, String table, String requete,
			String fileDest, String statFile) {
		run(dbprop.getHost(), dbprop.getPort(), dbprop.getDbName(),
				dbprop.getUsername(), dbprop.getPassword(), table, requete,
				fileDest, statFile);

	}
}
