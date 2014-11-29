package org.notification1;


import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Notification  {
	
	private PropertiesHandler prop=null;
	public static int i=0;
	
	public  Notification(){prop=new PropertiesHandler();}//Constructor
	
	
//notification from DataBase
	/**
	 *@param request
	 * 		the select request to use in order to get the data needed for the notification 
	 *@param destination
	 *	    the real destination  
	 *	    example: it can be an e-mail address or a GSM number
	 *@throws Exception 
	 *		in case of any any problem with the parameters
	 */ 	
	public void  massiveme_notification(final String request, final String destination) throws Exception{
		
		System.out.println("##################### " + " DataBase " +" #####################");
		 String url="jdbc:oracle:thin://"+prop.getDBaddress();
		 
	        DataSource dataSource = setupDataSource(url,prop.getDBUser(),prop.getDBPassword());

	        SimpleRegistry reg = new SimpleRegistry() ;
	        reg.put("myDataSource",dataSource);

	        CamelContext context = new DefaultCamelContext(reg);
	        context.addRoutes(new RouteBuilder(){

				@Override
				public void configure() throws Exception {
				    	 from("timer:foo?period=5s")
				    	 
				         .setBody(constant(request)) 
				         .to("jdbc:myDataSource")
				         
				    	.process(new Processor() { public void process(Exchange arg0) throws Exception {
				    	  System.out.println("MESSAGEBODY" + arg0.getIn().getBody(String.class)); 
				    	  arg0.getIn().setBody(arg0.getIn().getBody(String.class));
				    	  
				    	  System.out.println("MESSAGEHEADER" + arg0.getIn().getHeader("CamelFileName",String.class));
				    	  }})
				    	  
				    	 .process(new Processor() { public void process(Exchange arg0) throws Exception {
				    	  System.out.println("MESSAGEBODY" + arg0.getIn().getBody(String.class)); 
				    	  arg0.getIn().setBody(arg0.getIn().getBody(String.class));
				    	  }})
				    	 // il faut passer par QUEUE pour lisser la charge vers l'application partenaire
				    	 .to("jms:queue:FlowControlQueueForDB")
				    	  .to(destination); // prop.getMessagingPath() l'app partenaire de messagerie destination contiendra plutot l'@mail ou num GSM
				}
	        });
	        
	        context.start();
	        Thread.sleep(5000);
	        context.stop();	
	};
	

//Notification from a folder containing simple files xml, txt...
/**
 *@param source
 * 		full path to the source folder that is containing the files 
 *      for example: source could be "C:/sourceFolder"
 *@param destination
 *	    the real destination  
 *	    example: it can be an e-mail address or a GSM number
 *@param batch_size
 *		namber of the messages to be aggregated and treated together 
 *@param nb_replay
 *		number of replays in case the message couldn't be sent to the destination
 *@throws Exception 
 *		in case of any any problem with the parameters
 */ 
	public void  massiveme_notification(final String source, final String destination, int batch_size, final int nb_replay) throws Exception{
		System.out.println("##################### " + " simple Files " +" #####################");
		
	//	CamelContext camelContext = new DefaultCamelContext();
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:/META-INF/spring/camel-context.xml"});
        CamelContext camelContext = SpringCamelContext.springCamelContext(context);
        camelContext.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				
				errorHandler(defaultErrorHandler()
		 				.maximumRedeliveries(nb_replay)
		 				.redeliveryDelay(1000)
		 				.retryAttemptedLogLevel(LoggingLevel.ERROR));
				
			 from("file:"+ source +"?"+prop.getFolderOptions())//.transacted() <-- for jms only
			 
			     .log("\n ################# BEFORE AGGREGATION ########## \n MESSAGEBODY: ${body} \n MESSAGEHEADER: ")
				    	  
			  .aggregate(simple(prop.getFolderCorrolationExpression()),new NotificationAggregationStrategy())
		   	  .completionSize(prop.getFolderBatchSize())//.forceCompletionOnStop()  
		   	  .process(new Processor(){
				@Override
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setHeader("Destination", destination);
				}})
		   	  
		   	         .log(" \n ################# AFTER AGGREGATION ########## \n MESSAGEBODY: ${body} \n MESSAGEHEADER: ")
        
			    
			  .to("jms:queue:FlowControlQueueForFolders")
			  .to("file:target/OOOO");// prop.getMessagingPath() :"l'application partenaire qui va envoyer les mails et sms"
			}

        });
		
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
	};
	
	
//Notification from JMS Queue
	/**
	 *@param queue_source
	 * 		the source queue's name 
	 *@param destination
	 *	    the real destination  
	 *	    example: it can be an e-mail address or a GSM number
	 *@param batch_size
	 *		number of the messages to be aggregated and treated together 
	 *@throws Exception 
	 *		in case of any any problem with the parameters
	 */ 
	public void  massiveme_notification( final String queue_source,final String destination, final int batch_size) throws Exception{
			System.out.println("##################### " + " JMS Queue " +" #####################");
	    	
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:/META-INF/spring/camel-context.xml"});
	       CamelContext camelcontext = SpringCamelContext.springCamelContext(context);
	       camelcontext.addRoutes(new RouteBuilder(){

	 	@Override
	 	public void configure() throws Exception {
	 		

	    	
	    //	from("file:C:/test?noop=true").transacted()
	    	//.to("jms:queue:"+queue_source);
	    	//.to("file:target/my_test_mq");
	    	
	 from("jms:queue:"+queue_source).transacted()
	   	.aggregate(simple(prop.getjmsCorrolationExpression()),new NotificationAggregationStrategy())
		   	  .completionSize(batch_size)//.forceCompletionOnStop()
		      .parallelProcessing()
		        .log("\n  MESSAGEBODY: ${body} \n MESSAGEHEADER: ")
		
         .to("jms:queue:FlowControlQueueForJMS")
    	.to(destination);// prop.getMessagingPath() l'app partenaire de messagerie, destination contiendra plutot l'@mail ou num GSM
	    	
	 }});
	    	
	         camelcontext.start();
	         Thread.sleep(5000);
	         camelcontext.stop(); 
  }

	
 
/**	
 * @param connectURI 
 * 		the URI of the database
 * @param user
 * 		the login of the database user
 * @param password
 * 		the password of the database user
 * @return the instance of the DataSource to be added to the registry 
 */
    private static DataSource setupDataSource(String connectURI,String user, String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUsername(user);;
        ds.setPassword(password);
        ds.setUrl(connectURI);
        return ds;
    } 

}

