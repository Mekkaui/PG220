public abstract class Meteo {
	protected int t[];

	protected int h[];

	protected int nb_days;

	protected int w[];

	public Meteo(int nb_days){
		this.nb_days = nb_days;
		this.t = new int[nb_days];
		this.h = new int[nb_days];
		this.w = new int[nb_days];
	}
	public int get_t(int day){
		return this.t[day];
	}
	public int get_h(int day){
		return this.h[day];
	}
	public int get_w(int day){
		return this.w[day];
	}
	public void description(String title,int h_y,int w_y,int f_y){
		System.out.print("+-------------+");
		for (int i =0;i<this.nb_days;i++){
			System.out.print("--------+");
		}
		System.out.println("");
		
		
		System.out.print(title);
		for (int i =0;i<this.nb_days;i++){
			if(f_y!=0) {
				int far = (int)( this.t[i] * 1.8 ) + 32;
				System.out.print("  "+String.format("%02d", (int)far)+"°F  |");
			}
			else {
				System.out.print("  "+String.format("%02d", (int)this.t[i])+"°C  |");
			}
			
		}
		if(h_y!=0){
			System.out.println("");
			System.out.print("|             |");
			for (int i =0;i<this.nb_days;i++){
				if (this.h[i] == -1)
				{
					System.out.print("  "+"--"+"    |");
				}
				else {
				System.out.print("  "+  String.format("%02d", (int)this.h[i]) +"%   |");
				}
			}
		}
		if(w_y!=0){
			System.out.println("");
			System.out.print("|             |");
			for (int i =0;i<this.nb_days;i++){
				if (this.h[i] == -1)
				{
					System.out.print("  "+"--"+"    |");
				}
				else {
				System.out.print(" "+String.format("%02d", (int)this.w[i])+"km/h |");
				}
			}
		}
		System.out.println("");
	}
}