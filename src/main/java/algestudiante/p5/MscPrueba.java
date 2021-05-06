package algestudiante.p5;

public class MscPrueba {

	public static void main(String arg[]) {
		String str1 = arg[0]; // primera secuencia
		String str2 = arg[1]; // segunda secuencia

		System.out.println("PROGRAMACIÓN DINÁMICA:");
		MSC msc = new MSC(str1, str2);
		System.out.println("Inicializando la tabla...");
		msc.iniTabla();
		System.out.println("Rellenando la tabla...");
		msc.rellenaTabla();
		System.out.println("Imprimiendo la tabla...");
		msc.imprimirTabla();
		System.out.println("Buscando la MSC...");
		msc.encontarMSC(true);
		System.out.println("Imprimiendo la MSC...");
		msc.imprimeMCS();

		System.out.println("\n/****************/\n");

		System.out.println("RECURSIVO:");
		MSCRec lcsrec = new MSCRec();
		System.out.println("Buscando la MSC...");
		System.out.println(lcsrec.encontrarMSC(str1, str2));

		System.out.println("Program terminated.");
	}

}
