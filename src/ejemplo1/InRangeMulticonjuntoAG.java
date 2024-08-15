package ejemplo1;

import java.util.List;

import _datos.DatosMulticonjunto;
import _soluciones.SolucionMulticonjunto;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.math.Math2;

public class InRangeMulticonjuntoAG implements ValuesInRangeData<Integer, SolucionMulticonjunto> {

	public static InRangeMulticonjuntoAG create(String linea) {
		return new InRangeMulticonjuntoAG(linea);
	}

	public InRangeMulticonjuntoAG(String linea) {
		DatosMulticonjunto.iniDatos(linea);
	}

	@Override
	public Integer size() {
		return DatosMulticonjunto.getNumElementos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Integer max(Integer i) {
		return DatosMulticonjunto.getMultiplicidad(i) + 1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	public Long k(List<Integer> ls_chrm) {
		return Math2.pow(size(), 10);
	}

	@Override
	public Double fitnessFunction(List<Integer> ls_chrm) {
		double goal = 0, error = 0, sum = 0;

		for (int i = 0; i < size(); i++) {
			if (ls_chrm.get(i) > 0) {
				goal += ls_chrm.get(i);
				sum += ls_chrm.get(i) * DatosMulticonjunto.getElemento(i);
			}
		}
		
		error += Math.abs(sum - DatosMulticonjunto.getSuma()); 
		return -goal-k(ls_chrm)*error;
	}

	@Override
	public SolucionMulticonjunto solucion(List<Integer> value) {
		return SolucionMulticonjunto.of_Range(value);
	}

}
