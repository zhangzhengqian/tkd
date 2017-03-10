package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMysql {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.driver";
		String url = "jdbc:mysql//127.0.0.1/tkd_oa";
		String user = "root";
		String password = "tkd2016data";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			if(!con.isClosed()){
				System.out.println("connect to dataBase success");
			}
			Statement statement = con.createStatement();
			
			String sql = "select DISTINCT id ,content from oa_user ";
			ResultSet rs = statement.executeQuery(sql);  
			
			//输出id值和content值
			  while(rs.next()) {
			   System.out.println(rs.getString("id") + "\t" + rs.getString("username")+"\t"+rs.getString("password")); 
			   } 
			  
			  rs.close(); 
			  con.close();  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
