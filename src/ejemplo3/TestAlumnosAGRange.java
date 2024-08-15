package ejemplo3;

import java.util.Locale;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

public class TestAlumnosAGRange {

	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		StoppingConditionFactory.NUM_GENERATIONS = 6000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
		
		//InRangeAlumnosAG p = new InRangeAlumnosAG("ficheros/Ejemplo3DatosEntrada1.txt");
	//	AlgoritmoAG<List<Integer>, SolucionAlumnos> ap = AlgoritmoAG.of(p);
//		ap.ejecuta();
		
		System.out.println("================================");
	//	System.out.println(ap.bestSolution());
		System.out.println("================================");
		
		
		

	}

}
