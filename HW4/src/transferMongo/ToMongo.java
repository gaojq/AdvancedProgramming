package transferMongo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ToMongo {
	
	static MongoClient mongoClient = new MongoClient("localhost", 27017);
	static MongoDatabase dbObj;
	public void createDB(){
		
		//Create file names
		String[] files = {"ActivFit","Activity","BatterySensor","Bluetooth","HeartRate","LightSensor"};
		
		//Create Database
		mongoClient.dropDatabase("homeWork4");
		dbObj = mongoClient.getDatabase("homeWork4");
		
		for(int i=0; i< files.length; i++) {
			File file = new File("/Users/jianqinggao/Desktop/AdvancedJava/HW4/"+files[i]+".txt");
			
			//Create collections
			dbObj.createCollection(files[i]);
			
			//Convert files from Json to MongoDB
			try {
				transferToMongo(file, files[i]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//Users/jianqinggao/Desktop/AdvancedJava/HW4/XXX
	}
	private static void transferToMongo(File file, String s) throws FileNotFoundException {
		//Get database
		dbObj = mongoClient.getDatabase("homeWork4");
		MongoCollection<Document> col = dbObj.getCollection(s);
		Scanner scan = new Scanner(file);	
		
		//Get from Json file(txt with Json format) line by line
		while( scan.hasNext()) {
			final String line = scan.nextLine().toLowerCase();
			//System.out.println(line);
			col.insertOne(Document.parse(line));
		}

	}
	
	//Getter method
	public MongoDatabase getDBObj() {
		return dbObj;
	}
}
