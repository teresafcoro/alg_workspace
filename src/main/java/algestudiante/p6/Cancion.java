package algestudiante.p6;

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

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public String toString() {
		return "Cancion [identificador=" + identificador + ", duracion=" + duracion + ", puntuacion=" + puntuacion
				+ "]";
	}
	
}
