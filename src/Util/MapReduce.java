package Util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;

public class MapReduce {

	public static String sampleMapReduce() {
            String price = "5";
		DB db = Connection.getDataBaseInstance();
		DBCollection listing = db.getCollection(Connection.LISTING);
		String map = "function() { " +

				"var category; " +

				"if ( this.price >= "+price +" ) " +

				"category = 'Costly'; " +

				"else " +

				"category = 'Cheap'; " +

				"emit(category, {name: this.name});}";
		String reduce = "function(key, values) { " +

				"var sum = 0; " +

				"values.forEach(function(doc) { " +

				"sum += 1; " +

				"}); " +

				"return {count: sum};} ";

		MapReduceCommand cmd = new MapReduceCommand(listing, map, reduce,

				null, MapReduceCommand.OutputType.INLINE, null);

		MapReduceOutput out = listing.mapReduce(cmd);

                String res= "";
		for (DBObject o : out.results()) {

			System.out.println(o.toString());
                        res += o.toString()+"\n";

		}
                return res;

	}
	public static void main(String[] args) {
		sampleMapReduce();
	}

}
