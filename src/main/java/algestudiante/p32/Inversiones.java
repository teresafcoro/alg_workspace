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
		if (j-i <= 1) {	// condicion de parada
			if (j-i == 1 && i < j && ranking.get(i) > ranking.get(j))
				inversiones ++;
			return inversiones;
		}
		int m = (i+j)/2;
		inversiones += invRecursivo(i, m);
		inversiones += invRecursivo(m+1, j);
		List<Integer> rankingA = ranking.subList(i, m);
		List<Integer> rankingB = ranking.subList(m+1, j);
		inversiones += combina(rankingA, rankingB);
		return inversiones;
	}
	
	private long combina(List<Integer> rA, List<Integer> rB) {
		long inversiones = 0;
		int a = 0, b = 0;
		inversiones += new InversionesCuadratico(rA).start();
		inversiones += new InversionesCuadratico(rB).start();
		for (int k = 0; k < (rA.size() + rB.size()); k++) {
			if (a == rA.size() || b == rB.size())
				return inversiones;
			else if (a < b && ranking.get(a) > ranking.get(b)) {
				b++;
				inversiones += rA.size() - (a + 1);
			}
			else
				a++;
		}
		return inversiones;
	}
	
}
