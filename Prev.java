public class Prev extends Meteo{

	public Prev(int nb){
		super(nb);
	}
	
	void update(String city) throws Exception{
		String url = "https://www.prevision-meteo.ch/services/json/"+city;
		Object line = parse_url(url);
			for(int i=0;i<super.nb_days;i++)
			{
				Object fcst_day = parse_object (line,"fcst_day_"+i);
				Object h_data = parse_object(fcst_day,"hourly_data");
				long wind=0;
				long humidity=0;
				long tmp_max=(long)parse_object(fcst_day,"tmax");
				long tmp_min=(long)parse_object(fcst_day,"tmin");
				long tmp=(tmp_max+tmp_min)/2;
				for (int j=0;j<24;j++){
					Object this_h = parse_object(h_data,j+"H00");
					wind=wind+(long)parse_object(this_h,"WNDSPD10m");
					humidity=humidity+(long)parse_object(this_h,"RH2m");
				}
				this.h[i]=(int)(humidity/24);
				this.w[i]=(int)(double)wind/24;
				this.t[i]=(int)(double)tmp;

			}

	}
}
