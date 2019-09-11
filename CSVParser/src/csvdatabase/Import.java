package csvdatabase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Import {
	
	//important scanner and strings that are called later 
	private Scanner readName; //scanner for reading the file path
	private String filePath; //local variable for the name/location of the file
	private String fileName; //file name separated from the file path
	
	//arrays
	//initialize the array that we will be working from
	protected String[][] masterArray;
	private String[][] goodArray;
	
	
	//important integer counts that are called by multiple methods
	private int numOfRows = 0; // keep a count of the number of rows for the masterArray
	private int numOfCols = 0; //count of columns
	private int good = 0; //number of good complete entries, for use later when a table of the correct size is made
	private int bad = 0;
	
	
	/*
	 * this method does not get the name of the file form the extension
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
		return this.masterArray;
	}
	
	//return number of good entries
	public int getGood() {
		return this.good;
	}
	
	//return number of bad entries
	public int getBad() {
		return this.bad;
	}
	
	//return total number of entries from original .csv file
	public int getTotal() {
		return this.numOfRows;
	}
	
	
	
	public void inputFile() {
		
		//initialize the scanner tool and create received object
		readName = new Scanner(System.in);
		//assign input to input
		filePath = readName.next();
		extractFileName();//get the file name so it can be used for naming later
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

	//credit mkyong.com for the tutorial	
	public void getArraySize() {
			
		CSVReader reader = null;
		
		try {
			
			reader = new CSVReader(new FileReader(filePath));
			String[] line;
			
			//this replaces the method for finding the number of rows we will need in our array
			while ((line = reader.readNext()) != null) {
				numOfRows++;
			}
			CSVReader reader2 = new CSVReader(new FileReader(filePath));
			String[] line2 = reader2.readNext();
			numOfCols = line2.length;
			reader2.close();
			System.out.println("We have found " + numOfRows + " rows of " + numOfCols + " fields of data.");
		
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void createArray() {
	
		//changing our array variable from null to the correct number of rows and columns
		masterArray = new String[numOfRows][numOfCols];
		
		//testing a fell solution for masterArray
		//for (String[] row: masterArray) {
		//	Arrays.fill(row, "null");
		//}
		//System.out.println(Arrays.deepToString(masterArray[12]));
		
		//trying this to see if it will work
		//for (String[] row : )
		
		CSVReader reader = null;
		
		int rowPointer = 0;
		
		try {
			
			reader = new CSVReader(new FileReader(filePath));
			
			String[] currentLine;
			
			// this is for writing each line of the .csv file to our array
			while ((currentLine = reader.readNext()) != null) {
				
				//here is the money maker
				int max = currentLine.length;
				if(max > 10) {
					max = 10;
				}
				
				for (int k = 0; k < max; k++) {
					masterArray[rowPointer][k] = currentLine[k];
				}	
				rowPointer++;
			}
			
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		//for testing, array preview
		//System.out.println(Arrays.deepToString(masterArray[0]));
		//System.out.println(Arrays.deepToString(masterArray[11]));
		//System.out.println(Arrays.deepToString(masterArray[12]));
		//System.out.println(Arrays.deepToString(masterArray[13]));
		//System.out.println(Arrays.deepToString(masterArray));
		
		
		//sort into proper arrays
		qualityControl(masterArray);
		
	}
	
	
	/*
	 * This is my method for sorting our the entries that cannot go into the database.
	 * unsorted is the local name what what is the masterArray
	 * the masterArray will be split into the tempGood and tempBad
	 * tempGood and tempBad will have the same parameters as the masterArray
	 * Then, the contents of the temp arrays will be put into the appropriately sized goodArray and badArray
	 */
	public void qualityControl(String[][] unsorted) {
		
		String[][] tempBad = new String[numOfRows][numOfCols]; //temp for holding the entries with null values
		goodArray = new String[numOfRows][numOfCols]; //the array of values that can be passed on for insertion into the database
		boolean theRowIsAcceptable = true; //whether or not the row is worthy of insertion
		//String itsnull = "TRUE";
		
		//this is where we move through the array searching full null values for the discard pile
		for (int x = 0; x < numOfRows; x++) { //until the count is equal to the number of rows in the unsorted array
			
			//steps through each row, x is the current row
			for (int y = 0; y < numOfCols; y++) { //until the count is equal to the number of columns
				
				//sets through each column for the row, y is the current column
				if (unsorted[x][y].length() == 0) { //if the value is empty
					System.arraycopy(unsorted[x], 0, tempBad[bad], 0, numOfCols);//copy the whole row to bad
					bad++; //increase count for bad entries by 1
					theRowIsAcceptable = false; //shows that the row is not acceptable so that it will not be added to the good array
					y = numOfCols; //end the if y loop, so that it we move on to the next row
				}
				
			}
			if (theRowIsAcceptable == true) { // if the row is good
				System.arraycopy(unsorted[x], 0, goodArray[good], 0, numOfCols);// copy the row to the good array
				good++;//add one to the good count
			}	
			theRowIsAcceptable = true; // resets the value for the next cycle
		}
		//System.out.println(Arrays.deepToString(goodArray));//testing
		printCSV(bad, tempBad); //calls print CSV so that we get a list of the bad entries that need to be fixed
	}
	
	
	/*
	 * prints the required outputs csv file, bad entries
	 * int incomplete: number of rows that need to be coppied
	 * String[][] rejected, the temporary array that holds the bad entries
	 * help from tutorials from callicoder.com
	 */
	public void printCSV(int incomplete, String[][] rejected) {
		
		// first task is transferring the data to a better fitting array
		String[][] badArray = new String[incomplete][numOfCols];
		
		for (int k = 0; k < incomplete; k++) {
			System.arraycopy(rejected[k], 0, badArray[k], 0, numOfCols);
		}
		
		//part 2 printing out the entries with null fields to a .csv file
		String badLocation = "C:\\CSVParser\\" + fileName + "-bad.csv";
		
		try {
			Writer writer = Files.newBufferedWriter(Paths.get(badLocation));
			
			CSVWriter csvWriter = new CSVWriter(writer,
					CSVWriter.DEFAULT_SEPARATOR,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER,
					CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_LINE_END);
				
			String[] headerRecord = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
			csvWriter.writeNext(headerRecord);
				
			for (int l = 0; l < incomplete; l++) {
				csvWriter.writeNext(new String[] {Arrays.deepToString(badArray[l])});
			}
			csvWriter.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	//this takes the data form our array and puts it into the database
	public void insertIntoDatabase() {
		
		//create a new object for the Database class
		Database database = new Database();
		
		//a loop that will put the rows of the array into the database one at a time
		for (int k = 0; k < good; k++) {
			//the command that inserts one row of data
			database.insert(fileName + ".db", goodArray[k][0], goodArray[k][1], goodArray[k][2], goodArray[k][3], goodArray[k][4], 
					goodArray[k][5], goodArray[k][6], goodArray[k][7], goodArray[k][8], goodArray[k][9]);
			//outputs confirmation that command was sent to input the data
			System.out.println(Arrays.deepToString(masterArray[k]));
			//System.out.println("Successfuly created entry for USER: " + goodArray[k][2]);
		}
		
	}

}
