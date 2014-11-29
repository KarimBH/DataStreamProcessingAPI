package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class json implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		
		String custom = exchange.getIn().getBody(String.class);
		String newb= "[ \n";
		newb = newb + custom +"\n ]";

		exchange.getIn().setBody(newb.toString());	

	}

}
