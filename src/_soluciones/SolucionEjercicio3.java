package _soluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import _datos.DatosEjercicio3;
import _datos.DatosEjercicio3.Investigador;
public class SolucionEjercicio3 {

	public static SolucionEjercicio3 of_Range(List<Integer> value) {
		return new SolucionEjercicio3(value);
	}

	private Integer calidad= 0;
	private List<Investigador> investigadores=DatosEjercicio3.getInvestigadores();
	private List<List<Integer>> dias =new ArrayList<>();
	private SortedMap<String, List<Integer>> solucion= new TreeMap<>();

	private SolucionEjercicio3() {
	}
	
	private SolucionEjercicio3(List<Integer> ls) {
		Integer nIvestigadores = DatosEjercicio3.getnInvestigadores();
		Integer nTrabajos = DatosEjercicio3.getmTrabajos();
		Integer nEspecialidades = DatosEjercicio3.geteEspecialidades();
		
		dias = new ArrayList<>();
		investigadores =DatosEjercicio3.getInvestigadores();
		solucion = new TreeMap<>();
		calidad = 0;
	
		//creamos una lista para cada invstigador
		for (int i = 0; i < nIvestigadores; i++) {
			dias.add(new ArrayList<>());
		}
		
		for (int j = 0; j < nTrabajos; j++) {
			Integer IndiceIni = nIvestigadores * j;
			Integer IndiceFinal = IndiceIni + nIvestigadores;
			List<Integer> trabajos = ls.subList(IndiceIni, IndiceFinal);
			
			// Añadimos a la lista del investigador los dias
			for (int i = 0; i < nIvestigadores; i++) {
				dias.get(i).add(trabajos.get(i));
				solucion.put(investigadores.get(i).nombre(), dias.get(i));
			}
			
			Boolean cumpleDias = true;
			for (int k = 0; k < nEspecialidades; k++) {
				Integer suma = 0;
				for (int i = 0; i < nIvestigadores; i++) {
					suma += trabajos.get(i) * DatosEjercicio3.tieneEspecialidad(i, k);
				}
				if (suma < DatosEjercicio3.diasNecesarios(j, k)) {
					cumpleDias = false;
					k = nEspecialidades;
				}
			}
			
			// Si se realiza el trabajo, se suma su calidad
			if (cumpleDias) {
				calidad += DatosEjercicio3.getCalidadTrabajo(j);
			}
		}

	}
	public static SolucionEjercicio3 empty() {
		return new SolucionEjercicio3();
	}

	public String toString() {
		
		System.out.println("Reparto obtenido (dias trabajados por cada investigador en cada trabajo):");
		solucion.entrySet().stream().forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));
		return String.format("\nSUMA DE LAS CALIDADES DE LOS TRABAJOS REALIZADOS: " + calidad);
	}
}
