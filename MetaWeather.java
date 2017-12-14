import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MetaWeather extends Meteo{

	public MetaWeather(int nb){
		super(nb);
	}

	void update(String city) throws Exception{
		DataQuery query1 = new DataQuery("https://www.metaweather.com/api/location/search/?query="+city);
		JSONParser line1 = new JSONParser();
		try{
			Object line1_obj = line1.parse(query1.getData());
			JSONArray array = (JSONArray)line1_obj;

			JSONObject location_search = (JSONObject)array.get(0);
			String woeid = String.valueOf(location_search.get("woeid"));

			DataQuery query2 = new DataQuery("https://www.metaweather.com/api/location/"+woeid);
			JSONParser line2 = new JSONParser();
			Object line2_obj = line2.parse(query2.getData());
			JSONArray line2_parsed = new JSONArray();
			line2_parsed.add(line2_obj);
			JSONObject location = (JSONObject)line2_parsed.get(0);
			Object consolidated_weather = location.get("consolidated_weather");
			for(int i=0;i<super.nb_days;i++)
			{
				JSONArray c = (JSONArray)consolidated_weather;
				JSONObject infos = (JSONObject)c.get(i);
				this.t[i]=(int)(double)(infos.get("the_temp"));
				this.h[i]=(int)(long)(infos.get("humidity"));
				this.w[i]=(int)(double)(infos.get("wind_speed"));
			}
		}catch(ParseException pe){

			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}
}
