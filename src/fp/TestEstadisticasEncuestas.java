package fp;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

public class TestEstadisticasEncuestas {
	private static EstadisticasEncuestas TestLeeFichero(String path){
		EstadisticasEncuestas res = new EstadisticasEncuestas(FactoriaEncuestas.parseCSV(path));
		System.out.println("Elmentos leidos: "+ res.getEncuestas().size());
		System.out.println("Los 3 primeros:");
		for(int i = 0; i != 2; i++) {
			System.out.println(res.getEncuestas().get(i));
		}
		return res;
	}
	
	private static void TestgetMediaNumEncuestadosConsultorayFecha (EstadisticasEncuestas e, 
																	String consultora, 
																	LocalDate fechaMaxima) {
		Double res = e.getMediaNumEncuestadosConsultorayFecha(consultora, fechaMaxima);
		System.out.println("\nLa media de la consultora " + consultora + " con fecha de fin anterior a " +
		fechaMaxima + " es: " + res);
	}
	
	private static void TestgetEncuestaMasEncuestadosPorDia(EstadisticasEncuestas e, TipoEncuesta tipo) {
		Encuesta res = e.getEncuestaMasEncuestadosPorDia(tipo);
		System.out.println("\n	 La encuesta de tipo " + tipo + " con mayor ratio de encuestados por día es:");
		System.out.println(res);
	}
	
	private static void TestgetPartidosMasFrecuentesOrdenados(EstadisticasEncuestas e,Integer n) {
		System.out.println("\n	 Los"+n +" partidos más frecuentes en las encuestas son::");
		List<String> res = e.getPartidosMasFrecuentesOrdenados(n);
		for(String a:res) {
			System.out.println(a);
		}
	 
	}
	private static void TestgetSuperaEncuestadosPorPais(EstadisticasEncuestas e, Integer umbral) {
		SortedMap<String, Boolean> res = e.getSuperaEncuestadosPorPais(umbral);
		System.out.println("	¿Todas las encuestas de estos países superan los " + umbral +  " encuestados?:");
		System.out.println(res);
	}
	
	private static void TestgetPaisesPorPartidoMayorPorcentaje(Double umbral, EstadisticasEncuestas e) {
		Map<String, SortedSet<String>> res = e.getPaisesPorPartidoMayorPorcentaje(umbral);
		System.out.println("Países donde cada partido ha obtenido un porcentaje de votos superior a " + umbral + "%:");
		System.out.println(res);
	}
	
	
	public static void main(String args[]) {
		EstadisticasEncuestas stats = TestLeeFichero("./data/encuestas_electorales.csv");
		System.out.println("EJ1 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = \n");
		TestgetMediaNumEncuestadosConsultorayFecha(stats, "Consultora A", LocalDate.of(2024, 05, 29));
		TestgetMediaNumEncuestadosConsultorayFecha(stats, "Consultora B", LocalDate.of(2024, 05, 26));
		System.out.println("\nEJ2 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
		TestgetEncuestaMasEncuestadosPorDia(stats, TipoEncuesta.valueOf("TELEFONICA"));
		TestgetEncuestaMasEncuestadosPorDia(stats, TipoEncuesta.valueOf("PRESENCIAL"));
		System.out.println("\nEJ3 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
		TestgetPartidosMasFrecuentesOrdenados(stats, 5);
		System.out.println("\nEJ4 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
		TestgetSuperaEncuestadosPorPais(stats, 3500);
		System.out.println("\nEJ5 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = ");
		TestgetPaisesPorPartidoMayorPorcentaje(90.0, stats);
		TestgetPaisesPorPartidoMayorPorcentaje(97.0, stats);
	}
	
}
