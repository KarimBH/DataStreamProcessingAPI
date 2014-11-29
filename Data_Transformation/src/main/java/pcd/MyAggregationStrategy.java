package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
			}
			String oldBody = oldExchange.getIn().getBody(String.class);
			String newBody = newExchange.getIn().getBody(String.class);
			String[] lines = newBody.split("\n");
			int l = lines.length;
			String  newb = "" ;
			for(int i=1;i<l;i++)
			{
				newb= newb+lines[i];
			}
			oldExchange.getIn().setBody(oldBody +  newb);
			return oldExchange;
	}

}
