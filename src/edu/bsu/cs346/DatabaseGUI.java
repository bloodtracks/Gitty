package edu.bsu.cs346;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.demo.jvmti.hprof.Tracker;

public class DatabaseGUI extends JPanel {
	private static final long serialVersionUID = 5038884369787660797L;
	public JTextField weightInputField;
	public JTextField outputField;
	public JTextField exerciseInputField;
	public JCheckBox waterBottleBox1;
	public JCheckBox waterBottleBox2;
	public JCheckBox waterBottleBox3;
	public JCheckBox waterBottleBox4;
	public JCheckBox glassOfWaterBox1;
	public JCheckBox glassOfWaterBox2;
	public JCheckBox glassOfWaterBox3;
	public JCheckBox glassOfWaterBox4;
	public JCheckBox beerWineShotBox1;
	public JCheckBox beerWineShotBox2;
	public JCheckBox beerWineShotBox3;
	public JCheckBox beerWineShotBox4;
	public JCheckBox exceededCaffieneAmountBox;

	Tracker tracker = new Tracker();
	private double waterIntake;
	private double caffieneIntake;
	private double alcoholIntake;
	private double waterNeeded;

	public static void main(String[] args) {

		JFrame frame = new JFrame("Hydration Tracker");
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.getContentPane().add(new DatabaseGUI());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public DatabaseGUI() {
		weightInputField = new JTextField(85);
		exerciseInputField = new JTextField(85);
		outputField = new JTextField(85);

		waterBottleBox1 = new JCheckBox("Water Bottle");
		waterBottleBox2 = new JCheckBox("Water Bottle");
		waterBottleBox3 = new JCheckBox("Water Bottle");
		waterBottleBox4 = new JCheckBox("Water Bottle");
		glassOfWaterBox1 = new JCheckBox("Glass of Water");
		glassOfWaterBox2 = new JCheckBox("Glass of Water");
		glassOfWaterBox3 = new JCheckBox("Glass of Water");
		glassOfWaterBox4 = new JCheckBox("Glass of Water");
		beerWineShotBox1 = new JCheckBox(
				"Bottle of Beer, Glass of Wine, or a Shot of Alcohol");
		beerWineShotBox2 = new JCheckBox(
				"Bottle of Beer, Glass of Wine, or a Shot of Alcohol");
		beerWineShotBox3 = new JCheckBox(
				"Bottle of Beer, Glass of Wine, or a Shot of Alcohol");
		beerWineShotBox4 = new JCheckBox(
				"Bottle of Beer, Glass of Wine, or a Shot of Alcohol");

		exceededCaffieneAmountBox = new JCheckBox(
				"Check if you have had over 5 cups of coffee or 2 energy drinks.");

		JButton updateButton = new JButton("Find total amuont of water needed");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
				} catch (NumberFormatException nfe) {
					outputField
							.setText("Please enter a valid number for all fields");
				}
			}
		});
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.cyan);

		panel.add(new JLabel(" "));
		panel.add(new JLabel("Please enter your weight (lbs)"));
		panel.add(weightInputField);
		panel.add(new JLabel(" "));
		panel.add(new JLabel("Water Consumed (Check all that apply)"));
		panel.add(waterBottleBox1);
		panel.add(new JLabel(" "));
		panel.add(waterBottleBox2);
		panel.add(new JLabel(" "));
		panel.add(waterBottleBox3);
		panel.add(new JLabel(" "));
		panel.add(waterBottleBox4);
		panel.add(new JLabel(" "));

		panel.add(glassOfWaterBox1);
		panel.add(new JLabel(" "));
		panel.add(glassOfWaterBox2);
		panel.add(new JLabel(" "));
		panel.add(glassOfWaterBox3);
		panel.add(new JLabel(" "));
		panel.add(glassOfWaterBox4);
		panel.add(new JLabel(" "));

		panel.add(new JLabel("Alchohol consumed (Check all that apply)"));
		panel.add(beerWineShotBox1);
		panel.add(new JLabel(" "));
		panel.add(beerWineShotBox2);
		panel.add(new JLabel(" "));
		panel.add(beerWineShotBox3);
		panel.add(new JLabel(" "));
		panel.add(beerWineShotBox4);
		panel.add(new JLabel(" "));

		panel.add(new JLabel("Caffiene Consumed"));
		panel.add(exceededCaffieneAmountBox);
		panel.add(new JLabel(" "));
		panel.add(new JLabel(
				"Please enter the time of intense exercise, such as running or heavy lifting, in minutes"));
		panel.add(exerciseInputField);
		panel.add(new JLabel(" "));

		panel.add(new JLabel(" "));
		panel.add(updateButton);
		panel.add(new JLabel(" "));
		panel.add(outputField);
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));

		add(panel);
}
}