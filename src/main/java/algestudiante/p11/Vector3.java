package algestudiante.p11;

public class Vector3 {
	
	private static int[] v;

	public static void main(String[] args) {
		// Con este formato podremos copiar la salida y pegar en excel para hacer la tabla
		System.out.println("Tiempos suma");
		System.out.println("n\tt");
		
		for (int n = 1000000000; n < 1000000000; n += 1000000) {
			v = new int[n];
			Vector1.rellena(v);

			long t1 = System.currentTimeMillis();

			// Medimos el tiempo de esta operacion
			Vector1.suma(v);

			long t2 = System.currentTimeMillis();
			long tmedido = t2 - t1;
			System.out.println(n + "\t" + tmedido);
		}
	}

}
