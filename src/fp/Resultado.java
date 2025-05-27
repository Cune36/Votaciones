package fp;

import fp.utiles.Checkers;

public record Resultado(String partido, Double porcentaje) {
	
	public Resultado{
		Checkers.check("El porcentaje no puede ser negativo", porcentaje >= 0);
	}
	public String toString() {
		return "(" + partido + ": " + porcentaje + ")";
	}
}
