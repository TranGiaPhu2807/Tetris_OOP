

import java.awt.FlowLayout;  
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class Menu extends JFrame {
	
	private JTextField InpuField;
	private JButton button;
	public JLabel score;
	
	private Main m;
	private Tetris t;
	private int scorex=0; 
	
	public Menu() {
		super("MENU");
		setLayout(new FlowLayout());
		button = new JButton("		START		");
		add(button);
		m = new Main();
		button.addActionListener(m);
		
		InpuField = new JTextField(16);
		add(InpuField);
		
		String x = Integer.toString(0);
		score = new JLabel(x);
		add(score);
		
		
	}
	
	public void setRow(int r) {
		remove(score);
		String x = Integer.toString(r);
		score = new JLabel(x);
		add(score);
	}
	
	public String getInText() {
		return InpuField.getText();
	}
	
	
	

}




