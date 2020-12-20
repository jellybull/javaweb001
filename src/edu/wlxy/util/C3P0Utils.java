package edu.wlxy.util;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	public static DataSource ds=null;
	static {
		ComboPooledDataSource cpds=new ComboPooledDataSource("jk5182");
	ds=cpds;
	}
	
	public static DataSource  getDataSource(){
		return ds;
	}
	
}
