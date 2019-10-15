package transferMongo;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.gt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


import com.mongodb.client.MongoCollection;


public class GetDB {
	//ToMongo tomongo = new ToMongo();
	
	static MongoClient mongoClient = new MongoClient();
	static MongoDatabase db = mongoClient.getDatabase("homeWork4");
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("homeWork4");
		String[] files = {"ActivFit","Activity","BatterySensor","Bluetooth","HeartRate","LightSensor"};
		//Set up a delay, to make cleaner sheet
		try
		{
		    Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		System.out.println("Pick a section");
		System.out.println("1. Count the number of heart-rate\n"+
		"2. Check running\n"+"3. Count steps");
		Scanner scan = new Scanner(System.in);
		int i = scan.nextInt();
		System.out.println("Input date(for example: mar/5/2017)");
		String date = scan.next();
		
		switch(i) {
			case 1:
				countDay(date, files[2]);
				
				break;
			case 2:
				
				running(date, files[0]);
				break;
			case 3:
				countSteps(date, "Activity");
				break;
				
		}
		//BasicDBObject df = new
		
		//int count = countDay(date, files[i]);
		//System.out.println(count);
		
	}
	
	private static void countDay(String date, String file) {
		int count = 0;
		//split data
		String day = date.split("/")[0]+" "+date.split("/")[1];
		String year = date.split("/")[2];
		MongoCollection<Document> doc = db.getCollection(file);
		
		//set up filter, use and method to combine them
		//eq is equal, gt is greater than
		Bson filter = and(eq("timestamp", new BasicDBObject("$regex", day)),
				eq("timestamp", new BasicDBObject("$regex", year)));
		System.out.println(day);
		System.out.println(year);
		
		//Use variable count to count the number of files that have been find
		for(Document document: doc.find(filter)) {
			count++;
		}
		System.out.println("Number of heart rate recieved: "+ count);
		
	}
	
	private static void running(String date, String file) {
		String day = date.split("/")[0]+" "+date.split("/")[1];
		String year = date.split("/")[2];
		int count = 0;
		
		MongoCollection<Document> doc = db.getCollection(file);
		
		// Make a filter with more request
		Bson filter = and(eq("timestamp.start_time", new BasicDBObject("$regex", day)),
				eq("timestamp.start_time", new BasicDBObject("$regex", year)),
				eq("timestamp.end_time", new BasicDBObject("$regex", day)),
				eq("timestamp.end_time", new BasicDBObject("$regex", year)),
				eq("sensor_data.activity","running"));
		
		//count documents
		for(Document document: doc.find(filter)) {
			count++;
		}
		
		System.out.print(count);
		
		//If there is document exist then it means the person has run today
		if(count>0) {
			for(Document document: doc.find(filter)) {
				Document time = (Document) document.get("timestamp");
				String s = (String) time.get("start_time");
				String e = (String) time.get("end_time");
				//String e = (String) document.get("timestamp.end_time").toString();
				System.out.println("running time is: "+s+" to "+e);
			}
		}else {
			//Otherwise no running
			System.out.println("No running today");
		}
	}
	
	public static void countSteps(String date, String file) {
		//Split data
		String day = date.split("/")[0]+" "+date.split("/")[1];
		String year = date.split("/")[2];
		MongoCollection<Document> doc = db.getCollection(file);
		
		//Create filter
		Bson filter = and(eq("time_stamp", new BasicDBObject("$regex", day)),
				eq("time_stamp", new BasicDBObject("$regex", year)));
		
		List<Document> list = doc.find(filter).into(new ArrayList<>());
		
		//Find the last document of the day and get step info
		Document last = (Document) list.get(list.size()-1).get("sensor_data");
		int steps = (Integer) last.get("step_counts");
		
		System.out.println("Steps that has been walked "+steps);
	}
	
}
