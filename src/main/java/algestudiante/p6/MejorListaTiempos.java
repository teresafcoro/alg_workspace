package algestudiante.p6;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase MejorListaTiempos
 * Calcula el tiempo que tarda en ejecutarse el algoritmo de backtracking de la clase MejorLista
 * @author UO263728
 */
public class MejorListaTiempos {
	
	private static Cancion[] cancionesAleatorias;
	private static MejorLista ml;
	private static List<Cancion> bloque1 = new ArrayList<Cancion>();
	private static List<Cancion> bloque2 = new ArrayList<Cancion>();
	
	/**
	 * Mide el tiempo que tarda el algoritmo de backtracking en ejecutarse
	 * @param nVeces, veces a ejecutar el algoritmo para un array dado
	 */
	public static void medirTiempos(int nVeces) {
		System.out.println("Creo la mejor lista sobre canciones aleatorias");
		long t1, t2;
		for (int n = 5; n <= 100; n += 5) {
			t1 = System.currentTimeMillis();
			
			cancionesAleatorias = new Cancion[n];
			int t = 0;
			for (int i = 0; i < cancionesAleatorias.length; i++) {
				cancionesAleatorias[i] = new Cancion(identAleatorio(), (int) (Math.random()*300+120), (int) (Math.random()*5000+1000));
				t += cancionesAleatorias[i].getDuracion();
			}
			t = (int) ((t/60)*0.25);
			ml = new MejorLista(cancionesAleatorias, t);
			
			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				ml.Backtracking(bloque1);
				ml.Backtracking(bloque2);
			}

			t2 = System.currentTimeMillis();
			
			System.out.println("n=" + n + "**TIEMPO=" + (t2 - t1) + "**nVeces=" + nVeces);
		}
	}

	/**
	 * Crea una cadena aleatoria para el identificador de la cancion
	 * @return ident, String
	 */
	private static String identAleatorio() {
		String ident = "";
		String opciones = "abcdefghijklmnñopqrstuvwxyz0123456789";
		for (int i = 0; i < 7; i++) {
			char alea = opciones.charAt((int) (Math.random()*opciones.length()+0));
			ident += alea;
		}
		return ident;
	}

	/**
	 * Llamo al metodo medirTiempos para medir el metodo backtracking de la clase MejorLista
	 * @param arg, array de Strings
	 */
	public static void main(String[] arg) {
		int nVeces = Integer.parseInt(arg[0]);
		medirTiempos(nVeces);
	}

}
