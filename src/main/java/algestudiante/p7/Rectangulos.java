package algestudiante.p7;

import java.util.ArrayList;
import java.util.UUID;

class Rectangulos extends RamificaYPoda {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]); // tama�o tablero
		Rectangulos problema = new Rectangulos(n);
		problema.ramificaYPoda(problema.getNodoRaiz());

		problema.mostrarTrazaSolucion();
	}

	public Rectangulos(int n) {
		nodoRaiz = new NodoP3(n);
	}

}

/***************************************************/

class NodoP3 extends Estado
/* Concrecci�n del nodo en este Problema 3 */
{
	private int[][] tablero; // tablero
	private ArrayList<Pieza> piezas; // rect�ngulos a colocar

	public NodoP3(int n)
	/* Contruye nodo raiz (estado inicial) */
	{
		tablero = new int[n][n];

		// EJEMPLO de 4 PIEZAS (4 Rect�ngulos)
		piezas = new ArrayList<Pieza>();
		Pieza p1 = new Pieza(1, 2);
		Pieza p2 = new Pieza(2, 3);
		Pieza p3 = new Pieza(1, 3);
		Pieza p4 = new Pieza(2, 2);

		piezas.add(p1);
		piezas.add(p2);
		piezas.add(p3);
		piezas.add(p4);
	}

	public NodoP3(int[][] tablero, ArrayList<Pieza> piezas, int profundidad, UUID idPadre)
	/* construye todos los dem�s nodos no raiz */
	{
		this.tablero = tablero;
		this.piezas = piezas;
		this.profundidad = profundidad;
		this.idPadre = idPadre;
		calcularValorHeuristico();
	}

	private ArrayList<Object> colocarNuevaPieza() {
		ArrayList<Object> tableros = new ArrayList<Object>();

		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++) {
				int[][] nuevoTablero = intentarPosNuevaPieza(i, j, Orientacion.Horizontal);
				if (nuevoTablero != null)
					tableros.add(nuevoTablero);

				nuevoTablero = intentarPosNuevaPieza(i, j, Orientacion.Vertical);
				if (nuevoTablero != null)
					tableros.add(nuevoTablero);
			}
		return tableros;
	}

	private int[][] intentarPosNuevaPieza(int x, int y, Orientacion orientacion) {
		int[][] nuevoTablero = new int[tablero.length][tablero.length];
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++)
				nuevoTablero[i][j] = tablero[i][j]; // copia

		if (insertarNuevaPieza(x, y, orientacion, nuevoTablero, piezas.get(profundidad)))
			return nuevoTablero;
		else
			return null; // nodo no v�lido
	}

	private boolean insertarNuevaPieza(int x, int y, Orientacion orientacion, int[][] nuevoTablero, Pieza pieza) {
		boolean resultado = false;

		int limiteX = 0;
		int limiteY = 0;
		if (orientacion == Orientacion.Horizontal)
		// orientation por defecto
		{
			limiteX = pieza.x;
			limiteY = pieza.y;
		} else
		// orientacion vertical - giro 90�
		{
			limiteX = pieza.y;
			limiteY = pieza.x;
		}

		if ((x + limiteX > nuevoTablero.length) || (y + limiteY > nuevoTablero.length))
			// comprobar que cabe en el tablero
			return false;

		// comprobar si la pieza est� en otra pieza (se solapa)
		for (int i = x; i < x + limiteX; i++)
			for (int j = y; j < y + limiteY; j++)
				if (nuevoTablero[i][j] != 0)
					return false;

		// la pieza tiene que estar pegada a otra
		if (profundidad == 0)
			resultado = true; // el primero no tiene vecinos
		else {
			if (x + limiteX < nuevoTablero.length)
				// a la derecha
				for (int k = y; k < y + limiteY; k++)
					if (nuevoTablero[x + limiteX][k] != 0)
						resultado = true; // hay vecino

			if (x != 0)
				// a la izquierda
				for (int k = y; k < y + limiteY; k++)
					if (nuevoTablero[x - 1][k] != 0)
						resultado = true; // hay vecino
			if (y != 0)
				// hacia arriba
				for (int k = x; k < x + limiteX; k++)
					if (nuevoTablero[k][y - 1] != 0)
						resultado = true; // hay vecino

			if (y + limiteY < nuevoTablero.length)
				// hacia abajo
				for (int k = x; k < x + limiteX; k++)
					if (nuevoTablero[k][y + limiteY] != 0)
						resultado = true; // hay vecino
		}

		// insertamos la pieza en el tablero
		for (int i = x; i < x + limiteX; i++)
			for (int j = y; j < y + limiteY; j++)
				nuevoTablero[i][j] = profundidad + 1;

		return resultado;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("=============\n");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++)
				sb.append(tablero[i][j]);
			sb.append("\n");
		}
		sb.append("=============\n");
		return sb.toString();
	}

	@Override
	public void calcularValorHeuristico() {
		int primerValorX = -1;
		int primerValorY = -1;
		int ultimoValorX = -1;
		int ultimoValorY = -1;
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++) {
				if ((primerValorY == -1) && (tablero[i][j] != 0))
					primerValorY = i;
				if (tablero[i][j] != 0)
					ultimoValorY = i;
			}

		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++) {
				if ((primerValorX == -1) && (tablero[j][i] != 0))
					primerValorX = i;
				if (tablero[j][i] != 0)
					ultimoValorX = i;
			}

		int lado1 = Math.abs(ultimoValorX - primerValorX) + 1;
		int lado2 = Math.abs(ultimoValorY - primerValorY) + 1;
		valorHeuristico = lado1 * lado2; // area
		// return valorHeuristico;
	}

	@Override
	public ArrayList<Estado> expandir()
	/* genera hijos del nodo actual */
	{
		ArrayList<Estado> resultado = new ArrayList<Estado>();
		ArrayList<Object> tableros = new ArrayList<Object>();
		NodoP3 temp;
		int[][] tableroTest;

		tableros = colocarNuevaPieza(); // posibles colocaciones

		for (Object posible : tableros) {
			tableroTest = (int[][]) posible;
			temp = new NodoP3(tableroTest, piezas, profundidad + 1, getId());
			resultado.add(temp);
		}
		return resultado;
	}

	@Override
	public boolean solucion()
	/* cuando se ciolocan todas las piezas es una soluci�n */
	{
		if (profundidad == piezas.size())
			return true;
		else
			return false;
	}
} // NodoP3

/***************************************************/

class Pieza {
	int x;
	int y;

	public Pieza(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

/***************************************************/

enum Orientacion {
	Horizontal, Vertical
}

/***************************************************/