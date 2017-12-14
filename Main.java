import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
class Main {

	public static void main(String[] args) throws Exception{
		int h_y=0;
		int w_y=0;
		int nb_days = 1;
		String city="";
		for(int i=0; i < args.length; i++) {
			if(i==0){
				city=args[0];
			}
			else {
				if(args[i].equals("-j")){
					i++;
					nb_days= Integer.parseInt(args[i]);
				}
				else if(args[i].equals("-w")){
					w_y=1;
					
				}
				else if(args[i].equals("-h")){
					h_y=1;
					
				}
			}
		  }
		
		first_line(nb_days);


		MetaWeather meta = new MetaWeather(nb_days);
		meta.update(city);
		meta.description("| MetaWeather |", h_y, w_y);

		Prev prev = new Prev(nb_days);
		prev.update(city);
		prev.description("|   P-Meteo   |", h_y, w_y);
		
		Yahoo yaho = new Yahoo(nb_days);
		yaho.update(city);
		yaho.description("|   Yahoo     |", h_y, w_y);
		
		last_line(nb_days);

	}
	public static void first_line(int nb_days){
		System.out.print("+-------------+");
		for (int i =0;i<nb_days;i++){
			System.out.print("--------+");
		}
		System.out.println("");
		System.out.print("|             |");
		for (int i =0;i<nb_days;i++){
			System.out.print("  J+"+i+"   |");
		}
		System.out.println("");
	}
	public static void last_line(int nb_days){
		System.out.print("+-------------+");
		for (int i =0;i<nb_days;i++){
			System.out.print("--------+");
		}
		System.out.println("");
	}

}