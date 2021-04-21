package algestudiante.p6;

import java.util.ArrayList;
import java.util.List;

public class MejorListaTiempos {
	
	private static Cancion[] cancionesAleatorias;
	private static MejorLista ml;
	private static List<Cancion> bloque1 = new ArrayList<Cancion>();
	private static List<Cancion> bloque2 = new ArrayList<Cancion>();
	
	/**
	 * 
	 * @param nVeces, veces a ejecutar el algoritmo para un array dado
	 * @param opcion, algoritmo a ejecutar
	 */
	public static void medirTiempos(int nVeces, int min) {
		System.out.println("Creo la mejor lista con bloques de " + min + " minutos sobre canciones aleatorias");
		long t1, t2;
		for (int n = 5; n < 30; n += 5) {
			t1 = System.currentTimeMillis();
			
			cancionesAleatorias = new Cancion[n];
			for (int i = 0; i < cancionesAleatorias.length; i++)
				cancionesAleatorias[i] =  new Cancion(identAleatorio(), (int) (Math.random()*240+60), (int) (Math.random()*6000+1000));
			ml = new MejorLista(cancionesAleatorias, min);
			
			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				System.out.println("***Calculo Bloque1:");
				ml.Backtracking(bloque1);
				System.out.println("***Calculo Bloque2:");
				ml.Backtracking(bloque2);
			}
			
			t2 = System.currentTimeMillis();
			
			System.out.println("n=" + n + "**TIEMPO=" + (t2 - t1) + "**nVeces=" + nVeces);
		}
	}

	private static String identAleatorio() {
		String ident = "";
		String opciones = "abcdefghijklmnñopqrstuvwxyz0123456789";
		for (int i = 0; i < 7; i++) {
			char alea = opciones.charAt((int) (Math.random()*opciones.length()+0));
			ident += alea;
		}
		return ident;
	}

	public static void main(String[] args) {
		int nVeces = Integer.parseInt(args[0]);
		int min = Integer.parseInt(args[1]);
		medirTiempos(nVeces, min);
	}

}
