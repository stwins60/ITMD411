package lab3_FAGBEMI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 
 */

/**
 * @author idris Fagbemi
 * Purpose: A program that read, process and print bank data from Bank of IIT file.
 * Date: 9/23/19
 *
 */

// A class method BankRecords that extend the Client abstract class
public class BankRecords extends Client {
	//DECLARING VARIABLES
	private String id; //String
	private int age; //Numeric
	private String sex; //FEMALE, MALE
	protected String region; //INNER_CITY, RURAL, SUBURBAN
	private double income; //Numeric
	private String married; //YES, NO
	private int children; //0, 1, 2, 3
	private String car; //YES, NO
	private String save_act; // YES, NO
	private String current_act; // YES, NO
	private String mortgage; // YES, NO
	private String pep; // YES, NO
	
	
	//Declaring the Variables
	static BankRecords robjs[];
	//static BankRecords robjs[] = new BankRecords[600];
	static ArrayList<List<String>> array = new ArrayList<>();
	
	//A public method that takes in the bank details
	/*
	 * public BankRecords() { id = "None"; age = 22; sex = "FEMALE"; region =
	 * "INNER_CITY"; income = 5000.00; married = "NO"; children = 2; car = "YES";
	 * save_act = "YES"; current_act = "YES"; mortgage = "NO"; pep = "NO"; }
	 */
	
	//A void method that reads the file (bank-Detail.csv)
	@Override
	public void readData() {
		// TODO Auto-generated method stub
		//System.out.println("Reading Data _ _ _");
		String line;
		
		//an error method to catch any error if the file is missing
		try{
			// A file reader, that reads the file from source
			//File file = new File(getClass().getResource("bank-Detail.csv").toURI());
			File bankDetail = new File("src/bank-Detail.csv"); 
			FileReader myfile = new FileReader(bankDetail);
			BufferedReader BR = new BufferedReader(myfile);
			
			while((line = BR.readLine()) != null) {
				array.add(Arrays.asList(line.split(",")));
			}
			
		}
		catch(IOException er) {
			er.printStackTrace();

			System.out.println("Error, File is Missing!!!!");
		}
		
		
		processData(); //call function to process the data
		
	}
	
	// a void method to process data
	@Override
	public void processData() {
		
		// a throw exception error object to check for errors within the data
		try {
		
		//System.out.println("Processing Data _ _ _");
		// creating an index for array while checking through the arraylist
		
		robjs = new BankRecords[array.size()];
		
		int FileIndex = 0;
		
		//creating a for each to go through the arraylist of values and 
	    for (List<String> rowData: array) {
		      //initialize array of objects
		    	robjs[FileIndex] = new BankRecords();
		    	
		    	//call setters below and populate them, item by item
		    	robjs[FileIndex].setId(rowData.get(0)); //get 1st column
		    	robjs[FileIndex].setAge(Integer.parseInt(rowData.get(1))); 
		    	robjs[FileIndex].setSex(rowData.get(2));//get 2nd column
		    	robjs[FileIndex].setRegion(rowData.get(3)); //get 3rd column
		    	robjs[FileIndex].setIncome(Double.parseDouble(rowData.get(4))); //get 4th column
		    	robjs[FileIndex].setMarried(rowData.get(5)); //get 5th column
		    	robjs[FileIndex].setChildren(Integer.parseInt(rowData.get(6))); //get 6th column
		    	robjs[FileIndex].setCar(rowData.get(7)); //get 7th column
		    	robjs[FileIndex].setSave_act(rowData.get(8)); //get 8th column
		    	robjs[FileIndex].setCurrent_act(rowData.get(9)); //get 9th column
		    	robjs[FileIndex].setMortgage(rowData.get(10)); //get 10th column
		    	robjs[FileIndex].setPep(rowData.get(11)); //get 11th column
		    	
		    	FileIndex++;
	    }
	    //printData(); //call function to print the data held in memory
		}
		catch(java.lang.NumberFormatException er2){
			er2.printStackTrace();
		}

	}
	
	// A void method to print the data
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		//System.out.println("Printing Data _ _ _");
		System.out.println("\n\t\t\t\t\t\tBank Details");
		
		// a print line to print the data headers
		System.out.println("\nID \tAge \tSex \tRegion \t\tIncome \tMarried? \tChildren? \tCar? \tSaving Account? \tCurrent Account? \tMortgage? \tPep?");
		
		int FileIndex = 0;
		
		for(FileIndex = 0; FileIndex < array.size(); FileIndex++) {
			System.out.print(robjs[FileIndex].getId() + "\t");
			System.out.print(robjs[FileIndex].getAge() + "\t");
			System.out.print(robjs[FileIndex].getSex() + "\t");
			System.out.print(robjs[FileIndex].getRegion() + "\t");
			
			if(robjs[FileIndex].getRegion().equals("RURAL") || robjs[FileIndex].getRegion().equals("TOWN")) {
				System.out.print("\t");
			}
			System.out.print(robjs[FileIndex].getIncome() + "\t");
			System.out.print(robjs[FileIndex].getMarried() + "\t\t");
			System.out.print(robjs[FileIndex].getChildren() + "\t\t");
			System.out.print(robjs[FileIndex].getCar() + "\t");
			System.out.print(robjs[FileIndex].getSave_act() + "\t\t\t");
			System.out.print(robjs[FileIndex].getCurrent_act() + "\t\t\t");
			System.out.print(robjs[FileIndex].getMortgage() + "\t\t");
			System.out.print(robjs[FileIndex].getPep() + "\t\t\n");
			
			FileIndex ++;
		}
	}
	
	// CREATING INSTANCES OF ALL DECLARED VARIBLES
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getSave_act() {
		return save_act;
	}
	public void setSave_act(String save_act) {
		this.save_act = save_act;
	}
	public String getCurrent_act() {
		return current_act;
	}
	public void setCurrent_act(String current_act) {
		this.current_act = current_act;
	}
	public String getMortgage() {
		return mortgage;
	}
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}
	public String getPep() {
		return pep;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}

	

}
