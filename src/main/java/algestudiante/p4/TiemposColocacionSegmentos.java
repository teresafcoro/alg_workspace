package algestudiante.p4;

/**
 * Clase TiemposColocacionSegmentos
 * Calcula el tiempo que tardan en ejecutarse los tres algoritmos devoradores
 * @author UO263728
 */
public class TiemposColocacionSegmentos {
	
	// longitudes de los segmentos aleatorios con tamaño de 100 en adelante
	private static int[] segAleatorios;
	// Clase ColocacionSegmentos donde se encuentran implementados los algoritmos devoradores
	private static ColocacionSegmentos cs;
	
	/**
	 * Realiza las mediciones de tiempos del algoritmo devorador
	 *  con los distintos tamaños y valores de los arrays de longitudes de segmentos
	 *  usando Math.Random
	 * @param nVeces, veces a ejecutar el algoritmo para un array dado
	 * @param opcion, algoritmo a ejecutar
	 */
	public static void medirTiempos(int nVeces, int opcion) {
		System.out.println("DEVORADOR" + opcion + ":");
		long t1, t2;
		for (int n = 100; n < 100000; n *= 2) {
			t1 = System.currentTimeMillis();
			
			segAleatorios = new int[n];
			for (int i = 0; i < segAleatorios.length; i++)
				segAleatorios[i] = (int) (Math.random()*100+1);  // Valor entero entre 1 y 99, ambos incluidos.
			cs = new ColocacionSegmentos(segAleatorios);
			
			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				if (opcion == 1)
					cs.devorador1();
				else if (opcion == 2)
					cs.devorador2();
				else
					cs.devorador3();
			}
			
			t2 = System.currentTimeMillis();
			
			System.out.println("n=" + n + "**TIEMPO=" + (t2 - t1) + "**nVeces=" + nVeces);
		}
	}
	
	/**
	 * Realiza las llamadas para la medicion de tiempos de los tres algoritmos devoradores
	 * @param arg, String
	 * Tras un casting indica el numero de veces a ejecutar el algoritmo dado
	 *  para obtener resultados significativos en la medicion de tiempo
	 */
	public static void main(String arg[]) {
		int nVeces = Integer.parseInt(arg[0]);
		medirTiempos(nVeces, 1);
		medirTiempos(nVeces, 2);
		medirTiempos(nVeces, 3);
	}
	
}
