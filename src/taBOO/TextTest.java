package taBOO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TextTest {

	public static void main(String[] args) {

		try {
			File f = new File("text1.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String s;
			
			while( (s = br.readLine()) != null ) {
				
				System.out.println(s);
			
					
				
			}
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
