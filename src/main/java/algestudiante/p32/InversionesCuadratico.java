package algestudiante.p32;

import java.util.List;

/**
 * Algoritmo iterativo
 * Complejidad O(n^2)
 */
public class InversionesCuadratico {
	
	private List<Integer> ranking;

	public InversionesCuadratico(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public long start() {
		long inversiones = 0;
		for (int i = 0; i < ranking.size(); i++) {
			for (int j = 0; j < ranking.size(); j++) {
				if (i < j && ranking.get(i).compareTo(ranking.get(j)) > 0)
					inversiones++;
			}
		}
		return inversiones;
	}

}
