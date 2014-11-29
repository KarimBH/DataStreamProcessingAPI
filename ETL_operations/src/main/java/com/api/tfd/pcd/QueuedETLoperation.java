package com.api.tfd.pcd;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class QueuedETLoperation {
	DefaultCamelContext context;

	DataSourceFactory srcDS;
	String jndiSrcDBname;

	DataSourceFactory destDS;
	String jndiDestDBname;

	JndiRegistrySetting registrySet;
	Processor processor;

	CustomRouteBuilder operationRoute;
	
	public void extractTransformLoadQueued(DBProperties srcDBprops,
			String query, Processor processor, DBProperties destDBprops) {
		try {
			extractToJMSqueue(srcDBprops, query, processor, "tempQueue");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void extractToJMSqueue(DBProperties srcDBprops, String query,
			Processor processor, String jmsQueueName) throws Exception {
		try {
			this.processor = processor;

			srcDS = new DataSourceFactory(srcDBprops);

			registrySet = new JndiRegistrySetting();

			jndiSrcDBname = "test1";

			registrySet.bindDataSourceToRegistry(jndiSrcDBname, srcDS);

			operationRoute = new CustomRouteBuilder(context, "jdbc:"
					+ jndiSrcDBname, this.processor, jmsQueueName
					+ ":queue:ETLop");

			context = new DefaultCamelContext(registrySet.reg);

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"admin", "admin", ActiveMQConnection.DEFAULT_BROKER_URL);
			context.addComponent(jmsQueueName,
					JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

			context.addRoutes(operationRoute);
			context.start();

			ProducerTemplate template = context.createProducerTemplate();
			template.sendBody("direct:start", query);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			context.stop();

			registrySet.reg.close();
		}
	}

	public void loadFromJMSqueue(String jmsQueueName,Processor processor, DBProperties destDB)throws Exception {
		try {
			this.processor = processor;

			srcDS = new DataSourceFactory(destDB);

			registrySet = new JndiRegistrySetting();

			jndiSrcDBname = "test1";

			registrySet.bindDataSourceToRegistry(jndiSrcDBname, srcDS);

			operationRoute = new CustomRouteBuilder(context, "jdbc:"
					+ jndiSrcDBname, this.processor, jmsQueueName
					+ ":queue:ETLop");

			context = new DefaultCamelContext(registrySet.reg);

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					"admin", "admin", ActiveMQConnection.DEFAULT_BROKER_URL);
			context.addComponent(jmsQueueName,
					JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

			context.addRoutes(operationRoute);
			context.start();

			ProducerTemplate template = context.createProducerTemplate();
			template.sendBody("direct:start", "");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			context.stop();

			registrySet.reg.close();
		}
	}

}
