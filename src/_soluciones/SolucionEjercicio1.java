package _soluciones;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import _datos.DatosEjercicio1;
import us.lsi.common.Map2;

public class SolucionEjercicio1 {
	
	
	public static SolucionEjercicio1 of_Range(List<Integer> value) {
		return new SolucionEjercicio1(value);
	}

	private Double beneficio = 0.;
	private Map<String, Integer> soluciones = new TreeMap<>();
	
	
	private SolucionEjercicio1() {
		beneficio = 0.;
		soluciones = Map2.empty();
	
	}
	
	private SolucionEjercicio1(List<Integer>ls) {
//		beneficio =0.;
//		soluciones = new TreeMap<>();
		for (int i = 0; i<ls.size();i++) {
//			if (ls.get(i)>0) {
				Integer kg= ls.get(i);
				Double b = DatosEjercicio1.getBeneficio(i)*kg;
				soluciones.put(String.format("P0%d ",i+1 ), kg);
				beneficio += b;
				
	//		}
		}
	}
	public static SolucionEjercicio1 empty() {
		return new SolucionEjercicio1();
	}
	
	public String toString() {
		System.out.println("Variedades de cafe seleccionadads: ");
		soluciones.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));
		
		return String.format("Beneficio: "+"%s", beneficio);
	}

}
