package com.api.tfd.pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder2 extends RouteBuilder {

	@Override
	public void configure() {
		from("direct:start").process(new Processor() {
			public void process(Exchange arg0) throws Exception {
				// TODO Auto-generated method stub
				System.out.println(arg0.getIn().getBody());
			}
		})
		// .setBody(constant("select * from produits"))
				.to("jdbc:test5").process(new Processor() {
					public void process(Exchange arg0) throws Exception {
						
						// TODO Auto-generated method stub
				
					}
				});
//				.to("jms:queue").process(new Processor() {
//					public void process(Exchange arg0) throws Exception {
//						// TODO Auto-generated method stub
//						System.out.println(arg0.getIn().getBody()
//								+ arg0.getExchangeId());
//					}
//				});
	}


}