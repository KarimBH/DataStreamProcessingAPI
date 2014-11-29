package org.notification1;


import java.util.Map;

import javax.activation.DataHandler;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Messages {
	
	private int batch_size;
	private int count; 
	private CamelContext camel;
	private String destination;
	private String bodies[]; 
	private String body;
	private Map<String, Object> headers;
	private Map<String, DataHandler> attachements;
	
	//constructor @ destination: the receiver of the mail of sms.. example @mail or GSM number
	Messages(final String destination ) throws Exception{ 
		count=0;
	   // this.destination=destination;
	    ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:/META-INF/spring/camel-context.xml"});
        camel = SpringCamelContext.springCamelContext(context);
		camel.addRoutes(new RouteBuilder(){

			@Override
			public void configure() throws Exception {
				from("jms:"+destination)
				.process(new Processor(){

					@Override
					public void process(Exchange exchange) throws Exception {
						body= exchange.getIn().getBody(String.class);
						headers=exchange.getIn().getHeaders();
						attachements=exchange.getIn().getAttachments();
						
					}})
					.split(body()).log("Split line ${body}")
				.to("");
				
			}});
		        
	}
	
	
	public void getThings() throws Exception
	{ 
		for(int i=0;i<batch_size;i++)
		{ 
			//bodies[i]=;
		
		}
		
		
	}
	
	

}
