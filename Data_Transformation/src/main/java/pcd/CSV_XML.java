package pcd;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CSV_XML implements Processor {
	
	
public void process(Exchange exchange) throws Exception {
		
		
	String custom = exchange.getIn().getBody(String.class);
	String[] lines = custom.split("\n");
	int l=lines.length;
	
	String attr = lines[0];
	String[] col = attr.split(";");
	int ll= col.length;	
	String id;
	String type;
	
	
	
	StringBuilder xml = new StringBuilder();
	xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	xml.append("\n");
	xml.append("<root> \n");
	for(int j=1;j<l;j++)
	{
		String att = lines[j];
		col=att.split(";");
		id=col[0];
		type=col[1];
		xml.append("<row> \n");
		xml.append("\t <id>").append(id.trim()).append("</id>");
		xml.append("\n");
		xml.append("\t <type>").append(type.trim()).append("</type>");
		xml.append("\n");
		xml.append("\t <Valeurs> \n");
		String val= "val1";
		
		for(int i=2;i<ll;i++)
		{
            int incr=1;
			
			xml.append("\t \t <"+val+">").append(col[i].trim()).append("</"+val+">");
			xml.append("\n");
			incr = incr + 1;
			String a= Integer.toString(incr);
			val= "val"+a;
			
		}
		
		xml.append("\t </Valeurs> \n");
		xml.append("</row> \n");
		
	}
	xml.append("</root> \n");
	exchange.getIn().setBody(xml.toString());
	
		}

}
