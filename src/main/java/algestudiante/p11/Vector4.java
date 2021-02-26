package algestudiante.p11;

public class Vector4 {
	
	private static int[] v;

	public static void main(String[] args) {
		System.out.println("n\ttmedido");
//		System.out.println("n\ttmedido/nVeces");
		
		// medimos nVeces una operación para conseguir medidas más precisas
		int nVeces = 100000000;
		
		// Variación de los tamaños del problema a medir
		for (int n = 10; n < 1000000000; n *= 3) {
			
			// Inicialización para la operación a medir
			v = new int[n];
			Vector1.rellena(v);

			long t1 = System.currentTimeMillis();

			for (int repeticiones = 0; repeticiones < nVeces; repeticiones++) {
				Vector1.suma(v);
//				Vector1.maximo(v, v);
			}

			long t2 = System.currentTimeMillis();
			long tmedido = t2 - t1;

			System.out.println(n + "\t" + tmedido);
			// La división da cuánto tarda una operación
//			System.out.println(n + "\t" + tmedido/(float)nVeces);
		}
	}

}
