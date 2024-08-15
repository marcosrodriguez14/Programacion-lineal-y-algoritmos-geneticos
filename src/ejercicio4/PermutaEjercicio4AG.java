package ejercicio4;

import java.util.List;
import _datos.DatosEjercicio4;
import _soluciones.SolucionEjercicio4;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.math.Math2;

public class PermutaEjercicio4AG implements SeqNormalData<SolucionEjercicio4> {

	public PermutaEjercicio4AG(String fichero) {
		DatosEjercicio4.iniDatos(fichero);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	public Long k(List<Integer> ls_chrm) {
		return Math2.pow(size(), 10);
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		double goal = 0, error = 0, sumaKms = 0;
		// suma Beneficio - penalización por tiempo
		for (int i = 0; i < value.size(); i++) {
			if (i == 0) {
				// penalizamos a 1 cent por km
				// tenemos que restarle al beneficio la suma de las distancias
				if (DatosEjercicio4.existeArista(0, value.get(i))) {
					sumaKms += DatosEjercicio4.getPeso(0, value.get(i));
					goal += DatosEjercicio4.getBeneficio(value.get(i)) - sumaKms;
				}
				// Si no existe la arista penalizamos
				else {
					error++;
				}
				// si no estamos en el vertice inicial
			} else {
				if (DatosEjercicio4.existeArista(value.get(i - 1), value.get(i))) {
					sumaKms += DatosEjercicio4.getPeso(value.get(i - 1), value.get(i));
					goal += DatosEjercicio4.getBeneficio(value.get(i)) - sumaKms;
				} else {
					error++;
				}
			}
		}
		// La penalización es doble si el ultimo vertice no es el 0
		if (value.get(value.size() - 1) != 0) {
			if (error == 0) {
				error += 2;
			} else {
				error = error * 2;
			}
		}
		// sumamos todos los km
		sumaKms = 0.;
		for (int i = 0; i < value.size(); i++) {
			sumaKms += DatosEjercicio4.getBeneficio(value.get(i));
		}
		return goal - k(value) * error;
	}

	@Override
	public SolucionEjercicio4 solucion(List<Integer> value) {
		return SolucionEjercicio4.of_Range(value);
	}

	@Override
	public Integer itemsNumber() {
		return DatosEjercicio4.getnVertices();
	}
}
