package org.notification1;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {
	
	private String DBuser;
	private String DBpassword;
	private String DBaddress;
	private String jdbcDriver;
	
	private String folderOptions;
	private String folderAggregationCorrolationExpression;
	private int folderBatchSize=1;
	
	private String jmsAggregationCorrolationExpression;
	private int jmsBatchSize=1;
	
	private String messagingAppPath;
	
	
//constructor
	PropertiesHandler()
	{
		try (FileReader reader = new FileReader("config.properties")) {
			Properties properties = new Properties();
			properties.load(reader);
			DBuser = properties.getProperty("DB.user");
			DBpassword = properties.getProperty("DB.password");
			DBaddress = properties.getProperty("DB.address");
			jdbcDriver= properties.getProperty("jdbc.driver");
			messagingAppPath=	properties.getProperty("messagingApp.path");	
			
			folderBatchSize = Integer.parseInt(properties.getProperty("folder.batchSize")); if(folderBatchSize<1) folderBatchSize=1;
			folderOptions=properties.getProperty("folder.options");
			folderAggregationCorrolationExpression = properties.getProperty("folder.aggregationCorrolationExpression");
			
			jmsAggregationCorrolationExpression = properties.getProperty("jms.aggregationCorrolationExpression");
			jmsBatchSize= Integer.parseInt(properties.getProperty("jms.batchSize"));  if(jmsBatchSize<1) jmsBatchSize=1;
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//Getters
  //DB properties
/**	
 * @return the login of the database user
 */
	public String getDBUser(){return DBuser;}
/**	
 * @return the password of the database user
 */
	public String getDBPassword(){return DBpassword;}
/**	
 * @return the database address
 */
	public String getDBaddress(){return DBaddress;}
/**	
 * @return the jdbc driver: software used to with databases
 */
	public String getJdbcDriver(){return jdbcDriver;}
  //Folder properties
/**
 * @return the options used with the file component 
 */
	public String getFolderOptions(){return folderOptions;}
/**
 * @return the corrolation expression to be used for the aggregation of folders 
 */
	public String getFolderCorrolationExpression(){return folderAggregationCorrolationExpression;}
/**	
 * @return the number of files to be treated at once  
 */
	public int getFolderBatchSize(){return folderBatchSize;}
  // JMS properties
/**	
 *@return the corrolation expression to be used for the aggregation of messages from a jms queue
 */
	public String getjmsCorrolationExpression(){return jmsAggregationCorrolationExpression;}
/**	
 * @return the number of files to be treated at once 
 */
	public int getJMSBatchSize(){return jmsBatchSize;}
  // messaging application
/**	
 * @return the path to the messaging application
 */
	public String getMessagingPath(){return messagingAppPath;}
	

}
