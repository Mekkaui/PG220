import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Prev extends Meteo{

	public Prev(int nb){
		super(nb);
	}
	
	void update(String city) throws Exception{
		DataQuery query1 = new DataQuery("https://www.prevision-meteo.ch/services/json/"+city);
		JSONParser line1 = new JSONParser();

		try{
			Object line1_obj = line1.parse(query1.getData());
			JSONObject array = (JSONObject)line1_obj;

		
			for(int i=0;i<super.nb_days;i++)
			{
				JSONObject fcst_day = (JSONObject)array.get("fcst_day_"+i);
				JSONObject h_data = (JSONObject)fcst_day.get("hourly_data");
				long wind=0;
				long humidity=0;
				long tmp_max=(long)fcst_day.get("tmax");
				long tmp_min=(long)fcst_day.get("tmin");
				long tmp=(tmp_max+tmp_min)/2;
				for (int j=0;j<24;j++){
					JSONObject this_h = (JSONObject)h_data.get(j+"H00");
					wind=wind+(long)(this_h.get("WNDSPD10m"));
					humidity=humidity+(long)(this_h.get("RH2m"));
				}
				this.h[i]=humidity/24;
				this.w[i]=(double)wind/24;
				this.t[i]=(double)tmp;

			}

		}catch(ParseException pe){

			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}
	}
}