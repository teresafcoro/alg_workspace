package algestudiante.p2;

/**
 * Este programa sirve para ordenar n elementos con un algoritmo de los "malos"
 * Es la INSERCIÓN DIRECTA
 * Complejidad: caso mejor O(n), caso medio O(n^2) y caso peor O(n^2)
 * En esta implementacion O(n^2)
 */
public class Insercion extends Vector {

	public Insercion(int nElementos) {
		super(nElementos);
	}

	/**
	 * Ordenación por inserción directa
	 */
	@Override
	public void ordenar() {
		int n = elements.length;
		for (int i = 1; i < n; i++) {
			int x = elements[i];
			int j = i - 1;
			while (j >= 0 && x < elements[j]) {
				elements[j + 1] = elements[j];
				j--;
			}
			elements[j + 1] = x;
		}
	}

	@Override
	public String getNombre() {
		return "Inserción directa";
	}

}
