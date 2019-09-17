/***
 * Jianqing Gao
 ***/
package hw2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		 File zipFile = new File("SampleUserSmartwatch");
		 Unzip.inDirectory(zipFile);
		 File newFiles= new File("new");
		 MergeFiles.createDir(newFiles);
		 Scanner scan = new Scanner(System.in);
		 System.out.println("Select your sensor");
		 System.out.println("# Activity " + "# LightSensor "+"# BatterySensor "+"# Bluetooth"+ "# ScreenUsage"+"# HeartRate ");
		 while(scan.hasNext()){
			 String s = scan.nextLine();
			 if(s.contains("stop")) break;
			 System.out.println("Input scripts");
			 String y = scan.nextLine();
			 System.out.println("Plz be patience");
			 Find.search(s,y);
			 
	 }
		 //Find.search("LightSensor", "Mon Mar 6 02:19:06 EST 2017");
	}
	
}
