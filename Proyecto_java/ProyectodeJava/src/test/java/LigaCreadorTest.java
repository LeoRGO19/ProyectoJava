import org.Logica.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LigaCreadorTest {

    private final LigaCreador creador = new LigaCreador();

    // Método: crearTorneo
    // Camino 1: Caso normal - Parámetros válidos
    // Camino 2: Caso límite - maxParticipantes = 2
    // Camino 3: Caso de error - Nombre nulo
    // Camino 4: Caso de error - Nombre vacío
    // Camino 5: Caso de error - maxParticipantes < 2
    // Camino 6: Caso de error - Disciplina inválida
    @Test
    public void testCrearTorneo_CasoNormal() throws TorneoException {
        var torneo = creador.crearTorneo("Torneo Test", "FUTBOL", 4);
        assertNotNull(torneo);
        assertInstanceOf(TorneoLiga.class, torneo);
        assertEquals("Torneo Test", ((TorneoAbstracto) torneo).obtenerNombre());
    }
    @Test
    public void testCrearTorneo_CasoLimiteMinParticipantes() throws TorneoException {
        var torneo = creador.crearTorneo("Torneo Test", "FUTBOL", 2);
        assertNotNull(torneo);
        assertInstanceOf(TorneoLiga.class, torneo);
        assertEquals("Torneo Test", ((TorneoAbstracto) torneo).obtenerNombre());
    }
    @Test
    public void testCrearTorneo_NombreNulo() {
        assertThrows(TorneoException.class, () -> creador.crearTorneo(null, "FUTBOL", 4));
    }
    @Test
    public void testCrearTorneo_NombreVacio() {
        assertThrows(TorneoException.class, () -> creador.crearTorneo("", "FUTBOL", 4));
    }
    @Test
    public void testCrearTorneo_MaxParticipantesMenorA2() {
        assertThrows(TorneoException.class, () -> creador.crearTorneo("Torneo Test", "FUTBOL", 1));
    }
    @Test
    public void testCrearTorneo_DisciplinaInvalida() {
        assertThrows(TorneoException.class, () -> creador.crearTorneo("Torneo Test", "INVALIDA", 4));
    }
}