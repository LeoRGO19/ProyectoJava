import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorEstadoEncuentroTest {

    private GestorEstadoEncuentro gestor;

    @BeforeEach
    public void setUp() {
        gestor = new GestorEstadoEncuentro();
    }

    // Método: iniciar
    // Camino 1: Caso normal - Cambia a EN_CURSO
    @Test
    public void testIniciar_CasoNormal() {
        gestor.iniciar();
        assertEquals(GestorEstadoEncuentro.EN_CURSO, gestor.obtenerEstado());
    }

    // Método: terminar
    // Camino 1: Caso normal - Cambia a TERMINADO
    @Test
    public void testTerminar_CasoNormal() {
        gestor.iniciar(); // Primero inicia para simular transición
        gestor.terminar();
        assertEquals(GestorEstadoEncuentro.TERMINADO, gestor.obtenerEstado());
    }

    // Método: obtenerEstado
    // Camino 1: Caso normal - Retorna estado actual
    @Test
    public void testObtenerEstado_CasoNormal() {
        assertEquals(GestorEstadoEncuentro.PENDIENTE, gestor.obtenerEstado());
        gestor.iniciar();
        assertEquals(GestorEstadoEncuentro.EN_CURSO, gestor.obtenerEstado());
        gestor.terminar();
        assertEquals(GestorEstadoEncuentro.TERMINADO, gestor.obtenerEstado());
    }

    // Método: estaPendiente
    // Camino 1: Caso normal - estado == PENDIENTE
    // Camino 2: Caso normal - estado != PENDIENTE
    @Test
    public void testEstaPendiente_CasoPendiente() {
        assertTrue(gestor.estaPendiente());
    }
    @Test
    public void testEstaPendiente_CasoNoPendiente() {
        gestor.iniciar();
        assertFalse(gestor.estaPendiente());
    }

    // Método: estaTerminado
    // Camino 1: Caso normal - estado == TERMINADO
    // Camino 2: Caso normal - estado != TERMINADO
    @Test
    public void testEstaTerminado_CasoTerminado() {
        gestor.iniciar();
        gestor.terminar();
        assertTrue(gestor.estaTerminado());
    }
    @Test
    public void testEstaTerminado_CasoNoTerminado() {
        assertFalse(gestor.estaTerminado());
    }
}