package csvdatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import com.opencsv.CSVReader;

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
		//getArraySize();
		
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
		
		System.out.println("Full Array Size:");
		System.out.println("Rows: " + rows + " " + "Columns: " + cols);
			
	}
	
	

	//credit mkyong.com for the tutorial	
	public void createArray() {
		
		
		
			
		CSVReader reader = null;
		
		try {
			
			reader = new CSVReader(new FileReader(filePath));
			String[] line;
			int numOfRows = 0; // keep a count of the number of rows for the newArray
			
			//this replaces the method for finding the number of rows we will need in our array
			while ((line = reader.readNext()) != null) {
				numOfRows++;
			}
			
			System.out.println("We have found " + numOfRows + " of data.");
			
			//changing our array variable from null to the correct number of rows and columns
			newArray = new String[numOfRows][10];
			
			int currentRow = 0;
			
			// this is for writing each line of the .csv file to our array
			while ((line = reader.readNext()) != null) {
				newArray[currentRow][0] = line[0];
				newArray[currentRow][1] = line[1];
				newArray[currentRow][2] = line[2];
				newArray[currentRow][3] = line[3];
				newArray[currentRow][4] = line[4];
				newArray[currentRow][5] = line[5];
				newArray[currentRow][6] = line[6];
				newArray[currentRow][7] = line[7];
				newArray[currentRow][8] = line[8];
				newArray[currentRow][9] = line[9];
				currentRow++;
			}
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		//for testing
		System.out.println(Arrays.deepToString(newArray[0]));
		System.out.println(Arrays.deepToString(newArray[1]));
		System.out.println(Arrays.deepToString(newArray[2]));
		System.out.println(Arrays.deepToString(newArray[3]));
		
		
		//testing purposes
		extractFileName();
		
	}
	
	
	//this takes the data form our array and puts it into the database
	public void insertIntoDatabase() {
		
		//create a new object for the Database class
		Database database = new Database();
		
		//a loop that will put the rows of the array into the database one at a time
		for (int k = 0; k < rows; k++) {
			//the command that inserts one row of data
			database.insert(newArray[k][0], newArray[k][2], newArray[k][3], newArray[k][4], newArray[k][5], 
					newArray[k][6], newArray[k][7], newArray[k][8], newArray[k][9], newArray[k][10]);
			//outputs confirmation that command was sent to input the data
			System.out.println("Successfuly created entry for USER: " + newArray[k][2]);
		}
		
	}
	
	public void verifyData() {
		
	}

}
