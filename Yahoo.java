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
			JSONObject line = (JSONObject)parser.parse(x.getData());
			JSONObject channel = (JSONObject)((JSONObject)((JSONObject)line.get("query")).get("results")).get("channel");
			// For wind speed
			this.w[0] =  Integer.parseInt(( (String)( (JSONObject)channel.get("wind") ).get("speed") ).toString());
			// For humidity
			this.h[0] = Integer.parseInt((  (String) ( (JSONObject) channel.get("atmosphere") ).get("humidity")  ).toString());
			// Pas d'information
			for (int i=1; i<nb_days; i++)
			{
				this.h[i] = -1;
				this.w[i] = -1;
			}
			//For days
			JSONArray forecast_array = (JSONArray)( (JSONObject)channel.get("item") ).get("forecast");
			for(int i=0;i<super.nb_days;i++)
			{
				JSONObject infos = (JSONObject)forecast_array.get(i);
				int high = Integer.parseInt((String)infos.get("high"));
				int low = Integer.parseInt((String)infos.get("low"));
				int celc = (((low +high)/2) - 32) * 5/9;
				this.t[i]= (celc);
			}
		}catch(ParseException pe){
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}
}