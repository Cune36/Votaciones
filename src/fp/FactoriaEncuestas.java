package fp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FactoriaEncuestas {
	private static Encuesta parse(String s) {
		String[] res = s.split(",");
		if(res.length != 8) {
			throw new IllegalArgumentException("Formato no valido");
		}
		LocalDate fechaC = LocalDate.parse(res[1].trim(), DateTimeFormatter.ofPattern("y-M-d"));
		LocalDate fechaF = LocalDate.parse(res[2].trim(), DateTimeFormatter.ofPattern("y-M-d"));
		Integer numEncuestados = Integer.valueOf(res[3].trim());
		TipoEncuesta tipo = TipoEncuesta.valueOf(res[5].trim().toUpperCase());
		Double porcentaje = Double.valueOf(res[6].trim());
		List<Resultado> resultados = parseLista(res[7].trim());
		Encuesta e = new Encuesta(res[0].trim(), fechaC, fechaF, numEncuestados, res[4].trim(), tipo, porcentaje, resultados);
		return e;
		
	}

	private static List<Resultado> parseLista(String s) {
		String[] res = s.substring(1, s.length()-1).split(";");
		List<Resultado> lista = new ArrayList<>();
		for(String a:res) {
			String[] loc = a.trim().substring(1, a.trim().length()-1).split(":");
			Resultado resul = new Resultado(loc[0].trim(), Double.valueOf(loc[1].trim()));
			lista.add(resul);
		}
		return lista;
	}
	public static Stream<Encuesta> parseCSV(String path){
		try {
			return Files.lines(Paths.get(path))
					.skip(1)
					.map(x-> parse(x));
					
		}catch(IOException e) {
			System.out.println("Skill issue");
			return null;
		}
	}
}
