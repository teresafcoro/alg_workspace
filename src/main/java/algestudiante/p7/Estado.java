package algestudiante.p7;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Estado implements Comparable<Estado> {
	
	protected int valorHeuristico; // valor del heurístico calculado

	protected int profundidad; // número de nodos desarrollados en una rama
	protected UUID id; // ID de este estado
	protected UUID idPadre; // ID del nodo padre para poder realizar trazabilidad

	public Estado() { // valores por defecto
		profundidad = 0;
		idPadre = null; // no tiene nodo padre el nodo raíz
		id = UUID.randomUUID();
	}

	/**
	 * Devuelve valor del heurístico correspondiente al estado Se utiliza
	 * internamente para comparar estados y ordenarlos en la cola de prioridad
	 * @return Valor del heurístico de ramificación para el estado
	 */
	public int getHeuristico() {
		return valorHeuristico;
	}

	/**
	 * Calcula el valor del heurístico y lo guarda
	 */
	public abstract void calcularValorHeuristico();

	/**
	 * Genera todos los hijos válidos del actual
	 * @return ArrayList con todos los hijos
	 */
	public abstract ArrayList<Estado> expandir();

	/**
	 * Comprueba si el estado actual es solucion
	 * @return true- si es solución
	 */
	public abstract boolean solucion();

	/**
	 * Profundidad del árbol a la que se encuentra el estado, esto se corresponde
	 * con el número de elemento completado en la tupla de la solución parcial
	 * @return Profundidad del árbol
	 */
	public int getProfundidad() {
		return profundidad;
	}

	public UUID getIdPadre() {
		return idPadre;
	}

	public UUID getId() {
		return id;
	}

	public boolean equals(Estado n) {
		return (n.toString().equals(toString()));
	}

	/**
	 * Para implementar la interfaz Comparable que utiliza el montículo de
	 * Ramificación y poda
	 */
	@Override
	public int compareTo(Estado nodo2) {
		if (valorHeuristico > nodo2.valorHeuristico)
			return 1; // this tiene más prioridad
		else if (valorHeuristico == nodo2.valorHeuristico)
			return 0; // los dos tienen la misma prioridad
		else
			return -1; // this tienen menos prioridad
	}

	/**
	 * Si no se sobreescribe este método no hay cota inicial
	 * 
	 * @return Valor MAX_VALUE, no hay cota inicial
	 */
	public int valorInicialPoda() {
		return Integer.MAX_VALUE; // por defecto
	}

}
