package algestudiante.p7;

/**
 * Clase principal para el problema de Asignación de agentes - tareas
 * Herada de RamificaYPoda 
 */
class Asignacion extends RamificaYPoda {
	private static boolean traza= false;

	public static void main(String[] args) {
		System.out.println("Problema de Agentes - tareas /// Ramificación y poda");
		Asignacion problemaAsigna = new Asignacion(); 
		problemaAsigna.ramificaYPoda(); 
		System.out.println("Solución óptima del problema $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(problemaAsigna.mejorSolucion);
		if (traza)
			problemaAsigna.mostrarTrazaSolucion(); 
	}

	/**
	 * Constructor crea el estaod inicial para iniciar el problema
	 */
	public Asignacion() {
		nodoRaiz = new EstadoMejorLista(); //costes iniciales
	}

}

