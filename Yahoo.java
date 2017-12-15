public class Yahoo extends Meteo{

	public Yahoo(int nb){
		super(nb);
	}
	void update(String city) throws Exception{
			String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
			Object line = parse_url(url);
			Object query = parse_object(line,"query");
			Object results = parse_object(query,"results");
			Object channel = parse_object(results,"channel");
			
			// For wind speed
			Object wind = parse_object(channel,"wind");
			Object speed = parse_object(wind,"speed");
			
			// For humidity
			Object atmosphere = parse_object(channel,"atmosphere");
			Object humidity = parse_object(atmosphere,"humidity");
			
			//For days
			Object item = parse_object(channel,"item");
			Object forecast_array = parse_object( item ,"forecast");
			
			for(int i=0;i<super.nb_days;i++)
			{
				Object infos = parse_array(forecast_array,i);
				int high = Integer.parseInt((String)parse_object(infos,"high"));
				int low = Integer.parseInt((String)parse_object(infos,"low"));
				int celc = (((low +high)/2) - 32) * 5/9;
				this.t[i]= (celc);
			}
			this.w[0] =  Integer.parseInt( (String)( speed.toString()));
			this.h[0] = Integer.parseInt((  (String) ( humidity  ).toString()));
		
	}
}
