package ejemplo2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import _datos.DatosSubconjunto;
import _soluciones.SolucionSubconjunto;
import us.lsi.ag.BinaryData;

public class BinSubconjuntoAG implements BinaryData<SolucionSubconjunto> {

	public BinSubconjuntoAG(String fichero) {
		DatosSubconjunto.iniDatos(fichero);
	}

	@Override
	public Integer size() {
		return DatosSubconjunto.getNumSubconjuntos();
	}

	@Override
	public Double fitnessFunction(List<Integer> ls_chrm) {
		double goal = 0, error = 0;

		Set<Integer> se = new HashSet<>();

		for (int i = 0; i < size(); i++) {
			if (ls_chrm.get(i) > 0) {
				goal += DatosSubconjunto.getPeso(i);
				se.addAll(DatosSubconjunto.getElementos(i));
			}
		}

		error += DatosSubconjunto.getNumElementos() - se.size();

		return -goal - 10000 * error * error;

	}

	@Override
	public SolucionSubconjunto solucion(List<Integer> value) {
		return SolucionSubconjunto.create(value);
	}

}
