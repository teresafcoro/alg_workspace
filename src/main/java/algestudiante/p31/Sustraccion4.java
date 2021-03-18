package algestudiante.p31;

/**
 * Complejidad O(3^(n/2)), por tanto, {a=3, b=2, k=0}, con a>1.
 */
public class Sustraccion4 {

	static long cont;

	public static boolean rec4(int n) {
		if (n <= 0)
			cont++;
		else {
			cont++;
			rec4(n - 2);
			rec4(n - 2);
			rec4(n - 2);
		}
		return true;
	}

	@SuppressWarnings("unused")
	public static void main(String arg[]) {
		long t1, t2, cont;
		int nVeces = Integer.parseInt(arg[0]);
		boolean b = true;
		for (int n = 10; n <= 100; n += 2) {
			t1 = System.currentTimeMillis();
			
			for (int repeticiones = 1; repeticiones <= nVeces; repeticiones++) {
				cont = 0;
				b = rec4(n);
			}
			
			t2 = System.currentTimeMillis();
			
			System.out.println(b + " n=" + n + "**TIEMPO=" + (t2 - t1) + "**nVeces=" + nVeces);
		}
	}

}
