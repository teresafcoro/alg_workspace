package algestudiante.p11;

import java.util.Random; // Clase que genera números aleatorios

/**
 * Esta clase permite trabajar con vectores
 */
public class Vector1 {

	static int[] v;

	public static void main(String[] arg) {
		// tamaño del problema leido de línea de comandos
		int n = Integer.parseInt(arg[0]);

		v = new int[n];

		rellena(v);
		escribe(v);

		int s = suma(v);
		System.out.println("Suma de los elementos del vector = " + s);

		int[] m = new int[2];
		maximo(v, m);
		System.out.println("Valor del máximo del vector = " + m[1]);
		System.out.println("Posición del máximo del vector = " + m[0]);
	}

	/**
	 * Este método da valores aleatorios a un vector de enteros, utiliza para ello
	 * la clase Random del paquete java.util
	 */
	public static void rellena(int[] a) {
		Random r = new Random();
		int n = a.length;
		for (int i = 0; i < n; i++)
			a[i] = r.nextInt(199) - 99;// valores entre -99 y 99
	}

	/**
	 * Este método saca el contenido por pantalla
	 */
	public static void escribe(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++)
			System.out.println("Elemento " + i + " = " + a[i]);
		System.out.println();
	}

	/**
	 * Este método suma los elementos de un vector y devuelve el resultado
	 */
	public static int suma(int[] a) {
		int s = 0;
		int n = a.length;
		for (int i = 0; i < n; i++)
			s = s + a[i];
		return s;
	}

	/**
	 * Este método calcula el máximo y su posición y devuelve los dos valores.
	 */
	public static void maximo(int[] a, int[] m) {
		int n = a.length;
		m[0] = 0; // posición inicial
		m[1] = a[0]; // primer elemento como referencia
		for (int i = 1; i < n; i++) {
			if (a[i] > m[1]) {
				m[0] = i;
				m[1] = a[i];
			}
		}
	}

}
