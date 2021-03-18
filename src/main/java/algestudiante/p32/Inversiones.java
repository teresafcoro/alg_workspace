package algestudiante.p32;

import java.util.List;

/**
 * Algoritmo Divide y Venceras
 * Complejidad O(nlogn)
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
		if (j-i <= 1)	// condicion de parada
			return inversiones;
		int m = (i+j)/2;
		inversiones += invRecursivo(i, m);
		inversiones += invRecursivo(m+1, j);
		inversiones += combina(ranking.subList(i, m), ranking.subList(m+1, j));
		return inversiones;
	}
	
	private long combina(List<Integer> rA, List<Integer> rB) {
		long inversiones = 0;
		int i=0, j=0, c1=0, c2=0;
		for (int k = 0; k < rA.size() + rB.size(); k++) {
			if (i < rA.size())	c1 = rA.get(i);
			else c1 = Integer.MAX_VALUE;
			
			if (j < rB.size())	c2 = rB.get(j);
			else c2 = Integer.MAX_VALUE;
			
			if (c1 <= c2) {
				ranking.set(k, c1);
				i++;
			} else {
				ranking.set(k, c2);
				j++;
				inversiones += rA.size() - i;
			}
		}
		return inversiones;
	}
	
}
