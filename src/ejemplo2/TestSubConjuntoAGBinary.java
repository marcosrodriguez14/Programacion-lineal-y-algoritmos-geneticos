package ejemplo2;

import java.util.List;
import java.util.Locale;

import _soluciones.SolucionSubconjunto;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

public class TestSubConjuntoAGBinary {

	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
		
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
		
		BinSubconjuntoAG p = new BinSubconjuntoAG("ficheros/Ejemplo2DatosEntrada1.txt");
		AlgoritmoAG<List<Integer>, SolucionSubconjunto> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
		

	}

}
