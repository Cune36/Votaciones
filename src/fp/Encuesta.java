package fp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import fp.utiles.Checkers;

public class Encuesta implements Comparable<Encuesta>{
	private String nombre;
	private LocalDate fechaComienzo;
	private LocalDate fechaFin;
	private Integer numEncuestados;
	private String pais;
	private TipoEncuesta tipo;
	private List<Resultado> resultados;
	private Double porcentajeIndecisos;
	
	public Encuesta(String nombre, LocalDate fechaComienzo, LocalDate fechaFin, Integer numEncuestados, String pais,
			TipoEncuesta tipo, Double porcentajeIndecisos, List<Resultado> resultados) {
		super();
		this.nombre = nombre;
		this.fechaComienzo = fechaComienzo;
		this.fechaFin = fechaFin;
		this.numEncuestados = numEncuestados;
		this.pais = pais;
		this.tipo = tipo;
		this.resultados = resultados;
		this.porcentajeIndecisos = porcentajeIndecisos;
		Checkers.check("La fecha de fin no puede ser anterior a la fecha de comienzo", fechaFin.isAfter(fechaComienzo));
		Checkers.check(" La lista de resultados no puede estar vacía", resultados.size() != 0);
		Checkers.check("El número de encuestados debe ser mayor o igual que cero", numEncuestados >= 0);
	}

	public Integer getNumEncuestados() {
		return numEncuestados;
	}

	public void setNumEncuestados(Integer numEncuestados) {
		this.numEncuestados = numEncuestados;
	}

	public Double getPorcentajeIndecisos() {
		return porcentajeIndecisos;
	}

	public void setPorcentajeIndecisos(Double porcentajeIndecisos) {
		this.porcentajeIndecisos = porcentajeIndecisos;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaComienzo() {
		return fechaComienzo;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public String getPais() {
		return pais;
	}

	public TipoEncuesta getTipo() {
		return tipo;
	}
	
	public List<Resultado> getResultados() {
		return resultados;
	}

	public Double getRatioEncuestadosPorDia() {
		return numEncuestados/( Double.valueOf(ChronoUnit.DAYS.between(fechaComienzo, fechaFin)));
	}

	@Override
	public String toString() {
		return "Encuesta [nombre=" + nombre + ", fechaComienzo=" + fechaComienzo + ", fechaFin=" + fechaFin
				+ ", numEncuestados=" + numEncuestados + ", pais=" + pais + ", tipo=" + tipo + ", resultados="
				+ resultados + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		Encuesta other = (Encuesta) obj;
		if(this.fechaComienzo.equals(other.getFechaComienzo()) && this.getFechaFin().equals(other.getFechaFin())
				&& this.getNombre().equals(other.getNombre()) && this.getNumEncuestados() == (other.getNumEncuestados()))
			return true;
		else
			return false;
		
	}
	@Override
	public int compareTo(Encuesta o) {
		int r = 0;
		if(!(this.getFechaComienzo().equals(o.getFechaComienzo())))
			r = this.getFechaComienzo().compareTo(o.getFechaFin());
		else if(!(this.getFechaFin().equals(o.getFechaFin())))
			r = this.getFechaFin().compareTo(o.getFechaFin());
		else if(!(this.getNombre().equals(o.getNombre())))
			r = this.getNombre().compareTo(o.getNombre());
		else
			r = this.getNumEncuestados().compareTo(o.getNumEncuestados());
		return r;
	}
	@Override
	public int hashCode() {
		return this.getFechaComienzo().getYear() + getFechaFin().getDayOfMonth() + this.getNombre().length() + this.getNumEncuestados();
	}
	
	
}
