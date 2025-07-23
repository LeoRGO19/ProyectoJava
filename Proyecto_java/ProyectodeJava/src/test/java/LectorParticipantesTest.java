import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LectorParticipantesTest {

    private static final String TEST_FILE = "participantes.txt";

    @BeforeEach
    public void setUp() {}

    // Método: leerParticipantes
    // Camino 1: Caso normal - Archivo con IndividuoParticipante (sin %)
    // Camino 2: Caso normal - Archivo con Equipo (con %)
    // Camino 3: Caso de error - Archivo no encontrado
    // Camino 4: Caso de error - Formato inválido (edad no numérica)
    @Test
    public void testLeerParticipantes_CasoNormalIndividuos() throws TorneoException, IOException {
        ArrayList<Participante> participantes = LectorParticipantes.leerParticipantes("4_Individuos.txt");
        assertEquals(4, participantes.size());
        assertInstanceOf(IndividuoParticipante.class, participantes.getFirst());
        assertEquals("Elena", participantes.getFirst().obtenerNombre());
        assertEquals("Martínez", ((IndividuoParticipante) participantes.getFirst()).obtenerApellido());
        assertEquals(25, ((IndividuoParticipante) participantes.getFirst()).obtenerEdad());
        assertEquals("elena.martinez@outlook.com", participantes.getFirst().obtenerContacto());
    }

    @Test
    public void testLeerParticipantes_CasoNormalEquipo() throws TorneoException {
        ArrayList<Participante> participantes = LectorParticipantes.leerParticipantes("2_Equipos.txt");
        assertEquals(2, participantes.size());
        assertInstanceOf(Equipo.class, participantes.getFirst());
        Equipo equipo = (Equipo) participantes.getFirst();
        assertEquals("Equipo 1", equipo.obtenerNombre());
        assertEquals(2, equipo.obtenerListaEquipo().size());
        assertEquals("Juan", equipo.obtenerListaEquipo().get(0).obtenerNombre());
        assertEquals("María", equipo.obtenerListaEquipo().get(1).obtenerNombre());
    }
    @Test
    public void testLeerParticipantes_CasoErrorArchivoNoEncontrado() {
        assertThrows(TorneoException.class, () -> LectorParticipantes.leerParticipantes("archivo_inexistente.txt"));
    }
}