package ejercicio3;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import _datos.DatosEjercicio3;
import _datos.DatosEjercicio3.Investigador;
import _soluciones.SolucionEjercicio3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.math.Math2;

public class InRangeEjercicio3AG implements ValuesInRangeData<Integer, SolucionEjercicio3> {

	public static InRangeEjercicio3AG create(String linea) {
		return new InRangeEjercicio3AG(linea);
	}

	public InRangeEjercicio3AG(String linea) {
		DatosEjercicio3.iniDatos(linea);
	}

	@Override
	public Integer size() {

		return DatosEjercicio3.getmTrabajos() * DatosEjercicio3.getnInvestigadores();
	}

	@Override
	public ChromosomeType type() {

		return ChromosomeType.Range;
	}

	@Override
	public Integer max(Integer i) {
		List<Investigador> Investigadores = DatosEjercicio3.getInvestigadores();
		List<Integer> ls = Investigadores.stream().map(inv -> inv.capacidad()).collect(Collectors.toList());
		return Collections.max(ls) + 1;
	}

	@Override
	public Integer min(Integer i) {

		return 0;
	}

	public Long k(List<Integer> ls_chrm) {
		return Math2.pow(size(), 10);
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double goal = 0., error = 0.;
		Integer nInvestigadores = DatosEjercicio3.getnInvestigadores();
		Integer nTrabajos = DatosEjercicio3.getmTrabajos();
		Integer nEspecialidades = DatosEjercicio3.geteEspecialidades();

		for (int j = 0; j < nTrabajos; j++) {
			Boolean cumpleDias = true;
			Integer IndiceIni = nInvestigadores * j;
			Integer IndiceFinal = IndiceIni + nInvestigadores;
			// indice final = inicial + nIvestigadores
			// Obtenemos sublistas de trabajos para recorrerlas posteriormente
			List<Integer> trabajos = value.subList(IndiceIni, IndiceFinal);
			for (int k = 0; k < nEspecialidades; k++) {
				Integer suma = 0;
				for (int i = 0; i < nInvestigadores; i++) {
					// si el investigador i tiene la especialidad --> valor chr
					// si no la tiene suma =0
					suma += trabajos.get(i) * DatosEjercicio3.tieneEspecialidad(i, k);
					// System.out.println("investigador "+ i+" con especialidad
					// "+DatosEjercicio3.getEspecialidad(i)+"\nEspecialidad del bucle "+k+"\ntrabajo
					// "+j+ "\ndias "+trabajos.get(i)*DatosEjercicio3.getTieneEspecialidad(i,
					// k)+"\n" );
				}

				// Restricción: si el valor que nos devuelve la suma no es igual al de dias
				// necesarios, ponemos cumpleDias a false y penalizamos
				if (suma != DatosEjercicio3.diasNecesarios(j, k)) {
					cumpleDias = false;
				}
				if (suma > DatosEjercicio3.diasNecesarios(j, k)) {
					error += suma - DatosEjercicio3.diasNecesarios(j, k);
				}
			}

			if (cumpleDias) {
				// Si cumpleDias es true significa que se han cumplido todas las sumas por lo
				// tanto el trabajo
				goal += DatosEjercicio3.getCalidadTrabajo(j);
			}

			// Recorremos cada sublista de trabajo, accediendo a cada investigador.

			for (int i = 0; i < nInvestigadores; i++) {
				Integer dias = 0;
				for (int k = i; k < value.size(); k += nInvestigadores) {
					dias += value.get(k);
				}
				// Penalizamos si los dias del chr se pasan de la capacidad de cada investigador
				if (dias > DatosEjercicio3.getCapacidad(i)) {
					error += dias - DatosEjercicio3.getCapacidad(i);
				}
			}
		}
		return goal - k(value) * error;
	}

	@Override
	public SolucionEjercicio3 solucion(List<Integer> value) {
		return SolucionEjercicio3.of_Range(value);
	}

}
