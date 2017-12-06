import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MetaWeather extends Meteo{

	public MetaWeather(int nb){
		super(nb);
	}
	
	void update(String city) throws Exception{
		DataQuery x1 = new DataQuery("https://www.metaweather.com/api/location/search/?query="+city);
		JSONParser parser = new JSONParser();
		
	    try{
		       Object z = parser.parse(x1.getData());
		       JSONArray array = (JSONArray)z;
				
		       JSONObject line = (JSONObject)array.get(0);
		       String id = String.valueOf(line.get("woeid"));
		   	   
		       DataQuery x2 = new DataQuery("https://www.metaweather.com/api/location/"+id);
		   	   JSONParser parser2 = new JSONParser();
		   	   Object z2 = parser.parse(x2.getData());
		   	   JSONArray array2 = new JSONArray();
		   	   array2.add(z2);
		   	   JSONObject line2 = (JSONObject)array2.get(0);
		   	   Object consolidated_weather = line2.get("consolidated_weather");
		   	   for(int i=0;i<super.nb_days;i++)
		   	   {
		   		JSONArray c = (JSONArray)consolidated_weather;
		   		JSONObject infos = (JSONObject)c.get(i);
		   		this.t[i]=(double)(infos.get("the_temp"));
		   		this.h[i]=(long)(infos.get("humidity"));
		   		this.w[i]=(double)(infos.get("wind_speed"));
		   	   }
		       
		         


		    }catch(ParseException pe){
				
		       System.out.println("position: " + pe.getPosition());
		       System.out.println(pe);
		    }
		
		
		
	}
}
