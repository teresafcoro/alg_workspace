package algestudiante.p32;

import java.util.List;

/**
 * Algoritmo Divide y Venceras
 * Complejidad O(nlogn), {a=, b=, k=1} <- Por division.		(a=b)
 */
public class Inversiones {

	private List<Integer> ranking;
	private long inversiones;
	
	public Inversiones(List<Integer> ranking) {
		this.ranking = ranking;
	}

	public long start() {
		inversiones = 0;
//		recursivo(0);
		return inversiones;
	}
	
	private void recursivo(int j) {
//		if (j < ranking.size()) {
//			for (int i = 0; i < ranking.size(); i++) {
//				if (j < i && ranking.get(j).compareTo(ranking.get(i)) > 0)
//					inversiones++;
//			}
//			j++;
//			recursivo(j);
//		}
	}

}
