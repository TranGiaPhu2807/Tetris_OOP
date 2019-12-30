
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;






public class Main implements ActionListener {
	
	
	private Tetris t;
	private static String h_name;
	private static int h_score;
	private Clip clip;
	static String getH_name() {
		return h_name;
	}
	public static void setH_name(String h_name) {
		Main.h_name = h_name;
	}
	public static int getH_score() {
		return h_score;
	}
	public static void setH_score(int h_score) {
		Main.h_score = h_score;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	static Menu m;
	
    public static void main(String[] args) {
    	
    	
    	try {
			Store s = new Store();
			h_name=s.getHname();
			h_score = s.getHscore();
			System.out.print(h_score);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    	m = new Menu();
     	m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     	m.setSize(400,450);
     	m.setVisible(true);
     	
    }

    public void write_h_name(String newName) throws IOException {
		
		String fileContent = newName;
		 
		BufferedWriter writer = new BufferedWriter(new FileWriter("source/high_score_person.txt"));
		writer.write(fileContent);
		writer.close();
		
		
	}
	public void write_h_score(int S_score) throws IOException {
		String fileContent2 = Integer.toString(S_score);
		 
		BufferedWriter writer2 = new BufferedWriter(new FileWriter("source/high_score.txt"));
		writer2.write(fileContent2);
		writer2.close();
			
	}
	
	public void musicx() {
		AudioInputStream music;
        try {
			music = AudioSystem.getAudioInputStream(new File("source/2.wav"));
			clip = AudioSystem.getClip();
			clip.open(music);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	

    
    
    @Override
	public void actionPerformed(ActionEvent event) {
    		
	    	name = m.getInText();
			t = new Tetris();
			m.setVisible(false);
			
			
        
	        t.setSize(t.getWidth(), t.getHeight());
	        t.setVisible(true);
	        
	       
	        t.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        musicx();
	        
	        
	        WindowListener exitListener = new WindowAdapter() {
	            @SuppressWarnings("deprecation")
				@Override
	            public void windowClosing(WindowEvent e) {
	                t.pauseGame();
	                t.setVisible(false);
	                clip.stop();
	                
	                if (t.rowsCleared>h_score) {
	                	try {
	                		m.remove();
							write_h_name(name);
							write_h_score(t.rowsCleared);
							m.set_highScore(t.rowsCleared);
							m.set_highScore_Person(name);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
		            }
	                m.setRow(t.rowsCleared);
 	                m.setVisible(true);
	            }
	        };
	        t.addWindowListener(exitListener);	
	        
	       
	}
}



   