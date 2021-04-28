package algestudiante.p7;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Estado Mejor Lista Asignación Agentes - Tareas Hereda de la clase Estado
 */
public class EstadoMejorLista extends Estado {
	
	// Datos comunes a todos los estados, por eso los declaramos "static"
	private static int n; // Tamaño del problema, num. agentes y num. tareas
	private static int[][] costes; // matriz costes
	private static boolean traza = true; // permite activar o desactivar traza

	// Estado
	private int[] solParcial; // solParcial[i]=k es al trabajador i se le asigna la tarea k
	private boolean[] tareaConTrabajador; // Marca las tareas ya asignadas

	/**
	 * Constructor para crear el estado inicial, establecemos los datos y
	 * condiciones iniciales del problema
	 */
	public EstadoMejorLista() {
		super();

		// Condiciones iniciales
		n = 4; // numero de agentes y tareas
		// matriz de costes
		costes = new int[n][n];
		costes[0][0] = 58;
		costes[0][1] = 67;
		costes[0][2] = 58;
		costes[0][3] = 65;
		costes[1][0] = 60;
		costes[1][1] = 68;
		costes[1][2] = 65;
		costes[1][3] = 55;
		costes[2][0] = 62;
		costes[2][1] = 70;
		costes[2][2] = 67;
		costes[2][3] = 56;
		costes[3][0] = 56;
		costes[3][1] = 68;
		costes[3][2] = 60;
		costes[3][3] = 54;

		if (RamificaYPoda.TRAZA)
			imprimirCostes();

		// Inicializa variables de cada estado
		solParcial = new int[n];
		for (int i = 0; i < solParcial.length; i++)
			solParcial[i] = -1;// sin asignaciones inicialmente

		tareaConTrabajador = new boolean[solParcial.length];
		for (int i = 0; i < n; i++)
			tareaConTrabajador[i] = false;
	}

	/**
	 * Constructor para crear un estado a partir del padre
	 */
	public EstadoMejorLista(EstadoMejorLista padre, int j) {
		super();
		// copiamos toda la informacion del padre
		solParcial = Arrays.copyOf(padre.solParcial, n);
		tareaConTrabajador = Arrays.copyOf(padre.tareaConTrabajador, n);
		profundidad = padre.profundidad;
		idPadre = padre.getId();

		// añade lo correspondiente a este hijo
		solParcial[profundidad] = j; // profundidad se corresponde con el primer agente sin asignar
		tareaConTrabajador[j] = true; // marcamos la tarea como asignada
		profundidad++;
		calcularValorHeuristico();

		// UNIFICA LOS DOS ÚLTIMOS NIVELES DEL ÁRBOL DE ESTADOS
		if (profundidad == n - 1) {
			int tareaPorAsignar = -1;
			// Busca �ltima tarea por asignar
			for (int k = 0; k < n; k++)
				if (!tareaConTrabajador[k])
					tareaPorAsignar = k;

			solParcial[profundidad] = tareaPorAsignar; // profundidad se corresponde con el primer agente sin asignar
			tareaConTrabajador[tareaPorAsignar] = true; // marcamos la tarea como asignada
			profundidad++;
			calcularValorHeuristico();
		}
	}

	/**
	 * Calcula el valor del heur�stico y lo guarda
	 */
	@Override
	public void calcularValorHeuristico() {
		valorHeuristico = 0;

		// Suma los costes de los agentes con tarea
		for (int i = 0; i < profundidad; i++)
			valorHeuristico += costes[i][solParcial[i]];

		// Suma los minimos de lo que resta de columna por cada tarea no asignada
		for (int j = 0; j < n; j++)
			if (!tareaConTrabajador[j])
				valorHeuristico += minimoColumna(profundidad, j);
	}

	/**
	 * Calcula el valor minimo por columnas
	 * @param profundidad para saber cuantas tareas están ya asignadas
	 * @param j ara saber que tarea se asigna en este paso
	 * @return valor mínimo por columnas
	 */
	private int minimoColumna(int profundidad, int j) {
		int minValorColumna = Integer.MAX_VALUE;
		for (int i = profundidad; i < n; i++)
			// calculamos el mínimo de columnas
			if (costes[i][j] < minValorColumna)
				minValorColumna = costes[i][j];
		return minValorColumna;
	}

	@Override
	public ArrayList<Estado> expandir() {
		ArrayList<Estado> listaHijos = new ArrayList<Estado>();

		// Recorre todas las tareas
		for (int j = 0; j < n; j++)
			if (!tareaConTrabajador[j]) { // Crea hijo sólo para tareas NO asignadas
				Estado estadoHijo = new EstadoMejorLista(this, j);
				listaHijos.add(estadoHijo);

				if (traza) { // imprimimos estado hijo generado
					System.out.println("Nivel: " + profundidad + " - Hijo: " + j);
					System.out.println(estadoHijo);
				}
			}
		return listaHijos;
	}

	@Override
	public boolean solucion() {
		if (profundidad == n)
			return true;
		else
			return false;
	}

	/*
	 * Inicialmente el valor de poda va a ser la menor de la suma de ambas
	 * diagonales
	 */
	@Override
	public int valorInicialPoda() {
		int diagonal1 = 0;
		for (int i = 0; i < n; i++)
			diagonal1 += costes[i][i];
		int diagonal2 = 0;
		for (int i = 0; i < n; i++)
			diagonal2 += costes[i][costes.length - 1 - i];

		if (RamificaYPoda.TRAZA) { // muestra el valor inicial
			System.out.println("Valor inicial de poda");
			System.out.println("Diagonal 1: " + diagonal1);
			System.out.println("Diagonal 2: " + diagonal2);
			System.out.println("Valor cota de poda: " + (diagonal1 <= diagonal2 ? diagonal1 : diagonal2) + "\n");
		}

		if (diagonal1 <= diagonal2)
			return diagonal1;
		else
			return diagonal2;
	}

	/**
	 * Imprime la matriz de costes
	 */
	public void imprimirCostes() {
		System.out.println("Número de agentes / tareas = " + n);
		System.out.println("Matriz de costes");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				System.out.printf("%5d", costes[i][j]);
			System.out.println();
		}
		System.out.println();

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("===============\n");
		for (int i = 0; i < solParcial.length; i++) {
			if (solParcial[i] != -1)
				sb.append("AL TRABAJADOR " + i + " SE LE ASIGNA LA TAREA " + solParcial[i] + "\n");
			else
				sb.append("LA TAREA " + i + " NO HA SIGO ASIGNADA TODAVIA\n");
		}
		sb.append("Valor heuristico = " + valorHeuristico + "\n");
		sb.append("===============\n");
		return sb.toString();
	}

}
