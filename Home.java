import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Home {
	/**
	 * numarul de camaere
	 */
	private int nb_rooms;
	
	/**
	 * temperatura globala
	 */
	private double global_temp;
	
	/**
	 * umiditatea globala
	 */
	private double umiditate;
	
	/**
	 * vector de camere pe care le contine casa
	 */
	public ArrayList<Rooms> R;
	
	/**
	 * Constructor fara parametrii
	 */
	Home(){
		this.nb_rooms = 0;
		this.global_temp = 0;
		this.umiditate = 0;
		R = new ArrayList<Rooms> (0);
	}
	
	/**
	 * Constructor cu parametrii
	 * 
	 * @param nb_rooms numarul de camere din casa
	 * @param temperature temperatura globala
	 * @param umiditate umiditatea globala
	 */
	Home(int nb_rooms, double temperature, double umiditate) {
		this.nb_rooms = nb_rooms;
		this.global_temp = temperature;
		this.umiditate = umiditate;
		R = new ArrayList<Rooms> (this.nb_rooms);	

	}	
	
	/**
	 * Returneaza id - ul camereii de pe pozitia data
	 * 
	 * @param pos indexul camerei a carui id se doreste
	 * @return id - ul camerei de pe pozitia pos
	 */
	public String getRoom_id(int pos) {
		return R.get(pos).D.get_id();
	}
	
	/**
	 * Se seteaza temperatura globala din casa cu valoarea
	 * primita ca argument
	 * 
	 * @param x temperatura globala ce se doreste in casa
	 */
	public void TEMPERATURE(double x) {
		this.global_temp = x;
	}
	
	/**
	 * Se calculeaza dupa cerintele date media ponderata a 
	 * temperaturilo si umiditatilor si se analizeaza daca 
	 * centrala trebuie pornita sau nu, afisandu - se mesaje
	 * sugestive.
	 * 
	 * @throws IOException
	 */
	public void TRIGGER_HEAT() throws IOException {
		File fileWriter = new File("therm.out");
		FileWriter output = new FileWriter(fileWriter, true);
		int i;
		double sum_T = 0;
		double sum_U = 0;
		double sum_p = 0;
		
		for(i = 0; i < nb_rooms; i++) {	
			sum_T += R.get(i).min_temp() * R.get(i).get_area();
			//in cazul in care nu exista nicio temperatura in camera respectiva
			//nu se mai ia in considerare
			if(R.get(i).min_temp() != 0) {
				sum_p += R.get(i).get_area();
			}
		}
		
		sum_T = sum_T/sum_p;

		if(this.umiditate != 0) {
			sum_p = 0;
			for(i = 0; i < nb_rooms; i++) {	
				sum_T += R.get(i).max_umiditate() * R.get(i).get_area();
				if(R.get(i).max_umiditate() != 0) {
					sum_p += R.get(i).get_area();
				}
			}
			
			sum_U = sum_U/sum_p;
			
			if(sum_U > this.umiditate) {
				output.write("NO\n");
			}
			
			else {
				if(sum_T < global_temp) {
					output.write("YES\n");
				}
				
				else {
					output.write("NO\n");
				}				
			}
			
			output.close();
			return;
		}
		
		if(sum_T < global_temp) {
			output.write("YES\n");
		}
		
		else {
			output.write("NO\n");
		}
		output.close();
	}
}
