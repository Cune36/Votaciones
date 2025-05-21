# Fundamentos de Programación
# Curso 24-25. Segundo parcial. Sesión 1: Votaciones

**Autor:**  Manuel Carranza 
**Revisores:** José María Luna, Toñi Reina, Mariano González.
**Última modificación:** 13/05/2025.

Tenemos un fichero csv para guardar distintas encuestas electorales con vistas a las elecciones europeas del próximo mes de junio. Para cada encuesta se tiene la siguiente información:

- **Consultora**: Nombre de la consultora que realiza la encuesta.
- **Fecha de comienzo:** Fecha de comienzo de la encuesta.
- **Fecha de fin:** Fecha de fin de la encuesta.
- **Número de encuestados:** Entero con el número de encuestados.
- **País:** País de la encuesta.
- **Tipo de Encuesta:** Medio donde se realiza la encuesta, con valores Telefónica, Internet, o Presencial.
- **Porcentaje de indecisos:** Porcentaje de encuestados indecisos.
- **Resultados de partidos:** Lista de pares con el nombre del partido y el porcentaje de voto esperado.

Una línea de este fichero contiene los siguientes campos:

```
Consultora B,2024-05-18,2024-05-27,3203,Espana,Internet,16.15,'(Partido 1: 42.29); (Partido 2: 49.52); (Partido 3: 6.01); (Partido 4: 1.76); (Partido 5: 0.18); (Partido 6: 0.23)'
```

Esta fila indica que la Consultora B, ha realizado una encuesta que comenzó el día 2024-05-18 y finalizó el día 2024-05-27, habiendo encuestado a 3203 personas. El país donde se realiza la encuesta es España, a través de internet y con un 16,15% de indecisos. A continuación, se lista el porcentaje de voto esperado para seis partidos distintos.

**Ejercicio 1: Tipo Resultado (0,5 ptos)**

Implemente el tipo Resultado mediante un **record**, de acuerdo con la siguiente información:

Propiedades:

- **Partido,** de tipo String, consultable
- **Porcentaje,** de tipo Double, consultable.

Constructores: 

- C1: recibe un parámetro por cada propiedad básica del tipo, en el mismo orden en el que están definidas.

Representación como cadena: una cadena con todas las propiedades básicas del tipo.

Restricciones:

- R1: El porcentaje no puede ser negativo.


**Ejercicio 2: tipo Encuesta (1,5 ptos)**

Implemente el tipo Encuesta utilizando una clase, de acuerdo con la siguiente información

Propiedades:

- **Nombre,** de tipo String, consultable
- **Fecha de comienzo,** de tipo LocalDate, consultable.
- **Fecha de fin,** de tipo LocalDate, consultable.
- **Número encuestados**, de tipo Integer, consultable y modificable. 
- **País,** de tipo String, consultable.
- **Tipo,** de tipo TipoEncuesta, consultable. Puede tomar los valores TELEFONICA, INTERNET, PRESENCIAL.
- **Porcentaje de indecisos,** de tipo Double, consultable y modificable.
- **Resultados,** de tipo List<Resultado>, consultable. 
- **Ratio de encuestados por día,** de tipo Double, consultable. Ratio del número de encuestados por día de duración de la encuesta.

Constructores: 

- C1: recibe un parámetro por cada propiedad básica del tipo.

Restricciones: 

- R1: La fecha de fin no puede ser anterior a la fecha de comienzo.
- R2: La lista de resultados no puede estar vacía.
- R3: El número de encuestados debe ser mayor o igual que cero.

Representación como cadena: una cadena con todas las propiedades básicas del tipo.

Criterio de igualdad: dos encuestas son iguales si lo son su fecha de comienzo y de fin, su consultora, y su número de encuestados.

Criterio de ordenación: dos encuestas se ordenan por fecha de comienzo, por fecha de fin, y a igualdad de fecha por su consultora, y después por su número de encuestados.


**Ejercicio 3: Factoría (1 pto)**


En la clase **FactoriaEncuestas**, que se le da parcialmente implementada, implemente el método:

*Encuesta parsearEncuesta(String lineaCSV)*: crea un objeto de tipo Encuesta a partir de una cadena de caracteres. La cadena de caracteres debe tener el mismo formato que las líneas del fichero CSV.

**Ejercicio 4: Tratamientos secuenciales (7 ptos)**

El tipo EstadisticasEncuestas tiene la siguiente descripción:

Propiedades:

- **encuestas**: lista de encuesta, de tipo List<Encuesta>, consultable.

Constructores: 

- C1: recibe un parámetro de tipo Stream<Encuesta>.

Representación como cadena: una cadena con todas las encuestas.

Criterio de igualdad: dos objetos de tipo EstadisticasEncuestas son iguales si lo son sus encuestas.

Implemente el tipo **EstadisticasEncuestas** y añada los siguientes tratamientos secuenciales. Debe resolver todos los métodos **mediante streams**, salvo que se le indique expresamente que debe utilizar bucles:

1. *Double getMediaNumEncuestadosConsultorayFecha (String consultora, LocalDate fechaMaxima):* Devuelve la media del número de encuestados de las encuestas realizadas por la consultora dada como parámetro cuya fecha de fin sea anterior a *fechaMaxima*. Si no se puede calcular, se devuelve cero. *(1 pto)*

