

import java.awt.FlowLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.Scanner;



public class Menu extends JFrame {
	
	private JTextField InpuField;
	public static int close = 0;
	private JButton button;
	private JLabel items;
	public boolean start = false;
	
	public Menu() {
		super("MENU");
		setLayout(new FlowLayout());
		button = new JButton("		START		");
		add(button);
		Main h = new Main();
		button.addActionListener(h);
		
		InpuField = new JTextField(16);
		add(InpuField);
		
		InpuField.addActionListener(h);
	
		
	}
	
	public String getInText() {
		return InpuField.getText();
	}
	
	

}




