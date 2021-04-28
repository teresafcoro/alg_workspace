package algestudiante.p7;

public class Cancion {
	
	private String identificador;
	private int duracion, puntuacion;

	public Cancion(String identificador, int duracion, int puntuacion) {
		this.identificador = identificador;
		this.duracion = duracion;
		this.puntuacion = puntuacion;
	}

	public String getIdentificador() {
		return identificador;
	}

	public int getDuracion() {
		return duracion;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	@Override
	public String toString() {
		return "Cancion [identificador=" + identificador + ", duracion (en seg.)=" + duracion + ", puntuacion=" + puntuacion
				+ "]";
	}
	
}
