

import java.awt.FlowLayout;  
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;




public class Menu extends JFrame {
	
	private JTextField InpuField;
	private JButton button;
	
	private Main m;
	
	public Menu() {
		super("MENU");
		setLayout(new FlowLayout());
		button = new JButton("		START		");
		add(button);
		m = new Main();
		button.addActionListener(m);
		
		InpuField = new JTextField(16);
		add(InpuField);
		
		
	
		
	}
	
	public String getInText() {
		return InpuField.getText();
	}
	
	
	

}




