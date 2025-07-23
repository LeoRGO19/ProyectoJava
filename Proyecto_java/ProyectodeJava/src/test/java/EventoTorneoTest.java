import org.Logica.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventoTorneoTest {

    @Test
    public void testConstructor_CasoNormal() {
        TorneoEliminacionSimple torneo = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 4);
        Object datos = new Object();
        EventoTorneo evento = new EventoTorneo(TipoEvento.TORNEO_INICIADO, torneo, datos);

        assertNotNull(evento);
        assertEquals(TipoEvento.TORNEO_INICIADO, evento.getTipo());
        assertEquals(torneo, evento.getTorneo());
        assertEquals(datos, evento.getDatos());
    }

    @Test
    public void testConstructor_CasoLimiteDatosNulo() {
        TorneoEliminacionSimple torneo = new TorneoEliminacionSimple("Torneo Test", "FUTBOL", 4);
        EventoTorneo evento = new EventoTorneo(TipoEvento.TORNEO_INICIADO, torneo, null);

        assertNotNull(evento);
        assertEquals(TipoEvento.TORNEO_INICIADO, evento.getTipo());
        assertEquals(torneo, evento.getTorneo());
        assertNull(evento.getDatos());
    }
}