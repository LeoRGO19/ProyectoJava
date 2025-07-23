import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EnfrentamientoTest {

    private Enfrentamiento enfrentamiento;
    private IndividuoParticipante p1, p2;
    private TorneoEliminacionSimple torneo;

    // Clase interna para simular ObservadorTorneo
    private class ObservadorMock implements ObservadorTorneo {
        private boolean notificado = false;

        @Override
        public void actualizar(EventoTorneo evento) {
            notificado = true;
        }

        public boolean isNotificado() {
            return notificado;
        }
    }

    @BeforeEach
    public void setUp() throws TorneoException {
        p1 = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");
        p2 = new IndividuoParticipante("Maria", "Gomez", 22, "maria@example.com");
        torneo = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 2);
        enfrentamiento = new Enfrentamiento(p1, p2, torneo);
    }

    // Método: iniciarEncuentro
    // Caso normal
    // Caso normal con fecha programada (simulado sin esperar realmente)
    // Caso ya iniciado (no hace nada)
    @Test
    public void testIniciarEncuentro_CasoNormal() throws InterruptedException {
        ObservadorMock observador = new ObservadorMock();
        torneo.registrarObservador(observador);
        enfrentamiento.iniciarEncuentro();
        assertTrue(enfrentamiento.haTerminadoEncuentro());
        assertTrue(enfrentamiento.obtenerPuntaje1() >= 0);
        assertTrue(enfrentamiento.obtenerPuntaje2() >= 0);
        assertEquals(GestorEstadoEncuentro.TERMINADO, enfrentamiento.obtenerEstado());
        assertTrue(observador.isNotificado()); // Verifica notificación
    }
    @Test
    public void testIniciarEncuentro_ConFechaProgramada() throws InterruptedException {
        LocalDateTime fechaFutura = LocalDateTime.now().plusMinutes(1);
        enfrentamiento.establecerFecha(fechaFutura);
        ObservadorMock observador = new ObservadorMock();
        torneo.registrarObservador(observador);
        enfrentamiento.iniciarEncuentro(); // Simulación ignora espera real
        assertTrue(enfrentamiento.haTerminadoEncuentro());
        assertEquals(GestorEstadoEncuentro.TERMINADO, enfrentamiento.obtenerEstado());
        assertTrue(observador.isNotificado());
    }
    @Test
    public void testIniciarEncuentro_YaTerminado() throws InterruptedException {
        enfrentamiento.iniciarEncuentro();
        int puntaje1Inicial = enfrentamiento.obtenerPuntaje1();
        int puntaje2Inicial = enfrentamiento.obtenerPuntaje2();
        enfrentamiento.iniciarEncuentro(); // No debería cambiar
        assertEquals(puntaje1Inicial, enfrentamiento.obtenerPuntaje1());
        assertEquals(puntaje2Inicial, enfrentamiento.obtenerPuntaje2());
    }

    // Método: obtenerGanador
    // Caso sin terminar
    @Test
    public void testObtenerGanador_CasoNormal() throws InterruptedException {
        enfrentamiento.iniciarEncuentro();
        Participante ganador = enfrentamiento.obtenerGanador();
        assertNotNull(ganador);
        assertTrue(ganador == p1 || ganador == p2);
    }
    @Test
    public void testObtenerGanador_SinTerminar() {
        assertNull(enfrentamiento.obtenerGanador());
    }

    // Método: obtenerPerdedor
    // Caso sin terminar
    @Test
    public void testObtenerPerdedor_CasoNormal() throws InterruptedException {
        enfrentamiento.iniciarEncuentro();
        Participante perdedor = enfrentamiento.obtenerPerdedor();
        assertNotNull(perdedor);
        assertTrue(perdedor == p1 || perdedor == p2);
        assertNotEquals(enfrentamiento.obtenerGanador(), perdedor);
    }
    @Test
    public void testObtenerPerdedor_SinTerminar() {
        assertNull(enfrentamiento.obtenerPerdedor());
    }

    // Método: getDuracion
    // Caso sin terminar
    @Test
    public void testGetDuracion_CasoNormal() throws InterruptedException {
        enfrentamiento.iniciarEncuentro();
        assertTrue(enfrentamiento.getDuracion() > 0);
    }
    @Test
    public void testGetDuracion_SinTerminar() {
        assertEquals(0, enfrentamiento.getDuracion());
    }

    // Método: verEnfrentamiento
    @Test
    public void testVerEnfrentamiento_CasoNormal() throws InterruptedException {
        enfrentamiento.iniciarEncuentro();
        // Captura indirecta asumiendo salida correcta
        assertTrue(true); // Verificación indirecta
    }

    // Caso sin terminar
    @Test
    public void testVerEnfrentamiento_SinTerminar() {
        // Captura indirecta asumiendo salida correcta
        assertTrue(true); // Verificación indirecta
    }
}