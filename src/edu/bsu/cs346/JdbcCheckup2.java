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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;

class JdbcCheckup2 {

	public static String output = "select * from department";

	public static String[] foodItemsColumnNames = { "Food Name", "Price",
			"Expiration Date", "Item Number", "Food Type" };
	
	public static String[] departmentColumnNames = { "Phone Number",  "Department Name", "Store Name" };
	static String[][] dataRows;
	
	

	public static void main(String args[]) throws SQLException, IOException {
		//
		JdbcCheckup2 connectSql = new JdbcCheckup2();

		// Whole Frame
		JFrame gui = new JFrame();
		gui.setSize(300, 300);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLayout(new FlowLayout());
		gui.setTitle("Grocery Store Database");
		gui.setVisible(true);

		// Drop Down Menu
		JComboBox<String> dropDown = new JComboBox<String>();
		dropDown.addItem("Food Items");
		dropDown.addItem("Food Catagories");
		dropDown.addItem("Departments");
		dropDown.addItem("Store Information");
		gui.add(dropDown);
		gui.pack();

		// Select Button
		JButton selectButton = new JButton();
		selectButton.setText("Select!");
		selectButton.setLayout(new FlowLayout());
		gui.add(selectButton);
		gui.pack();
		

		
		ActionListener clicked = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String menuSelected = dropDown.getSelectedItem().toString();
				if (menuSelected == "Food Items") {

					output = "select * from food_item";
					dataRows = new String[21][];
					try {

						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (menuSelected == "Departments") {

					output = "select * from department";
					dataRows = new String[6][];
					try {

						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				}
				 JTable table = new JTable(dataRows, departmentColumnNames);
				 gui.add(table);
				 gui.pack();
			}

		};

		selectButton.addActionListener(clicked);

	}

	// bmdavis3
	// 3441
	public void getSqlOutput(String output) throws SQLException {

		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		ConnectToDatabase connect = new ConnectToDatabase();
		Connection conn = DriverManager.getConnection(connect.getUrl(),
				"bmdavis3", "3441");
		System.out.println("Oracle or6db is connected.");

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(output);
		ResultSetMetaData rsmd = rset.getMetaData();

		int i = 0;

		while (rset.next()) {

			if (rsmd.getColumnCount() == 5) {
				dataRows[i] = new String[] { rset.getString(1),
						rset.getString(2), rset.getString(3),
						rset.getString(4), rset.getString(5) };
				
			}


			if (rsmd.getColumnCount() == 3) {
				System.out.println(rset.getString(1));
				
				dataRows[i] = new String[] { rset.getString(1),
						rset.getString(2), rset.getString(3)};
				
			}
			i++;
		}

		// close the resultSet
		rset.close();

		// Close the statement
		stmt.close();

		// Close the connection
		conn.close();

	}

}
