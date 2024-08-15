package _soluciones;

import java.util.ArrayList;
import java.util.List;

import _datos.DatosEjercicio4;
import utils.Cliente;

public class SolucionEjercicio4 {

	public static SolucionEjercicio4 of_Range(List<Integer> value) {
		return new SolucionEjercicio4(value);
	}

	private Double kms;
	private Double beneficio;
	private List<Cliente> clientes;

	private SolucionEjercicio4() {
		kms = 0.;
		beneficio = 0.;
		clientes = new ArrayList<>();
		//Añadimos el vertice 0 
		clientes.add(DatosEjercicio4.getCliente(0));
	}

	private SolucionEjercicio4(List<Integer> value) {
		kms = 0.;
		beneficio = 0.;
		clientes = new ArrayList<>();
		//Añadimos el vertice 0
		clientes.add(DatosEjercicio4.getCliente(0));
		for (int i = 0; i < value.size(); i++) {
			clientes.add(DatosEjercicio4.getCliente(value.get(i)));
			//si el vertice es 0 y existe una arista del 0 al vertice sumamos la distancia y la restamos al beneficio
			if (i == 0) {
				if (DatosEjercicio4.existeArista(0, value.get(i))) {
					kms += DatosEjercicio4.getPeso(0, value.get(i));
					beneficio += DatosEjercicio4.getBeneficio(value.get(i)) - kms;
				}
			} else {
				if (DatosEjercicio4.existeArista(value.get(i - 1), value.get(i))) {
					kms += DatosEjercicio4.getPeso(value.get(i - 1), value.get(i));
					beneficio += DatosEjercicio4.getBeneficio(value.get(i)) - kms;
				}
			}
		}
	}

	public static SolucionEjercicio4 empty() {
		return new SolucionEjercicio4();
	}

	public String toString() {
		List<Integer> vertices = clientes.stream().map(c -> c.id()).toList();
		return "Camino desde 0 hasta 0:\n" + vertices + "\nKms: " + kms + "\nBeneficio: " + beneficio;
	}

}
