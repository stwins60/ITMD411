package lab4_FAGBEMI;

import java.util.Comparator;

public class ChildComparator implements Comparator<BankRecords> {
	
	@Override
	public int compare(BankRecords o1, BankRecords o2) {
		
		//use compareTo to compare strings
		int result = o1.getChildren() - o2.getChildren();
		return result;
	}
}
