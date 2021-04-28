package algestudiante.p7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase MejorLista
 * Crea dos bloques de canciones a partir de una lista, teniendo estos en total la maxima puntuacion
 * NO se permite fragmentacion de canciones
 * @author UO263728
 */
public class MejorListaRP {
	
	// canciones de la lista cargada
	private static Cancion[] listaCanciones;
	
	private static List<Cancion> bloque1;
	private static List<Cancion> bloque2;
	private static String fileName;
	private static int segundos;
	private static int duracion;
	private static int puntuacion;
	private static int duracionMejor;
	private static int puntuacionMejor;
	private static int puntuacionTotal;
	private static boolean[] usadas;
	private static boolean bloqueCompleto;
	
	/**
	 * Constructor
	 * @param name, nombre del fichero a leer para obtener la lista de canciones
	 * @param mins, minutos de canciones que puede contener el bloque
	 */
	public MejorListaRP(String name, int mins) {
		bloque1 = new ArrayList<Cancion>();
		bloque2 = new ArrayList<Cancion>();
		fileName = Paths.get("").toAbsolutePath().toString() 
				+ "/src/main/java/algestudiante/p6/datos/" + name + ".txt";
		segundos = mins*60;
	}
	
	/**
	 * Constructor
	 * Inicializa el array de canciones con el array pasado como parametro
	 * @param cancionesAleatorias, lista de objetos Cancion
	 * @param mins, minutos de canciones que puede contener el bloque
	 */
	public MejorListaRP(Cancion[] cancionesAleatorias, int mins) {
		listaCanciones = cancionesAleatorias;
		usadas = new boolean[listaCanciones.length];
		segundos = mins*60;
	}
	
	/**
	 * Metodo recursivo que crea una lista de canciones en un bloque determinado
	 * Siendo estas las que proporcionan una puntuacion maxima
	 * @param bloque de canciones que se quiere crear
	 */
	public void Backtracking(List<Cancion> bloque) {
		if (bloqueCompleto) {
			puntuacionMejor = puntuacion;
			duracionMejor = duracion;
		}
		else {
			int mejorPuntuacion = 0, cancionSeleccionada = -1;
			for (int i = 0; i < listaCanciones.length; i++) {
				if (!usadas[i] && listaCanciones[i].getPuntuacion() > mejorPuntuacion &&
						duracion+listaCanciones[i].getDuracion() <= segundos) {
					mejorPuntuacion = listaCanciones[i].getPuntuacion();
					cancionSeleccionada = i;
				}
			}
			if (cancionSeleccionada == -1) {
				bloqueCompleto = true;
				Backtracking(bloque);
			}
			else {
				bloque.add(listaCanciones[cancionSeleccionada]);
				usadas[cancionSeleccionada] = true;
				puntuacion += listaCanciones[cancionSeleccionada].getPuntuacion();
				duracion += listaCanciones[cancionSeleccionada].getDuracion();
				Backtracking(bloque);
				bloqueCompleto = false;
//				usadas[cancionSeleccionada] = false;
				puntuacion -= listaCanciones[cancionSeleccionada].getPuntuacion();
				duracion -= listaCanciones[cancionSeleccionada].getDuracion();
			}
		}
	}
	
	/**
	 * Lee las listas de las canciones y muestra la mejor lista
	 * @param args, array de String
	 */
	public static void main(String[] args) {
		MejorListaRP ml = new MejorListaRP("Lista02", 5);
		System.out.println("A partir del fichero: " + fileName);
		ml.readFromFile(fileName);
		usadas = new boolean[listaCanciones.length];
		System.out.println("Creo la mejor lista con bloques de " + segundos/60 + " minutos");
		ml.muestraLista();
		System.out.println("***Bloque1:");
		ml.Backtracking(bloque1);
		ml.muestraSolucion(bloque1);
		puntuacionTotal = puntuacionMejor;
		System.out.println("***Bloque2:");
		ml.Backtracking(bloque2);
		ml.muestraSolucion(bloque2);
		puntuacionTotal += puntuacionMejor;
		System.out.println("Puntuacion TOTAL: " + puntuacionTotal);
	}
	
	/**
	 * Lee el contenido de un fichero y lo almacena en un array de enteros
	 * @param file, String, nombre y ruta del fichero
	 */
	public void readFromFile(String file) {
		BufferedReader fichero = null;
		String line;
		String[] elements = null;
		try {
			fichero = new BufferedReader(new FileReader(file));
			line = fichero.readLine();
			if (line != null) {	// Numero de canciones de la lista
				listaCanciones = new Cancion[Integer.parseInt(line)];
				line = fichero.readLine();
			}	// Canciones de la lista
			// Tres valores separados por tabuladores: identificador de la canción (código alfanumérico),
			// tiempo que dura la canción (minutos:segundos) y puntuación.
			for (int i = 0; line != null; i++) {
	    		elements = line.split("\t");
	    		String[] duracion = elements[1].split(":");
	    		int valor = Integer.parseInt(duracion[0])*60 + Integer.parseInt(duracion[1]);	// en segundos
	    		listaCanciones[i] = new Cancion(elements[0], valor, Integer.parseInt(elements[2]));
				line = fichero.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No existe el fichero: " + file);
		} catch (IOException e) {
			System.out.println("Error leyendo el fichero: " + file);
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (IOException e) {
					System.out.println("Error cerrando el fichero: " + file);
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Muestra por consola la informacion de la lista de canciones
	 */
	private void muestraLista() {
		int dur = 0;
		int punt = 0;
		for (int i = 0; i < listaCanciones.length; i++) {
			dur += listaCanciones[i].getDuracion();
			punt += listaCanciones[i].getPuntuacion();
		}
		System.out.println("Número de canciones: " + listaCanciones.length);
		System.out.println("Duración (en min.): " + dur/60.0);
		System.out.println("Puntuación: " + punt);
	}
	
	/**
	 * Muestra por consola la solucion del algoritmo de backtracking para cada bloque
	 * @param bloque, lista de canciones especificada
	 */
	private void muestraSolucion(List<Cancion> bloque) {
		for (int i = 0; i < bloque.size(); i++)
			System.out.println(bloque.get(i).toString());
		System.out.println("Número de canciones: " + bloque.size());
		System.out.println("Duración (en min.): " + duracionMejor/60.0);
		System.out.println("Puntuación: " + puntuacionMejor);
	}
	
}
