package csvdatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Import {
	
	//scanner for reading the file path
	private Scanner readName;
	//local variable for the name/location of the file
	private String filePath;
	//file name separated from the file path
	private String fileName;
	//initialize the array that we will be working from
	protected String[][] newArray;
	
	
	//variable initialization that used to be within createArray()
	Scanner fromScan = null;
	int rowx = 0;
	int rows = 0;
	int colx = 0;
	int cols = 0;
	String currentLine = "";
	String[] tempArray;
	
	/*
	 * this method does not get the name of the file from the extension
	 * instead this method calls the variable to other classes
	 */
	public String getFileName() {
		return this.fileName;
	}
	
	/*
	 * this method makes our array created from the file accessible
	 * to other classes
	 */
	public String[][] getNewArray() {
		return this.newArray;
	}
	
	public void inputFile() {
		
		//initialize the scanner tool and create received object
		readName = new Scanner(System.in);
		//assign input to input
		filePath = readName.next();
		//call openFile, enter the file name so we can read it
		//openFile(file);
		getArraySize();
		
	}
	
	/*
	 * differs greatly from getFileName
	 * getFileName is for other classes to access the variable
	 * this method is for separating the file name from the path and extension
	 */
	public void extractFileName() {
		
		//TODO: insert the part about getting red of the path
		File name = new File(filePath);
		String fileNamewEx = name.getName(); //getting the name works
		
		fileName = fileNamewEx.replaceFirst("[.][^.]+$","");
		
		//for testing NOTE: THIS IS STILL CALLED FROM createArray()
		System.out.println("fileNamewEx: " + fileNamewEx);
		System.out.println("fileName: " + fileName);
		System.out.println("filePath: " + filePath);
	}
	
	
	public void getArraySize() {
		
		try {
			//setup scanner
			fromScan = new Scanner(new BufferedReader(new FileReader(filePath)));
			
			//loop for while we still have a next row
			while (fromScan.hasNextLine()) {
				
				//read the line form the file
				currentLine = fromScan.nextLine();
				//split data into individual values in row
				tempArray = currentLine.split(",");
				
				rows++;
				cols = tempArray.length;
					
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("Rows: " + rows + " " + "Columns: " + cols);
			
	}
	
	

		
	public void createArray() {
		
		
		newArray = new String[rows][cols];
			
		//moved variable initialization to the top
		
		currentLine = "";
		
		//String fileLocation;
		
		try {
			//setup scanner
			fromScan = new Scanner(new BufferedReader(new FileReader(filePath)));
			
			//loop for while we still have a next row
			while (fromScan.hasNextLine()) {
				
				//read the line form the file
				currentLine = fromScan.nextLine();
				//split data into individual values in row
				tempArray = currentLine.split(",");
				
				//transfer data from tempArray to newArray
				for(int x=0; x < tempArray.length; x++) {
					
					newArray[rowx][x] = tempArray[x];
					
				}
				rowx++; //move to next row
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(Arrays.deepToString(newArray[0]));
		System.out.println(Arrays.deepToString(newArray[1]));
		System.out.println(Arrays.deepToString(newArray[2]));
		System.out.println(Arrays.deepToString(newArray[3]));
		
		
		//testing purposes
		extractFileName();
		
	}

}
