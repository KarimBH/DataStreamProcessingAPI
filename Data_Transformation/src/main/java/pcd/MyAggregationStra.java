package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStra implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		
		if (oldExchange == null) {
			return newExchange;
			}
			String oldBody = oldExchange.getIn().getBody(String.class);
			String newBody = newExchange.getIn().getBody(String.class);
			String[] lines = newBody.split("\n");
			String[] line = oldBody.split("\n");
			int l = lines.length;
			int ll = line.length;
			String  newb = "" ;
			String  oldb= "";
			
			for(int i=0;i<ll-1;i++)
			{
				oldb= oldb+line[i]+"\n";
			}
			
			
			for(int i=2;i<l-1;i++)
			{
				newb= newb+"\n"+lines[i];
			}
			oldExchange.getIn().setBody(oldb +  newb);
			return oldExchange;
	}

}
