package edu.bsu.cs346;

//You need to import the java.sql package to use JDBC
//We import java.io to be able to read from the command line
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class JdbcCheckup2 implements ActionListener {
	public static void main(String args[]) throws SQLException, IOException  {
		//Connects to the database
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		ConnectToDatabase connect = new ConnectToDatabase();
		Connection conn = DriverManager.getConnection(connect.getUrl(), "bmdavis3", "3441");
		System.out.println("Oracle or6db is connected.");

		
		//Create a statement and get data from SQL
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt
				.executeQuery("SELECT * from FOOD_ITEM order by Price");

		
		//creates a window with food_Name data
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> prices = new ArrayList<String>();
		while (rset.next()) {
			String name = rset.getString(1);
			String price = rset.getString(2);
			names.add(name);
			prices.add(price);
			System.out.println(rset.getString(1) + " Price: "
					+ rset.getString(2));
		}

		String[] stringNames = {"FOOD_ITEM", "DEPARTMENT", "CATAGORY","AILSE","STORE"};
	
		
		
		JFrame frame = new JFrame("Food Names With Prices");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(200, 200);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);

		for (int i = 0; i < names.size(); i++) {
			panel.add(new JLabel(names.get(i)));
			panel.add(new JLabel(prices.get(i)));
		}

		// close the resultSet
		rset.close();

		// Close the statement
		stmt.close();

		// Close the connection
		conn.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String stringName = (String)cb.getSelectedItem();
        
		
	}

}
