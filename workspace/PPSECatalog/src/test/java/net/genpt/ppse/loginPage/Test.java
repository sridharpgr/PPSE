package net.genpt.ppse.loginPage;

import java.util.ArrayList;

import com.google.common.collect.Ordering;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("923");
		list.add("456");
		list.add("789");
		boolean isSorted = Ordering.natural().isOrdered(list);
		System.out.println(isSorted);
		
		

	}

}
