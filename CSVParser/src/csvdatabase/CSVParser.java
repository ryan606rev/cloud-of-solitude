package csvdatabase;
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
		
		importedFile.createArray();
		
		//Database sqliteDatabase = new Database();
		
		//sqliteDatabase.newDatabase(importedFile.getFileName());
		
		importedFile.insertIntoDatabase();

	}

}
