package algestudiante.p7;

/**
 * Clase principal para el problema de Asignación de agentes - tareas Herada de RamificaYPoda
 */
class Asignacion extends RamificaYPoda {
	
	private static boolean traza = false;

	public static void main(String[] args) {
		System.out.println("Problema de Agentes - tareas /// Ramificación y poda");

		// Creamos una instancia para resolver el problema
		Asignacion problemaAsigna = new Asignacion();

		// Ejecutamos el m�todo que va recorriendo el espacio de soluciones con ramifica
		// y poda
		problemaAsigna.ramificaYPoda(problemaAsigna.getNodoRaiz());

		// Soluci�n final
		System.out.println("Solución óptima del problema $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(problemaAsigna.mejorSolucion);

		if (traza)
			problemaAsigna.mostrarTrazaSolucion();
	}

	/**
	 * Constructor crea el estado inicial para iniciar el problema
	 */
	public Asignacion() {
		nodoRaiz = new EstadoMejorLista(); // costes iniciales
	}

}
