import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, ArrayIndexOutOfBoundsException, IOException {
		/**
		 * Se retine casa
		 */
		Home H;
		
		/**
		 * numarul de camere din casa
		 */
		int nb = 0;
		
		/**
		 * variabila ce retine cate camere au fost citite
		 */
		int ok = 0;
		
		int i;
		
		/**
		 * timpul de referinta
		 */
		long ref_time = 0;
		
	    try {
	    	//fisierul de scriere
	    	File fileWriter = new File("therm.out");
			FileWriter output = new FileWriter(fileWriter, true);
			        
			//fisierul de citire
			FileReader fileReader = new FileReader("therm.in");
			BufferedReader bufferedReader = new BufferedReader(fileReader);		        
			
			/**
			 * string pentru stocarea fiecarei linii din fisier
			 */
			 String s;
			 
			 /**
			  * vector pentru s
			  */
			 String [] r;
			 
			 s = bufferedReader.readLine();			
			 r = s.split("\\s");
			 nb = Integer.parseInt(r[0]);
			 
			 /**
			  * daca dimensiunea primei linii din fisier are lungimea 3 inseamna
			  * ca nu se ia in considerare si cazul cu umiditatea iar daca este 
			  */
			 if(r.length == 3) {
				 H = new Home(Integer.parseInt(r[0]), Double.parseDouble(r[1]), 0);
				 ref_time = Long.parseLong(r[2]);
			 }
			 else {
				 H = new Home(Integer.parseInt(r[0]), Double.parseDouble(r[1]), Double.parseDouble(r[2]));
				 ref_time = Long.parseLong(r[3]);				 
			 }

			 
			 while ((s = bufferedReader.readLine()) != null) {
				 
				 r = s.split("\\s");				 
				/**
				 * primele nb linii sunt camere si se efctueaza citirea acestora
				 * dupa aceea se citesc comenzile
				 */
				 if(ok < nb) {
					 Rooms R = new Rooms(r[0], Integer.parseInt(r[2]));
					 H.R.add(R);
					 H.R.get(ok).D = new Devices(r[1], ref_time);
					 ok++;
				 }
				
				 else if(r[0].equals("OBSERVE")){
				 	 for(i = 0; i < nb; i++) {
						 if((H.R.get(i).D.get_id()).equals(r[1])) {
							 H.R.get(i).D.Observe(Double.parseDouble(r[2]), Double.parseDouble(r[3]));
						 }
					 }
				 }
				 
				 else if(r[0].equals("OBSERVEH")){
				 	 for(i = 0; i < nb; i++) {
						 if((H.R.get(i).D.get_id()).equals(r[1])) {
							 H.R.get(i).D.Observeh(Double.parseDouble(r[2]), Double.parseDouble(r[3]));
						 }
					 }
				 }		
				 
				 else if(r[0].equals("TEMPERATURE")) {
					 H.TEMPERATURE(Double.parseDouble(r[1]));
				 }
				 
				 else if(r[0].equals("LIST")) {
				 	 for(i = 0; i < nb; i++) {
						 if((H.R.get(i).get_id()).equals(r[1])) {
							H.R.get(i).LIST(Double.parseDouble(r[2]), Double.parseDouble(r[3]));
						 }	
				 	 }
				 	 output.close();
				 }	
				 
				 else if(r[0].equals("TRIGGER")) {
					 H.TRIGGER_HEAT();
				 }
				 
			 }		
			 
		     fileReader.close();
		     bufferedReader.close();
	    }		
		
	    catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
		}	     
		
	    catch (Exception e){
            e.printStackTrace();
		}
	}
}
