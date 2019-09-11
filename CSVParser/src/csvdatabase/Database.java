package csvdatabase;

//Sourced from sqlitetutorial.net

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	
	/**
	 * 
	 * connect to existing or create a new database
	 * 
	 * the parameter is the name of the database being connected to
	 * 
	 * @param databaseName
	 */
	public void newDatabase(String databaseName) {
		
		String url = "jdbc:sqlite:C:\\CSVParser\\" + databaseName; //file path for database
		
		try (Connection connect = DriverManager.getConnection(url)) {  //gets connection to the database
			
			if (connect != null) { // if there is no connection
				
				DatabaseMetaData meta = connect.getMetaData(); // initialize meta with meta data
				System.out.println("The driver name is " + meta.getDriverName()); //print out the meta data
				System.out.println(databaseName + " is ready for use."); //print message to confirm the database has been created
				
			}
			
		} catch(SQLException e) { //throws exception if needed
			
			System.out.println(e.getMessage());
			
		}
		
	}
	
	/*
	 * for creating a new table within the database
	 */
	public void newTable(String databaseName) {
		
		String url = "jdbc:sqlite:C:\\CSVParser\\" + databaseName; //the url for the database into the variable
		
		String sql = "CREATE TABLE IF NOT EXISTS client_data (\n" //the table parameters
				+ "		A text NOT NULL,\n"
				+ "		B text NOT NULL,\n"
				+ "		C text NOT NULL PRIMARY KEY,\n"
				+ "		D text NOT NULL,\n"
				+ "		E text NOT NULL,\n"
				+ "		F text NOT NULL,\n"
				+ "		G text NOT NULL,\n"
				+ "		H text NOT NULL,\n"
				+ "		I text NOT NULL,\n"
				+ "		J text NOT NULL\n"
				+ ");";
		
		try (Connection con = DriverManager.getConnection(url); //tries to get connection
				Statement stmt = con.createStatement()) {
			
			//create a new table
			stmt.execute(sql);
			System.out.println("The table client_data in "+ databaseName +" is ready for use.");
			
		} catch (SQLException e) {  //exception if it doesn't work
			System.out.println(e.getMessage());
		}
		
	}
	
	
	private Connection connect() {
		
		Import importData = new Import();
		
		String url = "jdbc:sqlite:C:\\CSVParser\\" + importData.getFileName() + ".db";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
		
	}
	
	//insertion method for the database, each field is a parameter
	public void insert(String databaseName, String A, String B, String C, String D, String E, String F, String G, String H, String I, String J)
	{
		
		String url = "jdbc:sqlite:C:\\CSVParser\\" + databaseName; //file path for database
		
		//String sql = "INSERT INTO client_data(a,b,c,d,e,f,g,h,i,j) VALUES(?,?,?,?,?,?,?,?,?,?)";
		//String values = "VALUES(" + a + "," + b + "," + c + "," + d + "," + e + "," + f + "," + g + "," + h + "," + i + "," + j + ")";
		String sql = "INSERT INTO client_data\n"
				+ "(A,B,C,D,E,F,G,H,I,J)\n"
				+ "VALUES\n"
				+ "('" + A + "', '" + B + "', '" + C + "', '" + D + "', '" + E + "', '" + F + "', '" + G + "', '" + H + "', '" + I + "', '" + J + "')";
		
		try (Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			//pstmt.setString(1,A);
			//pstmt.setString(2,B);
			//pstmt.setString(3,C);
			//pstmt.setString(4,D);
			//pstmt.setString(5,E);
			//pstmt.setString(6,F);
			//pstmt.setString(7,G);
			//pstmt.setString(8,H);
			//pstmt.setString(9,I);
			//pstmt.setString(10,J);
			pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
}
