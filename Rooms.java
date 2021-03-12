import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Rooms {
	/**
	 * id - ul camerei
	 */
	private String id;
	
	/**
	 * suprafata camerei
	 */
	private int area;
	
	/**
	 * senzorul din camera
	 */
	public Devices D;
	
	/**
	 * Constructor fara parametrii
	 */
	Rooms(){
		this.id = "";
		this.area = 0;
		D = new Devices();
	}
	
	/**
	 * Constructor cu parametrii
	 * 
	 * @param id id - ul camerei
	 * @param area suprafata camerei
	 */
	Rooms(String id, int area) {
		this.id = id;
		this.area = area;
		D = new Devices(); 
	}
	
	/**
	 * Intoarce id - ul camerei
	 * @return id - ul camerei
	 */
	public String get_id() {
		return this.id;
	}
	
	/**
	 * Idtoarce suprafata camerei
	 * @return suprafata camerei
	 */
	public int get_area() {
		return this.area;
	}
	
	/**
	 * Metoda cauta ultimul interval cu temperaturi 
	 * si din aceasta se intoarce cea mai mica valoarea
	 * adica temperatura de pe pozitia 0
	 * @return temperatura minima
	 */
	public double min_temp() {
		int i = 0;
		
		for(i = 0; i < 24; i++) {
			if(!D.tempList.get(i).isEmpty()) {
				return  D.tempList.get(i).get(0);				
			}
		}
		
		return 0;
	}
	
	/**
	 * Metoda cauta ultimul interval cu umiditati
	 * si din aceasta se intoarce cea mai mica valoarea
	 * adica umiditatea de pe pozitia 0
	 * @return umiditatea minima
	 */
	public double max_umiditate() {
		int i = 0;
		
		for(i = 0; i < 24; i++) {
			if(!D.umiditateList.get(i).isEmpty()) {
				return  D.umiditateList.get(i).get(0);				
			}
		}
		
		return 0;
	}
	
	/**
	 * Metoda calculeaza indicii timpilor dati ca parametru la fel ca si 
	 * in comanda OBSERVE. Se retine rezultatul diferentei celor doi indici
	 * pentru a cunoaste numarul intervalelor de afisat.
	 * 
	 * @param x timpul de start
	 * @param y timpul de final
	 * @throws IOException
	 */
	public void LIST(double x, double y) throws IOException {
		File fileWriter = new File("therm.out");
		FileWriter output = new FileWriter(fileWriter, true);
		
		int s = 0, f = 0, i, k;
		s = (int)(D.time_ref - x)/3600;
		f = (int)(D.time_ref - y)/3600;
		
		output.write(id + " ");
		k = s - f;
		while(k != 0) {
			for(i = 0; i < D.tempList.get(f).size(); i++) {
				if(k == 1 && (i == D.tempList.get(f).size() - 1)) {
					output.write(String.format("%.2f",D.tempList.get(f).get(i)));
				}
				
				else {
					output.write(String.format("%.2f", D.tempList.get(f).get(i)) + " ");
				}
			}
			f++;
			k--;
		}

		output.write("\r\n");	
		output.close();
	}
}
