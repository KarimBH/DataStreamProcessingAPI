package pcd;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


public class xml_csv implements Processor {
	
	 static org.jdom.Document document;
	 static Element racine;
	 
     public void process(Exchange exchange) throws Exception {
    	
    	StringBuilder csv = new StringBuilder();
		csv.append("id;type;");	
        Message msg = exchange.getIn();
        String fileName = (String) msg.getHeader("CamelFileName");
	    SAXBuilder sxb = new SAXBuilder();
        document = sxb.build(new File(fileName));
       racine = document.getRootElement();
       List listrows = racine.getChildren("row");
       int lo= listrows.size();
       Iterator i = listrows.iterator();
       String[][] tab = new String[50][50]; ;
       int longu = 0;
       int k=0;
       int j;
       while(i.hasNext())
       { Element courant = (Element)i.next();
    	 String id =courant.getChild("id").getText();
    	 String type = courant.getChild("type").getText();
    	 Element couran = courant.getChild("valeurs");
    	 tab[k][0] = id;
    	 tab[k][1] = type;
    	 List qq =couran.getChildren("val");
    	 longu = qq.size();
    	 Iterator ii = qq.iterator();
    	 j=2;
    	 while(ii.hasNext())
         { Element c = (Element)ii.next();
    		 String v = c.getText();
    		 tab[k][j] = v;
    		 j++;}
    	  k++;}

        for(int b=1;b<=longu;b++)
		{
			csv.append("val"+b+";");
		}
        csv.append("\n");
       
     for(k=0;k<lo;k++)
     { 
    	 for(j=0;j<longu+2;j++) 
         {   
    	 csv.append(tab[k][j].trim()+";");
    	 System.out.println(tab[k][j]);
         }
    	 csv.append("\n");
     }
     
 
	
     exchange.getIn().setBody(csv.toString());
	
		}
}

