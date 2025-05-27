package fp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstadisticasEncuestas {
	private List<Encuesta> encuestas;
	public EstadisticasEncuestas(Stream<Encuesta> encuestas) {
		this.encuestas = encuestas.collect(Collectors.toList());
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	
	
	@Override
	public String toString() {
		return "EstadisticasEncuestas [encuestas=" + encuestas + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		EstadisticasEncuestas other = (EstadisticasEncuestas) obj;
		if(this.getEncuestas().equals(other.getEncuestas()))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return this.getEncuestas().size();
	}
	
	public Double getMediaNumEncuestadosConsultorayFecha (String consultora, LocalDate fechaMaxima) {
		return this.getEncuestas().stream()
				.filter(x-> x.getNombre().equals(consultora))
				.filter(x-> x.getFechaFin().isBefore(fechaMaxima))
				.mapToInt(Encuesta::getNumEncuestados)
				.average()
				.orElse(0.0);
	}
	
	public Encuesta getEncuestaMasEncuestadosPorDia(TipoEncuesta tipo) {
		return this.getEncuestas().stream()
				.filter(x-> x.getTipo().equals(tipo))
				.max(Comparator.comparing(Encuesta::getRatioEncuestadosPorDia))
				.orElseThrow(NoSuchElementException::new);
	}
	
	public List<String> getPartidosMasFrecuentesOrdenados(Integer n) {
		return this.getEncuestas().stream()
				.flatMap(x-> x.getResultados().stream())
				.collect(Collectors.groupingBy(x-> x.partido(),Collectors.counting())).entrySet().stream()
				.sorted(Comparator.comparing(Entry<String,Long>::getValue).reversed())
				.limit(n)
				.map(x-> x.getKey())
				.collect(Collectors.toList());
				
	}
	
	public SortedMap<String, Boolean> getSuperaEncuestadosPorPais(Integer umbral){
		/*//Creamos sortedmap con paises y encuestas en lista
		 * 
		 */
		Function<List<Encuesta>, Boolean> funcion = x -> x.stream()
				.allMatch(l-> l.getNumEncuestados() >= umbral);
		return this.getEncuestas().stream()
				.collect(Collectors.groupingBy(Encuesta::getPais, TreeMap::new, 
						Collectors.collectingAndThen(Collectors.toList(), funcion)));
	}
	
	public Map<String, SortedSet<String>> getPaisesPorPartidoMayorPorcentaje (Double umbralPorcentaje){
		Map<String, SortedSet<String>> res = new HashMap<>();
		for(Encuesta e: this.getEncuestas()) {
			for(Resultado r: e.getResultados()) {
				if(r.porcentaje() >= umbralPorcentaje) {
					if(res.keySet().contains(r.partido())) {
						SortedSet<String> conj = res.get(r.partido());
						conj.add(e.getPais());
						res.put(r.partido(), conj);
					}else { //La clave del partido no esta en el diccionario, hay que añadirla
						SortedSet<String> conj = new TreeSet<>(Comparator.naturalOrder());
						conj.add(e.getPais());
						res.put(r.partido(), conj);
					}
				}
			}
		}
		return res;
	}
}
