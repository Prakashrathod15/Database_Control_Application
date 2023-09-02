package databaseControl.bullTaro.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Operation {
	
	public Connection createDatabase(Connection con)
	{
		try {
			String query = "create database ";
			// It is DDL operation it mean it is database defination language command
			// DDL - Create, Alter, Drop, Truncate
			
			System.out.println(" Enter new database name : ");
			Scanner sc = new Scanner(System.in);
			String db = sc.next();
			Statement stmt = con.createStatement();
			stmt.execute(query+db);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public Connection deleteDatabase(Connection con)
	{
		
		 try {
			String query = "Drop database ";
			 System.out.println(" Enter database name : ");
			 Scanner sc = new Scanner(System.in);
			 String db = sc.next();
			 Statement stmt = con.createStatement();
			 stmt.execute(query+db);
			 stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return con;
	}
	
	public Connection createTable(Connection con)
	{
		System.out.println(" Enter new table name : ");
		Scanner sc = new Scanner(System.in);
		String table = sc.next();
		String[] columnName = new String[10];
		String[] datatype = new String[10];
		boolean reapet = true;
		int count = 1;
		int i = 0;
		while(reapet && count <= 10)
		{
			System.out.println(" Enter "+count+" Column Name as  ");
			columnName[i] = sc.next();
			System.out.println(" Enter "+count+" data type as '  ");
			datatype[i] = sc.next();
			System.out.println(" Want to add more column : yes , no ");
		    String f = sc.next();
			if(f.equals("no"))
			{
				reapet = false;
			}
			i++;
			count++;
		}
		
		String query = "create table "+table + " (";
		
	    for(i = 0; columnName[i]!=null; i++)
	    {
	    	if(columnName[i+1]==null)
	    	{
	    		query = query+" "+columnName[i]+" "+datatype[i];
	    	} else {
	    	query = query+" "+columnName[i]+" "+datatype[i]+", ";
	    	}
	    }
		query = query+" );";
	    System.out.println(" Final query is : "+query);
	    
	    Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try {
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return con;
	}
	
	public Connection insertData(String tableName, Connection con)
	{
		     
		  try {
			  
			  Statement stmt = con.createStatement();
			  
			  String query = "desc "+tableName;
			  
			  
			  ResultSet res = stmt.executeQuery(query);
			  
			  System.out.println();
			  
			  String[] data = new String[10];
			  String[] datatype = new String[10];
			  
			  Scanner sc = new Scanner(System.in);
			  
			  int i = 0;
			  int count = 0;
			  while(res.next())
			  {
				  System.out.println(" Enter "+res.getString(2)+" type value for "+res.getString(1));
				  datatype[i] = res.getString(2);
				  data[i] = sc.next();
				  i++;
				  count++;
				  System.out.println();
			  }
			  
			  System.out.println();
			  
			  stmt.close();
			  
			  String question = " ? ,";
				
				
				 query = "insert into "+tableName+" values ( ";
				
				while(count!=0)
				{
					if(count-1==0)
					{
						query = query+" ? ) ";
					}else
					{
						query = query+question;
					}
					count--;
				}
				
				System.out.println(" query is : "+query);
				
				PreparedStatement pstmt = con.prepareStatement(query);
				
				 i = 0;
				 count  = 1;
				while(datatype[i]!=null)
				{
					if(datatype[i].equals("Int"))
					{
						pstmt.setInt(1,Integer.parseInt(data[i]) );
					} else 
					{
						pstmt.setString(count, data[i]);
					}
					count++;
					i++;
				}
				
				pstmt.executeUpdate();
				
				pstmt.close();
				
				System.out.println(" Inserted SuccessFulled ");
				
				
			
			  
			  return con;
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  return con;
		  
	}
	
	public Connection deleteTable(String tableName, Connection con)
	{
		try {
			String query = " drop table "+tableName;
			
			Statement stmt = con.createStatement();
			
			stmt.execute(query);
			
			System.out.println(tableName+" deleted successFully from database ");
			
			return con;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return con;
	}
	
	public Connection showTabledata(String tableName, Connection con)
	{
		
		
		try {
			String query = " desc "+tableName;
			
			Statement stmt = con.createStatement();
						
			ResultSet res = stmt.executeQuery(query);
			
			String[] datatype = new String[10];
			String[] data = new String[10];
			
			int i = 0;
			while(res.next())
			{
				datatype[i] = res.getString(2);
				data[i] = res.getString(1);
				i++;
			}
			
			System.out.println(datatype[0]+" "+datatype[1]);
			
		    int count = 0;
			while(data[count]!=null)
			{
				count++;
			}
			
			
			
			query = "select * from "+tableName;
			
			res = stmt.executeQuery(query);
			
			
			i = 0;
			
			
	
			while(res.next())
			{
     			if(count==2 && datatype[0].equals("int") && datatype[1].equals("varchar(50)"))
				{
					System.out.println(data[0]+" : "+res.getInt(1)+" "+data[1]+" : "+res.getString(2));
    			} else if(count==3 && datatype[0].equals("int") && datatype[1].equals("varchar(50)") && datatype[2].equals("varchar(50)"))
    			{
    				System.out.println(data[0]+" : "+res.getInt(1)+" "+data[1]+" :  "+res.getString(2)+" "+data[2]+" : "+res.getString(2));
    			}
				
			}
			
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return con;
	}
	
  

}
