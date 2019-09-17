/***
 * Jianqing Gao
 ***/
package hw2;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

  
//Set up a directory , and unzip all the files under the same folder
public class Unzip {  
	//Create new files for diferrent sensors under setup directory
    //Check directory
	public static void inDirectory(File zipFile) {
		String aimpath ="new";
		if(zipFile.isDirectory()) {
			 File[] listFiles = zipFile.listFiles();
			 for (int i = 0; i < listFiles.length; i++) {
				 //traverse all files in the folder
				 if(listFiles[i].isDirectory()) {
					 inDirectory(listFiles[i]);
				 }
				 //if listFiles is a file，also end with .zip，then unzip it 
				 if(listFiles[i].toString().endsWith(".zip")) {
					 try {
						unZipFiles(listFiles[i], aimpath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}    //listFiles[i]：the zip file   aimpath：path that the file unzipped to   
				 }
			}
		 }
	}
	
	 //Unzip to the directory that has already been set up
	 private static void unZipFiles(File zipFile,String ditrect)throws IOException{
		 
		 //build directory
		 File pathFile = new File(ditrect);
		 if(!pathFile.exists()){
			 pathFile.mkdirs();
		 }
		 
		 @SuppressWarnings("resource")
		ZipFile zip = new ZipFile(zipFile);
		 
		 for(Enumeration entries = zip.entries();entries.hasMoreElements();){
			  ZipEntry entry = (ZipEntry)entries.nextElement();
			  String zipEntryName = entry.getName();
			  InputStream in = zip.getInputStream(entry);
			  String outPath = (ditrect+"/"+zipEntryName);
			  //See if the path exist, if not then create new
			  File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			  if(!file.exists()){
				  file.mkdirs();
			  }
			  //print out the path
			  System.out.println(outPath);
			   
			  OutputStream out = new FileOutputStream(outPath);
			  byte[] buff = new byte[1024];
			  int len;
			  //write file
			  while((len=in.read(buff))>0){
				  out.write(buff,0,len);
			  }
			  in.close();
			  out.close();
		  }
		 System.out.println("******************Unzipped********************");
	 }
}  


