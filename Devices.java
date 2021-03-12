import java.util.*;

public class Devices {
	
	/**
	 * id - ul senzorului din camera
	 */
	private String id_device;
	
	/**
	 * vector de vectori pentru retinerea temperaturilor pe ore
	 */
	public ArrayList<ArrayList<Double>> tempList;
	
	/**
	 * vector de vectori pentru retinerea umiditatilor pe ore
	 */
	public ArrayList<ArrayList<Double>> umiditateList;
	
	/**
	 * timpul de referinta primit ca input
	 */
	public double time_ref;

	/**
	 * Constructor fara parametrii
	 */
	Devices(){
		int i;
		
		this.id_device = "";
		this.time_ref = 0;
		
		tempList = new ArrayList<ArrayList<Double>> (25);
		for(i = 0; i < 24; i++) {
			tempList.add(new ArrayList<Double> ());
		}
		
		umiditateList = new ArrayList<ArrayList<Double>> (25);
		for(i = 0; i < 24; i++) {
			umiditateList.add(new ArrayList<Double> ());
		}
	}
	
	/**
	 * Constructor cu parametrii
	 * @param id id - ul senzorului
	 * @param time timpul de referinta
	 */
	Devices(String id, double  time){
		int i;
		
		this.id_device = id;
		this.time_ref = time;
		tempList = new ArrayList<ArrayList<Double>> (24);
		for(i = 0; i < 24; i++) {
			tempList.add(new ArrayList<Double> ());
		}	
		
		umiditateList = new ArrayList<ArrayList<Double>> (25);
		for(i = 0; i < 24; i++) {
			umiditateList.add(new ArrayList<Double> ());
		}
	}
	
	/**
	 * @return id - ul senzorului din camera curenta
	 */
	public String get_id() {
		return this.id_device;
	}
	
	/**
	 * Se calculeaza indexul la care trebuie adaugata temperatura in 
	 * vector, facand diferenta dintre timpul dat si timpul de diferenta
	 * si se imparte la 3600. In cazul in care diferenta este negativa nu
	 * se ia in calcul aceea temperatura intrucat depaseste timestampul 
	 * initial. Dupa aflarea pozitiei se verifica daca exista deja acea 
	 * temperatura in intervalul de timp, in caz afirmativ se nu se mai 
	 * adauga. Dupa adaugarea temperaturii vectorul este sortat.
	 * 
	 * @param timestamp timpul la care trebuie adaugata temperatura
	 * @param temperature temperatura de adaugat la ora respectiva
	 */
	public void Observe(double timestamp, double temperature) {
		int index = (int)(time_ref - timestamp);
		
		if(index < 1) {
			return;
		}
		
		index = index/3600;
		
		if(tempList.get(index).contains(temperature)) {
			return;
		}
		
		tempList.get(index).add(temperature);
		Collections.sort(tempList.get(index));
	}
	
	/**
	 * Metoda este exact ca cea prezentata anterior doar ca tratea
	 * cazul umiditatilor
	 * 
	 * @param timestamp timpul la care trebuie adaugata umiditatea
	 * @param umiditate umiditatea de adaugat la ora respectiva
	 */
	public void Observeh(double timestamp, double umiditate) {
		int index = (int)(time_ref - timestamp);
		
		if(index < 1) {
			return;
		}
		
		index = index/3600;
		
		if(umiditateList.get(index).contains(umiditate)) {
			return;
		}
		
		umiditateList.get(index).add(umiditate);
		Collections.sort(umiditateList.get(index));
	}		
}
