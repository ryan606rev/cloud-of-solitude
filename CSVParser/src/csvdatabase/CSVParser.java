package csvdatabase;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 */

/**
 * @author Ryan Bovee\
 * HELLOWORLD HAS NOTHING ON ME
 *
 */



public class CSVParser {
	
	//creates a directory if one does not already exists for the output files
	public static void directory() {
		File dir = new File("C:\\CSVParser"); //directory location
		boolean exists = dir.exists(); //does it exist? true or false
		
		try {
			if (exists == false) { //if there is no directory
				dir.mkdirs(); //make the directory
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		directory(); //make the directory
		
		//use scanner to get input from the user
		//we need to use scanner to get the file name from the user
		//as well as use scanner to read the csv file
		
		Import importedFile = new Import();
		
		System.out.println("Enter the location of your file:");
		
		importedFile.inputFile();
		
		importedFile.getArraySize();
		
		importedFile.createArray();
		
		
		/*
		 * create database object
		 * establish connection to the database or create it
		 * create a table in the database
		 */
		Database sqliteDatabase = new Database();//establish the database object
		
		sqliteDatabase.newDatabase(importedFile.getFileName() + ".db");//call the database, creation if needed
		
		sqliteDatabase.newTable(importedFile.getFileName() + ".db");
		
		//call import to call database insert();
		importedFile.insertIntoDatabase();
		
		/*
		 * tutorial from coderanch.com
		 * logging section
		 */
		Logger log = Logger.getLogger(CSVParser.class.getName());
		FileHandler fh;
		
		try {
			//configure the logger with the handler and formatter
			fh = new FileHandler( "C:\\CSVParser\\" + importedFile.getFileName() + ".log");
			log.addHandler(fh);

			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//messages that will be logged
		log.info("Number of records received: " + importedFile.getTotal());
		log.info("Number of records successful: " + importedFile.getGood());
		log.info("Number of records failed: " + importedFile.getBad());
		
		System.out.println("Your files can be found at: C:\\Program Files\\CSVParser");
		
	}

}
