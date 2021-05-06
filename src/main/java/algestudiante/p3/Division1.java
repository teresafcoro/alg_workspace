package algestudiante.p3;

/*
 * Division:
 * 	O(n^k), a < b^k
 *  O(n^k * logn), a = b^k
 *  O(n^(log en base b de a)), a > b^k
 *  
 *  *a=numero de llamadas al metodo recursivo
 *  *b=valor que se sustrae
 *  *k=valor al que se eleva n en la complejidad del algoritmo
 *  
 *  **complejidades secuenciales se suman, es decir, me quedo con la mayor
 *  **complejidades anidadas se multiplican
 */
/**
 * Es un método recursivo POR DIVISION Los parámetros son: a=1; b=3; k=1 Luego
 * la complejidad temporal es lineal O(n) y la complejidad MPILA es O(log n),
 * por lo que por mucho que crezca n no se desbordará
 */
public class Division1 {

	static long cont;

	public static boolean rec1(int n) {
		if (n <= 0)
			cont++;
		else {
			for (int i = 1; i < n; i++)
				cont++; // O(n)
			rec1(n / 3);
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void main(String arg[]) {
		long t1, t2, cont;
		int nVeces = Integer.parseInt(arg[0]);
		boolean b = true;

		for (int n = 100; n <= 10_000_000; n *= 2) {
			t1 = System.currentTimeMillis();

			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				cont = 0;
				b = rec1(n);
			}

			t2 = System.currentTimeMillis();

			System.out.println(b + " n=" + n + "**TIEMPO=" + (t2 - t1) + "**nVeces=" + nVeces);
		} // for
	} // main

} // class
