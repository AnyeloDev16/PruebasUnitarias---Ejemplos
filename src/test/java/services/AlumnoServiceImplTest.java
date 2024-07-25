package services;

import java.util.Arrays;
import java.util.List;
import models.Alumno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.AlumnoRepository;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceImplTest {

    @Mock
    AlumnoRepository alumnoRepository;

    @InjectMocks
    AlumnoServiceImpl alumnoService; //Tiene que ser una clase que pueda ser instanciada

    List<Alumno> listaAlumnos;

    @BeforeEach
    void setUp() {

        //MockitoAnnotations.openMocks(this);
        listaAlumnos = Arrays.asList(new Alumno("Anyelo"), new Alumno("Pepe"), new Alumno("Jose"));
        //alumnoRepository = mock(AlumnoRepository.class);
        //alumnoService = new AlumnoServiceImpl(alumnoRepository);       

    }

    @Nested
    class TestFinAlumnoForName {

        @Test
        void TestObtenerAlumnoPorNombre() {
            //given
            String nombreAlumno = "Anyelo";
            //when
            when(alumnoRepository.findAll()).thenReturn(listaAlumnos);
            Alumno alumno = alumnoService.findAlumnoForName(nombreAlumno);
            //then
            assertAll(
                    () -> assertNotNull(alumno),
                    () -> assertEquals(nombreAlumno, alumno.getNombre())
            );

        }

        @Test
        void TestObtenerAlumnoPorNombreEquivocado() {
            //given
            String nombreAlumno = "Pablo";
            //when
            when(alumnoRepository.findAll()).thenReturn(listaAlumnos);
            Alumno alumno = alumnoService.findAlumnoForName(nombreAlumno);
            //then
            assertAll(
                    () -> assertNull(alumno)
            );

        }

        @Test
        void TestObtenerAlumnoPorNombreNull() {
            //given
            String nombreAlumno = null;
            //when
            lenient().when(alumnoRepository.findAll()).thenReturn(listaAlumnos);
            Alumno alumno = alumnoService.findAlumnoForName(nombreAlumno);
            //then
            verify(alumnoRepository, never()).findAll();
            assertAll(
                    () -> assertNull(alumno)
            );

        }

    }

    @Nested
    class TestSaveAlumno {

        @Test
        void testGuardarAlumno() {

            //Given
            Alumno alumnoGuardar = new Alumno("Tito");
            //When
            when(alumnoRepository.save(any(Alumno.class))).thenReturn(true);
            boolean isSave = alumnoService.saveAlumno(alumnoGuardar);
            //Then
            assertTrue(isSave);
        }

        @Test
        void testGuardarAlumnoNull() {
            //Given
            Alumno alumnoGuardar = null;
            //When
            lenient().when(alumnoRepository.save(null)).thenReturn(false);
            boolean isSave = alumnoService.saveAlumno(alumnoGuardar);
            //Then
            assertAll(
                    () -> assertFalse(isSave),
                    () -> verify(alumnoRepository, never()).save(alumnoGuardar)
            );
        }

        @Test
        void testGuardarAlumnoNombreNull() {
            // Given
            Alumno alumnoGuardar = new Alumno(null);

            // When
            lenient().when(alumnoRepository.save(alumnoGuardar)).thenReturn(false);
            boolean isSave = alumnoService.saveAlumno(alumnoGuardar);

            // Then
            assertAll(
                    () -> assertFalse(isSave),
                    () -> verify(alumnoRepository, never()).save(alumnoGuardar)
            );

        }

        @Test
        void testGuardarAlumnoNombreVacio() {
            // Given
            Alumno alumnoGuardar = new Alumno("");

            // When
            lenient().when(alumnoRepository.save(alumnoGuardar)).thenReturn(false);
            boolean isSave = alumnoService.saveAlumno(alumnoGuardar);

            // Then
            assertAll(
                    () -> assertFalse(isSave),
                    () -> verify(alumnoRepository, never()).save(alumnoGuardar)
            );
        }

        @Test
        void testGuardarAlumnoNombreConEsapcio() {
            // Given
            Alumno alumnoGuardar = new Alumno(" ");

            // When
            lenient().when(alumnoRepository.save(alumnoGuardar)).thenReturn(false);
            boolean isSave = alumnoService.saveAlumno(alumnoGuardar);

            // Then
            assertAll(
                    () -> assertFalse(isSave),
                    () -> verify(alumnoRepository, never()).save(alumnoGuardar)
            );
        }

    }

}
