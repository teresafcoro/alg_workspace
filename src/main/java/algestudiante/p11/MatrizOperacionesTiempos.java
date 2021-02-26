package algestudiante.p11;

import algestudiante.p0.MatrizOperaciones;

public class MatrizOperacionesTiempos {

	private static MatrizOperaciones m;

	public static void main(String[] args) {
		System.out.println("n\ttmedido");
		
		int nVeces = 10000000;
		
		// Variacion de los tamanos del problema a medir
		for (int n = 3; n < 1000000000; n *= 2) {
			// Inicializacion para la operacion a medir
			m = new MatrizOperaciones(n, 2, 8);

			long t1 = System.currentTimeMillis();
			
			long c = 0;

			// Repeticiones de la operacion para conseguir buenas medidas
			for (int repeticiones = 0; repeticiones < nVeces; repeticiones++) {
				// Medimos el tiempo de esta operacion
				c += m.sumarDiagonal1();
//				c += m.sumarDiagonal2();
			}

			long t2 = System.currentTimeMillis();
			long tmedido = t2 - t1;

			System.out.println(n+"\t"+tmedido+"\t"+c);
		}

	}
}
