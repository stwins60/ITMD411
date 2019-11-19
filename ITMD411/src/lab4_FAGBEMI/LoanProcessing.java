package lab4_FAGBEMI;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanProcessing extends BankRecords {
	public static void main(String[] args) {
		
		BankRecords br = new BankRecords();
		br.readData();
		Dao dao = new Dao();
		//dao.createTable();
		dao.insertRecords(robjs); // perform inserts
		ResultSet rs = dao.retrieveRecords(); // fill result set object
			
		// Create heading for display
		System.out.println("\nLOAN ANALYSIS REPORT:\n");
		//print out all the records from the recordset to the console in a nice column format 
		//included with heading names for id, income and pep
		System.out.printf("%7s %12s %7s\n", "ID", "INCOME", "PEP");
		
		// Extract data from result set
		try {
			while (rs.next()) {
				// Retrieve data by column name (i.e., for id,income,pep)
				// Display values for id,income,pep
				
		        String id = rs.getString("id").toUpperCase();
		        Double income = rs.getDouble("income");
		        String pep = rs.getString("pep");
		        
		        // print the results
		        System.out.printf("%7s %10.2f %7s\n", id, income, pep);
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
		try {
			rs.close(); // closes result set object
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		} 
	}

}
