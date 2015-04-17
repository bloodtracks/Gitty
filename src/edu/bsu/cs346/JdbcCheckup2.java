package edu.bsu.cs346;

//You need to import the java.sql package to use JDBC
//We import java.io to be able to read from the command line
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class JdbcCheckup2{
	
	 public static String output = "select * from department";
	 public static ArrayList<String> outputData;
	
	public static void main(String args[]) throws SQLException, IOException  {
		//
		JdbcCheckup2 connectSql = new JdbcCheckup2();
		
		// Whole Frame
		JFrame gui = new JFrame();
		gui.setSize(300, 300);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLayout(new FlowLayout());
		gui.setTitle("Grocery Store Database");
		gui.setVisible(true);
		
		// Select Button
		JButton selectButton = new JButton();
		selectButton.setText("Select!");
		selectButton.setLayout(new FlowLayout());
		gui.add(selectButton);
		
	
		
		//Drop Down Menu
		JComboBox<String> dropDown = new JComboBox<String>();
		dropDown.addItem("Food Items");
		dropDown.addItem("Food Catagories");
		dropDown.addItem("Departments");
		dropDown.addItem("Store Information");
		gui.add(dropDown);
		gui.pack();
		

		
		ActionListener clicked = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			 String menuSelected =	 dropDown.getSelectedItem().toString();
			 	if(menuSelected == "Food Items"){
			 		
			 		output = "select * from food_item";
			 		try {
						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 		
			 		for(int i= 0; i< outputData.size(); i++){
			 			JLabel data = new JLabel();
			 			data.setText(outputData.get(i));
			 			gui.add(data);
			 		}
			 		
			 	}
			 	
			 	 if(menuSelected == "Departments"){
			 		
			 		output = "select * from department";
			 		try {
						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			 		
			 		for(int i= 0; i< outputData.size(); i++){
			 			JLabel data = new JLabel();
			 			data.setText(outputData.get(i));
			 			gui.add(data);
			 		}
			 	}
			 	
			}
			
		};

		selectButton.addActionListener(clicked);
		
	}

	public void getSqlOutput(String output) throws SQLException{
		
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		ConnectToDatabase connect = new ConnectToDatabase();
		Connection conn = DriverManager.getConnection(connect.getUrl(), "bmdavis3", "3441");
		System.out.println("Oracle or6db is connected.");
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(output);
		outputData = new ArrayList<String>();
		while (rset.next()) {
			outputData.add(rset.getString(1));
			System.out.println(rset.getString(1) + " Price: "
					+ rset.getString(2));
		}
		
		
		
	
					// close the resultSet
					rset.close();

					// Close the statement
					stmt.close();

					// Close the connection
					conn.close();
		
		
			
			

		
	}
	

}
