package ejercicio2;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import _datos.DatosEjercicio2;
import _datos.DatosEjercicio2.Curso;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {
	private static Integer maxCentros;
	private static List<Curso> cursos;

	//Datos de entrada
	
		public static Integer getmaxCentros() {
			return maxCentros;
		}

		public static List<Curso> getCursos() {
			return cursos;
		}

		public static Integer getnCursos() {
			return cursos.size();
		}

		public static Integer getmTematicas() {
			return cursos.stream()
					.flatMap(c -> c.tematicas().stream())
					.collect(Collectors.toSet())
					.size();
		}

//		public static Integer getmTematicas() {
//			Set<Integer> tematicas = Set2.empty();
//			for (Curso c : cursos) {
//				for(Integer t : c.tematicas()) {
//					tematicas.add(t);
//				}
//			}
//			return tematicas.size();
//		}


		public static Set<Integer> getCentros() {
			return cursos.stream()
					.map(c -> c.centro())
					.collect(Collectors.toSet());
		}

		public static Integer getnCentros() {
			return getCentros().size();
		}
		
		public static Double getPrecio(Integer i ) {
			return cursos.get(i).coste();
		}
		
		
		
		public static Set<Integer> getTematicas (){
			return cursos.stream()
					.flatMap(c -> c.tematicas().stream())
					.collect(Collectors.toSet());
					
			
		}

		//en el curso i se trata la temática j
		//curso tine tematica j? si 1 no 0
		public static Integer tieneTematica(Integer i,Integer j) {
			return cursos.get(i).tematicas().contains(j)?1:0;
		}
		//el curso i se imparte en el centro k
		public static Integer imparteCentro(Integer i , Integer j) {
			return cursos.get(i).centro()==j?1:0;
		}

	public static void ejercicio2_model() throws IOException {

		DatosEjercicio2.iniDatos("ficheros_ejercicios/Ejercicio2DatosEntrada1.txt");

		maxCentros = DatosEjercicio2.getmaxCentros();
		cursos = DatosEjercicio2.getCursos();

		// si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para
		// no sobreescribirlo
		AuxGrammar.generate(Ejercicio2PLE.class, "lsi_models/Ejercicio2.lsi", "gurobi_models/Ejercicio2-1.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio2-1.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		//
		ejercicio2_model();
		System.out.println(tieneTematica(0, 0));
	}
}
