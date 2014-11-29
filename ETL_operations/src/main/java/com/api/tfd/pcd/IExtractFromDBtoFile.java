package com.api.tfd.pcd;
public interface IExtractFromDBtoFile {

	public void run(String hote, String port, String nomDB, String utilisateur,
			String motDePasse, String table, String requete, String fileDest,
			String statFile);

	public void run(DBProperties dbprop, String table, String requete, String fileDest,
			String statFile);

}
