package algestudiante.p7;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Estado implements Comparable<Estado> {
	protected int valorHeuristico; // valor del heur�stico calculado

	protected int profundidad; // n�mero de nodos desarrollados en una rama
	protected UUID id; // ID de este estado
	protected UUID idPadre; // ID del nodo padre para poder realizar trazabilidad

	public Estado() { // valores por defecto
		profundidad = 0;
		idPadre = null; // no tiene nodo padre el nodo ra�z
		id = UUID.randomUUID();
	}

	/**
	 * Devuelve valor del heur�stico correspondiente al estado Se utiliza
	 * internamente para comparar estados y ordenarlos en la cola de prioridad
	 * 
	 * @return Valor del heur�stico de ramificaci�n para el estado
	 */
	public int getHeuristico() {
		return valorHeuristico;
	}

	/**
	 * Calcula el valor del heur�stico y lo guarda
	 */
	public abstract void calcularValorHeuristico();

	/**
	 * Genera todos los hijos v�lidos del actual
	 * 
	 * @return ArrayList con todos los hijos
	 */
	public abstract ArrayList<Estado> expandir();

	/**
	 * Comprueba si el estado actual es solucion
	 * 
	 * @return true- si es soluci�n
	 */
	public abstract boolean solucion();

	/**
	 * Profundidad del �rbol a la que se encuentra el estado, esto se corresponde
	 * con el n�mero de elemento completado en la tupla de la soluci�n parcial
	 * 
	 * @return Profundidad del �rbol
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
	 * Para implementar la interfaz Comparable que utiliza el mont�culo de
	 * Ramificaci�n y poda
	 */
	@Override
	public int compareTo(Estado nodo2) {
		if (valorHeuristico > nodo2.valorHeuristico)
			return 1; // this tiene m�s prioridad
		else if (valorHeuristico == nodo2.valorHeuristico)
			return 0; // los dos tienen la misma prioridad
		else
			return -1; // this tienen menos prioridad
	}

	/**
	 * Si no se sobreescribe este m�todo no hay cota inicial
	 * 
	 * @return Valor MAX_VALUE, no hay cota inicial
	 */
	public int valorInicialPoda() {
		return Integer.MAX_VALUE; // por defecto
	}

}
