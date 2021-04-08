package algestudiante.p5;

import java.util.Random;

public class MSCRec {
	
	/**
	 * Genera una secuencia aleatoria
	 * @param n tamaño de la secuencia
	 * @return secuencia aleatoria, una cadena con caracters A, C, G and T
	 */
	public String genSecuenciaAleatoria(int n) {
		char[] dna_elements = { 'A', 'C', 'G', 'T' };
		String result = "";
		Random r = new Random();
		for (int i = 0; i < n; i++)
			result += dna_elements[r.nextInt(4)];
		return result;
	}

	/**
	 * Encuentra un MSC directamente a través de una aproximación recursiva
	 */
	public String encontrarMSC(String s1, String s2) {
		// TODO: encuentra un MSC de dos secuencias directamente (sin usar una tabla)
		// mediante recursion
		String msc = "";
		if (s1.isEmpty() || s2.isEmpty())
			return "";
		char c1 = s1.charAt(s1.length()-1);
		char c2 = s2.charAt(s2.length()-1);
		String s1_apostrofe = s1.substring(0, s1.length()-1);
		String s2_apostrofe = s2.substring(0, s2.length()-1);
		String l1 = encontrarMSC(s1_apostrofe, s2);
		String l2 = encontrarMSC(s1, s2_apostrofe);
		String l3 = encontrarMSC(s1_apostrofe, s2_apostrofe);
		int max;
		if (c1 == c2)
			max = maxima(l1, l2, l3+1);
		else
			max = maxima(l1, l2, l3);
		if (max == 1)
			msc = msc.concat(l1);
		else if (max == 2)
			msc = msc.concat(l2);
		else
			msc = msc.concat(l3);
		return msc;
	}

	/**
	 * Devuelva el indice de la cadena más larga introducida
	 * @param l1 e.g. input L1=MSC(S1', S2). S1', S1 without its last char
	 * @param l2 e.g. input L2=MSC(S1, S2'). S2', S2 without its last char
	 * @param l3 e.g. input L3=MSC(S1', S2') or L3+1 when both current chars are equal
	 * @return índice de la cadena más larga
	 */
	private int maxima(String l1, String l2, String l3) {
		// TODO (opcional): a partir de tres cadenas (p.e. subsecuencias) determina el
		// índice de la más larga
		int num1 = l1.length(), num2 = l2.length(), num3 = l3.length();
		if (num1 > num2 && num1 > num3)
			return 1;
		else if (num2 > num1 && num2 > num3)
			return 2;
		else if (num3 > num1 && num3 > num2)
			return 3;
		else if (num1 == num3 && num1 > num2)
			return 3;
		else
			return 2;
	}

}
