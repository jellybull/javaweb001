package edu.wlxy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



/*
 * 提供2个方法：创建并返回数据库连接；关闭数据库对象释放资源
 * 
 */
public class JDBCUtils {
    
	public static DataSource ds=null;
	static{
		BasicDataSource bds=new BasicDataSource();
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://127.0.0.1:3306/dbbook");
		bds.setUsername("root");
		bds.setPassword("123456");
		bds.setInitialSize(10);
		bds.setMaxActive(15);
		ds=bds;
		
	}
	
	
	static {
		ComboPooledDataSource cpds=new ComboPooledDataSource("jk5182");
	ds=cpds;
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
//		Class.forName("com.mysql.jdbc.Driver");
//		String dburl="jdbc:mysql://127.0.0.1:3306/dbbook";
//		String dbusername="root";
//		String dbpwd="root";
//		Connection conn=DriverManager.getConnection(dburl, dbusername, dbpwd);
		Connection conn=ds.getConnection();
		return  conn;
	}
	
	public static void release(Connection conn,PreparedStatement pst){
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void release(Connection conn,PreparedStatement pst,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		release(conn,pst);
		
		
		
	}
}
