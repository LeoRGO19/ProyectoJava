import org.Logica.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IndividuoParticipanteTest {

    @Test
    public void testConstructor_CasoNormal() throws TorneoException {
        IndividuoParticipante participante = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");

        assertNotNull(participante);
        assertEquals("Juan", participante.obtenerNombre());
        assertEquals("Perez", participante.obtenerApellido());
        assertEquals(20, participante.obtenerEdad());
        assertEquals("juan@example.com", participante.obtenerContacto());
    }

    @Test
    public void testConstructor_CasoErrorNombreNulo() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante(null, "Perez", 20, "juan@gmail.com"));
    }

    @Test
    public void testConstructor_CasoErrorNombreVacio() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("", "Perez", 20, "juan@gmail.com"));
    }

    @Test
    public void testConstructor_CasoErrorApellidoNulo() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", null, 20, "juan@gmail.com"));
    }

    @Test
    public void testConstructor_CasoErrorApellidoVacio() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", "", 20, "juan@gmail.com"));
    }
    @Test
    public void testConstructor_CasoExtremoEdad18() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", "", 18, "juan@gmail.com"));
    }
    @Test
    public void testConstructor_CasoExtremoEdad100() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", "", 100, "juan@gmail.com"));
    }
    @Test
    public void testConstructor_CasoErrorEdadMayorA100() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", "", 102, "juan@gmail.com"));
    }

    @Test
    public void testConstructor_CasoErrorEdadNegativa() { //tambien cumple edad menor a 18
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", "Perez", -1, "juan@gmail.com"));
    }

    @Test
    public void testConstructor_CasoErrorContactoNulo() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", "Perez", 20, null));
    }

    @Test
    public void testConstructor_CasoErrorContactoVacio() {
        assertThrows(TorneoException.class, () -> new IndividuoParticipante("Juan", "Perez", 20, ""));
    }
}