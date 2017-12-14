public abstract class Meteo {
	protected double t[];

	protected long h[];

	protected int nb_days;

	protected double w[];

	public Meteo(int nb_days){
		this.nb_days = nb_days;
		this.t = new double[nb_days];
		this.h = new long[nb_days];
		this.w = new double[nb_days];
	}
	public double get_t(int day){
		return this.t[day];
	}
	public long get_h(int day){
		return this.h[day];
	}
	public double get_w(int day){
		return this.w[day];
	}
	public void description(String title,int h_y,int w_y){
		System.out.print("+-------------+");
		for (int i =0;i<this.nb_days;i++){
			System.out.print("--------+");
		}
		System.out.println("");
		
		
		System.out.print(title);
		for (int i =0;i<this.nb_days;i++){
			System.out.print("  "+String.format("%02d", (int)this.t[i])+"°   |");
		}
		if(h_y!=0){
			System.out.println("");
			System.out.print("|             |");
			for (int i =0;i<this.nb_days;i++){
				if (this.h[i] == -1)
				{
					System.out.print("  "+"--"+"    |");
				}
				else
				{
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
				else
				{
					System.out.print(" "+String.format("%02d", (int)this.w[i])+"km/h |");
				}
			}
		}
		System.out.println("");
	}
}