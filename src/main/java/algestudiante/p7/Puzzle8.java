package algestudiante.p7;

import java.util.ArrayList;
import java.util.UUID;

class Puzzle8 extends RamificaYPoda {
	
	static int inicio; // dos ejemplos de tablero inicial
	static int metodo; // dos heuristicos implementados

	/*
	 * Los argumentos de ejecuci�n son : inicio metodo primer tablero por
	 * PosicionIncorrecta : 1 1 primer tablero por Manhattan : 1 1 segundo tablero
	 * por PosicionIncorrecta: 2 1 segundo tablero por Manhattan : 2 2
	 */
	public static void main(String[] arg) {
		inicio = Integer.parseInt(arg[0]);
		metodo = Integer.parseInt(arg[1]);

		TipoHeuristico heuristico;
		if (metodo == 1)
			heuristico = TipoHeuristico.PosicionIncorrecta;
		else
			heuristico = TipoHeuristico.Manhattan;

		Puzzle8 problema = new Puzzle8(inicio, heuristico);

		problema.ramificaYPoda(problema.getNodoRaiz());

		problema.mostrarTrazaSolucion();
	}

	public Puzzle8(int inicio, TipoHeuristico tipoHeuristico) {
		nodoRaiz = new NodoP2(inicio, tipoHeuristico);
	}
}

class NodoP2 extends Estado {
	/* Concretar la clase Nodo en el Problema 2 (Puzzle) */
	
	private int[] tablero = new int[9];
	private TipoHeuristico tipoH;

	public NodoP2(int inicio, TipoHeuristico tipoH) {
		this.tipoH = tipoH;
		if (inicio == 1) {
			// TABLERO 1: TIENE SOLUCION CON 46 PASOS con Manhattan
			// y 54 PASOS con PosicionIncorrecta
			tablero[0] = 2;
			tablero[1] = 3;
			tablero[2] = 6;
			tablero[3] = 1;
			tablero[4] = 5;
			tablero[5] = 4;
			tablero[6] = 9;
			tablero[7] = 7;
			tablero[8] = 8;
		} else {
			// TABLERO 2 (NO TIENE SOLUCION)
			tablero[0] = 9;
			tablero[1] = 3;
			tablero[2] = 7;
			tablero[3] = 6;
			tablero[4] = 5;
			tablero[5] = 4;
			tablero[6] = 8;
			tablero[7] = 2;
			tablero[8] = 1;
		}

		if (podar())
			System.out.println("NO HAY SOLUCION");
		else
			System.out.println("HAY SOLUCION");
	}

	public NodoP2(int[] tablero, TipoHeuristico tipoH, int profundidad, UUID idPadre) {
		this.tablero = tablero;
		this.tipoH = tipoH;
		this.profundidad = profundidad;
		this.idPadre = idPadre;
		calcularValorHeuristico();
	}

	private int[] arriba()
	/* Movimiento arriba */
	{
		int celdaTemporal;
		int[] temp = tablero.clone();
		int celdaVacia = 0;
		for (int i = 0; i < 9; i++)
			if (temp[i] == 9)
				celdaVacia = i;

		if (celdaVacia < 3)
			// movimiento imposible
			return temp;
		celdaTemporal = temp[celdaVacia];
		temp[celdaVacia] = temp[celdaVacia - 3];
		temp[celdaVacia - 3] = celdaTemporal;
		return temp;
	}

	private int[] abajo()
	/* Movimiento abajo */
	{
		int celdaTemporal;
		int[] temp = tablero.clone();
		int celdaVacia = 0;
		for (int i = 0; i < 9; i++)
			if (temp[i] == 9)
				celdaVacia = i;

		if (celdaVacia > 5)
			// movimiento imposible
			return temp;
		celdaTemporal = temp[celdaVacia];
		temp[celdaVacia] = temp[celdaVacia + 3];
		temp[celdaVacia + 3] = celdaTemporal;
		return temp;
	}

	private int[] izquierda()
	/* Movimiento izquierda */
	{
		int celdaTemporal;
		int[] temp = tablero.clone();
		int celdaVacia = 0;
		for (int i = 0; i < 9; i++)
			if (temp[i] == 9)
				celdaVacia = i;

		if (celdaVacia % 3 == 0)
			// movimiento imposible
			return temp;
		celdaTemporal = temp[celdaVacia];
		temp[celdaVacia] = temp[celdaVacia - 1];
		temp[celdaVacia - 1] = celdaTemporal;
		return temp;
	}

