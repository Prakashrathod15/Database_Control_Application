package databaseControl.bullTaro.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import databaseControl.bullTaro.info.infoSQL;
import databaseControl.bullTaro.operation.Operation;

public class mainApp {

	public static void main(String[] args) {
		
          infoSQL obj = new infoSQL();
          Operation operation = new Operation();
          Connection con = obj.getConnection();
 
  		Scanner sc = new Scanner(System.in);

  		int index = 0;
  		
  		int stop = 0;
  		
  		while(stop!=10)
  		{
  			System.out.println();
  			System.out.println("1.Show DataBases  ");
  			System.out.println("2.UseDatabase ");
  			System.out.println("3.Show Table Present in Database ");
  			System.out.println("4.Create Table ");
  			System.out.println("5.Insert Data in Table ");
  			System.out.println("6.Delete Table ");
  			System.out.println("7.show table data  ");
  			System.out.println("8.Exit");
  			System.out.println("  Enter choice ");
  			int op = sc.nextInt();
  			
  			switch(op)
  			{
  			
  			 case 1:
  			 {
  				obj.showDatabases(con);
  				 break;
  			 }
  			 case 2:
  			 {
  				 System.out.println(" Enter database Name that you want to Use : ");
  				 String databaseName = sc.next();
  				con =  obj.UseDatabase(databaseName, con);
  				 break;
  			 }
  			 case 3:
  			 {
  				 System.out.println(" Enter database Name that you want to see table  : ");
 				 String databaseName = sc.next();
 				 con =  obj.UseDatabase(databaseName, con);
  				 con = obj.tablePresentInDatabase(databaseName, con);
  				 break;
  			 }
  			 case 4:
  			 {
  				 obj.showDatabases(con);
  				 System.out.println(" In which database you want to create table please select ");
  				 System.out.println(" Enter database Name that you want to see table  : ");
 				 String databaseName = sc.next();
 				 con =  obj.UseDatabase(databaseName, con); 
  				 con = operation.createTable(con);
  				 break;
  			 }
  			 case 5:
  			 {  
  				 obj.showDatabases(con);
 				 System.out.println(" In which database you want to insert data please select ");
 				 System.out.println(" Enter database Name that you want to see table  : ");
				 String databaseName = sc.next();
				 con =  obj.UseDatabase(databaseName, con);
				 con = obj.tablePresentInDatabase(databaseName, con);
				 System.out.println(" Enter table Name : ");
				 String tableName = sc.next();
  				con = operation.insertData(tableName, con);
  				 break;
  			 }
  			 case 6:
  			 {   
  				 System.out.println(" Enter database Name that you want to see table  : ");
				 String databaseName = sc.next();
				 con =  obj.UseDatabase(databaseName, con);
				 con = obj.tablePresentInDatabase(databaseName, con);
				 System.out.println(" Enter table Name that you want to delete : ");
				 String tableName = sc.next();
  				con = operation.deleteTable(tableName, con);
  				 
  				 break;
  			 }
  			 case 7:
  			 {
  				 
  				 System.out.println(" Enter database Name that you want to see table  : ");
				 String databaseName = sc.next();
				 con =  obj.UseDatabase(databaseName, con);
				 con = obj.tablePresentInDatabase(databaseName, con);
				 System.out.println(" Enter table Name that you want to delete : ");
				 String tableName = sc.next();
				 con = operation.showTabledata(tableName,con);
  				 break;
  
  			 }
  			 case 8:
  			 {
  				 obj.closeConnection(con);
  				 stop = 10;
 				 break;
  			 }
  			
  			 
  			}
  		}
  		
  		obj.closeConnection(con);
  		
  		
  		
  		
  		
  		
  		
  		
          
          
	}

}
 