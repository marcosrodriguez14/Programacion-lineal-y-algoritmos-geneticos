package _soluciones;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import _datos.DatosEjercicio2;
import us.lsi.common.Set2;

public class SolucionEjercicio2 {
	
	
	public static SolucionEjercicio2 of_Range(List<Integer> value) {
		return new SolucionEjercicio2(value);
	}
	private Double coste;
	private Set<String> soluciones;
	
	
	private SolucionEjercicio2() {
		coste = 0.;
		soluciones = Set2.empty();
	}
	private SolucionEjercicio2(List<Integer>ls) {
		coste =0.;
		soluciones = new TreeSet<>();
		for (int i = 0; i<ls.size();i++) {
			if (ls.get(i)>0) {
				Double c = DatosEjercicio2.getPrecio(i);
				soluciones.add(String.format("S%d", i));
				coste += c;
			}
		}
		
	}
	public static SolucionEjercicio2 empty() {
		return new SolucionEjercicio2();
	}
	
	public String toString() {
		System.out.println("Variedades de cafe seleccionadads: ");
		//soluciones.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));
		System.out.println("Cursos elegidos: "+ soluciones.toString().replace("[", "{").replace("]", "}") );
		return String.format("Coste Total: "+"%s", coste);
	}
	
	
	
	
	
	
	
	
	
	
}