	private int[] derecha()
	/* Movimiento derecha */
	{
		int celdaTemporal;
		int[] temp = tablero.clone();
		int celdaVacia = 0;
		for (int i = 0; i < 9; i++)
			if (temp[i] == 9)
				celdaVacia = i;

		if (celdaVacia % 3 == 2)
			// movimiento imposible
			return temp;
		celdaTemporal = temp[celdaVacia];
		temp[celdaVacia] = temp[celdaVacia + 1];
		temp[celdaVacia + 1] = celdaTemporal;
		return temp;
	}

	private int getValorHeuristicoPosicionIncorrecta()
	/* Heuristico 1: n�mero de fichas en posici�n incorrecta */
	{
		int incorrecto = 0;
		for (int i = 0; i < 9; i++)
			if (tablero[i] != (i + 1))
				incorrecto++;
		return incorrecto;
	}

	private int getValorHeuristicoManhattan()
	/*
	 * Heuristico 2: distancia Manhattan: suma para todas las fichas de las
	 * distancias entre la posici�n actual y la posici�n final
	 */
	{
		int manhattan = 0;
		int x1, x2, y1, y2;
		for (int i = 0; i < 9; i++) {
			x1 = tablero[i] % 3; // actual coordenada horizontal
			x2 = (i + 1) % 3; // final coordenada horizontal
			y1 = tablero[i] / 3; // actual coordenada vertical
			y2 = (i + 1) / 3; // final coordenada vertical
			manhattan += Math.abs(x1 - x2) + Math.abs(y1 - y2);
		}
		return manhattan;
	}

	private boolean podar()
	/*
	 * Si no se puede alcanzar la soluci�n devuelve true En caso contrario devuelve
	 * false
	 */
	{
		boolean resultado = false;
		int x = 0;
		int sum = 0;
		for (int i = 1; i <= 9; i++)
			sum = sum + menor(i);
		if ((posicion(9) % 2) == 0)
			x = 1;
		if ((sum + x) % 2 == 1) {
			System.out.println("PODA POR SUMA IMPAR=" + (sum + x));
			resultado = true;
		}
		return resultado;
	}

	private int menor(int i)
	/*
	 * n�mero of fichas que cumplen j<i and position(j)>position(i)
	 */
	{
		int j = 0;
		for (int k = posicion(i); k < 9; k++)
			if (tablero[k] < i)
				j++;
		return j;
	}

	private int posicion(int i)
	/* posici�n en el estado inicial de ficha n�mero i */

	{
		int posicion = -1;
		for (int k = 0; k < 9; k++)
			if (tablero[k] == i)
				posicion = k + 1; // we start in 0
		return posicion;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("===============\n");
		for (int i = 1; i <= 9; i++) {
			if ((i % 3) == 1)
				sb.append(" | "); // first |
			if (tablero[i - 1] == 9)
				sb.append("  | ");
			else
				sb.append(tablero[i - 1] + " | ");
			if ((i % 3) == 0) // cada 3 n�meros
				sb.append("\n===============\n");
		}
		return sb.toString();
	}

	@Override
	public void calcularValorHeuristico() {
		if (podar())
			valorHeuristico = Integer.MAX_VALUE;
		else {
			switch (tipoH) {
			case Manhattan:
				valorHeuristico = getValorHeuristicoManhattan();
				break;
			case PosicionIncorrecta:
				valorHeuristico = getValorHeuristicoPosicionIncorrecta();
				break;
			}
		}
		// return valorHeuristico;
	}

	@Override
	public ArrayList<Estado> expandir()
	/*
	 * genera los hijos nodo actual. Los hijos apuntan a su padre a trav�s idPadre
	 */
	{
		ArrayList<Estado> resultado = new ArrayList<Estado>();
		int[] tableroTest;
		NodoP2 temp;

		// Ensayar los 4 movimientos
		tableroTest = arriba(); // ARRIBA
		temp = new NodoP2(tableroTest, tipoH, profundidad + 1, getId());
		resultado.add(temp);

		tableroTest = abajo(); // ABAJO
		temp = new NodoP2(tableroTest, tipoH, profundidad + 1, getId());
		resultado.add(temp);

		tableroTest = izquierda(); // IZQUIERDA
		temp = new NodoP2(tableroTest, tipoH, profundidad + 1, getId());
		resultado.add(temp);

		tableroTest = derecha(); // DERECHA
		temp = new NodoP2(tableroTest, tipoH, profundidad + 1, getId());
		resultado.add(temp);
		return resultado;
	}

	@Override
	public boolean solucion() {
		if (valorHeuristico == 0)
			return true;
		else
			return false;
	}

} // NodoP2

/***************************************************/

enum TipoHeuristico {
	Manhattan, PosicionIncorrecta
}
