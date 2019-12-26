

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



public class Menu extends JFrame {
	
	 
	
	
	private JButton button;
	private JLabel items;
	public boolean start = false;
	public Menu() {
		super("MENU");
		setLayout(new FlowLayout());
		
		
		button = new JButton("		START		");
		add(button);
		Handler h = new Handler();
		button.addActionListener(h);
		
		
	}
	
	

}

