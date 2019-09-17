/***
 * Jianqing Gao
 ***/
package hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Find {
	public static void search(String name, String searchVal){
		File file = new File("new/"+name+".txt");
		String string = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try{
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			String line = null;
			//Read every line in txt file and convert it into a string
			while((line = br.readLine())!=null){
				string = string+line;
			}
			
			String newData = string.replace("}{", "}-{");
			String[] list = newData.split("-");
			for(String all: list){
				if(all.contains(searchVal)){
					System.out.println(all);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(isr!=null){
				try{
					isr.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try{
					fis.close();
				}catch(IOException e){
					e.printStackTrace();
				}
		}
	}
	}
}
