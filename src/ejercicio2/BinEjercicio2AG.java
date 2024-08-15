package ejercicio2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import _datos.DatosEjercicio2;
import _soluciones.SolucionEjercicio2;
import us.lsi.ag.BinaryData;
import us.lsi.math.Math2;

public class BinEjercicio2AG<S> implements BinaryData<SolucionEjercicio2> {

	public BinEjercicio2AG(String fichero) {
		DatosEjercicio2.iniDatos(fichero);
	}
	
	@Override
	public Integer size() {
		
		return DatosEjercicio2.getnCursos();
	}

	public Long k(List<Integer> ls_chrm) {
		return Math2.pow(size(), 10);
	}
	
	@Override
	public Double fitnessFunction(List<Integer> value) {
	double goal =0, error=0;
	Set<Integer> tematicas = new HashSet<>(); //tematicas del curso i
	Set<Integer> centros = new HashSet<>();
	Integer m = DatosEjercicio2.getmTematicas();	//Número de temáticas que hay
	Integer nc = DatosEjercicio2.getmaxCentros();//Número de centros distintos
	
	for (int i = 0; i<value.size();i++) {
		if(value.get(i)>0) {
			goal += DatosEjercicio2.getPrecio(i);
			tematicas.addAll(DatosEjercicio2.getTematicasCruso(i));
			centros.add(DatosEjercicio2.getCentroCurso(i));
		}
	}
	//Penalizo si el número de tematicas que hay en el curso i es menor que todas las tematicas
	if (tematicas.size()<m) {
		error += m - tematicas.size();
	}
	//penalizo si el numero de centros que cojo es mayor que el máximo número de centros
	if(centros.size() > nc) {
		error += centros.size()-nc;
	}
	return -goal -k(value) *error;
	}

	@Override
	public SolucionEjercicio2 solucion(List<Integer> value) {
		return SolucionEjercicio2.of_Range(value);
	}
}
