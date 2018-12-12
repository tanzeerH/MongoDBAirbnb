
package Util;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;


import com.mongodb.DB;
import com.mongodb.MongoClient;
public class Connection {

	private static DB airBnbDB = null;
	private static final String DBNAME = "dbairbnb";
	public static final String LISTING = "listing";
	public static DB getDataBaseInstance() {
		if(airBnbDB == null)
		{
			try {
				MongoClient mongoClient = new MongoClient("localhost");
				
		         airBnbDB = mongoClient.getDB(DBNAME);
		         
		                 
		               
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return airBnbDB;
	}
	public static void main(String[] args) {
		
	}
}
