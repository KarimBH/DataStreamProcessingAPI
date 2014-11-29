package etl_api_tfd_1.extractandmap_0_1;

import routines.Mathematical;
import routines.DataOperation;
import routines.Relational;
import routines.TalendDate;
import routines.TalendDataGenerator;
import routines.Numeric;
import routines.SQLike;
import routines.TalendString;
import routines.StringHandling;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")
/**
 * Job: extractAndMap Purpose: Extraire les données d'une base et créer un fichier plat avec.<br>
 * Description: Extraire les données d'une base sans connaissance du schéma et créer un fichier plat avec.
 Les doublons des données extraites de la base sont automatiquement supprimer. <br>
 * @author karim.ben.hamidou@gmail.com
 * @version 5.4.1.r111943
 * @status 
 */
public class extractAndMap implements TalendJob {

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset
			.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends java.util.Properties {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (hote != null) {

				this.setProperty("hote", hote.toString());

			}

			if (port != null) {

				this.setProperty("port", port.toString());

			}

			if (nomDB != null) {

				this.setProperty("nomDB", nomDB.toString());

			}

			if (utilisateur != null) {

				this.setProperty("utilisateur", utilisateur.toString());

			}

			if (motDePasse != null) {

				this.setProperty("motDePasse", motDePasse.toString());

			}

			if (table != null) {

				this.setProperty("table", table.toString());

			}

			if (requete != null) {

				this.setProperty("requete", requete.toString());

			}

			if (fileDest != null) {

				this.setProperty("fileDest", fileDest.toString());

			}

			if (statFile != null) {

				this.setProperty("statFile", statFile.toString());

			}

		}

		public String hote;

		public String getHote() {
			return this.hote;
		}

		public String port;

		public String getPort() {
			return this.port;
		}

		public String nomDB;

		public String getNomDB() {
			return this.nomDB;
		}

		public String utilisateur;

		public String getUtilisateur() {
			return this.utilisateur;
		}

		public String motDePasse;

		public String getMotDePasse() {
			return this.motDePasse;
		}

		public String table;

		public String getTable() {
			return this.table;
		}

		public String requete;

		public String getRequete() {
			return this.requete;
		}

		public String fileDest;

		public String getFileDest() {
			return this.fileDest;
		}

		public String statFile;

