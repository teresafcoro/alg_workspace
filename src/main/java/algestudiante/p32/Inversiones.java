package algestudiante.p32;

import java.util.List;

/**
 * Algoritmo Divide y Venceras
 * Complejidad O(nlogn), {a=2, b=2, k=1} <- Por division con a=b^k
 */
public class Inversiones {

	private List<Integer> ranking;
	
	public Inversiones(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public long start() {
		return invRecursivo(0, ranking.size()-1);
	}
	
	private long invRecursivo(int i, int j) {
		long inversiones = 0;
		if (j-i <= 1) {
			if (j-i == 1 && i < j && ranking.get(i).compareTo(ranking.get(j)) > 0)
				inversiones++;
			return inversiones;
		}
		int m = (i+j)/2;
		inversiones += invRecursivo(i, m);
		inversiones += invRecursivo(m+1, j);
//		combina(i, m, m+1, j);	// Mergesort
		return inversiones;
	}
	
}
