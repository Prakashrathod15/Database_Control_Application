package databaseControl.bullTaro.info;

import java.security.DigestException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class infoSQL {

	public Connection getConnection() {
		
			Connection con = null;
			try {
			// 1. Load driver 
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			// 2. Established Connection
	          String url = "jdbc:mysql://localhost:3306";
	          String userName = "root";
	          String passWord =  "Vinayaksir@2022";
			 con = DriverManager.getConnection(url,userName,passWord);
			 System.out.println(" Connection established successfully ! ");
			} catch(ClassNotFoundException e)
			{
				System.out.println(" Class not found Exception encounterd ");
			} catch(SQLException ex)
			{
				System.out.println(" SQL Exception encounterd ");
			}
				
			return con;
		}
	
	public void closeConnection(Connection con)
	{
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(" Exception occured ");
		}
		
		 System.out.println(" Connection closed Successfully ! ");
	}
	
	public void showDatabases(Connection con)
	{
		try {
			// 3 create statement 
			
			String query = "Show databases";
			
			Statement stmt = con.createStatement();
			
			// 4 fire query 

			ResultSet res  = stmt.executeQuery(query);
			
			// 5 show data 
			System.out.println();
			System.out.println(" Databases are given below : ");
			System.out.println();
			while(res.next())
			{
				System.out.println(" DataBase Name : "+res.getString(1));
			}
			System.out.println();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection UseDatabase(String databaseName, Connection con)
	{
		Connection newCon = null;
		try {
			con.close();
	
			// we Established new connection with database 
			String url = "jdbc:mysql://localhost:3306/";
			String userName = "root";
			String passWord = "Vinayaksir@2022";
			
			newCon = DriverManager.getConnection(url+databaseName,userName,passWord);
			
			System.out.println(" Connection established with "+databaseName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newCon;
	}
	
	public Connection tablePresentInDatabase(String databaseName, Connection con)
	{
		Connection newCon = null;
		try {
			System.out.println();
			newCon  = UseDatabase(databaseName,con);
			System.out.println(" Connection Switched ");
			Statement stmt = newCon.createStatement();
			
			String query = "Show tables";
			
			ResultSet res = stmt.executeQuery(query);
			System.out.println();
			System.out.println(" Tables in database "+databaseName);
			while(res.next())
			{
				System.out.println(" Table Name : "+res.getString(1));
			}
			System.out.println();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newCon;
	}

	
	
}
