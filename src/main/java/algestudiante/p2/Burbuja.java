package algestudiante.p2;

/**
 * Este programa sirve para ordenar n elementos con un algoritmo de BURBUJA
 * Complejidad: O(n^2), para cualquier caso (mejor, medio y peor).
 */
public class Burbuja extends Vector {

	public Burbuja(int nElementos) {
		super(nElementos);
	}

	/**
	 * Ordenación por el método de Burbuja
	 */
	@Override
	public void ordenar() {
		int n = elements.length;
		for (int i = 0; i <= n - 2; i++) {
			for (int j = n - 1; j > i; j--) {
				if (elements[j - 1] > elements[j])
					intercambiar(j - 1, j);
			}
		}
	}

	@Override
	public String getNombre() {
		return "Burbuja";
	}

}