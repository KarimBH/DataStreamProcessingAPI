package com.api.tfd.pcd;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExtractDataFromExchange implements Processor {

	public void process(Exchange exchange) throws Exception {
		System.out.println(exchange.getIn().getBody()+"\n\n -----------------------------------------");
		ArrayList<?> s =  (ArrayList<?>) exchange.getIn().getBody();
		StringBuilder str = new StringBuilder();
		for (int i=0; i<s.size();i++){
		
			}
		exchange.getIn().setBody(str);
		
	}

}
