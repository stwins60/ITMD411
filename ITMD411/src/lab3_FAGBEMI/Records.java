package lab3_FAGBEMI;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Records extends BankRecords {

	//create formatted object to write output directly to console & file
	static FileWriter fw = null;

	public  Records() {
		try {
			fw = new FileWriter("bankrecords.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		//calling the instance of records object in the main method
		Records br = new Records();
		br.readData();

		// call functions to perform analytics
		AvgComp(); // analyze average income per loc
		femsComp(); // female count w. mort/savings accounts
		malesComp(); // male counts per loc. w. car & 1 child

		// *** close out file object ***//

		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void AvgComp() {
		Arrays.sort(robjs, new SexComparator());
		DecimalFormat df = new DecimalFormat("0.00");

		// set up needed variables to gather counts & income by sex
		// to determine average income by sex

		int maleCt =0, femCt = 0;
		double maleInc = 0, femInc = 0;
		
		//a coditional statement that get the result of the female
		//income
		for (int i = 0; i < robjs.length; i++) 
			//System.out.println(i);
			if (robjs[i].getSex().equals("FEMALE")) {
				
				femInc += robjs[i].getIncome();
				++femCt;
				//System.out.println("Female = " + femInc + " " + femCt);
			}
			else {
				//(robjs[i].getSex().equals("MALE")) {
					
					maleInc += robjs[i].getIncome();
					++maleCt;
					//System.out.println("Male = " + maleInc + " " + maleCt);
				//}
			}
		
		// display resulting averages to console and to file

		System.out.println("Data Analytics Result: ");
		System.out.printf("Avg inc. for Females: $" + df.format(femInc/femCt));
		System.out.printf("\nAvg inc. for Males: $" + df.format(maleInc/maleCt));

		try {
			fw.write("Data Analytics Result: ");
			fw.write("\n");
			fw.write("Avg inc. for Females: $" + df.format(femInc/femCt));
			fw.write("\n");
			fw.write("Avg inc. for Males: $" + df.format(maleInc/maleCt));
			fw.write("...");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void femsComp() {
		Arrays.parallelSort(robjs, new SexComparator());
		//storing female comparator count
		int femsCt = 0;
		//a coditional statement that get the result of the female
		//with savings and mortgage account
		
		for (int i = 0; i < robjs.length; i++) {
			if(robjs[i].getSex().equals("FEMALE") && robjs[i].getSave_act().equals("YES") && robjs[i].getMortgage().equals("YES")) {
				femsCt += 1;
			}
		}
		
		// displaying result to console and to file
		System.out.printf("\nFemale count w. mort/savings accounts: " + femsCt);
		
		try {
			fw.write("\nFemale count w. mort/savings accounts: " + femsCt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void malesComp() {
		Arrays.parallelSort(robjs, new SexComparator());

		//storing male  comparator count
		int maleInc_Suburban = 0, maleInc_Inner = 0, maleInc_Rural = 0, maleInc_Town = 0;
		
		//a coditional statement that get the result of the male in regions
		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("SUBURBAN") && robjs[i].getChildren() == 1) {
				maleInc_Suburban += 1;
			}
			else if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("INNER") && robjs[i].getChildren() == 1) {
				maleInc_Inner += 1;
			}
			else if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("RURAL") && robjs[i].getChildren() == 1) {
				maleInc_Rural += 1;
			}
			else if (robjs[i].getSex().equals("MALE") && robjs[i].getRegion().equals("TOWN") && robjs[i].getChildren() == 1) {
				maleInc_Town += 1;
			}
			
			
		}
		
		// display resulting averages to console and to file
		
		System.out.printf("\nSuburban male counts per loc. w. car & 1 child: " + maleInc_Suburban);
		System.out.printf("\nInner-City male counts per loc. w. car & 1 child: " + maleInc_Inner);
		System.out.printf("\nRural male counts per loc. w. car & 1 child: " + maleInc_Rural);
		System.out.printf("\nTown male counts per loc. w. car & 1 child: " + maleInc_Town);
		
		String TimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("\nProgrammed by Idris Fagbemi " + " " + TimeStamp);
		try {
			fw.write("\nSuburban male counts per loc. w. car & 1 child: " + maleInc_Suburban);
			fw.write("\nInner-City male counts per loc. w. car & 1 child: " + maleInc_Inner);
			fw.write("\nRural male counts per loc. w. car & 1 child: " + maleInc_Rural);
			fw.write("\nTown male counts per loc. w. car & 1 child: " + maleInc_Town);
			fw.write("\nProgrammed by Idris Fagbemi " + " " + TimeStamp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
