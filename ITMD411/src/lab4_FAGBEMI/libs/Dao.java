package lab4_FAGBEMI.libs;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

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
	//INSERT INTO METHOD
	public void insertRecords(BankRecords[] robjs) {
		try {
			//Execute a query
			System.out.println("Inserting records into the table..");
			stmt = conn.connect().createStatement();
			String sql = null;

			//include all object data to the database table
			for(int i = 0; i < robjs.length; i++) {

				//finish string assignment to insert all object data
				//(id,income,pep) into the your database table

				sql = "INSERT INTO if_fagbemi_tab(pid,income,pep)" + 
						/*"VALUES(' "+value1+"', '"+value2+"', '"+value n+"')";*/
						"VALUES(' "+robjs[i].getId()+"', '"+robjs[i].getIncome()+"', "
								+ "'"+robjs[i].getPep()+"')";
				stmt.executeUpdate(sql);
			}
			System.out.println("Records inserted into the table...");
			conn.connect().close();
		}
		catch(SQLException se){
			se.printStackTrace();
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
