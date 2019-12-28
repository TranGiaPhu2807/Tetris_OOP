import javax.swing.JFrame;
import javax.swing.JLabel;

public class Close extends JFrame{
	Main m;
	Tetris t; 
	public Close() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
      	this.setSize(400,400);
      	this.setVisible(true);
         
		JFrame window = new JFrame();
		String name = m.getName();
		JLabel label = new JLabel(name, JLabel.CENTER);
		
	}
	

}
