package algestudiante.p0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase MatrizOperaciones
 * @author UO263728
 */
public class MatrizOperaciones {
	
	private int[][] matriz;	// matriz de tipo int
	
	/**
	 * Constructor
	 * Inicializa la matriz cuadrada con la pasada por parametro
	 * @param m, tipo int[][]
	 */
	public MatrizOperaciones(int[][] m) {
		matriz = m;
	}
	
	/**
	 * Constructor
	 * Inicializa la matriz cuadrada con el tamaño pasado por parametro
	 * @param tam, tipo int
	 */
	public MatrizOperaciones(int tam) {
		matriz = new int[tam][tam];
	}
	
	/**
	 * Constructor
	 * Crea una matriz de tamaño nxn
	 * La rellena con valores aleatorios, estos deben de ser parametrizables
	 * entre un máximo (max) y un mínimo (min).
	 * @param n, tipo int
	 * @param min, tipo int
	 * @param max, tipo int
	 */
	public MatrizOperaciones(int n, int min, int max) {
		matriz = new int[n][n];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++)
				matriz[i][j] = valorAleatorio(min, max);
		}
	}
	
	/**
	 * Calcula un valor aleatorio para rellenar la matriz
	 * Dicho valor aleatorio debe estar en el intervalo [min, max]
	 * @param min, tipo int
	 * @param max, tipo int
	 * @return random, tipo int
	 */
	private int valorAleatorio(int min, int max) {
		int random;
		do {
			random = (int) (Math.random()*max+1);
		} while (min > random || random > max);
		return random;
	}
	
	/**
	 * Constructor
	 * Crea una matriz a partir de los datos del fichero
	 * El formato del fichero será:
	 * -Primera línea, un entero con tamaño de la matriz (n).
	 * -Resto de las líneas, la fila correspondiente en la que cada valor estará separado por un tabulador del siguiente.
	 * @param nomFich, tipo String
	 */
	public MatrizOperaciones(String nomFich) {
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nomFich));
			String linea = fichero.readLine();
		    int n = Integer.parseInt(linea);
		    matriz = new int[n][n];
		    int cont = 0;
			while (fichero.ready()) {
			    linea = fichero.readLine();
			    String[] valores = linea.split("\t");
			    for (int i = 0; i < valores.length; i++)
			    	matriz[cont][i] = Integer.parseInt(valores[i]);
			    cont++;
			}
			fichero.close();
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("Archivo no encontrado.");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}
	
	/**
	 * Devuelve el tamaño de la matriz
	 * @return tipo int
	 */
	public int getTam() {
		return matriz.length;
	}
	
	/**
	 * Muestra el contenido de la matriz por pantalla
	 */
	public void escribir() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++)
				System.out.print(matriz[i][j] + " ");
			System.out.println();
		}
	}
	
	/**
	 * Calcula de forma iterativa la suma de los valores de la diagonal principal
	 * Forma 1: recorrer toda la matriz, pero sólo sumando los elementos de la diagonal
	 * @return suma, tipo int
	 */
	public int sumarDiagonal1() {
		int suma = 0;
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz[i].length; j++) {
				if (i == j)
					suma += this.matriz[i][j];
			}
		}
		return suma;
	}
	
	/**
	 * Calcula de forma iterativa la suma de los valores de la diagonal principal
	 * Forma 2: recorrer los elementos de la diagonal sumándolos
	 * @return suma, tipo int
	 */
	public int sumarDiagonal2() {
		int suma = 0;
		for (int i = 0; i < this.matriz.length; i++)
			suma += this.matriz[i][i];
		return suma;
	}
	
	/**
	 * En una matriz cuyos valores var�an entre 1 y 4 vamos a trazar un �camino�
	 * partiendo de la posici�n (i,j) que pasamos como par�metro y utilizando los
	 * valores de la matriz como c�digos de direcci�n:
	 * 		1 - arriba, 2 - derecha, 3 - abajo, 4 � izquierda.
	 * Vamos a utilizar para marcar el camino el c�digo -1.
	 * El proceso finalizar� cuando el camino salga de los l�mites de la matriz
	 * o bien alcance una casilla ya recorrida.
	 * @param i, tipo int
	 * @param j, tipo int
	 */
	public void recorrerCamino(int i, int j) {
		do {
			int value = matriz[i][j];
			matriz[i][j] = -1;
			if (value == 1) i--;
			else if (value == 2) j++;
			else if (value == 3) i++;
			else j--;
			if (0 > i || i >= getTam() || 0 > j || j >= getTam())
				return;
		} while (matriz[i][j] != -1);
	}

	/**
	 * Metodo principal de la clase
	 * @param args, array de tipo String
	 */
	public static void main(String[] args) {
//		int[][] matriz = {{2,2,1,2,4},{3,4,3,3,2},{1,4,4,1,1},{3,1,1,2,2},{1,2,3,3,2}};
//		MatrizOperaciones m = new MatrizOperaciones(matriz);
//		MatrizOperaciones m = new MatrizOperaciones(4);
		MatrizOperaciones m = new MatrizOperaciones(5, 1, 4);
//		MatrizOperaciones m = new MatrizOperaciones("files/matrizPrueba.txt");
		System.out.printf("Tamaño de la matriz: %dx%d\n", m.getTam(), m.getTam());
		System.out.println("Matriz:");
		m.escribir();	// muestro la matriz por consola
		System.out.printf("Sumar diagonal (forma1): %d", m.sumarDiagonal1());
		System.out.printf("\nSumar diagonal (forma2): %d", m.sumarDiagonal2());
		System.out.println("\nTras recorrer camino de la matriz, desde posicion (2,4):");
		m.recorrerCamino(2, 4);
		m.escribir();
	}
	
}
