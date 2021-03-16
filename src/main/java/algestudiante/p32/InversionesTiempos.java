package algestudiante.p32;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InversionesTiempos {
	
	private static List<Integer> ranking;

	public static void main(String args[]) {
		int numberFiles = 7;

		// No tomamos tiempos de la primera ejecución ya que la primera ejecución es más
		// lenta de lo normal
		for (int i = 1; i <= numberFiles; i++) {
			String fileName = Paths.get("").toAbsolutePath().toString() 
					+ "/src/main/java/algestudiante/p32/datos/ranking" + i + ".txt";
			System.out.println("Fichero: " + fileName);
			System.out.println("Fichero: ranking" + i + ".txt");

			// Algoritmo DV
			ranking = readRankingFromFile(fileName);
			Inversiones inv1 = new Inversiones(ranking);
			long t1 = System.currentTimeMillis();
			System.out.println("Número de inversiones = " + inv1.start());
			long t2 = System.currentTimeMillis();
			if (i > 0)
				System.out.println("El tiempo para el algoritmo DV es: " + (t2 - t1) + " milisegundos");

			// Algoritmo iterativo
			ranking = readRankingFromFile(fileName);
			InversionesCuadratico inv2 = new InversionesCuadratico(ranking);
			t1 = System.currentTimeMillis();
			System.out.println("Número de inversiones = " + inv2.start());
			t2 = System.currentTimeMillis();
			if (i > 0)
				System.out.println("El tiempo para el algortimo iterativo es: " + (t2 - t1) + " milisegundos");

			System.out.println("\n****************************\n");
		}
	}

	public static List<Integer> readRankingFromFile(String file) {
		BufferedReader fich = null;
		String line;
		List<Integer> elements = new ArrayList<Integer>();
		try {
			// abrimos el fichero
			fich = new BufferedReader(new FileReader(file));
			line = fich.readLine(); // Primer elemento del fichero
			while (line != null) {
				elements.add(Integer.parseInt(line));
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
