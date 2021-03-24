package algestudiante.p4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Clase ColocacionSegmentos
 * Calcula el coste, en pufosos, de la seleccion de segmentos segun tres devoradores
 * Leyendo las longitudes de dichos segmentos de unos ficheros
 * @author UO263728
 */
public class ColocacionSegmentos {
	
	// longitudes de los segmentos leidos del fichero
	private static int[] longitudesSeg;
	
	/**
	 * Constructor
	 * Inicializa el array de longitudes de segmentos con el array pasado como parametro
	 * @param segAleatorios, array de enteros
	 */
	public ColocacionSegmentos(int[] segAleatorios) {
		longitudesSeg = segAleatorios;
	}

	/**
	 * O(n)
	 * Selecciona los segmentos segun el devorador usado
	 * También calcula y muestra el coste, en pufosos, del algoritmo
	 * @param n, tipo de devorador usado
	 */
	private static void seleccionSeg(int n) {
		int x, y = 0;
		double m, coste = 0;
		for (int i = 0; i < longitudesSeg.length; i++) {
			x = y;
			y += longitudesSeg[i];
			m = (x + y) / 2.0;
			System.out.println("S"+i+": ("+x+" a "+y+"), punto medio = "+m);
			coste += m;
		}
		System.out.println("Coste DEVORADOR"+n+" = "+ coste +" pufosos");
	}
	
	/**
	 * Se colocan en el mismo orden en que estén en el fichero
	 */
	public static void devorador1() {
		seleccionSeg(1);
	}
	
	/**
	 * Se colocan de mayor a menor longitud
	 */
	public static void devorador2() {
		Arrays.sort(longitudesSeg);	// O(nlog(n))
		ordenInverso();
		seleccionSeg(2);
	}

	/**
	 * Se colocan de menor a mayor longitud
	 */
	public static void devorador3() {
		Arrays.sort(longitudesSeg);
		seleccionSeg(3);
	}
	
	/**
	 * Ordena de mayor a menor un array ordenado
	 * O(n)
	 */
	private static void ordenInverso() {
		int n = longitudesSeg.length;
		int[] lon = new int[n];
		for (int i = 0; i < n; i++)
			lon[i] = longitudesSeg[n - i - 1];
		longitudesSeg = lon;
	}
	
	/**
	 * Lee las longitudes de los segmentos y muestra la solucion de cada devorador
	 * @param args, array de String
	 */
	public static void main(String[] args) {
		int numberFiles = 2;
		for (int i = 1; i <= numberFiles; i++) {
			String fileName = Paths.get("").toAbsolutePath().toString() 
					+ "/src/main/java/algestudiante/p4/datos/juego" + i + ".txt";
			System.out.println("Fichero: " + fileName);
			longitudesSeg = readFromFile(fileName);
			System.out.println("Devorador1:");
			devorador1();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Devorador2:");
			devorador2();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Devorador3:");
			devorador3();
			System.out.println("--------------------------------------------------------------------");
		}
	}
	
	/**
	 * Lee el contenido de un fichero y lo almacena en un array de enteros
	 * @param file, String, nombre y ruta del fichero
	 * @return elements, array de enteros
	 */
	public static int[] readFromFile(String file) {
		BufferedReader fich = null;
		String line;
		int[] elements = null;
		try {
			fich = new BufferedReader(new FileReader(file));
			line = fich.readLine();
			if (line != null) {	// numero de segmentos = tamaño del array
				elements = new int[Integer.parseInt(line)];
				line = fich.readLine();
			}	// añado las longitudes de los segmentos
			for (int i = 0; line != null; i++) {
				elements[i] = Integer.parseInt(line);
				line = fich.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No existe el fichero: " + file);
		} catch (IOException e) {
			System.out.println("Error leyendo el fichero: " + file);
		} finally {
			if (fich != null)
				try {
					fich.close();
				} catch (IOException e) {
					System.out.println("Error cerrando el fichero: " + file);
					e.printStackTrace();
				}
		}
		return elements;
	}

}
