package org.notification1;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class myMain {
public static void main(String[] args) throws Exception {
	
	
	
	//Notification from JMS Queue
			System.out.println("##################### " + " JMS Queue " +" #####################");
	    	

		/*	ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"file:C:/Users/MAHA/workspace_EE/notification1/src/main/ressources/META-INF/spring/jms_context.xml",
			"classpath*:/pom.xml"});*/
			
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:/META-INF/spring/jms_context.xml"});
	       CamelContext camel = SpringCamelContext.springCamelContext(context);
	     
	       
	   //  CamelContext camel = new DefaultCamelContext();
	    	 camel.addRoutes(new RouteBuilder(){


	 	public void configure() throws Exception {
	 		

	    	
	    //	from("file:C:/test?noop=true").transacted()
	    	//.to("jms:queue:"+queue_source);
	    	//.to("file:target/my_test_mq");
	    	
	 from("jms:queue:Q").transacted()
	/*   //	.aggregate(simple("${header.id}.substring(0,15)"),new StringAggregationStrategy())
		 //  	  .completionSize(batch_size).forceCompletionOnStop()
		     // .parallelProcessing()
		      .process(new Processor() { public void process(Exchange arg0) throws Exception {
				    	  System.out.println("MESSAGEBODY" + arg0.getIn().getBody(String.class)); 
				    	  arg0.getIn().setBody(arg0.getIn().getBody(String.class));
				    	  }})
		    //  .to("file:F:/test");
*/ //from("file:src/s?noop=true").to("jms:queue:Q");
    	.to("file:target/mytarget");
	    	
	    	 
	    	
	 }});
	    	
	    	 camel.start();
	         Thread.sleep(5000);
	         camel.stop(); 
	         System.out.println("END Camel Context");
  
}
}
