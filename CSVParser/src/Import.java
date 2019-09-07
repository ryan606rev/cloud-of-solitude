import java.io.*;
import java.util.*;

public class Import {
	
	//variable for storing the inputed file name
	private Scanner readName;
	//variable for storing the contents of the file
	private Scanner readFile;
	//local variable for the name/location of the file
	private String file;
	
	public void inputFile() {
		
		//initialize the scanner tool and create received object
		readName = new Scanner(System.in);
		//assign input to input
		file = readName.next();
		//call openFile, enter the file name so we can read it
		openFile(file);
		
	}
	
	public void openFile(String fileName) {
		
		try {
			
			readFile = new Scanner(new File(fileName));
			
		}
		catch(Exception e) {
			
			System.out.println("Error: File Not Found.");
			
		}
		
	}
	
	public void readFile() {
		
		while(readFile.hasNext()) {
			
			String a = readFile.next();
			String b = readFile.next();
			String c = readFile.next();
			String d = readFile.next();
			String e = readFile.next();
			String f = readFile.next();
			String g = readFile.next();
			String h = readFile.next();
			String i = readFile.next();
			String j = readFile.next();
			
			//print for purposes of testing
			System.out.printf("%s %s %s %s %s %s %s %s %s %s %s %s %s\n", a, b, c, d, e, f, g, h, i, j);
			
			//need to write to an array instead of just printing
			
		}
		
	}
	
	public void closeFile() {
		
		readFile.close();
		
	}

}
