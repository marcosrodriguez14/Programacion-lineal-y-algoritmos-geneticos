package ejercicio3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import _datos.DatosEjercicio3;
import _datos.DatosEjercicio3.Investigador;
import _datos.DatosEjercicio3.Trabajo;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	private static List<Investigador> investigadores;
	private static List<Trabajo> trabajos;

	// Metodos de entrada

		public static Integer getnInvestigadores() {
			return investigadores.size();
		}

		public static Integer geteEspecialidades() {
			return investigadores.stream().map(x -> x.especialidad()).collect(Collectors.toSet()).size();
		}

		public static Integer getmTrabajos() {
			return trabajos.size();
		}

		// devuelve 1 si el trabajador i tiene la especialidad k
		public static Integer tieneEspecialidad(Integer i, Integer k) {
			return investigadores.get(i).especialidad() == k ? 1 : 0;
		}

		// devuelve los dias disposibles del trabajador j
		// capacidad = dias disponibles
		public static Integer getCapacidad(Integer i) {
			return investigadores.get(i).capacidad();
		}

		// dias necesarios para el trabajo j de investigador con especialidad k
		public static Integer diasNecesarios(Integer j, Integer k) {
			return trabajos.get(j).reparto().get(k);
		}

		public static Integer getCalidadTrabajo(Integer j) {
			return trabajos.get(j).calidad();
		}
		public static  List<Investigador> getInvestigadores() {
			return investigadores;
		}
		public static List<Trabajo> getTrabajos() {
			return trabajos;
		}
		
		

	public static void ejercicio3_model() throws IOException {

		DatosEjercicio3.iniDatos("ficheros_ejercicios/Ejercicio3DatosEntrada3.txt");

		investigadores = DatosEjercicio3.getInvestigadores();
		trabajos = DatosEjercicio3.getTrabajos();

		// si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para
		// no sobreescribirlo
		AuxGrammar.generate(Ejercicio3PLE.class, "lsi_models/Ejercicio3.lsi", "gurobi_models/Ejercicio3-1.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio3-1.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		//
		ejercicio3_model();
	
	}
}
