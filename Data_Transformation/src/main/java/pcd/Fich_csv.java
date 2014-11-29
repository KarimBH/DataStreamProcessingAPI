package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Fich_csv implements Processor {
	
	
public void process(Exchange exchange) throws Exception {
		
		
		String custom = exchange.getIn().getBody(String.class);
		StringBuilder csv = new StringBuilder();
		csv.append("id;type;");
		String[] lines = custom.split("\n");
		int l=lines.length;
		int li= l-2;
		for(int j=1;j<=li;j++)
		{
			csv.append("val"+j+";");
		}
		
		csv.append("\n");
		String id =  lines[0];
		String type = lines[1];
		csv.append(id.trim()).append(";").append(type.trim()).append(";");
		for(int i=2;i<l;i++)
		{			
			csv.append(lines[i].trim()+";");

		}
		exchange.getIn().setBody(csv.toString());
		
		}

}
