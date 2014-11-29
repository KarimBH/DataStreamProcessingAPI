package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Fich_json implements Processor {
public void process(Exchange exchange) throws Exception {
		
		
		String custom = exchange.getIn().getBody(String.class);
		String[] lines = custom.split("\n");
		int l=lines.length;
		String id = lines[0];
		String type = lines[1];
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\n");
		json.append("\"id\" : \"").append(id.trim()).append("\",");
		json.append("\n");
		json.append("\"type\" : \"").append(type.trim()).append("\",");
		json.append("\n");
		json.append("\"valeurs\" : [\n");
		String val= "val1";
		for(int i=2;i<l;i++)
		{
			int incr=1;
			
			json.append("{ \""+val+"\" : \"").append(lines[i].trim()).append("\"},");
			json.append("\n");
			incr = incr + 1;
			String a= Integer.toString(incr);
			val= "val"+a;
			
		}
		json.append("]\n}");
		exchange.getIn().setBody(json.toString());
		
		}

}
