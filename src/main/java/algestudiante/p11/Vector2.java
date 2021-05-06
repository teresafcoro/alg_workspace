package algestudiante.p11;

public class Vector2 {
	
	private static int[] v;

	public static void main(String[] args) {
		int n = 1000000000;
		v = new int[n];
		Vector1.rellena(v);

		long t1 = System.currentTimeMillis();

		// Medimos el tiempo de esta operación
		Vector1.suma(v);
			
		long t2 = System.currentTimeMillis();
		long tmedido = t2 - t1;
		System.out.print("Tamaño problema, n= " + n);
		System.out.println(" ; Tiempo de la operacion suma: " + tmedido);
	}

}
