package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Fich_XML implements Processor {
	
	
	
	public void process(Exchange exchange) throws Exception {
		
		
		String custom = exchange.getIn().getBody(String.class);
		String[] lines = custom.split("\n");
		int l=lines.length;
		String id = lines[0];
		String type = lines[1];
		StringBuilder xml = new StringBuilder();
		xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("\n<root>\n<row>");
		xml.append("\n");
		xml.append("<id>").append(id.trim()).append("</id>");
		xml.append("\n");
		xml.append("<type>").append(type.trim()).append("</type>");
		xml.append("\n");
		xml.append("<valeurs>\n");
		String val= "val1";
		for(int i=2;i<l;i++)
		{
			int incr=1;
			
			xml.append("<"+val+">").append(lines[i].trim()).append("</"+val+">");
			xml.append("\n");
			incr = incr + 1;
			String a= Integer.toString(incr);
			val= "val"+a;
			
		}
		xml.append("</valeurs>\n</row>\n</root>");
		exchange.getIn().setBody(xml.toString());
		
		}
		}


