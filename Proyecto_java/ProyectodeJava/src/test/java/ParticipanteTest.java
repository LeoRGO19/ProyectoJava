import org.Logica.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParticipanteTest {

    // Clase concreta para pruebas
    private static class ParticipanteTestImpl extends Participante {
        public ParticipanteTestImpl(String nombre, String contacto) throws TorneoException {
            super(nombre, contacto);
        }

        @Override
        public int obtenerPuntaje() {
            return 0; // Implementación mínima
        }
    }

    // Método: Constructor
    // Camino 1: Caso normal - Nombre y contacto válidos
    // Camino 2: Caso de error - Nombre nulo
    // Camino 3: Caso de error - Contacto nulo
    @Test
    public void testConstructor_CasoNormal() throws TorneoException {
        Participante participante = new ParticipanteTestImpl("participante1", "p1@gmail.com");
        assertNotNull(participante);
        assertEquals("participante1", participante.obtenerNombre());
        assertEquals("p1@gmail.com", participante.obtenerContacto());
    }
    @Test
    public void testConstructor_NombreNulo() {
        assertThrows(TorneoException.class, () -> new ParticipanteTestImpl(null, "p1@gmail.com"));
    }
    @Test
    public void testConstructor_ContactoNulo() {
        assertThrows(TorneoException.class, () -> new ParticipanteTestImpl("participante1", null));
    }
}