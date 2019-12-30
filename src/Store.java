import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Store {
	
	
	private int hscore;
	private String hname;
	
	public int getHscore() {
		return hscore;
	}
	
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	
	
	public Store() throws IOException {

		  read_h_name();
		  read_h_score();
		
	}
	
	public void read_h_name() throws IOException {
		File file= new File("source/high_score_person.txt"); 		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 	  
		String st1;
		  while (( st1 = br.readLine()) != null) {
			  setHname(st1);
		    }
	}
	
	public void read_h_score() throws NumberFormatException, IOException {
		File file2 = new File("source/high_score.txt"); 		  
		BufferedReader br2 = new BufferedReader(new FileReader(file2)); 	  
		String st2; 	
		  while ((st2 = br2.readLine()) != null) {
			  hscore = Integer.parseInt(st2);
		    }
	}
	
//	public void write_h_name(String newName) throws IOException {
//		
//		String fileContent = newName;
//		 
//		BufferedWriter writer = new BufferedWriter(new FileWriter("source/high_score_person.txt"));
//		writer.write("asdf");
//		writer.close();
//		
//		
//	}
	
//	public void write_h_score(int S_score) throws IOException {
//		String fileContent2 = Integer.toString(S_score);
//		 
//		BufferedWriter writer2 = new BufferedWriter(new FileWriter("source/high_score.txt"));
//		writer2.write(fileContent2);
//		writer2.close();
//			
//	}
//	

}
