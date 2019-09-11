package csvdatabase;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 */

/**
 * @author Ryan Bovee
 *
 */



public class CSVParser {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//use scanner to get input from the user
		//we need to use scanner to get the file name from the user
		//as well as use scanner to read the csv file
		
		Import importedFile = new Import();
		
		System.out.println("Enter the location of your file:");
		
		importedFile.inputFile();
		
		importedFile.getArraySize();
		
		importedFile.createArray();
		
		Database sqliteDatabase = new Database();
		
		sqliteDatabase.newDatabase(importedFile.getFileName());
		
		importedFile.insertIntoDatabase();
		
		//tutorial from coderanch.com
		Logger log = Logger.getLogger("DataEntryLog");
		FileHandler fh;
		
		try {
			//configure the logger with the handler and formatter
			fh = new FileHandler("C:temp/test/" + importedFile.getFileName() + ".log");
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
		
	}

}
