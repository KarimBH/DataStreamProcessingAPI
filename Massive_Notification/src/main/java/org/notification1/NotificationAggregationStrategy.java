package org.notification1;


import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class NotificationAggregationStrategy implements AggregationStrategy {
	

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
    	return newExchange;
		}
		
		
		String oldBody = oldExchange.getIn().getBody(String.class);
		String newBody = newExchange.getIn().getBody(String.class);
		oldExchange.getIn().setBody(oldBody + "\n API_DFD_1_NOTIFICATION \n" + newBody);
		return oldExchange;
		}
		}
