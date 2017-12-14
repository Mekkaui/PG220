import java.io.*;
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
		int o_y = 0;
		int a_y = 0;
		int nb_days = 1;
		String city="";
		String FILENAME = "";
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
				else if(args[i].equals("-o")) {
					i++;
					o_y = 1;
					FILENAME = args[i];
				}
				else if(args[i].equals("-a")) {
					i++;
					a_y = 1;
					FILENAME = args[i];
				}
			}
		}
		
		cmdread(nb_days, FILENAME, city, h_y, w_y, o_y, a_y);
	}
	
	public static void get_args(String args[], int nb_days, String city, int h_y, int w_y, int o_y)
	{
		
	}
	
	public static void get_info(int nb_days, String city, int h_y, int w_y, int o_y) throws Exception
	{
		MetaWeather meta = new MetaWeather(nb_days);
		meta.update(city);
		meta.description("| MetaWeather |", h_y, w_y);

		Prev prev = new Prev(nb_days);
		prev.update(city);
		prev.description("|   P-Meteo   |", h_y, w_y);

		Yahoo yaho = new Yahoo(nb_days);
		yaho.update(city);
		yaho.description("|   Yahoo     |", h_y, w_y);
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
	
	public static void read_file(String FILENAME) {
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cmdread(int nb_days, String city, String FILENAME, int h_y, int w_y, int o_y, int a_y) throws Exception
	{
		first_line(nb_days);
		get_info(nb_days, city, h_y, w_y, o_y);
		last_line(nb_days);
		
		if (o_y == 1)
		{
			PrintStream out = new PrintStream(new FileOutputStream(FILENAME));
			System.setOut(out);
			first_line(nb_days);
			get_info(nb_days, city, h_y, w_y, o_y);
			last_line(nb_days);
			
		}
		
		if (a_y == 1)
		{
			PrintStream out = new PrintStream(new FileOutputStream(FILENAME));
			System.setOut(out);
			read_file(FILENAME);
			first_line(nb_days);
			get_info(nb_days, city, h_y, w_y, o_y);
			last_line(nb_days);
		}
		
	}
}