package com.api.tfd.pcd;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;

public class SettingCamelContext extends DefaultCamelContext {
	public  SettingCamelContext(JndiRegistry reg, int SleepTime, String jndiSrcDBname, String jndiDestDBname) throws Exception {
		 new DefaultCamelContext(reg);

		this.addRoutes(new CustomRouteBuilder(this, "jdbc:"
				+ jndiSrcDBname, null, "jdbc:" + jndiDestDBname));
		this.start();
		Thread.sleep(SleepTime);
		this.stop();
	}
}
