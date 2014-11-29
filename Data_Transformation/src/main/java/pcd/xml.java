package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class xml implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub


		String custom = exchange.getIn().getBody(String.class);
		String newb ;
		newb = custom +"\n</root>";

		exchange.getIn().setBody(newb.toString());	

		
		
	}

}