		public String getStatFile() {
			return this.statFile;
		}
	}

	private ContextProperties context = new ContextProperties();

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "extractAndMap";
	private final String projectName = "ETL_API_TFD_1";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	public boolean isExportedAsOSGI = false;

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	public void setDataSources(
			java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources
				.entrySet()) {
			talendDataSources.put(
					dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry
							.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
	}

	StatCatcherUtils tStatCatcher_1 = new StatCatcherUtils(
			"_DFEPwNNqEeOdZ8ZA12A3cw", "0.1");

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(
			new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private java.lang.Exception exception = null;

	public java.lang.Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends java.lang.Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private java.lang.Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(java.lang.Exception e, String errorComponent,
				final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public java.lang.Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(java.lang.Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null
						&& currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE",
							getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE",
						getExceptionCauseMessage(e));
				System.err
						.println("Exception in component " + currentComponent);
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					extractAndMap.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(extractAndMap.this, new Object[] { e,
									currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (java.lang.SecurityException e) {
					this.e.printStackTrace();
				} catch (java.lang.IllegalArgumentException e) {
					this.e.printStackTrace();
				} catch (java.lang.IllegalAccessException e) {
					this.e.printStackTrace();
				} catch (java.lang.reflect.InvocationTargetException e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tOracleInput_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tOracleInput_1", System.currentTimeMillis());

		tStatCatcher_1.addMessage(
				"failure",
				"tOracleInput_1",
				end_Hash.get("tOracleInput_1")
						- start_Hash.get("tOracleInput_1"));
		tStatCatcher_1Process(globalMap);

		status = "failure";

		tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

		tStatCatcher_1.addMessage(
				"failure",
				"tFileOutputDelimited_1",
				end_Hash.get("tFileOutputDelimited_1")
						- start_Hash.get("tFileOutputDelimited_1"));
		tStatCatcher_1Process(globalMap);

		status = "failure";

		tOracleInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tStatCatcher_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tStatCatcher_1", System.currentTimeMillis());

		status = "failure";

		tStatCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_2_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

		status = "failure";

		tStatCatcher_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tOracleInput_1_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tStatCatcher_1_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row1Struct implements
			routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_ETL_API_TFD_1_extractAndMap = new byte[0];
		static byte[] commonByteArray_ETL_API_TFD_1_extractAndMap = new byte[0];

		public Dynamic newColumn;

		public Dynamic getNewColumn() {
			return this.newColumn;
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETL_API_TFD_1_extractAndMap) {

				try {

					int length = 0;

					this.newColumn = (Dynamic) dis.readObject();

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Dynamic

				dos.writeObject(this.newColumn);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("newColumn=" + String.valueOf(newColumn));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tOracleInput_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tOracleInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";
		int iterateLoop = 0;
		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1",
						System.currentTimeMillis());

				tStatCatcher_1.addMessage("begin", "tFileOutputDelimited_1");
				tStatCatcher_1Process(globalMap);

				currentComponent = "tFileOutputDelimited_1";

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						context.fileDest)).getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(0, fileName_tFileOutputDelimited_1
										.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1
										.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
							.substring(0, fileName_tFileOutputDelimited_1
									.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(0, fileName_tFileOutputDelimited_1
										.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1
										.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(
						fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME",
						fileName_tFileOutputDelimited_1);

				boolean isFirstCheckDyn_tFileOutputDelimited_1 = true;
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitEvery_tFileOutputDelimited_1 = 1000;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /**
				 * Start field
				 * tFileOutputDelimited_1:FIELDSEPARATOR
				 */
				";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
				 * Start
				 * field tFileOutputDelimited_1:ROWSEPARATOR
				 */
				"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null
						&& directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(
							directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {

						dir_tFileOutputDelimited_1.mkdirs();

					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(
						new java.io.OutputStreamWriter(
								new java.io.FileOutputStream(
										fileName_tFileOutputDelimited_1, false),
								"ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_1",
						outtFileOutputDelimited_1);

				resourceMap.put("nb_line_tFileOutputDelimited_1",
						nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tOracleInput_1 begin ] start
				 */

				ok_Hash.put("tOracleInput_1", false);
				start_Hash.put("tOracleInput_1", System.currentTimeMillis());

				tStatCatcher_1.addMessage("begin", "tOracleInput_1");
				tStatCatcher_1Process(globalMap);

				currentComponent = "tOracleInput_1";

				int tos_count_tOracleInput_1 = 0;

				int nb_line_tOracleInput_1 = 0;
				java.sql.Connection conn_tOracleInput_1 = null;
				java.lang.Class.forName("oracle.jdbc.OracleDriver");

				String url_tOracleInput_1 = null;
				url_tOracleInput_1 = "jdbc:oracle:thin:@" + context.hote + ":"
						+ context.port + ":" + context.nomDB;

				String dbUser_tOracleInput_1 = context.utilisateur;
				String dbPwd_tOracleInput_1 = context.motDePasse;

				conn_tOracleInput_1 = java.sql.DriverManager.getConnection(
						url_tOracleInput_1, dbUser_tOracleInput_1,
						dbPwd_tOracleInput_1);
				java.sql.Statement stmtGetTZ_tOracleInput_1 = conn_tOracleInput_1
						.createStatement();
				java.sql.ResultSet rsGetTZ_tOracleInput_1 = stmtGetTZ_tOracleInput_1
						.executeQuery("select sessiontimezone from dual");
				String sessionTimezone_tOracleInput_1 = java.util.TimeZone
						.getDefault().getID();
				while (rsGetTZ_tOracleInput_1.next()) {
					sessionTimezone_tOracleInput_1 = rsGetTZ_tOracleInput_1
							.getString(1);
				}
				((oracle.jdbc.OracleConnection) conn_tOracleInput_1)
						.setSessionTimeZone(sessionTimezone_tOracleInput_1);

				java.sql.Statement stmt_tOracleInput_1 = conn_tOracleInput_1
						.createStatement();

				String dbquery_tOracleInput_1 = context.requete;

				globalMap.put("tOracleInput_1_QUERY", dbquery_tOracleInput_1);
				java.sql.ResultSet rs_tOracleInput_1 = null;
				try {
					rs_tOracleInput_1 = stmt_tOracleInput_1
							.executeQuery(dbquery_tOracleInput_1);
					java.sql.ResultSetMetaData rsmd_tOracleInput_1 = rs_tOracleInput_1
							.getMetaData();
					int colQtyInRs_tOracleInput_1 = rsmd_tOracleInput_1
							.getColumnCount();

					routines.system.Dynamic dcg_tOracleInput_1 = new routines.system.Dynamic();
					dcg_tOracleInput_1.setDbmsId("oracle_id");
					List<String> listSchema_tOracleInput_1 = new java.util.ArrayList<String>();

					int fixedColumnCount_tOracleInput_1 = 0;

					for (int i = 1; i <= rsmd_tOracleInput_1.getColumnCount() - 0; i++) {
						if (!(listSchema_tOracleInput_1
								.contains(rsmd_tOracleInput_1.getColumnName(i)
										.toUpperCase()))) {
							routines.system.DynamicMetadata dcm_tOracleInput_1 = new routines.system.DynamicMetadata();
							dcm_tOracleInput_1.setName(rsmd_tOracleInput_1
									.getColumnName(i));
							dcm_tOracleInput_1.setDbName(rsmd_tOracleInput_1
									.getColumnName(i));
							dcm_tOracleInput_1
									.setType(routines.system.Dynamic
											.getTalendTypeFromDBType(
													"oracle_id",
													rsmd_tOracleInput_1
															.getColumnTypeName(
																	i)
															.toUpperCase(),
													rsmd_tOracleInput_1
															.getPrecision(i),
													rsmd_tOracleInput_1
															.getScale(i)));
							dcm_tOracleInput_1.setDbType(rsmd_tOracleInput_1
									.getColumnTypeName(i));
							dcm_tOracleInput_1.setFormat("dd-MM-yyyy");
							if ("LONG".equals(rsmd_tOracleInput_1
									.getColumnTypeName(i).toUpperCase())) {
								String length = MetadataTalendType
										.getDefaultDBTypes(
												"oracle_id",
												"LONG",
												MetadataTalendType.DEFAULT_LENGTH);
								if (length != null && !("".equals(length))) {
									dcm_tOracleInput_1.setLength(Integer
											.parseInt(length));
								} else {
									dcm_tOracleInput_1
											.setLength(rsmd_tOracleInput_1
													.getPrecision(i));
								}
							} else {
								dcm_tOracleInput_1
										.setLength(rsmd_tOracleInput_1
												.getPrecision(i));
							}
							dcm_tOracleInput_1.setPrecision(rsmd_tOracleInput_1
									.getScale(i));
							dcm_tOracleInput_1.setNullable(rsmd_tOracleInput_1
									.isNullable(i) == 0 ? false : true);
							dcm_tOracleInput_1.setKey(false);
							dcm_tOracleInput_1
									.setSourceType(DynamicMetadata.sourceTypes.database);
							dcm_tOracleInput_1.setColumnPosition(i);
							dcg_tOracleInput_1.metadatas
									.add(dcm_tOracleInput_1);
						}
					}
					String tmpContent_tOracleInput_1 = null;
					int column_index_tOracleInput_1 = 1;

					while (rs_tOracleInput_1.next()) {
						nb_line_tOracleInput_1++;

						column_index_tOracleInput_1 = 1;

						if (colQtyInRs_tOracleInput_1 < column_index_tOracleInput_1) {
							row1.newColumn = null;
						} else {
							row1.newColumn = dcg_tOracleInput_1;
							routines.system.DynamicUtils
									.readColumnsFromDatabase(row1.newColumn,
											rs_tOracleInput_1,
											fixedColumnCount_tOracleInput_1);

						}

						/**
						 * [tOracleInput_1 begin ] stop
						 */
						/**
						 * [tOracleInput_1 main ] start
						 */

						currentComponent = "tOracleInput_1";

						tos_count_tOracleInput_1++;

						/**
						 * [tOracleInput_1 main ] stop
						 */

						/**
						 * [tFileOutputDelimited_1 main ] start
						 */

						currentComponent = "tFileOutputDelimited_1";

						StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();

						if (isFirstCheckDyn_tFileOutputDelimited_1
								&& filetFileOutputDelimited_1.length() == 0) {

							routines.system.DynamicUtils
									.writeHeaderToDelimitedFile(row1.newColumn,
											outtFileOutputDelimited_1,
											OUT_DELIM_tFileOutputDelimited_1);

							outtFileOutputDelimited_1
									.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
							isFirstCheckDyn_tFileOutputDelimited_1 = false;
						}

						if (row1.newColumn != null) {

							routines.system.DynamicUtils
									.writeValuesToStringBuilder(row1.newColumn,
											sb_tFileOutputDelimited_1,
											OUT_DELIM_tFileOutputDelimited_1);
						}

						sb_tFileOutputDelimited_1
								.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

						nb_line_tFileOutputDelimited_1++;
						resourceMap.put("nb_line_tFileOutputDelimited_1",
								nb_line_tFileOutputDelimited_1);

						outtFileOutputDelimited_1
								.write(sb_tFileOutputDelimited_1.toString());

						tos_count_tFileOutputDelimited_1++;

						/**
						 * [tFileOutputDelimited_1 main ] stop
						 */

						/**
						 * [tOracleInput_1 end ] start
						 */

						currentComponent = "tOracleInput_1";

					}
				} finally {
					stmt_tOracleInput_1.close();

					if (conn_tOracleInput_1 != null
							&& !conn_tOracleInput_1.isClosed()) {
						conn_tOracleInput_1.close();
					}

				}

				globalMap.put("tOracleInput_1_NB_LINE", nb_line_tOracleInput_1);

				ok_Hash.put("tOracleInput_1", true);
				end_Hash.put("tOracleInput_1", System.currentTimeMillis());

				tStatCatcher_1.addMessage(
						"end",
						"tOracleInput_1",
						end_Hash.get("tOracleInput_1")
								- start_Hash.get("tOracleInput_1"));
				tStatCatcher_1Process(globalMap);

				/**
				 * [tOracleInput_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE",
						nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME",
						fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1",
						System.currentTimeMillis());

				tStatCatcher_1.addMessage(
						"end",
						"tFileOutputDelimited_1",
						end_Hash.get("tFileOutputDelimited_1")
								- start_Hash.get("tFileOutputDelimited_1"));
				tStatCatcher_1Process(globalMap);

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tOracleInput_1 finally ] start
				 */

				currentComponent = "tOracleInput_1";

				/**
				 * [tOracleInput_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tOracleInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements
			routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_ETL_API_TFD_1_extractAndMap = new byte[0];
		static byte[] commonByteArray_ETL_API_TFD_1_extractAndMap = new byte[0];

		public java.util.Date moment;

		public java.util.Date getMoment() {
			return this.moment;
		}

		public String pid;

		public String getPid() {
			return this.pid;
		}

		public String father_pid;

		public String getFather_pid() {
			return this.father_pid;
		}

		public String root_pid;

		public String getRoot_pid() {
			return this.root_pid;
		}

		public Long system_pid;

		public Long getSystem_pid() {
			return this.system_pid;
		}

		public String project;

		public String getProject() {
			return this.project;
		}

		public String job;

		public String getJob() {
			return this.job;
		}

		public String job_repository_id;

		public String getJob_repository_id() {
			return this.job_repository_id;
		}

		public String job_version;

		public String getJob_version() {
			return this.job_version;
		}

		public String context;

		public String getContext() {
			return this.context;
		}

		public String origin;

		public String getOrigin() {
			return this.origin;
		}

		public String message_type;

		public String getMessage_type() {
			return this.message_type;
		}

		public String message;

		public String getMessage() {
			return this.message;
		}

		public Long duration;

		public Long getDuration() {
			return this.duration;
		}

		private java.util.Date readDate(ObjectInputStream dis)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos)
				throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETL_API_TFD_1_extractAndMap.length) {
					if (length < 1024
							&& commonByteArray_ETL_API_TFD_1_extractAndMap.length == 0) {
						commonByteArray_ETL_API_TFD_1_extractAndMap = new byte[1024];
					} else {
						commonByteArray_ETL_API_TFD_1_extractAndMap = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETL_API_TFD_1_extractAndMap, 0,
						length);
				strReturn = new String(
						commonByteArray_ETL_API_TFD_1_extractAndMap, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETL_API_TFD_1_extractAndMap) {

				try {

					int length = 0;

					this.moment = readDate(dis);

					this.pid = readString(dis);

					this.father_pid = readString(dis);

					this.root_pid = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.system_pid = null;
					} else {
						this.system_pid = dis.readLong();
					}

					this.project = readString(dis);

					this.job = readString(dis);

					this.job_repository_id = readString(dis);

					this.job_version = readString(dis);

					this.context = readString(dis);

					this.origin = readString(dis);

					this.message_type = readString(dis);

					this.message = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.duration = null;
					} else {
						this.duration = dis.readLong();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.moment, dos);

				// String

				writeString(this.pid, dos);

				// String

				writeString(this.father_pid, dos);

				// String

				writeString(this.root_pid, dos);

				// Long

				if (this.system_pid == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.system_pid);
				}

				// String

				writeString(this.project, dos);

				// String

				writeString(this.job, dos);

				// String

				writeString(this.job_repository_id, dos);

				// String

				writeString(this.job_version, dos);

				// String

				writeString(this.context, dos);

				// String

				writeString(this.origin, dos);

				// String

				writeString(this.message_type, dos);

				// String

				writeString(this.message, dos);

				// Long

				if (this.duration == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.duration);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("moment=" + String.valueOf(moment));
			sb.append(",pid=" + pid);
			sb.append(",father_pid=" + father_pid);
			sb.append(",root_pid=" + root_pid);
			sb.append(",system_pid=" + String.valueOf(system_pid));
			sb.append(",project=" + project);
			sb.append(",job=" + job);
			sb.append(",job_repository_id=" + job_repository_id);
			sb.append(",job_version=" + job_version);
			sb.append(",context=" + context);
			sb.append(",origin=" + origin);
			sb.append(",message_type=" + message_type);
			sb.append(",message=" + message);
			sb.append(",duration=" + String.valueOf(duration));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tStatCatcher_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tStatCatcher_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";
		int iterateLoop = 0;
		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				row3Struct row3 = new row3Struct();

				/**
				 * [tFileOutputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_2", false);
				start_Hash.put("tFileOutputDelimited_2",
						System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_2";

				int tos_count_tFileOutputDelimited_2 = 0;

				String fileName_tFileOutputDelimited_2 = "";
				fileName_tFileOutputDelimited_2 = (new java.io.File(
						context.statFile)).getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_2 = null;
				String extension_tFileOutputDelimited_2 = null;
				String directory_tFileOutputDelimited_2 = null;
				if ((fileName_tFileOutputDelimited_2.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") < fileName_tFileOutputDelimited_2
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(0, fileName_tFileOutputDelimited_2
										.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2
										.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
							.substring(0, fileName_tFileOutputDelimited_2
									.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(0, fileName_tFileOutputDelimited_2
										.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2
										.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					}
					directory_tFileOutputDelimited_2 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_2 = true;
				java.io.File filetFileOutputDelimited_2 = new java.io.File(
						fileName_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME",
						fileName_tFileOutputDelimited_2);

				int nb_line_tFileOutputDelimited_2 = 0;
				int splitEvery_tFileOutputDelimited_2 = 1000;
				int splitedFileNo_tFileOutputDelimited_2 = 0;
				int currentRow_tFileOutputDelimited_2 = 0;

				final String OUT_DELIM_tFileOutputDelimited_2 = /**
				 * Start field
				 * tFileOutputDelimited_2:FIELDSEPARATOR
				 */
				";"/** End field tFileOutputDelimited_2:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_2 = /**
				 * Start
				 * field tFileOutputDelimited_2:ROWSEPARATOR
				 */
				"\n"/** End field tFileOutputDelimited_2:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_2 != null
						&& directory_tFileOutputDelimited_2.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_2 = new java.io.File(
							directory_tFileOutputDelimited_2);
					if (!dir_tFileOutputDelimited_2.exists()) {

						dir_tFileOutputDelimited_2.mkdirs();

					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_2 = null;
				outtFileOutputDelimited_2 = new java.io.BufferedWriter(
						new java.io.OutputStreamWriter(
								new java.io.FileOutputStream(
										fileName_tFileOutputDelimited_2, false),
								"ISO-8859-15"));

				if (filetFileOutputDelimited_2.length() == 0) {

					outtFileOutputDelimited_2.write("moment");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("pid");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("father_pid");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("root_pid");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("system_pid");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("project");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("job");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("job_repository_id");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("job_version");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("context");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("origin");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("message_type");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("message");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write("duration");

					outtFileOutputDelimited_2
							.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_2",
						outtFileOutputDelimited_2);

				resourceMap.put("nb_line_tFileOutputDelimited_2",
						nb_line_tFileOutputDelimited_2);

				/**
				 * [tFileOutputDelimited_2 begin ] stop
				 */

				/**
				 * [tStatCatcher_1 begin ] start
				 */

				ok_Hash.put("tStatCatcher_1", false);
				start_Hash.put("tStatCatcher_1", System.currentTimeMillis());

				currentComponent = "tStatCatcher_1";

				int tos_count_tStatCatcher_1 = 0;

				for (StatCatcherUtils.StatCatcherMessage scm : tStatCatcher_1
						.getMessages()) {
					row3.pid = pid;
					row3.root_pid = rootPid;
					row3.father_pid = fatherPid;
					row3.project = projectName;
					row3.job = jobName;
					row3.context = contextStr;
					row3.origin = (scm.getOrigin() == null
							|| scm.getOrigin().length() < 1 ? null : scm
							.getOrigin());
					row3.message = scm.getMessage();
					row3.duration = scm.getDuration();
					row3.moment = scm.getMoment();
					row3.message_type = scm.getMessageType();
					row3.job_version = scm.getJobVersion();
					row3.job_repository_id = scm.getJobId();
					row3.system_pid = scm.getSystemPid();

					/**
					 * [tStatCatcher_1 begin ] stop
					 */
					/**
					 * [tStatCatcher_1 main ] start
					 */

					currentComponent = "tStatCatcher_1";

					tos_count_tStatCatcher_1++;

					/**
					 * [tStatCatcher_1 main ] stop
					 */

					/**
					 * [tFileOutputDelimited_2 main ] start
					 */

					currentComponent = "tFileOutputDelimited_2";

					StringBuilder sb_tFileOutputDelimited_2 = new StringBuilder();

					if (row3.moment != null) {

						sb_tFileOutputDelimited_2.append(

						FormatterUtils.format_Date(row3.moment,
								"yyyy-MM-dd HH:mm:ss")

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.pid != null) {

						sb_tFileOutputDelimited_2.append(

						row3.pid

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.father_pid != null) {

						sb_tFileOutputDelimited_2.append(

						row3.father_pid

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.root_pid != null) {

						sb_tFileOutputDelimited_2.append(

						row3.root_pid

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.system_pid != null) {

						sb_tFileOutputDelimited_2.append(

						row3.system_pid

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.project != null) {

						sb_tFileOutputDelimited_2.append(

						row3.project

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.job != null) {

						sb_tFileOutputDelimited_2.append(

						row3.job

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.job_repository_id != null) {

						sb_tFileOutputDelimited_2.append(

						row3.job_repository_id

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.job_version != null) {

						sb_tFileOutputDelimited_2.append(

						row3.job_version

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.context != null) {

						sb_tFileOutputDelimited_2.append(

						row3.context

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.origin != null) {

						sb_tFileOutputDelimited_2.append(

						row3.origin

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.message_type != null) {

						sb_tFileOutputDelimited_2.append(

						row3.message_type

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.message != null) {

						sb_tFileOutputDelimited_2.append(

						row3.message

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_tFileOutputDelimited_2);

					if (row3.duration != null) {

						sb_tFileOutputDelimited_2.append(

						row3.duration

						);

					}

					sb_tFileOutputDelimited_2
							.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);

					nb_line_tFileOutputDelimited_2++;
					resourceMap.put("nb_line_tFileOutputDelimited_2",
							nb_line_tFileOutputDelimited_2);

					outtFileOutputDelimited_2.write(sb_tFileOutputDelimited_2
							.toString());

					tos_count_tFileOutputDelimited_2++;

					/**
					 * [tFileOutputDelimited_2 main ] stop
					 */

					/**
					 * [tStatCatcher_1 end ] start
					 */

					currentComponent = "tStatCatcher_1";

				}

				ok_Hash.put("tStatCatcher_1", true);
				end_Hash.put("tStatCatcher_1", System.currentTimeMillis());

				/**
				 * [tStatCatcher_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 end ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (outtFileOutputDelimited_2 != null) {
					outtFileOutputDelimited_2.flush();
					outtFileOutputDelimited_2.close();
				}

				globalMap.put("tFileOutputDelimited_2_NB_LINE",
						nb_line_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME",
						fileName_tFileOutputDelimited_2);

				resourceMap.put("finish_tFileOutputDelimited_2", true);

				ok_Hash.put("tFileOutputDelimited_2", true);
				end_Hash.put("tFileOutputDelimited_2",
						System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_2 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tStatCatcher_1 finally ] start
				 */

				currentComponent = "tStatCatcher_1";

				/**
				 * [tStatCatcher_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (resourceMap.get("finish_tFileOutputDelimited_2") == null) {

					java.io.Writer outtFileOutputDelimited_2 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_2");
					if (outtFileOutputDelimited_2 != null) {
						outtFileOutputDelimited_2.flush();
						outtFileOutputDelimited_2.close();
					}

				}

				/**
				 * [tFileOutputDelimited_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tStatCatcher_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	private java.util.Properties context_param = new java.util.Properties();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final extractAndMap extractAndMapClass = new extractAndMap();

		int exitCode = extractAndMapClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		try {
			// call job/subjob with an existing context, like:
			// --context=production. if without this parameter, there will use
			// the default context instead.
			java.io.InputStream inContext = extractAndMap.class
					.getClassLoader().getResourceAsStream(
							"etl_api_tfd_1/extractandmap_0_1/contexts/"
									+ contextStr + ".properties");
			if (isDefaultContext && inContext == null) {

			} else {
				if (inContext != null) {
					// defaultProps is in order to keep the original context
					// value
					defaultProps.load(inContext);
					inContext.close();
					context = new ContextProperties(defaultProps);
				} else {
					// print info and job continue to run, for case:
					// context_param is not empty.
					System.err.println("Could not find the context "
							+ contextStr);
				}
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
			}
			context.hote = (String) context.getProperty("hote");
			context.port = (String) context.getProperty("port");
			context.nomDB = (String) context.getProperty("nomDB");
			context.utilisateur = (String) context.getProperty("utilisateur");
			context.motDePasse = (String) context.getProperty("motDePasse");
			context.table = (String) context.getProperty("table");
			context.requete = (String) context.getProperty("requete");
			context.fileDest = (String) context.getProperty("fileDest");
			context.statFile = (String) context.getProperty("statFile");
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("hote")) {
				context.hote = (String) parentContextMap.get("hote");
			}
			if (parentContextMap.containsKey("port")) {
				context.port = (String) parentContextMap.get("port");
			}
			if (parentContextMap.containsKey("nomDB")) {
				context.nomDB = (String) parentContextMap.get("nomDB");
			}
			if (parentContextMap.containsKey("utilisateur")) {
				context.utilisateur = (String) parentContextMap
						.get("utilisateur");
			}
			if (parentContextMap.containsKey("motDePasse")) {
				context.motDePasse = (String) parentContextMap
						.get("motDePasse");
			}
			if (parentContextMap.containsKey("table")) {
				context.table = (String) parentContextMap.get("table");
			}
			if (parentContextMap.containsKey("requete")) {
				context.requete = (String) parentContextMap.get("requete");
			}
			if (parentContextMap.containsKey("fileDest")) {
				context.fileDest = (String) parentContextMap.get("fileDest");
			}
			if (parentContextMap.containsKey("statFile")) {
				context.statFile = (String) parentContextMap.get("statFile");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil
				.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName,
				jobName, contextStr, jobVersion);

		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName,
				parent_part_launcher, Thread.currentThread().getId() + "", "",
				"", "", "", resumeUtil.convertToJsonText(context));

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();
		tStatCatcher_1.addMessage("begin");

		this.globalResumeTicket = true;// to run tPreJob

		try {
			tStatCatcher_1Process(globalMap);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tOracleInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tOracleInput_1) {
			globalMap.put("tOracleInput_1_SUBPROCESS_STATE", -1);

			e_tOracleInput_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory)
					+ " bytes memory increase when running : extractAndMap");
		}
		tStatCatcher_1.addMessage(status == "" ? "end" : status,
				(end - startTime));
		try {
			tStatCatcher_1Process(globalMap);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		int returnCode = 0;
		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher,
				Thread.currentThread().getId() + "", "", "" + returnCode, "",
				"", "");

		return returnCode;

	}

	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();
		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index),
							keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		}

	}

	private final String[][] escapeChars = { { "\\n", "\n" }, { "\\'", "\'" },
			{ "\\r", "\r" }, { "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" },
			{ "\\\\", "\\" } };

	private String replaceEscapeChars(String keyValue) {
		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}
		for (String[] strArray : escapeChars) {
			keyValue = keyValue.replace(strArray[0], strArray[1]);
		}
		return keyValue;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 64128 characters generated by Talend Enterprise Data Integration on the 5 mai
 * 2014 20:13:48 GMT+01:00
 ************************************************************************************************/
