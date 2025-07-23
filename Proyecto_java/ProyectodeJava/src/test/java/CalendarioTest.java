import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarioTest {

    private Calendario calendario;
    private TorneoEliminacionSimple torneo;

    @BeforeEach
    public void setUp() {
        calendario = new Calendario();
        torneo = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 4);
    }

    // Método: agregarEnfrentamiento
    // Camino 1: Caso normal - Enfrentamiento válido, índice positivo
    // Camino 2: Caso límite - Índice = 0
    // Camino 3: Caso de error - Enfrentamiento nulo
    @Test
    public void testAgregarEnfrentamiento_CasoNormal() throws Exception {
        IndividuoParticipante p1 = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");
        IndividuoParticipante p2 = new IndividuoParticipante("Maria", "Gonzalez", 22, "maria@example.com");
        Enfrentamiento enfrentamiento = new Enfrentamiento(p1, p2, torneo);
        calendario.agregarEnfrentamiento(enfrentamiento, 1);
        assertEquals(1, calendario.obtenerEnfrentamientos().size());
        assertNotNull(enfrentamiento.obtenerFecha());
        assertTrue(enfrentamiento.obtenerFecha().isAfter(calendario.obtenerFechaInicio()));
    }


    @Test
    public void testAgregarEnfrentamiento_CasoLimiteIndiceCero() throws Exception {
        IndividuoParticipante p1 = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");
        IndividuoParticipante p2 = new IndividuoParticipante("Maria", "Gonzalez", 22, "maria@example.com");
        Enfrentamiento enfrentamiento = new Enfrentamiento(p1, p2, torneo);
        calendario.agregarEnfrentamiento(enfrentamiento, 0);
        assertEquals(1, calendario.obtenerEnfrentamientos().size());
        assertEquals(calendario.obtenerFechaInicio(), enfrentamiento.obtenerFecha());
    }

    @Test
    public void testAgregarEnfrentamiento_EnfrentamientoNulo() {
        assertThrows(NullPointerException.class, () -> calendario.agregarEnfrentamiento(null, 1));
    }

    // Método: mostrarCalendario
    // Camino 1: Lista de enfrentamientos no vacía
    // Camino 2: Lista de enfrentamientos vacía
    // Caso límite: Lista con un solo enfrentamiento
    @Test
    public void testMostrarCalendario_CasoNormalListaNoVacia() throws Exception {
        IndividuoParticipante p1 = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");
        IndividuoParticipante p2 = new IndividuoParticipante("Maria", "Gonzalez", 22, "maria@example.com");
        Enfrentamiento enfrentamiento = new Enfrentamiento(p1, p2, torneo);
        calendario.agregarEnfrentamiento(enfrentamiento, 1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calendario.mostrarCalendario();
        assertTrue(outContent.toString().contains("Calendario del Torneo:"));
        assertTrue(outContent.toString().contains(p1.obtenerNombre()));
        assertTrue(outContent.toString().contains(p2.obtenerNombre()));
    }

    @Test
    public void testMostrarCalendario_CasoNormalListaVacia() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calendario.mostrarCalendario();
        assertTrue(outContent.toString().contains("Calendario del Torneo:"));
        assertFalse(outContent.toString().contains("vs")); // No hay enfrentamientos
    }

    @Test
    public void testMostrarCalendario_CasoLimiteUnEnfrentamiento() throws Exception {
        IndividuoParticipante p1 = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");
        IndividuoParticipante p2 = new IndividuoParticipante("Maria", "Gonzalez", 22, "maria@example.com");
        Enfrentamiento enfrentamiento = new Enfrentamiento(p1, p2, torneo);
        calendario.agregarEnfrentamiento(enfrentamiento, 1);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calendario.mostrarCalendario();
        assertEquals(1, calendario.obtenerEnfrentamientos().size());
        assertTrue(outContent.toString().contains(p1.obtenerNombre()));
    }
}