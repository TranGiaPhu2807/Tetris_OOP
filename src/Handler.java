import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class Handler implements ActionListener {
	



	@Override
	public void actionPerformed(ActionEvent event) {
	
			
			
			
			Tetris t = new Tetris();
        
        
	        t.setSize(t.getWidth(), t.getHeight());
	        t.setVisible(true);
	        
	       
	        t.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        
	        WindowListener exitListener = new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                t.pauseGame();
	                t.setVisible(false);
	                System.exit(1);
	                
	              
	            }
	        };
	        t.addWindowListener(exitListener);	
	        
	       
	}
	

}

