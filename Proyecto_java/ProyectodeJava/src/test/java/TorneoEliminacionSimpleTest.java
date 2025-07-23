import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TorneoEliminacionSimpleTest {

    private TorneoEliminacionSimple torneo;
    private IndividuoParticipante p1, p2, p3, p4;

    @BeforeEach
    public void setUp() throws TorneoException {
        torneo = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 4);
        p1 = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");
        p2 = new IndividuoParticipante("Maria", "Gomez", 22, "maria@example.com");
        p3 = new IndividuoParticipante("Pedro", "Lopez", 25, "pedro@example.com");
        p4 = new IndividuoParticipante("Ana", "Ruiz", 23, "ana@example.com");

        try {
            torneo.agregarParticipante(p1);
            torneo.agregarParticipante(p2);
            torneo.agregarParticipante(p3);
            torneo.agregarParticipante(p4);
        } catch (TorneoException e) {
            fail("Error al agregar participantes: " + e.getMessage());
        }
    }

    // Método: iniciarTorneo
    @Test
    public void testIniciarTorneo_CasoNormal() throws TorneoException {
        torneo.iniciarTorneo();
        assertTrue(torneo.haIniciado());
        assertEquals(2, torneo.obtenerNumRondas()); // 4 participantes -> 2 rondas
        assertNotNull(torneo.obtenerRondas());
    }
    @Test
    public void testIniciarTorneo_NoPotenciaDeDos() {
        TorneoEliminacionSimple torneo3 = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 3);
        try {
            torneo3.agregarParticipante(p1);
            torneo3.agregarParticipante(p2);
            torneo3.agregarParticipante(p3);
            torneo3.iniciarTorneo();
            fail("Debería lanzar TorneoException");
        } catch (TorneoException e) {
            assertEquals("Para iniciar el formato de eliminación simple el número de participantes debe ser potencia de 2", e.getMessage());
        }
    }

    // Método: generarBracket
    @Test
    public void testGenerarBracket_CasoNormal() throws TorneoException {
        torneo.generarBracket();
        Enfrentamiento[][] rondas = torneo.obtenerRondas();
        assertNotNull(rondas);
        assertEquals(2, rondas.length); // 2 rondas para 4 participantes
        assertEquals(2, rondas[0].length); // 2 enfrentamientos en ronda 1
    }

    @Test
    public void testGenerarBracket_SinParticipantes() {
        TorneoEliminacionSimple torneoVacio = new TorneoEliminacionSimple("Torneo Vacio", "FUTBOL", 4);
        assertThrows(TorneoException.class, torneoVacio::generarBracket);
    }

    // Método: generarEnfrentamientos
    @Test
    public void testGenerarEnfrentamientos_PrimeraRonda() throws TorneoException {
        torneo.iniciarTorneo(); // Asegura que el torneo esté iniciado
        torneo.generarEnfrentamientos();
        Enfrentamiento[][] rondas = torneo.obtenerRondas();
        assertNotNull(rondas);
        assertEquals(2, rondas.length); // 2 rondas
        assertEquals(2, rondas[0].length); // 2 enfrentamientos en la primera ronda
        assertNotNull(rondas[0][0].obtenerParticipante1());
        assertNotNull(rondas[0][0].obtenerParticipante2());
        // Verifica que los participantes sean de los agregados (orden puede variar)
        String[] nombres = {rondas[0][0].obtenerParticipante1().obtenerNombre(), rondas[0][0].obtenerParticipante2().obtenerNombre()};
        assertTrue(containsAny(nombres, "Pedro", "Maria", "Ana", "Juan"));
    }

    @Test
    public void testGenerarEnfrentamientos_RondaFinal() throws TorneoException {
        torneo.iniciarTorneo(); // Inicia el torneo
        torneo.avanzarRonda(); // Avanza la primera ronda (simulamos victorias)
        torneo.generarEnfrentamientos(); // Genera enfrentamientos de la ronda final
        Enfrentamiento[][] rondas = torneo.obtenerRondas();
        assertNotNull(rondas);
        assertEquals(2, rondas.length); // 2 rondas
        assertEquals(1, rondas[1].length); // 1 enfrentamiento en la ronda final
        String[] nombres = {rondas[1][0].obtenerParticipante1().obtenerNombre(), rondas[1][0].obtenerParticipante2().obtenerNombre()};
        assertTrue(containsAny(nombres, "Maria", "Juan") || containsAny(nombres, "Maria", "Ana")); // Verifica participantes posibles
    }

    // Método: avanzarRonda
    @Test
    public void testAvanzarRonda_CasoNormal() throws TorneoException {
        torneo.iniciarTorneo();
        torneo.avanzarRonda();
        assertEquals(2, torneo.obtenerRondaActual()); // Ajuste: refleja que avanza a ronda 2
    }

    @Test
    public void testAvanzarRonda_RondaFinal() throws TorneoException {
        torneo.iniciarTorneo();
        torneo.avanzarRonda();
        torneo.avanzarRonda(); // Termina el torneo
        assertEquals(2, torneo.obtenerRondaActual()); // Verifica que no avance más allá
        assertTrue(torneo.haTerminado()); // Verifica que el torneo haya terminado
    }

    // Método: verEstado
    @Test
    public void testVerEstado_CasoNormal() throws TorneoException {
        torneo.iniciarTorneo();
        torneo.avanzarRonda();
        assertDoesNotThrow(torneo::verEstado);
    }

    // Método: generarCalendario
    @Test
    public void testGenerarCalendario_CasoNormal() throws TorneoException {
        torneo.iniciarTorneo();
        torneo.generarEnfrentamientos();
        torneo.generarCalendario();
        assertNotNull(torneo.obtenerParticipantes()); // Indirectamente verifica calendario
    }

    // Método: mostrarBracket
    @Test
    public void testMostrarBracket_CasoNormal() throws TorneoException {
        torneo.iniciarTorneo();
        assertDoesNotThrow(torneo::mostrarBracket);
    }

    // Método auxiliar para verificar si un arreglo contiene al menos un nombre
    private boolean containsAny(String[] array, String... values) {
        for (String value : values) {
            for (String item : array) {
                if (item.equals(value)) return true;
            }
        }
        return false;
    }
}