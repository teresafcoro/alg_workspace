package algestudiante.p5;

public class MscTiempos {

//	static String mode = ""; // "Recursivo"
	static String mode = "progdin"; // "DV"
	static int nTimes = 1000000000;

	public static void main(String args[]) {
		long t1 = 0;
		long t2 = 0;
		float total = 0;
		
		System.out.println("MODO: " + mode);

		if (mode.equals("progdin")) {
			MSC msc;
			for (int n = 100; n <= 12800; n *= 2) {
				msc = new MSC(n);
				msc.iniTabla();
				msc.rellenaTabla();
				t1 = System.currentTimeMillis();
				
				for (int repeticiones = 1; repeticiones <= nTimes; repeticiones++)
					msc.encontarMSC(false);
				
				t2 = System.currentTimeMillis();
				total = (float) (t2 - t1);

				System.out.println("Tiempo [ms]= " + total / nTimes + ", n=" + n);// / nTimes
			}
		} else {
			MSCRec lcs_rec;
			String str1, str2;
			for (int n = 1; n <= 12800; n++) {
				lcs_rec = new MSCRec();
				str1 = lcs_rec.genSecuenciaAleatoria(n);
				str2 = lcs_rec.genSecuenciaAleatoria(n);
				t1 = System.currentTimeMillis();
				lcs_rec.encontrarMSC(str1, str2);
				t2 = System.currentTimeMillis();
				total = (float) (t2 - t1);

				System.out.println("Tiempo [ms]= " + total / nTimes + ", n=" + n);
			}
		}

	}

}
