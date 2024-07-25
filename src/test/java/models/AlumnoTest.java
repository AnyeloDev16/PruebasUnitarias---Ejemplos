package models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class AlumnoTest {

    Alumno alumno;

    @BeforeEach
    void setUp() {
        this.alumno = new Alumno("Anyelo");
    }
    
    @AfterEach
    void tearDown(){
        System.out.println("Metodo Test finalizado");
    }

    @Nested
    class testGetAndSetter{
        
        @Test
        @DisplayName("Testeando el Set Nombre")
        void testSetNombre() {
            // Given
            String nuevoNombre = "Juan";

            // When
            alumno.setNombre(nuevoNombre);

            // Then
            assertEquals(nuevoNombre, alumno.getNombre());
        }

        @RepeatedTest(3)
        void testGetNombre() {
            // Given
            String nombreEsperado = "Anyelo";

            // Then
            assertEquals(nombreEsperado, alumno.getNombre());
        }

        @Test
        void testGetNotas() {
            // Given
            List<Integer> notasEsperadas = Arrays.asList(12, 14, 13);
            alumno.setNotas(notasEsperadas);

            // Then
            assertEquals(notasEsperadas, alumno.getNotas());
        }

    }

    @Nested
    class testCalcularPromedio {

        @Test
        void testCalcularPromedio() {
            //Give - Declarar           
            alumno.setNotas(Arrays.asList(12, 14, 13, 14, 12, 13));
            double valorEsperado = 13;
            double valorActual;
            //When - Hacer
            valorActual = alumno.calcularPromedio();
            //Then - Afirmar
            assertEquals(valorEsperado, valorActual);
        }

        @Test
        void testCalcularPromedioSiNotas() {
            //Give - Declarar
            alumno.setNotas(Collections.EMPTY_LIST);
            double valorEsperado = 0;
            double valorActual;
            //When - Hacer
            valorActual = alumno.calcularPromedio();
            //Then - Afirmar
            assertEquals(valorEsperado, valorActual);
        }

        @Test
        void testCalcularPromedioConUnaNota() {
            //Give - Declarar
            alumno.setNotas(Arrays.asList(12));
            double valorEsperado = 12;
            double valorActual;
            //When - Hacer
            valorActual = alumno.calcularPromedio();
            //Then - Afirmar
            assertEquals(valorEsperado, valorActual);
        }
    }

    @Nested
    class testAgregarNota {

        @Test
        @Tag("AgregarNota")
        void testAgregarUnaNota() {
            //Given - declar
            boolean valorActual;
            Integer nota = 12;
            //When - Hacer
            valorActual = alumno.agregarNota(nota);
            //then - Afirmar
            assertTrue(valorActual);
        }

        @Test
        @Tag("AgregarNota")
        void testAgregarUnaNotaMayorA20() {
            //Given - declar
            boolean valorActual;
            Integer nota = 23;
            //When - Hacer
            valorActual = alumno.agregarNota(nota);
            //then - Afirmar
            assertFalse(valorActual);
        }

        @Test
        @Tag("AgregarNota")
        void testAgregarUnaNotaIgualA20() {
            //Given - declar
            boolean valorActual;
            Integer nota = 20;
            //When - Hacer
            valorActual = alumno.agregarNota(nota);
            //then - Afirmar
            assertTrue(valorActual);
        }

        @Test
         @Tag("AgregarNota")
        void testAgregarUnaNotaMenorA0() {
            //Given - declar
            boolean valorActual;
            Integer nota = -1;
            //When - Hacer
            valorActual = alumno.agregarNota(nota);
            //then - Afirmar
            assertFalse(valorActual);
        }

        @Test
        @Tag("AgregarNota")
        void testAgregarUnaNotaIgualA0() {
            //Given - declar
            boolean valorActual;
            Integer nota = 0;
            //When - Hacer
            valorActual = alumno.agregarNota(nota);
            //then - Afirmar
            assertTrue(valorActual);
        }

        @Test
        @Tag("AgregarNota")
        void testAgregarUnaNotaNull() {
            //Given - declar
            boolean valorActual;
            Integer nota = null;
            //When - Hacer
            valorActual = alumno.agregarNota(nota);
            //then - Afirmar
            assertAll(
                    
                    () -> assertNull(nota),
                    () -> assertFalse(valorActual)
                    
            );
        }

        @ParameterizedTest
        @ValueSource(ints = {12,-1,0,20,43})
        @Tag("AgregarNota")
        void testAgregarNotasConParameterized(int notas) {
            //Given - declar
            boolean valorActual;
            //When - Hacer
            valorActual = alumno.agregarNota(notas);
            //then - Afirmar
            assertTrue(valorActual);
        }
        
        @DisplayName("Probando CSV explicito")
        @ParameterizedTest
        @CsvSource({"12","-1","0","20","43"})
        @Tag("AgregarNota")
        void testAgregarNotasConParameterizedCsv(int notas) {
            //Given - declar
            boolean valorActual;
            //When - Hacer
            valorActual = alumno.agregarNota(notas);
            //then - Afirmar
            assertTrue(valorActual);
        }
        
        @DisplayName("Probando CSV file")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        @Tag("AgregarNota")
        void testAgregarNotasConParameterizedCsvFile(int notas) {
            //Given - declar
            boolean valorActual;
            //When - Hacer
            valorActual = alumno.agregarNota(notas);
            //then - Afirmar
            assertTrue(valorActual);
        }
        
    }

}
