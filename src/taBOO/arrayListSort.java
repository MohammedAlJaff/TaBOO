package taBOO;

import java.util.*;


public class arrayListSort {

	public static void main(String[] args) {
		
		ArrayList<String> s = new ArrayList<String>(5);
		s.add("ATT");
		s.add("ACT");
		s.add("HRT");
		s.add("TTT");
		s.add("HTT");
		
		String[] q = s.toArray(new String[s.size()]);
		
		for(String x: q) {
			System.out.println(x);
		}
		
		

	}

}
