package hw3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
	public static void main(String[] args){
		Map<String, Integer> map = new HashMap<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Give the sensor name");
		String s = scan.next();
		while(true){
			int n = SearchTime.searchtimes(s, map);
			System.out.println(s+" has been count for"+n+" times ");
			s = scan.next();
			if(s.equals("stop")) break;
		}
		System.out.println("It has been stopped");
	}	
}
