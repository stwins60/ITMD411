package lab4_FAGBEMI;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.PreparedStatement;

//import com.mysql.jdbc.Statement;

public class Dao {
	//Declare DB objects
	DbConnect conn = null;
	Statement stmt = null;

	//Constructor
	public Dao() { //create db object instance
		conn = new DbConnect();
	}

	//A createTable method for the database
	public void createTable() {
		try {
			//open a a connection
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");

			//Execute create query
			System.out.println("Creating table in given database...");

			stmt = conn.connect().createStatement();

			String sql = "CREATE TABLE if_fagbemi_tab" + 
					"(pid INTEGER not null AUTO_INCREMENT, " + 
					"id VARCHAR(10), " +
					" income numeric(8,2), " + 
					" pep VARCHAR(4), " + 
					" PRIMARY KEY (pid))";

			stmt.executeUpdate(sql);
			System.out.println("Created table in given database...");
			conn.connect().close(); //close db connection
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
	}
	// INSERT INTO METHOD
	public void insertRecords(BankRecords[] robjs) {
		PreparedStatement preparedStmt = null;// Prepared Statement object 
		ResultSet rs = null;
		try {
			
			 String sql  ="INSERT INTO if_fagbemi_tab(id,income,pep)" + "VALUES (?, ?, ?)";
			 preparedStmt = conn.connect().prepareStatement(sql);
			System.out.println("Inserting records into the table...");
			// Include all object data to the database table using prepared statement
			for (int i = 0; i < robjs.length; ++i) {
				
				//preparedStmt.setInt(1, i);
				preparedStmt.setString(1, robjs[i].getId());			//insert id
				preparedStmt.setDouble(2, robjs[i].getIncome());		//insert income
				preparedStmt.setString(3, robjs[i].getPep());			//insert pep

				preparedStmt.executeUpdate();							//execute prepared statement to insert data

			}
			
		System.out.println("Records inserted !");
		conn.connect().close();
		}catch (SQLException se) 
		{ 
			se.printStackTrace();  
		}
		  finally {
				try {
					
					if(preparedStmt != null) 
					{
						preparedStmt.close();			//close PreparedStatement
						preparedStmt = null;
					}
				if(conn!= null) {
					conn.connect().close(); // close db connection
				}
				} catch (SQLException se) { // Handle errors for JDBC
					se.printStackTrace();
				}
			}
	 }
	public ResultSet retrieveRecords() {
		ResultSet rs = null;

		System.out.println("Connecting to a selected database for record retrievals...");

		try {
			stmt = conn.connect().createStatement();
			System.out.println("Connected to database sucessfully...");
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		String sql = "select id, income, pep from if_fagbemi_tab order by pep desc";
		try {
			rs = stmt.executeQuery(sql);
			System.out.println("Records retrieved from the database...");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		try {
			conn.connect().close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		return rs;
	}
}
