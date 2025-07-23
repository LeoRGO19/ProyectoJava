import org.Logica.*;
import org.Logica.TipoEvento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorNotificacionesTest {

    private GestorNotificaciones notificador;
    private boolean notificado;
    private Enfrentamiento enfrentamientoNotificado;

    @BeforeEach
    public void setUp() {
        notificado = false;
        enfrentamientoNotificado = null;
        TorneoEliminacionSimple torneo = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 4);
        notificador = new GestorNotificaciones(torneo);

        // Registrar el observador antes de las pruebas
        torneo.registrarObservador(new org.Logica.ObservadorTorneo() {
            @Override
            public void actualizar(org.Logica.EventoTorneo evento) {
                if (evento.getTipo() == TipoEvento.RESULTADOS_ACTUALIZADOS && evento.getDatos() instanceof Enfrentamiento) {
                    notificado = true;
                    enfrentamientoNotificado = (Enfrentamiento) evento.getDatos();
                }
            }
        });
    }

    // Método: notificarResultadosActualizados
    // Camino 1: Caso normal - Notifica correctamente
    // Camino 2: Caso de error - Enfrentamiento nulo
    // Camino 2 (alternativo): Caso de error - Torneo nulo (simulado en constructor)
    @Test
    public void testNotificarResultadosActualizados_CasoNormal() {
        TorneoEliminacionSimple torneo = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 4);
        Enfrentamiento enfrentamiento = new Enfrentamiento(null, null, torneo);
        notificador.notificarResultadosActualizados(enfrentamiento);
        assertTrue(notificado);
        assertEquals(enfrentamiento, enfrentamientoNotificado);
    }
    @Test
    public void testNotificarResultadosActualizados_EnfrentamientoNulo() {
        notificador.notificarResultadosActualizados(null);
        // Verificamos que no falle y que notificado permanezca false si no hay evento válido
        assertFalse(notificado); // Asumimos que null no activa la notificación
    }
    @Test
    public void testNotificarResultadosActualizados_TorneoNulo() {
        GestorNotificaciones notificadorNulo = new GestorNotificaciones(null);
        assertThrows(NullPointerException.class, () -> notificadorNulo.notificarResultadosActualizados(new Enfrentamiento(null, null, new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 4))));
    }
}