package edu.bsu.cs346;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class JdbcCheckup2 {

	public static String output;

	public static String[] dataColumns = { "Nothing yet" };

	static String[][] dataRows = { { "Empty" }, { "Empty" } };

	static int rowCount = 0;

	JTable table = new JTable(dataRows, dataColumns);

	public static void main(String args[]) throws SQLException, IOException {
		//
		JdbcCheckup2 connectSql = new JdbcCheckup2();

		// Whole Frame
		JFrame gui = new JFrame();
		gui.setSize(300, 200);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLayout(new FlowLayout());
		gui.setTitle("Grocery Store Database");
		gui.setVisible(true);

		// Drop Down Menu
		JComboBox<String> dropDown = new JComboBox<String>();
		dropDown.addItem("Food Items");
		dropDown.addItem("Food Categories");
		dropDown.addItem("Departments");
		dropDown.addItem("Store Information");
		gui.add(dropDown);

		// Select Button
		JButton selectButton = new JButton();
		selectButton.setText("Select a Table!");
		selectButton.setLayout(new FlowLayout());
		gui.add(selectButton);

		JComboBox<String> querydropDown = new JComboBox<String>();
		querydropDown.addItem("Items Between $.90 and $2.00");
		querydropDown.addItem("Find Pork Bacon");
		querydropDown.addItem("Find all Bacon");
		querydropDown.addItem("Find all Items Under a Dollar");
		querydropDown.addItem("Items That Expire after June 30");
		querydropDown.addItem("Find Which Department occupies which Aisle");
		querydropDown.addItem("Every Item that has its Category Name in its Name");
		querydropDown.addItem("Items that will expire within a week");
		querydropDown.addItem("Average Price of Items in Each Category");
		querydropDown.addItem("Net Worth of Current Invetory");
		gui.add(querydropDown);
		
		

		// Query Drop Down
		JButton queryButton = new JButton();
		queryButton.setText("Select a Query!");
		queryButton.setLayout(new FlowLayout());
		gui.add(queryButton);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setSize(300, 300);
		panel.setName("Panel");
		panel.setVisible(true);
		gui.add(panel);
		gui.pack();

		JTable table = new JTable(dataRows, dataColumns);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		panel.add(new JScrollPane(table));
		gui.pack();
		
		
		JButton refreshButton = new JButton();
		refreshButton.setText("Refesh!");
		refreshButton.setLayout(new FlowLayout());
		gui.add(refreshButton);

		ActionListener queryClicked = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String menuSelected = querydropDown.getSelectedItem()
						.toString();

				if (menuSelected == "Items Between $.90 and $2.00") {
					output = "select price, f_name, item_number from food_item where price between .9 and 1.9";
					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dataRows = new String[rowCount][];
					try {
						connectSql.getSqlOutput(output);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (menuSelected == "Find Pork Bacon") {
					output = "select food_item.* from food_item where f_name = 'Bacon' and f_type ='Pork'";
					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dataRows = new String[rowCount][];
					try {
						connectSql.getSqlOutput(output);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (menuSelected == "Find all Bacon") {
					output = "select food_item.* from food_item where f_name = 'Bacon'";
					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dataRows = new String[rowCount][];
					try {
						connectSql.getSqlOutput(output);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (menuSelected == "Find all Items Under a Dollar") {
					output = "select food_item.f_name as f_name1, food_item.price from food_item where food_item.price < 1.00";
					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dataRows = new String[rowCount][];
					try {
						connectSql.getSqlOutput(output);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

					if (menuSelected == "Items That Expire after June 30") {
						output = "select food_item.* from food_item where food_item.expiration_date > '30-JUN-15'";
						try {
							connectSql.getSqlRowCount(output);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						dataRows = new String[rowCount][];
						try {
							connectSql.getSqlOutput(output);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if (menuSelected == "Find Which Department occupies which Aisle") {
						output = "select aisle.aisle_number, department.D_name from aisle join Department on aisle.d_location = department.d_name";
						try {
							connectSql.getSqlRowCount(output);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						dataRows = new String[rowCount][];
						try {
							connectSql.getSqlOutput(output);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if (menuSelected == "Every Item that has its Category Name in its Name") {
						output = "select f.f_name, f.f_type from food_item f where f.f_type in (select c_name from categories c where c.c_name = f.f_type)";
						try {
							connectSql.getSqlRowCount(output);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						dataRows = new String[rowCount][];
						try {
							connectSql.getSqlOutput(output);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if (menuSelected == "Items that will expire within a week") {
						output = "(select f_name from food_item) minus (select f_name from food_item where expiration_date > '26-APR-15')";
						try {
							connectSql.getSqlRowCount(output);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						dataRows = new String[rowCount][];
						try {
							connectSql.getSqlOutput(output);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if (menuSelected == "Average Price of Items in Each Category") {
						output = "select f_type, count(*), avg(price) from food_item group by f_type";
						try {
							connectSql.getSqlRowCount(output);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						dataRows = new String[rowCount][];
						try {
							connectSql.getSqlOutput(output);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					if (menuSelected == "Net Worth of Current Invetory") {
						output = "select SUM(price) from food_item";
						try {
							connectSql.getSqlRowCount(output);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						dataRows = new String[rowCount][];
						try {
							connectSql.getSqlOutput(output);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				

				panel.remove(table);
				JTable table = new JTable(dataRows, dataColumns);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				panel.add(new JScrollPane(table));
				gui.pack();

			}

		};

		queryButton.addActionListener(queryClicked);

		ActionListener clicked = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String menuSelected = dropDown.getSelectedItem().toString();
				if (menuSelected == "Food Items") {

					output = "select * from food_item";

					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dataRows = new String[rowCount][];
					try {

						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (menuSelected == "Departments") {

					output = "select * from department";
					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					dataRows = new String[rowCount][];
					try {

						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (menuSelected == "Food Categories") {

					output = "select * from categories";

					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dataRows = new String[rowCount][];
					try {

						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				if (menuSelected == "Store Information") {

					output = "select * from stores";

					try {
						connectSql.getSqlRowCount(output);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dataRows = new String[rowCount][];
					try {

						connectSql.getSqlOutput(output);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				panel.remove(table);
				JTable table = new JTable(dataRows, dataColumns);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				panel.add(new JScrollPane(table));
				gui.pack();
			}
		};

		selectButton.addActionListener(clicked);
		
		ActionListener refreshClicked = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.remove(table);
				JTable table = new JTable(dataRows, dataColumns);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				panel.add(new JScrollPane(table));
				gui.pack();
			}
			
		};
		
		refreshButton.addActionListener(refreshClicked);

	}

	// bmdavis3
	// 3441
	public void getSqlRowCount(String output) throws SQLException {
		rowCount = 0;
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		ConnectToDatabase connect = new ConnectToDatabase();
		Connection conn = DriverManager.getConnection(connect.getUrl(),
				"bmdavis3", "3441");
		System.out.println("Oracle or6db is connected.");

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(output);

		while (rset.next()) {
			rowCount++;
		}

	}

	public void getSqlOutput(String output) throws SQLException {

		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		ConnectToDatabase connect = new ConnectToDatabase();
		Connection conn = DriverManager.getConnection(connect.getUrl(),
				"bmdavis3", "3441");
		System.out.println("Oracle or6db is connected.");

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(output);
		ResultSetMetaData rsmd = rset.getMetaData();

		int columnCount = rsmd.getColumnCount();
		ArrayList<String> columns = new ArrayList<String>();
		for (int i = 1; i < columnCount + 1; i++) {
			String columnName = rsmd.getColumnName(i);
			columns.add(columnName);
		}
		dataColumns = new String[columns.size()];
		columns.toArray(dataColumns);

		int i = 0;

		while (rset.next()) {
			if (rsmd.getColumnCount() == 5) {
				dataRows[i] = new String[] { rset.getString(1),
						rset.getString(2), rset.getString(3),
						rset.getString(4), rset.getString(5) };
			}

			if (rsmd.getColumnCount() == 3) {
				dataRows[i] = new String[] { rset.getString(1),
						rset.getString(2), rset.getString(3) };
			}

			if (rsmd.getColumnCount() == 2) {
				dataRows[i] = new String[] { rset.getString(1),
						rset.getString(2) };
			}
			
			if (rsmd.getColumnCount() == 1) {
				dataRows[i] = new String[] { rset.getString(1)};
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
