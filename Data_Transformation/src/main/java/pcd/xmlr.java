package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class xmlr implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		
		String custom = exchange.getIn().getBody(String.class);
		
		String[] lines = custom.split("\n");
		int l = lines.length;
		String newb="" ;
		for(int i=0;i<l-1;i++)
		{
			newb= newb+"\n"+lines[i];
		}
		
		
		exchange.getIn().setBody(newb.toString());	
		
		
		
		

	}

}
