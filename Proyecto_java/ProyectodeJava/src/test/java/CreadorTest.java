import org.Logica.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreadorTest {

    private Creador creador = new Creador() {
        @Override
        public Torneo crearTorneo(String nombre, String disciplina, int maxParticipantes) {
            return null; // Implementación vacía para test
        }
    };

    // Método: validarParametros
    // Camino 1: Caso normal - Todos los parámetros válidos
    // Camino 2: Caso límite - maxParticipantes = 2
    // Camino 3: Caso de error - Nombre nulo
    // Camino 4: Caso de error - Nombre vacío
    // Camino 5: Caso de error - maxParticipantes < 2
    // Camino 6: Caso de error - Disciplina inválida
    @Test
    public void testValidarParametros_CasoNormal() throws TorneoException {
        creador.validarParametros("Torneo Test", "FUTBOL", 4);
        // No lanza excepción, test pasa
    }

    @Test
    public void testValidarParametros_CasoLimiteMinParticipantes() throws TorneoException {
        creador.validarParametros("Torneo Test", "FUTBOL", 2);
        // No lanza excepción, test pasa
    }

    @Test
    public void testValidarParametros_NombreNulo() {
        assertThrows(TorneoException.class, () -> creador.validarParametros(null, "FUTBOL", 4));
    }

    @Test
    public void testValidarParametros_NombreVacio() {
        assertThrows(TorneoException.class, () -> creador.validarParametros("", "FUTBOL", 4));
    }

    @Test
    public void testValidarParametros_MaxParticipantesMenorA2() {
        assertThrows(TorneoException.class, () -> creador.validarParametros("Torneo Test", "FUTBOL", 1));
    }

    @Test
    public void testValidarParametros_DisciplinaInvalida() {
        assertThrows(TorneoException.class, () -> creador.validarParametros("Torneo Test", "INVALIDA", 4));
    }
}