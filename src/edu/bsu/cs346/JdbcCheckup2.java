package edu.bsu.cs346;

//You need to import the java.sql package to use JDBC
import java.sql.*;
import java.util.ArrayList;
//We import java.io to be able to read from the command line
import java.io.*;




import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

 System.out.println ("Your JDBC installation is correct.");
 

 
 
 ResultSet rset = stmt.executeQuery("SELECT * from FOOD_ITEM order by Price");

  ArrayList<String> names = new ArrayList<String>();
  ArrayList<String> prices = new ArrayList<String>();
 while(rset.next()){
	String name = rset.getString(1);
	String price = rset.getString(2);
	 names.add(name);
	 prices.add(price);
	 System.out.println (rset.getString(1) + " Price: " + rset.getString(2));
 }

 
 JFrame frame = new JFrame("Test Frame"); 
 
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.pack();
 frame.setSize(200, 200);
 frame.setVisible(true);
	 
 JPanel panel = new JPanel();
 panel.add(new JLabel("Hello"));
 frame.getContentPane().add(panel);
 
 for(int i = 0; i< names.size(); i++){
	 panel.add(new JLabel(names.get(i)) );
 }
 
 // close the resultSet
 rset.close();

 // Close the statement
 stmt.close();

 // Close the connection
 conn.close();
}
  



}




