
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






public class Main extends Canvas {
	


    public static void main(String[] args) {
   
    	Menu m = new Menu();
     	m.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
     	m.setSize(400,400);
     	m.setVisible(true);
     	
     	Handler h = new Handler();
     	
     	
    }
 





}

   