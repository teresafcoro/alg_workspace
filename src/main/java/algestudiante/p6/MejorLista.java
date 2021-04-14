package algestudiante.p6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class MejorLista {
	
	// canciones de la lista cargada
	private static Cancion[] listaCanciones;
	private static String fileName;
	private static int minutos;
	
	/**
	 * Constructor
	 * Inicializa el array de canciones con el array pasado como parametro
	 * @param canciones, lista de objetos Cancion
	 */
	public MejorLista(String name, int min) {
		fileName = Paths.get("").toAbsolutePath().toString() 
				+ "/src/main/java/algestudiante/p6/datos/" + name;
		minutos = min;
	}
	
	/**
	 * 
	 */
	public static void Backtracking() {
		
	}

	/**
	 * Lee las listas de las canciones y muestra la mejor lista
	 * @param args, array de String
	 */
	public static void main(String[] args) {
		System.out.print("Fichero: " + fileName);
		readFromFile(fileName);
		System.out.println(", crea la mejor lista con bloques de: " + minutos + " minutos");
		/*
		 El resultado de la ejecución del programa debe mostrar claramente:
			El número de canciones, la duración total y la suma de las puntaciones de la lista contenida en el fichero.
			La suma de las puntuaciones de las canciones seleccionadas (que es lo que se pretende optimizar).
			Canciones que componen el Bloque 1 y qué duración total tienen, canciones que componen el bloque 2 y
			que duración tienen.
		 */
		System.out.println("Solución:");
		Backtracking();
	}
	
	/**
	 * Lee el contenido de un fichero y lo almacena en un array de enteros
	 * @param file, String, nombre y ruta del fichero
	 */
	public static void readFromFile(String file) {
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
	
}
