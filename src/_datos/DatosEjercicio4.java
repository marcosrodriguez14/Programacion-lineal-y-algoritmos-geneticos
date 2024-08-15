package _datos;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import utils.Cliente;
import utils.Conexion;

public class DatosEjercicio4 {

	@SuppressWarnings("exports")
	public static Graph<Cliente, Conexion> grafo;

	public static void iniDatos(String fichero) {
		grafo = GraphsReader.newGraph(fichero, Cliente::ofFormat, Conexion::ofFormat, Graphs2::simpleWeightedGraph);
	}

	public static Integer getnVertices() {
		return grafo.vertexSet().size();
	}

	@SuppressWarnings("exports")
	public static Cliente getCliente(Integer i) {
		Cliente cliente = null;
		List<Cliente> vertices = new ArrayList<>(grafo.vertexSet());
		for (int j = 0; j < vertices.size(); j++) {
			if (vertices.get(j).id() == i) {
				cliente = vertices.get(j);
			}
		}
		return cliente;
	}
	public static Double getBeneficio(Integer i) {
		return getCliente(i).beneficio();
	}

	public static Boolean existeArista(Integer i, Integer j) {
		return grafo.containsEdge(getCliente(i), getCliente(j));
	}

	public static Double getPeso(Integer i, Integer j) {
		return grafo.getEdge(getCliente(i), getCliente(j)).distancia();
	}
//Numero de vertices:grafo.vertexSet().size()
//Vertices: " + grafo.vertexSet()
//Numero de aristas: grafo.edgeSet().size() 
//Aristas: " + grafo.edgeSet());

	public static void main(String[] args) {
		iniDatos("ficheros_Ejercicios/Ejercicio4DatosEntrada1.txt");
		
	}
}