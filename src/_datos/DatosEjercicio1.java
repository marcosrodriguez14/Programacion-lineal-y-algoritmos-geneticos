package _datos;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosEjercicio1 {

	private static SortedMap<String, Integer> tipos;
	private static SortedMap<String, Tupla> variedades;

	public record Tupla(Double beneficio, SortedMap<String, Double> comp) {
		// Map<String,Double>comp

		public static Tupla of(Double beneficio, SortedMap<String, Double> comp) {
			return new Tupla(beneficio, comp);
		}

		public static Tupla parse(String linea) {

			SortedMap<String, Double> comp = new TreeMap<>();

			String[] str = linea.split(";");
			String[] str2 = str[0].split("beneficio=");
			String str3 = str[1].replace("comp=", "").trim();
			String str4 = str3.replace("(", "").replace(")", "");
			String[] str41 = str4.split(",");

			for (int i = 0; i < str41.length; i++) {

				String[] str5 = str41[i].split(":");
				String tipo = str5[0];
				Double porcentaje = Double.parseDouble(str5[1]);
				comp.put(tipo, porcentaje);
			}
			Double beneficio = Double.parseDouble(str2[1]);
			return Tupla.of(beneficio, comp);
		}
	}

	public static void iniDatos(String fichero) {
		tipos = new TreeMap<>();
		variedades = new TreeMap<>();
		Tupla tupla;
		List<String> lineas = Files2.linesFromFile(fichero);
		for (String linea : lineas) {
			if (linea.startsWith("C")) {
				String[] str = linea.split(":");
				String[] str2 = str[1].split("=");
				String[] str3 = str2[1].split(";");
				tipos.put(str[0].trim(), Integer.valueOf(str3[0].trim()));
			} else if (linea.startsWith("P")) {
				String[] str = linea.split(" ->");
				String variedad = str[0];
				tupla = Tupla.parse(linea);
				variedades.put(variedad, tupla);

			}
		}
//		System.out.println(tipos+"\n");
//		System.out.println(variedades);

	}
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

	public static Set<String> getcafesDeVariedad(Integer i) {
		String variedad = variedades.keySet().stream().toList().get(i);
		Set<String> cafes = variedades.get(variedad).comp().keySet();
		return cafes;
	}

	// Obtener el indice de un cafe dado su nombre
	public static Integer getCafe(String s) {
		List<String> cafes = tipos.keySet().stream().toList();
		return cafes.indexOf(s);

	}

	public static SortedMap<String, Integer> getTipos() {
		return tipos;
	}

	public static SortedMap<String, Tupla> getVariedades() {
		return variedades;
	}

	// Test de la lectura del fichero
	public static void main(String[] args) {
		iniDatos("ficheros_ejercicios/Ejercicio1DatosEntrada1.txt");

	}
}
