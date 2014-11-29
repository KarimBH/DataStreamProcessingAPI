package com.api.tfd.pcd;

import javax.sql.DataSource;

import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.test.CamelTestSupport;

public class JndiRegistrySetting extends CamelTestSupport {
	JndiRegistry reg;
	public JndiRegistrySetting(){
		try {
			reg = this.createRegistry();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void bindDataSourceToRegistry(String dataSourceName, DataSource dataSource){
		reg.bind(dataSourceName, dataSource);
	}
	
	public JndiRegistry getReg() {
		return reg;
	}
}
