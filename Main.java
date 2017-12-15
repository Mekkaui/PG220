class Main {

	public static void main(String[] args) throws Exception{
		int h_y=0;
		int w_y=0;
		int f_y=0;
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
		
		MetaWeather meta = new MetaWeather(nb_days);
		Prev prev = new Prev(nb_days);
		Yahoo yaho = new Yahoo(nb_days);
		try {
			meta.update(city);
		}
		catch(Exception e) {
			System.out.println("Error while getting datas from MetaWeather API.");
		}
		
		try {
			prev.update(city);
		}
		catch(Exception e) {
				System.out.println("Error while getting datas from Prevision-Meteos API.");
		}
		
		try {
			yaho.update(city);
		}
		catch(Exception e) {
			System.out.println("Error while getting datas from Yahoo API.");
		}
		
		first_line(nb_days);


		
		
		
		meta.description("| MetaWeather |", h_y, w_y,f_y);
		prev.description("|   P-Meteo   |", h_y, w_y,f_y);
		yaho.description("|   Yahoo     |", h_y, w_y,f_y);
		
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
