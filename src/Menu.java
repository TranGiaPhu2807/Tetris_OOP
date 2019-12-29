

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Menu extends JFrame {
	
	private JTextField InpuField;
	private JButton button;
	public JLabel score;
	JLabel h_score;
	JLabel scorehp;
	JLabel h_person;
	JLabel scoreh;
	
	
	private Main m;
	private Store s;
	
	
	public Menu() {
		super("MENU");
		m = new Main();
		
		
		//name
		
		JLabel labels = new JLabel();		
		labels.setText("Enter Name :");
		labels.setBounds(40, 10, 100, 20);
	
		//input name
		InpuField = new JTextField(16);
		InpuField.setBounds(150, 10, 200, 20);  
		//start button
		button = new JButton("		START		" );
		button.setBounds(150,30,200,20);
		button.addActionListener(m);
		
		//your score
		JLabel y_score = new JLabel();		
		y_score.setText("Your Score currently :");
		y_score.setBounds(40, 70, 200, 20);
		
		//your score
		String x = Integer.toString(0);
		score = new JLabel(x);
		score.setBounds(220,70,200,20);
		
		
		set_highScore(m.getH_score());
		
		set_highScore_Person(m.getH_name());
		
		
		
		
		add(InpuField);
		add(button);
		add(labels);
		add(y_score);
		add(score);
		
		
		
		//just for beauty
		JLabel label = new JLabel();		
		label.setText("");
		
		add(label);
		
	}
	
	public void set_highScore_Person(String person) {
		
		//highest score person
		h_person = new JLabel();		
		h_person.setText("Highest recorded by :");
		h_person.setBounds(40,100, 200, 20);
		
		//highest score person
		String hsp = person;
		scorehp = new JLabel(hsp);
		scorehp.setBounds(220,100,200,20);
		
		add(h_person);
		add(scorehp);
		
		JLabel label4 = new JLabel();		
		label4.setText("");
		
		add(label4);
				
	}
	
	
	public void set_highScore(int score) {
		//highest score	
		
		h_score = new JLabel();		
		h_score.setText("Highest Score :");
		h_score.setBounds(40, 130, 200, 20);
		
		
		//highest score
		String hs = Integer.toString(score);
		scoreh = new JLabel(hs);
		scoreh.setBounds(220,130,200,20);
		add(h_score);
		add(scoreh);
		
		JLabel label3 = new JLabel();		
		label3.setText("");
		
		add(label3);

	}
	//not only set new row but also check new h score person
	public void setRow(int r) {
		remove(score);
		String x = Integer.toString(r);
		score = new JLabel(x);
		score.setBounds(220,70,200,20);
		add(score);
		JLabel label2 = new JLabel();		
		label2.setText("");
		
		add(label2);
		remove(button);
		remove(h_score);
		remove(scoreh);
		remove(h_person);
		remove(scorehp);
		
		
	}
	
	public String getInText() {
		return InpuField.getText();
	}
	
	
	

}




