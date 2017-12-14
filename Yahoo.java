import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Yahoo extends Meteo{

	public Yahoo(int nb){
		super(nb);
	}

	void update(String city) throws Exception{

		try{

			DataQuery x = new DataQuery("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
			JSONParser parser = new JSONParser();
			Object z = parser.parse(x.getData());
			JSONArray array = new JSONArray();
			array.add(z);
			JSONObject line = (JSONObject)array.get(0);
			Object query = line.get("query");
			JSONObject q = (JSONObject) query;
			Object results = q.get("results");
			Object channel = ((JSONObject) results).get("channel");

			// For wind speed
			Object wind = ((JSONObject) channel).get("wind");
			Object speed = ((JSONObject) wind).get("speed");
			this.w[0] = Double.parseDouble(speed.toString());

			// For humidity
			Object atmosphere = ((JSONObject) channel).get("atmosphere");
			Object humidity = ((JSONObject) atmosphere).get("humidity");
			this.h[0] = (long)Double.parseDouble(humidity.toString());

			// Pas d'information
			for (int i=1; i<nb_days; i++)
			{
				this.h[i] = -1;
				this.w[i] = -1;
			}
			
			//For days
			Object item = ((JSONObject) channel).get("item");
			JSONArray arr = new JSONArray();
			arr.add(item);
			JSONObject line2 = (JSONObject)arr.get(0);
			Object forecast = line2.get("forecast");
			for(int i=0;i<super.nb_days;i++)
			{
				JSONArray c = (JSONArray)forecast;
				JSONObject infos = (JSONObject)c.get(i);
				double high = Double.parseDouble(infos.get("high").toString());
				double low = Double.parseDouble(infos.get("low").toString());
				this.t[i]= (low +high)/2;
			}

		}catch(ParseException pe){

			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}

}