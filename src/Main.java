
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter; 
import java.awt.Canvas;
import java.awt.Color;
import java.awt.color.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;






public class Main extends Canvas implements ActionListener {
	
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	static Menu m = new Menu();
    public static void main(String[] args) {
   
    	
     	m.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
     	m.setSize(400,400);
     	m.setVisible(true);

    }
    
    
    @Override
	public void actionPerformed(ActionEvent event) {
    		
    		
	    	name = m.getInText();
	        System.out.print(name);

			Tetris t = new Tetris();
			m.setVisible(false);
        
        
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

   