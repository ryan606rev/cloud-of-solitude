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
		
		String url = "jdbc:sqlite:C:/sqlite/db/" + databaseName;
		
		try (Connection connect = DriverManager.getConnection(url)) {
			
			if (connect != null) {
				
				DatabaseMetaData meta = connect.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
				
			}
			
		} catch(SQLException e) {
			
			System.out.println(e.getMessage());
			
		}
		
	}
	
	
	public void newTable() {
		
		Import importData = new Import();
		
		String url = "jdbc:sqlite:C://sqlite/db/" + importData.getFileName() + ".db";
		
		String sql = "CREATE TABLE IF NOT EXISTS clientData (\n"
				+ "		A text NOT NULL,\n"
				+ "		B text NOT NULL,\n"
				+ "		C text NOT NULL PRIMARY KEY,\n"
				+ "		D text NOT NULL,\n"
				+ "		E text NOT NULL,\n"
				+ "		F text NOT NULL,\n"
				+ "		G text NOT NULL,\n"
				+ "		H text NOT NULL,\n"
				+ "		I text NOT NULL,\n"
				+ "		J text NOT NULL,\n"
				+ ");";
		
		try (Connection con = DriverManager.getConnection(url);
				Statement stmt = con.createStatement()) {
			
			//create a new table
			stmt.execute(sql);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	private Connection connect() {
		
		Import importData = new Import();
		
		String url = "jdbc:sqlite:C://sqlite/db/" + importData.getFileName() + ".db";
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
		
	}
	
	//insertion method for the database, each field is a parameter
	public void insert(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j)
	{
		
		String sql = "INSERT INTO clientData(a,b,c,d,e,f,g,h,i,j) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		try (Connection conn = this.connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, a);
			pstmt.setString(1, b);
			pstmt.setString(1, c);
			pstmt.setString(1, d);
			pstmt.setString(1, e);
			pstmt.setString(1, f);
			pstmt.setString(1, g);
			pstmt.setString(1, h);
			pstmt.setString(1, i);
			pstmt.setString(1, j);
			
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
	}
}
