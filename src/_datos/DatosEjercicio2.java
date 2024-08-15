package _datos;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class DatosEjercicio2 {

	private static Integer maxCentros;
	private static List<Curso> cursos;

	public record Curso(Set<Integer> tematicas, Double coste, Integer centro) {

		public static Curso of(Set<Integer> tematicas, Double coste, Integer centro) {
			return new Curso(tematicas, coste, centro);
		}
	}

	public static void iniDatos(String fichero) {
		maxCentros = 0;
		cursos = List2.empty();

		List<String> lineas = Files2.linesFromFile(fichero);
		for (String linea : lineas) {
			if (linea.startsWith("Max_Centros =")) {
				String[] str = linea.split("=");
				maxCentros = Integer.valueOf(str[1].trim());
			} else if (linea.startsWith("{")) {
				Set<Integer> set = Set2.empty();
				String[] str = linea.split(":");
				String[] str2 = str[0].replace("{", "").replace("}", "").trim().split(",");
				for (String i : str2) {
					set.add(Integer.valueOf(i));
				}
				Double coste = Double.valueOf(str[1].trim());
				Integer centro = Integer.valueOf(str[2].trim());
				cursos.add(Curso.of(set, coste, centro));
			}
		}
	}

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

//	public static Integer getmTematicas() {
//		Set<Integer> tematicas = Set2.empty();
//		for (Curso c : cursos) {
//			for(Integer t : c.tematicas()) {
//				tematicas.add(t);
//			}
//		}
//		return tematicas.size();
//	}


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

	//en el curso i se trata la temática j
	//curso tine tematica j? si 1 no 0
	public static Integer tieneTematica(Integer i,Integer j) {
		return cursos.get(i).tematicas().contains(j)?1:0;
	}
	//el curso i se imparte en el centro k
	public static Integer imparteCentro(Integer i , Integer j) {
		return cursos.get(i).centro()==j?1:0;
	}
	
	//Obtiene las temáticas del curso i
	
	public static Set<Integer> getTematicasCruso(Integer i ) {
		return cursos.get(i).tematicas();
	}
	//Obtiene el centro del Curso i 
	
	public static Integer getCentroCurso(Integer i) {
		return cursos.get(i).centro();
	}
	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros_ejercicios/Ejercicio2DatosEntrada1.txt");
	}
}
