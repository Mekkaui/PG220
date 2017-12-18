import API.MetaWeather;
import API.Prev;
import API.Yahoo;
import Affichage.Affichage;
import Affichage.Arguments;

class Main {

	public static void main(String[] args) throws Exception{
		
		Arguments argms = new Arguments(args);
		MetaWeather meta = new MetaWeather(argms.nb_days);
		Yahoo yaho = new Yahoo(argms.nb_days);
		Prev prev = new Prev(argms.nb_days);
		meta.update(argms.city);
		prev.update(argms.city);
		yaho.update(argms.city);

		Affichage aff = new Affichage(argms,meta,prev,yaho);
		aff.cmdread();
	}


}