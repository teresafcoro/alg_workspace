package algestudiante.p7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.UUID;

public class ColaPrioridad {
	
	private static final boolean comprobarRepetido = true;

	// Cola de prioridad propiamente dicha,
	// para almacenar estados pendientes de desarrollar
	private PriorityQueue<Estado> nodos;

	// Guarda estados ya desarrollados
	// para componer la solución y también
	// para saber qué nodos han sido tratados
	private HashMap<UUID, Estado> nodosUsados;

	public ColaPrioridad() {
		nodos = new PriorityQueue<Estado>();
		nodosUsados = new HashMap<UUID, Estado>();
	}

	public void insertar(Estado nodo) {
		// Gestiona tema de estados repetidos:
		// Si no está activada la comprobación, inserta directamente en la cola
		// Si está activada, comprueba si está repetido --> entonces no lo inserta
		// De esta forma evitamos volver a comprobar estados ya comprobados,
		// que podrían formar bucles infinitos
		if (!comprobarRepetido || !nodoRepetido(nodo))
			nodos.add(nodo);
	}

	/**
	 * Comprueba si un estado ya ha sido explorado
	 * @param nodo estado a comprobar
	 * @return true - si ya se ha explorado, false en caso contrario
	 */
	private boolean nodoRepetido(Estado nodo) {
		// Recorre todos los estados ya explorados
		// creando una colección que se puede recorrer secuencialmente
		for (Estado n : nodosUsados.values()) {
			if (nodo.equals(n))
				return true;
		}
		return false;
	}

	/**
	 * Comprueba si la cola de prioridad está vacía
	 * @return true- si la cola está vacía
	 */
	public boolean vacia() {
		return nodos.isEmpty();
	}

	/**
	 * Accede a la cola de prioridad y consulta el estado más prometedor; pero no lo
	 * extrae
	 * @return valor del heurístico del estado más prometedor
	 */
	public int estimacionMejor() {
		return nodos.peek().getHeuristico();
	}

	/**
	 * Devuelve estado más prometedor de la cola de prioridad y lo elimina
	 * @return Devuelve estado más prometedor
	 */
	public Estado sacarMejorNodo() {
		Estado nodo = nodos.poll();
		nodosUsados.put(nodo.getId(), nodo);
		// lo guardamos porque puede ser parte de la
		// solución. Además, de este modo podemos
		// saber si un nodo ya ha sido tratado
		return nodo;
	}

	/**
	 * Utiliza el campo id de los estados para recuperar el camino desde el nodo
	 * proporcionados hasta el inicial
	 * @param nodo A partir del cual recuperamos el camino
	 * @return ArrayList con los estados que componen el camino
	 */
	public ArrayList<Estado> rutaHastaRaiz(Estado nodo) {
		// calcula la ruta hasta el nodo raiz
		ArrayList<Estado> resultado = new ArrayList<Estado>();

		resultado.add(nodo); // añadimos el último nodo tratado
		UUID idPadre = nodo.getIdPadre(); // buscamos su padre

		while (idPadre != null) {
		// mientras no s llegue al nodo raiz
			nodo = nodosUsados.get(idPadre);
			resultado.add(nodo);
			idPadre = nodo.getIdPadre();
		}

		return resultado;
	}
}
