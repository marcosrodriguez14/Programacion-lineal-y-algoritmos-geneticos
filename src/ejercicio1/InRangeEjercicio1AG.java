package ejercicio1;

import java.util.ArrayList;
import java.util.List;

import _datos.DatosEjercicio1;
import _soluciones.SolucionEjercicio1;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.math.Math2;

public class InRangeEjercicio1AG implements ValuesInRangeData<Integer,SolucionEjercicio1> {

	public static InRangeEjercicio1AG create(String linea) {
		return new InRangeEjercicio1AG(linea);
	}

	public InRangeEjercicio1AG(String linea) {
		DatosEjercicio1.iniDatos(linea);
	}

	@Override
	public Integer size() {

		return DatosEjercicio1.getmVariedades();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Integer max(Integer i) {
		return DatosEjercicio1.getKgMaxVariedad(i) + 1;
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
		double goal = 0, error = 0;
		List<Double>kgUsados = new ArrayList<>();
	
		for(int i =0 ; i< DatosEjercicio1.getnTipos();i++) {
			kgUsados.add(0.);
		}
		for (int i = 0; i < size(); i++) {
			if (value.get(i) > 0) {
				goal += value.get(i) * DatosEjercicio1.getBeneficio(i);
			}
			for (int j = 0; j < DatosEjercicio1.getnTipos(); j++) {
				kgUsados.set(j,(value.get(i)*DatosEjercicio1.getPorcentaje(j, i)+kgUsados.get(j))); 
					if (DatosEjercicio1.getKilogramos(j) < kgUsados.get(j)) {
						error +=  kgUsados.get(j) - DatosEjercicio1.getKilogramos(j);
					}
			}
		}
		return goal - k(value) * error;
	}

	@Override
	public SolucionEjercicio1 solucion(List<Integer> value) {
		return SolucionEjercicio1.of_Range(value);
		//return value;
	}
}
