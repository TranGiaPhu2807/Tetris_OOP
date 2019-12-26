
import java.awt.event.WindowAdapter; 
import java.awt.Color;
import java.awt.color.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        Tetris t = new Tetris();
        
        t.setSize(t.getWidth(), t.getHeight());
        t.setVisible(true);
        
        t.playMusic("bensound-summer.pm3",1);
        t.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                t.pauseGame();
                System.exit(1);
              
            }
        };
        t.addWindowListener(exitListener);
        
      
    }
}