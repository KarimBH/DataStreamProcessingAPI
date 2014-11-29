package pcd;

import javax.xml.bind.JAXBException;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;

public class Main_class {
	
	
	public static void main(String[] args) throws Exception {
		  
			  
			  
			  
			    Transformation.transformer_xml("src/in", "src/out", 2);
			  
			  //Transformation.transformer_json("src/in", "src/out", 2);
			  //Transformation.transformer_csv("src/in", "src/out", 2);
			  
			  //XML_model test = new XML_model();
			 // Transformation.transformer_from_xml_tojava("src/in");


			  
			// System.out.println(test.toString()); 
			  
		/*	  try {

			       CamelContext context = new DefaultCamelContext();

			       context.addRoutes(new RouteBuilder() {
			             public void configure() throws JAXBException {
    		       DataFormat Jaxb = new JaxbDataFormat("pcd");
           	
    		       
    		         from("file:src/in?noop=true")
           	    .unmarshal(Jaxb)
           	    .process(new Processor() { public void process(Exchange arg0) throws Exception {
				    	  System.out.println("gggg"); 

           	    	 Integer id = ((XML_model) arg0.getIn().getBody()).getId();
           	      	 Double Date = ((XML_model) arg0.getIn().getBody()).getDate();
           	       	 Double Heure = ((XML_model) arg0.getIn().getBody()).getHeure();
           	       	 Double latitude = ((XML_model) arg0.getIn().getBody()).getlatitude();
           	       	 Double longitude = ((XML_model) arg0.getIn().getBody()).getlongitude();
           	       	 Float vitesse = ((XML_model) arg0.getIn().getBody()).getvitesse();
           	       	 Double CAP = ((XML_model) arg0.getIn().getBody()).getCAP();
           	       	 String X = ((XML_model) arg0.getIn().getBody()).getX();
           	         String Y = ((XML_model) arg0.getIn().getBody()).getY();
           	       	 String Z = ((XML_model) arg0.getIn().getBody()).getZ();
           	       	 Float Z_Signé = ((XML_model) arg0.getIn().getBody()).getZ_Signé();
           	       	 Float Lat_deg = ((XML_model) arg0.getIn().getBody()).getLat_deg();
           	       	 Float lon_deg = ((XML_model) arg0.getIn().getBody()).getlon_deg();
           	       	 
           	       	 System.out.println(id);
           	       	 

           	       	 
           	       	 xml_java.setId(id);
         		         xml_java.setDate(Date);
         		         xml_java.setHeure(Heure);
         		         xml_java.setlatitude(latitude);
         		         xml_java.setlongitude(longitude);
         		         xml_java.setvitesse(vitesse);
         		         xml_java.setCAP(CAP);
         		         xml_java.setX(X);
         		         xml_java.setY(Y);
         		         xml_java.setZ(Z);
         		         xml_java.setZ_Signé(Z_Signé);
         		         xml_java.setLat_deg(Lat_deg);
         		         xml_java.setlon_deg(lon_deg);
         		         
       				   // System.out.println(xml_java.toString()+"hjghjg");
         		 
			    	  }});

	                  }
			          });
               

                  
                    context.start();
		 		    Thread.sleep(8000);
		 		    context.stop();

		 		    }
		 		    catch (Exception e) {
		 		    System.out.println(e); 
		 	        }*/
		
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			  
			 }
              
} 