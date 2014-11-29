package pcd;

import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class Transformation {
	
	     /*
	      * Conversion vers fichiers xml
	      */
          //fichier plat ( .txt, .csv, .json ) ---> fichier xml
	
			public static void transformer_xml(final String src, final String dest, final int agr)
			{
				 
				 final XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
				 xmlJsonFormat.setEncoding("UTF-8");
				 xmlJsonFormat.setRootName("Root");
				 xmlJsonFormat.setTrimSpaces(true);
				 xmlJsonFormat.setSkipNamespaces(true);
				 xmlJsonFormat.setRemoveNamespacePrefixes(true);
				 xmlJsonFormat.setExpandableProperties(Arrays.asList("mm"));
				 
				
				 try {
				       CamelContext context1 = new DefaultCamelContext();
				       CamelContext context2 = new DefaultCamelContext();
                       
				       context1.addRoutes(new RouteBuilder() {
		               public void configure() throws Exception {
		            	     	 
  	            	     //aggréger les messages .json dans un fichier intermédiaire
		            	   from("file:"+src+"?noop=true")
			            	 .choice()
			            	 .when(header("CamelFileName")
			                 .endsWith(".json"))
			                 .aggregate(constant(true), new MyAggregationStrat())
		            	     .completionSize(agr)
		            	     .process(new json())
		            	     .to("file:src/inter");
		 	          }
		 	          });
				        context1.start();
			 		    Thread.sleep(8000);
			 		    context1.stop();
			 		    
                       context2.addRoutes(new RouteBuilder() {
    		               public void configure() throws Exception {
    		            	   
    		            	   //agréger les messages .csv dans un fichier intermédiaire
 
    		            	     from("file:"+src+"?noop=true")
    			            	 .choice()
    			            	 .when(header("CamelFileName")
    			                 .endsWith(".txt"))
    			                 .to("file:src/inter")
    			            	 .when(header("CamelFileName")
    			                 .endsWith(".csv"))
    			                 .aggregate(constant(true), new MyAggregationStrategy())
    		            	     .completionSize(agr)
    		            	     .to("file:src/inter");
    		            	     
    		            	     //conversion de messages vers .xml
    		            	     
    		            	     from("file:src/inter?noop=true")
    		            	     .choice()
    			            	 .when(header("CamelFileName")
    		                     .endsWith(".txt"))
    		                     .process(new Fich_XML())
    		                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.xml")
    		                     .when(header("CamelFileName")
    		                     .endsWith(".csv"))
    		                     .process(new CSV_XML())
    		                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.xml")
    		                     .when(header("CamelFileName")
    		                     .endsWith(".json"))
    		                     .unmarshal(xmlJsonFormat)
    		                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.xml");
   		 	            }
    		 	        });
    
                    context2.start();
		 		    Thread.sleep(8000);
		 		    context2.stop();
		 		    }
		 		    catch (Exception e) {
		 		    System.out.println(e); 
		 	        }
				}


            /*
             * Conversion vers des fichiers .json
             */
			//fichier plat ( .txt, .csv, .xml ) ---> fichier xml

			public static void transformer_json(final String src, final String dest, final int agr)
			{
				 
				 final XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
				 xmlJsonFormat.setEncoding("UTF-8");
				 xmlJsonFormat.setRootName("Root");
				 xmlJsonFormat.setTrimSpaces(true);
				 xmlJsonFormat.setSkipNamespaces(true);
				 xmlJsonFormat.setRemoveNamespacePrefixes(true);
				 xmlJsonFormat.setExpandableProperties(Arrays.asList("mm"));
				 
				
				 try {
				       CamelContext context1 = new DefaultCamelContext();
				       CamelContext context2 = new DefaultCamelContext();
			           
				       context1.addRoutes(new RouteBuilder() {
			           public void configure() throws Exception {
			        	     	 
			      	     //aggréger les messages .xml dans un fichier intermédiaire
			        	    
			        	    from("file:"+src+"?noop=true")
			            	 .choice()
			            	 .when(header("CamelFileName")
			                 .endsWith(".xml"))
			                 .aggregate(constant(true), new MyAggregationStra())
			        	     .completionSize(agr)
			        	     .process(new xml())
                             .to("file:src/inter");
			        	    
			        	          if(agr==1){
			        	    	  from("file:src/inter")
			        	    	  .choice()
			            	      .when(header("CamelFileName")
			                      .endsWith(".xml"))
			        	    	  .process(new xmlr())
			        	    	  .to("file:src/inter");
			        	    }
			        	   	  
				          }
				          });
				        context1.start();
			 		    Thread.sleep(8000);
			 		    context1.stop();
			 		    
			               context2.addRoutes(new RouteBuilder() {
			               public void configure() throws Exception {
			            	   
			            	   //agréger les messages .csv dans un fichier intermédiaire

			            	     from("file:"+src+"?noop=true")
				            	 .choice()
				            	 .when(header("CamelFileName")
				                 .endsWith(".txt"))
				                 .to("file:src/inter")
				            	 .when(header("CamelFileName")
				                 .endsWith(".csv"))
				                 .aggregate(constant(true), new MyAggregationStrategy())
			            	     .completionSize(agr)
			            	     .to("file:src/inter");
			            	     
			            	     from("file:src/interm")
			        	    	 .to("file:src/inter");
			            	     
			            	     //conversion de messages vers .json
			            	     
			            	     from("file:src/inter?noop=true")
			            	     .choice()
				            	 .when(header("CamelFileName")
			                     .endsWith(".txt"))
			                     .process(new Fich_json())
			                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.json")
			                     .when(header("CamelFileName")
			                     .endsWith(".xml"))
			                     .marshal(xmlJsonFormat)
			                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.json")
			                     .when(header("CamelFileName")
			                     .endsWith(".csv"))
			                     .process(new CSV_XML())
		                         .marshal(xmlJsonFormat)
			                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.json");
				            }
			 	        });

			        context2.start();
					    Thread.sleep(8000);
					    context2.stop();
					    }
					    catch (Exception e) {
					    System.out.println(e); 
				        }
				}

			 /*
             * Conversion vers des fichiers .csv
             */
			//fichier plat ( .txt, .json, .xml ) ---> fichier csv

			public static void transformer_csv(final String src, final String dest, final int agr)
			{
				 
				 final XmlJsonDataFormat xmlJsonFormat = new XmlJsonDataFormat();
				 xmlJsonFormat.setEncoding("UTF-8");
				 xmlJsonFormat.setRootName("Root");
				 xmlJsonFormat.setTrimSpaces(true);
				 xmlJsonFormat.setSkipNamespaces(true);
				 xmlJsonFormat.setRemoveNamespacePrefixes(true);
				 xmlJsonFormat.setExpandableProperties(Arrays.asList("mm"));
				 
				
				 try {
				       CamelContext context1 = new DefaultCamelContext();
				       CamelContext context2 = new DefaultCamelContext();
				       CamelContext context3 = new DefaultCamelContext();
			           
				       context1.addRoutes(new RouteBuilder() {
			           public void configure() throws Exception {
			        	     	 
			      	     //aggréger les messages .xml dans un fichier intermédiaire
			        	    
			        	    from("file:"+src+"?noop=true")
			            	 .choice()
			            	 .when(header("CamelFileName")
			                 .endsWith(".xml"))
			                 .aggregate(constant(true), new MyAggregationStra())
			        	     .completionSize(agr)
			        	     .process(new xml())
                             .to("file:src/inter");
			        	    
			        	    if(agr==1){
			        	    	 from("file:src/inter")
			        	    	 .choice()
			            	     .when(header("CamelFileName")
			                     .endsWith(".xml"))
			        	    	  .process(new xmlr())
			        	    	 .to("file:src/interm");
			        	    }
			        	   	  
				          }
				          });
				        context1.start();
			 		    Thread.sleep(8000);
			 		    context1.stop();
			 		    
			               context2.addRoutes(new RouteBuilder() {
			               public void configure() throws Exception {
			            	   
			            	   //agréger les messages .csv dans un fichier intermédiaire

			            	     from("file:"+src+"?noop=true")
				            	 .choice()
				            	 .when(header("CamelFileName")
				                 .endsWith(".txt"))
				                 .to("file:src/inter")
				            	 .when(header("CamelFileName")
				                 .endsWith(".json"))
				                 .aggregate(constant(true), new MyAggregationStrat())
		            	         .completionSize(agr)
		            	         .process(new json())
		            	         .to("file:src/inter");
			            	     
			            	     from("file:src/interm") //fich xml tel que agr=1
			        	    	 .to("file:src/inter");
			            	     
			            	     //conversion de messages vers .json
			            	     
			            	     from("file:src/inter?noop=true")
			            	     .choice()
				            	 .when(header("CamelFileName")
			                     .endsWith(".txt"))
			                     .process(new Fich_csv())
			                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.csv")
			                     .when(header("CamelFileName")
			                     .endsWith(".xml"))
			                   //  .to("file:/C:/Users/emna/workspace/pcd?fileName=${file:onlyname.noext}.xml")
		                         .to("file:src/interx?fileName=${file:onlyname.noext}.xml")
			                     .when(header("CamelFileName")
			                     .endsWith(".json"))
			                     .unmarshal(xmlJsonFormat)
		                         .to("file:/C:/Users/emna/workspace/pcd?fileName=${file:onlyname.noext}.xml")
		                         .to("file:src/interj?fileName=${file:onlyname.noext}.xml");
			            	     
			            	     from("file:src/interx?noop=true")
			            	     .to("file:/C:/Users/emna/workspace/pcd?fileName=${file:onlyname.noext}.xml");
		                         
			            	     
				            }
			 	        });

			            context2.start();
					    Thread.sleep(8000);
					    context2.stop();
					    
					    context3.addRoutes(new RouteBuilder() {
					           public void configure() throws Exception {
					        	   
					        	   from("file:src/interx")
				            	     .process(new xml_csv())
				                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.csv");
				                     
				         	     
				            	     from("file:src/interj")
			                         .process(new xml_cs())
				                     .to("file:"+dest+"?fileName=${file:onlyname.noext}.csv");
					        	   
					           }
			 	        });

					    context3.start();
					    Thread.sleep(8000);
					    context3.stop();
					    }
					    catch (Exception e) {
					    System.out.println(e); 
				        }
				}


			/*
             * Transformation from an xml file to pojo objets
             */
			
			public static  void transformer_from_xml_tojava(final String src)
			{


				      final XML_model xml_java = new XML_model();
				      
				try {

				       CamelContext context = new DefaultCamelContext();

                       context.addRoutes(new RouteBuilder() {
		               public void configure() throws JAXBException {
         		       DataFormat Jaxb = new JaxbDataFormat("pcd");
                	
         		       
         		         from("file:src/in?noop=true")
	            	  //  .unmarshal(Jaxb)
	            	    .process(new Processor() { public void process(Exchange arg0) throws Exception {
					    	  System.out.println("gggg"); 

	            	    	/* Integer id = ((XML_model) arg0.getIn().getBody()).getId();
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
	          		         
	        				   // System.out.println(xml_java.toString()+"hjghjg");*/
	          		 
				    	  }});

		                  }
				          });
                    

                       
                    context.start();
   		 		    Thread.sleep(8000);
   		 		    context.stop();

   		 		    }
   		 		    catch (Exception e) {
   		 		    System.out.println(e); 
   		 	        }
				//return xml_java;
   				}
			
	
			
			
			
			
			
			
			
			
			}
