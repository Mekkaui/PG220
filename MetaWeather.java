public class MetaWeather extends Meteo{

	public MetaWeather(int nb){
		super(nb);
	}

	void update(String city) throws Exception{
		String url = "https://www.metaweather.com/api/location/search/?query="+city;
		Object line = parse_url(url);
		Object location_search = parse_array(line,0);
		Object woeid = parse_object(location_search,"woeid");
		Object location = parse_url("https://www.metaweather.com/api/location/"+String.valueOf(woeid));
		Object consolidated_weather = parse_object(location,"consolidated_weather");
		for(int i=0;i<super.nb_days;i++)
			{
				Object infos = parse_array(consolidated_weather,i);
				this.t[i]=(int)(double)(parse_object(infos,"the_temp"));
				this.h[i]=(int)(long)(parse_object(infos,"humidity"));
				this.w[i]=(int)(double)(parse_object(infos,"wind_speed"));
			}
	}
}
