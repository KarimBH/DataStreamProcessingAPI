package etl_api_tfd_1.extractfrommysqltocsv_0_1;

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
 * Job: ExtractFromMySQLtoCSV Purpose: Extract data from a MySQL DB and save it to a csv file<br>
 * Description:  <br>
 * @author karim.ben.hamidou@gmail.com
 * @version 5.4.1.r111943
 * @status 
 */
public class ExtractFromMySQLtoCSV implements TalendJob {

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

			if (host != null) {

				this.setProperty("host", host.toString());

			}

			if (port != null) {

				this.setProperty("port", port.toString());

			}

			if (username != null) {

				this.setProperty("username", username.toString());

			}

			if (password != null) {

				this.setProperty("password", password.toString());

			}

			if (dbName != null) {

				this.setProperty("dbName", dbName.toString());

			}

			if (tableName != null) {

				this.setProperty("tableName", tableName.toString());

			}

			if (query != null) {

				this.setProperty("query", query.toString());

			}

			if (destPath != null) {

				this.setProperty("destPath", destPath.toString());

			}

			if (rowSeparator != null) {

				this.setProperty("rowSeparator", rowSeparator.toString());

			}

			if (fieldSeparator != null) {

				this.setProperty("fieldSeparator", fieldSeparator.toString());

			}

		}

		public String host;

		public String getHost() {
			return this.host;
		}

		public String port;

		public String getPort() {
			return this.port;
		}

		public String username;

		public String getUsername() {
			return this.username;
		}

		public String password;

		public String getPassword() {
			return this.password;
		}

		public String dbName;

		public String getDbName() {
			return this.dbName;
		}

		public String tableName;

		public String getTableName() {
			return this.tableName;
		}

		public String query;

		public String getQuery() {
			return this.query;
		}

		public String destPath;

		public String getDestPath() {
			return this.destPath;
		}

		public String rowSeparator;

		public String getRowSeparator() {
			return this.rowSeparator;
		}

		public String fieldSeparator;

		public String getFieldSeparator() {
			return this.fieldSeparator;
		}
	}

	private ContextProperties context = new ContextProperties();

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "ExtractFromMySQLtoCSV";
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
					ExtractFromMySQLtoCSV.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(ExtractFromMySQLtoCSV.this, new Object[] {
									e, currentComponent, globalMap });
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

	public void tMysqlInput_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tMysqlInput_1", System.currentTimeMillis());

		status = "failure";

		tMysqlInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {
		end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

		status = "failure";

		tMysqlInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMysqlInput_1_onSubJobError(java.lang.Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row1Struct implements
			routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_ETL_API_TFD_1_ExtractFromMySQLtoCSV = new byte[0];
		static byte[] commonByteArray_ETL_API_TFD_1_ExtractFromMySQLtoCSV = new byte[0];

		public Dynamic newColumn;

		public Dynamic getNewColumn() {
			return this.newColumn;
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETL_API_TFD_1_ExtractFromMySQLtoCSV) {

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

	public void tMysqlInput_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tMysqlInput_1_SUBPROCESS_STATE", 0);

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

				currentComponent = "tFileOutputDelimited_1";

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						context.destPath)).getAbsolutePath().replace("\\", "/");
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

				if (filetFileOutputDelimited_1.exists()) {
					isFileGenerated_tFileOutputDelimited_1 = false;
				}

				boolean isFirstCheckDyn_tFileOutputDelimited_1 = true;
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitEvery_tFileOutputDelimited_1 = 1000;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /**
				 * Start field
				 * tFileOutputDelimited_1:FIELDSEPARATOR
				 */
				context.fieldSeparator/**
				 * End field
				 * tFileOutputDelimited_1:FIELDSEPARATOR
				 */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
				 * Start
				 * field tFileOutputDelimited_1:ROWSEPARATOR
				 */
				context.rowSeparator/**
				 * End field
				 * tFileOutputDelimited_1:ROWSEPARATOR
				 */
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
										fileName_tFileOutputDelimited_1, true),
								"ISO-8859-15"));

				resourceMap.put("out_tFileOutputDelimited_1",
						outtFileOutputDelimited_1);

				resourceMap.put("nb_line_tFileOutputDelimited_1",
						nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tMysqlInput_1 begin ] start
				 */

				ok_Hash.put("tMysqlInput_1", false);
				start_Hash.put("tMysqlInput_1", System.currentTimeMillis());

				currentComponent = "tMysqlInput_1";

				int tos_count_tMysqlInput_1 = 0;

				java.util.Calendar calendar_tMysqlInput_1 = java.util.Calendar
						.getInstance();
				calendar_tMysqlInput_1.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tMysqlInput_1 = calendar_tMysqlInput_1
						.getTime();
				int nb_line_tMysqlInput_1 = 0;
				java.sql.Connection conn_tMysqlInput_1 = null;
				java.lang.Class.forName("org.gjt.mm.mysql.Driver");
				String dbUser_tMysqlInput_1 = context.username;
				String dbPwd_tMysqlInput_1 = context.password;

				String url_tMysqlInput_1 = "jdbc:mysql://" + context.host + ":"
						+ context.port + "/" + context.dbName + "?"
						+ "noDatetimeStringSync=true";

				conn_tMysqlInput_1 = java.sql.DriverManager.getConnection(
						url_tMysqlInput_1, dbUser_tMysqlInput_1,
						dbPwd_tMysqlInput_1);

				java.sql.Statement stmt_tMysqlInput_1 = conn_tMysqlInput_1
						.createStatement();

				String dbquery_tMysqlInput_1 = context.query;

				globalMap.put("tMysqlInput_1_QUERY", dbquery_tMysqlInput_1);
				java.sql.ResultSet rs_tMysqlInput_1 = null;
				try {
					rs_tMysqlInput_1 = stmt_tMysqlInput_1
							.executeQuery(dbquery_tMysqlInput_1);
					java.sql.ResultSetMetaData rsmd_tMysqlInput_1 = rs_tMysqlInput_1
							.getMetaData();
					int colQtyInRs_tMysqlInput_1 = rsmd_tMysqlInput_1
							.getColumnCount();

					routines.system.Dynamic dcg_tMysqlInput_1 = new routines.system.Dynamic();
					dcg_tMysqlInput_1.setDbmsId("mysql_id");
					List<String> listSchema_tMysqlInput_1 = new java.util.ArrayList<String>();

					int fixedColumnCount_tMysqlInput_1 = 0;

					for (int i = 1; i <= rsmd_tMysqlInput_1.getColumnCount() - 0; i++) {
						if (!(listSchema_tMysqlInput_1
								.contains(rsmd_tMysqlInput_1.getColumnName(i)
										.toUpperCase()))) {
							routines.system.DynamicMetadata dcm_tMysqlInput_1 = new routines.system.DynamicMetadata();
							dcm_tMysqlInput_1.setName(rsmd_tMysqlInput_1
									.getColumnName(i));
							dcm_tMysqlInput_1.setDbName(rsmd_tMysqlInput_1
									.getColumnName(i));
							dcm_tMysqlInput_1.setType(routines.system.Dynamic
									.getTalendTypeFromDBType("mysql_id",
											rsmd_tMysqlInput_1
													.getColumnTypeName(i)
													.toUpperCase(),
											rsmd_tMysqlInput_1.getPrecision(i),
											rsmd_tMysqlInput_1.getScale(i)));
							dcm_tMysqlInput_1.setDbType(rsmd_tMysqlInput_1
									.getColumnTypeName(i));
							dcm_tMysqlInput_1.setFormat("dd-MM-yyyy");
							dcm_tMysqlInput_1.setLength(rsmd_tMysqlInput_1
									.getPrecision(i));
							dcm_tMysqlInput_1.setPrecision(rsmd_tMysqlInput_1
									.getScale(i));
							dcm_tMysqlInput_1.setNullable(rsmd_tMysqlInput_1
									.isNullable(i) == 0 ? false : true);
							dcm_tMysqlInput_1.setKey(false);
							dcm_tMysqlInput_1
									.setSourceType(DynamicMetadata.sourceTypes.database);
							dcm_tMysqlInput_1.setColumnPosition(i);
							dcg_tMysqlInput_1.metadatas.add(dcm_tMysqlInput_1);
						}
					}
					String tmpContent_tMysqlInput_1 = null;
					int column_index_tMysqlInput_1 = 1;

					while (rs_tMysqlInput_1.next()) {
						nb_line_tMysqlInput_1++;

						column_index_tMysqlInput_1 = 1;

						if (colQtyInRs_tMysqlInput_1 < column_index_tMysqlInput_1) {
							row1.newColumn = null;
						} else {
							row1.newColumn = dcg_tMysqlInput_1;
							routines.system.DynamicUtils
									.readColumnsFromDatabase(row1.newColumn,
											rs_tMysqlInput_1,
											fixedColumnCount_tMysqlInput_1);

						}

						/**
						 * [tMysqlInput_1 begin ] stop
						 */
						/**
						 * [tMysqlInput_1 main ] start
						 */

						currentComponent = "tMysqlInput_1";

						tos_count_tMysqlInput_1++;

						/**
						 * [tMysqlInput_1 main ] stop
						 */

						/**
						 * [tFileOutputDelimited_1 main ] start
						 */

						currentComponent = "tFileOutputDelimited_1";

						StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();

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
						 * [tMysqlInput_1 end ] start
						 */

						currentComponent = "tMysqlInput_1";

					}
				} finally {
					rs_tMysqlInput_1.close();
					stmt_tMysqlInput_1.close();

					if (conn_tMysqlInput_1 != null
							&& !conn_tMysqlInput_1.isClosed()) {
						conn_tMysqlInput_1.close();
					}

				}
				globalMap.put("tMysqlInput_1_NB_LINE", nb_line_tMysqlInput_1);

				ok_Hash.put("tMysqlInput_1", true);
				end_Hash.put("tMysqlInput_1", System.currentTimeMillis());

				/**
				 * [tMysqlInput_1 end ] stop
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
				 * [tMysqlInput_1 finally ] start
				 */

				currentComponent = "tMysqlInput_1";

				/**
				 * [tMysqlInput_1 finally ] stop
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

		globalMap.put("tMysqlInput_1_SUBPROCESS_STATE", 1);
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
		final ExtractFromMySQLtoCSV ExtractFromMySQLtoCSVClass = new ExtractFromMySQLtoCSV();

		int exitCode = ExtractFromMySQLtoCSVClass.runJobInTOS(args);

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
			java.io.InputStream inContext = ExtractFromMySQLtoCSV.class
					.getClassLoader().getResourceAsStream(
							"etl_api_tfd_1/extractfrommysqltocsv_0_1/contexts/"
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
			context.host = (String) context.getProperty("host");
			context.port = (String) context.getProperty("port");
			context.username = (String) context.getProperty("username");
			context.password = (String) context.getProperty("password");
			context.dbName = (String) context.getProperty("dbName");
			context.tableName = (String) context.getProperty("tableName");
			context.query = (String) context.getProperty("query");
			context.destPath = (String) context.getProperty("destPath");
			context.rowSeparator = (String) context.getProperty("rowSeparator");
			context.fieldSeparator = (String) context
					.getProperty("fieldSeparator");
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("host")) {
				context.host = (String) parentContextMap.get("host");
			}
			if (parentContextMap.containsKey("port")) {
				context.port = (String) parentContextMap.get("port");
			}
			if (parentContextMap.containsKey("username")) {
				context.username = (String) parentContextMap.get("username");
			}
			if (parentContextMap.containsKey("password")) {
				context.password = (String) parentContextMap.get("password");
			}
			if (parentContextMap.containsKey("dbName")) {
				context.dbName = (String) parentContextMap.get("dbName");
			}
			if (parentContextMap.containsKey("tableName")) {
				context.tableName = (String) parentContextMap.get("tableName");
			}
			if (parentContextMap.containsKey("query")) {
				context.query = (String) parentContextMap.get("query");
			}
			if (parentContextMap.containsKey("destPath")) {
				context.destPath = (String) parentContextMap.get("destPath");
			}
			if (parentContextMap.containsKey("rowSeparator")) {
				context.rowSeparator = (String) parentContextMap
						.get("rowSeparator");
			}
			if (parentContextMap.containsKey("fieldSeparator")) {
				context.fieldSeparator = (String) parentContextMap
						.get("fieldSeparator");
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

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tMysqlInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tMysqlInput_1) {
			globalMap.put("tMysqlInput_1_SUBPROCESS_STATE", -1);

			e_tMysqlInput_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		if (false) {
			System.out
					.println((endUsedMemory - startUsedMemory)
							+ " bytes memory increase when running : ExtractFromMySQLtoCSV");
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
 * 36428 characters generated by Talend Enterprise Data Integration on the 8 mai
 * 2014 19:28:15 GMT+01:00
 ************************************************************************************************/
