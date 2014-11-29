package com.api.tfd.pcd;

import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CustomRouteBuilder extends RouteBuilder {

	private String from;
	private String to;
	private Processor processor;

	public CustomRouteBuilder(CamelContext context, String from,
			Processor processor, String to) {
		super(context);
		this.from = from;
		this.to = to;
		this.processor = processor;
	}

	@Override
	public void configure() {
		from("direct:start")
				// .setBody(constant("select * from produits"))
				.to(from)
				.process(processor)
				.to(to);
				//.setBody(constant("select count(*) from produits")).to(to);
		//from("activemq:queue:NewOrders?brokerURL=tcp://192.168.64.144:61616")

	}

}