1. *Encuesta getEncuestaMasEncuestadosPorDia(TipoEncuesta tipo):* Devuelve la encuesta, del tipo dado como parámetro, que tiene un mayor ratio de número de encuestados por día de duración. Si no se puede calcular, se eleva la excepción NoSuchElementException *(1 pto)*

1. *List<String> getPartidosMasFrecuentesOrdenados(Integer n):* Dado un valor n, devuelve una lista con los n partidos que más veces aparecen en las encuestas ordenados de mayor a menor número de apariciones.  *(1,5 ptos)*

1. *SortedMap<String, Boolean> getSuperaEncuestadosPorPais(Integer umbral):* Devuelve un SortedMap, cuyas claves están ordenadas por orden natural, en el que a cada país le hace corresponder un Boolean con un valor de True si todas las encuestas de ese país tienen un número de encuestados superior al umbral dado como parámetro. *(1,75 pto)*

1. *Map<String, SortedSet<String>> getPaisesPorPartidoMayorPorcentaje (Double umbralPorcentaje):* Dado un valor *umbralPorcentaje* de tipo Double, devuelve un Map que relaciona el nombre de cada partido con el conjunto de países en los que ha obtenido un porcentaje de votos superior al umbral. **Implemente este método con bucles.** *(1,75 pto)*

Escriba una clase **TestEstadisticasEncuestas**. En la clase se leerán los datos del fichero y se probarán todos los tratamientos secuenciales, definiendo un método de test por cada tratamiento secuencial a probar. No se obtendrá la puntuación máxima del ejercicio si no se realiza el test y éste ejecuta. Los resultados esperados para el dataset proporcionado, con los valores indicados en los tests, son:

```
EJ1 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

La media de la consultora Consultora A con fecha de fin anterior a 2024-05-29 es 2993,785714

La media de la consultora Consultora B con fecha de fin anterior a 2024-05-26 es 2825,111111

EJ2 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

`	`La encuesta de tipo TELEFONICA con mayor ratio de encuestados por día es:

Encuesta [consultora=Consultora A, fechaComienzo=2024-05-20, fechaFin=2024-05-25, numeroEncuestados=3499, pais=Lituania, tipo=TELEFONICA, porcentajeIndecisos=23.99, resultados=[Resultado[partido=Partido 1, porcentaje=97.99], Resultado[partido=Partido 2, porcentaje=0.01], Resultado[partido=Partido 3, porcentaje=1.61], Resultado[partido=Partido 4, porcentaje=0.22], Resultado[partido=Partido 5, porcentaje=0.17]]] 

La encuesta de tipo PRESENCIAL con mayor ratio de encuestados por día es:

Encuesta [consultora=Consultora B, fechaComienzo=2024-05-19, fechaFin=2024-05-24, numeroEncuestados=3778, pais=Bulgaria, tipo=PRESENCIAL, porcentajeIndecisos=29.69, resultados=[Resultado[partido=Partido 1, porcentaje=31.14], Resultado[partido=Partido 2, porcentaje=19.44], Resultado[partido=Partido 3, porcentaje=23.82], Resultado[partido=Partido 4, porcentaje=2.33], Resultado[partido=Partido 5, porcentaje=7.54], Resultado[partido=Partido 6, porcentaje=3.77], Resultado[partido=Partido 7, porcentaje=4.4], Resultado[partido=Partido 8, porcentaje=3.9], Resultado[partido=Partido 9, porcentaje=2.79], Resultado[partido=Partido 10, porcentaje=0.69], Resultado[partido=Partido 11, porcentaje=0.0], Resultado[partido=Partido 12, porcentaje=0.08], Resultado[partido=Partido 13, porcentaje=0.07], Resultado[partido=Partido 14, porcentaje=0.02], Resultado[partido=Partido 15, porcentaje=0.01]]]

EJ3 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

`	`Los 5 partidos más frecuentes en las encuestas son:

[Partido 2, Partido 1, Partido 3, Partido 4, Partido 5]

EJ4 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

`	`¿Todas las encuestas de estos países superan los 3500 encuestados?:

{Alemania=false, Austria=false, Belgica=false, Bulgaria=false, Chipre=false, Croacia=false, Dinamarca=true, Eslovaquia=true, Eslovenia=false, Espana=false, Estonia=false, Finlandia=false, Francia=false, Grecia=false, Hungria=false, Irlanda=false, Italia=false, Letonia=false, Lituania=false, Luxemburgo=false, Malta=false, Paises Bajos=false, Polonia=false, Portugal=false, Rumania=false, Suecia=false} 

EJ5 = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

`	`Países donde cada partido ha obtenido un porcentaje de votos superior a 90%:

{Partido 2=[Francia], Partido 1=[Alemania, Austria, Espana, Francia, Grecia, Irlanda, Italia, Letonia, Lituania, Paises Bajos, Portugal, Rumania, Suecia]} 

`	`Países donde cada partido ha obtenido un porcentaje de votos superior a 97%:

{Partido 1=[Francia, Grecia, Letonia, Lituania]} 
```



