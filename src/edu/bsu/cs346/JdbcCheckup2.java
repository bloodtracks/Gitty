package edu.bsu.cs346;

//You need to import the java.sql package to use JDBC
import java.sql.*;

//We import java.io to be able to read from the command line
import java.io.*;

//import oracle.jdbc.OracleDriver;

class JdbcCheckup2
{
public static void main (String args [])
    throws SQLException, IOException
{
 // Load the Oracle JDBC driver
 DriverManager.registerDriver(new oracle.jdbc.OracleDriver());


 String serverName = "csor6.dhcp.bsu.edu";
 String portNumber = "1521";
 String sid = "or6db";
 String url ="jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;

 Connection conn =
 	      DriverManager.getConnection (url,
 	           "bmdavis3", "3441");



 System.out.println ("Oracle or6db is connected.");

 // Create a statement
 Statement stmt = conn.createStatement ();

 // Do the SQL
 ResultSet rset = stmt.executeQuery ("select sysdate from dual");

 System.out.println("System time is: ");
 while (rset.next ())
   System.out.println (rset.getString (1));

 System.out.println ("Your JDBC installation is correct.");

 // close the resultSet
 rset.close();

 // Close the statement
 stmt.close();

 // Close the connection
 conn.close();
}

}




