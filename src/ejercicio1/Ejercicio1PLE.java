package ejercicio1;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;

import _datos.DatosEjercicio1;
import _datos.DatosEjercicio1.Tupla;
import us.lsi.common.List2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {
	public static SortedMap<String, Integer> tipos;
	public static SortedMap<String, Tupla> variedades;

	// Datos de entrada

	// n entero tipos de cafe
	public static Integer getnTipos() {
		return tipos.keySet().size();
	}

	// m entero variedades de cafe
	public static Integer getmVariedades() {
		return variedades.keySet().size();
	}

	// cantidad disponible (kg) de un tipo de cafe j
	public static Integer getKilogramos(Integer i) {
		// Integer res = tipos.get(s);
		String cafe = tipos.keySet().stream().toList().get(i);
		return tipos.get(cafe);

	}

	// Beneficio de la venta de la variedad i
	public static Double getBeneficio(Integer i) {

		String nombreVariedad = variedades.keySet().stream().toList().get(i);
		return variedades.get(nombreVariedad).beneficio();
	}

	// Porcentaje de cafe de tipo j que se require para obtener un kg de la variedad
	// i
	public static Double getPorcentaje(Integer j, Integer i) {
		String cafe = tipos.keySet().stream().toList().get(j);
		// Integer intCafe = tipos.get(cafe);

		String variedad = variedades.keySet().stream().toList().get(i);

		// Tupla intVariedad = variedades.get(variedad);
		return variedades.get(variedad).comp().get(cafe) == null ? 0 : variedades.get(variedad).comp().get(cafe);
	}

	public static Double getPorcentaje(Integer j, String i) {
		String cafe = tipos.keySet().stream().toList().get(j);
		String variedad = i;
		return variedades.get(variedad).comp().get(cafe) == null ? 0 : variedades.get(variedad).comp().get(cafe);
	}

	// Obtener los kgMaximos para AG
	public static Integer getKgMaxVariedad(Integer i) {
		Set<String> cafes = tipos.keySet();
		List<Double> res = List2.empty();
		for (String j : cafes) {
			Double div = getKilogramos(getCafe(j)) / getPorcentaje(getCafe(j), i);
			res.add(div);
		}
		return Collections.min(res).intValue();

	}

	// Obtener el indice de un cafe dado su nombre
	public static Integer getCafe(String s) {
		List<String> cafes = tipos.keySet().stream().toList();
		return cafes.indexOf(s);

	}

	public static void ejercicio1_model() throws IOException {

		DatosEjercicio1.iniDatos("ficheros_ejercicios/Ejercicio1DatosEntrada1.txt");

		tipos = DatosEjercicio1.getTipos();
		variedades = DatosEjercicio1.getVariedades();

		// si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para
		// no sobreescribirlo
		AuxGrammar.generate(Ejercicio1PLE.class, "lsi_models/Ejercicio1.lsi", "gurobi_models/Ejercicio1-1.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-1.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		//
		ejercicio1_model();

	}

}
