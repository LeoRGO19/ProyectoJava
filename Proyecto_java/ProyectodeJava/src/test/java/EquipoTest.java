import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquipoTest {

    private Equipo equipo;

    @BeforeEach
    public void setUp() throws TorneoException {
        equipo = new Equipo("Equipo Test", "equipo.test@gmail.com");
    }
    // Método: Constructor
    // Camino 1: Caso normal
    // Camino 2: Caso de error - Nombre nulo
    // Camino 3: Caso de error - Contacto nulo
    @Test
    public void testConstructor_CasoNormal() throws TorneoException {
        Equipo equipo1 = new Equipo("equipo","equipo@gmail.com");
    }
    @Test
    public void testConstructor_CasoErrorNombreNulo() {
        assertThrows(TorneoException.class, () -> new Equipo(null, "equipo_1@gmail.com"));
    }
    @Test
    public void testConstructor_CasoErrorContactoNulo() {
        assertThrows(TorneoException.class, () -> new Equipo("Equipo1", null));
    }

    // Método: agregarMiembro
    // Camino 1: Caso normal - Miembro válido
    // Camino 2: Caso de error - Miembro nulo
    @Test
    public void testAgregarMiembro_CasoNormal() throws TorneoException {
        IndividuoParticipante miembro = new IndividuoParticipante("Juan", "Perez", 20, "juan@gmail.com");
        equipo.agregarMiembro(miembro);
        assertEquals(1, equipo.obtenerListaEquipo().size());
        assertTrue(equipo.obtenerListaEquipo().contains(miembro));
    }
    @Test
    public void testAgregarMiembro_MiembroNulo() {
        assertThrows(TorneoException.class, () -> equipo.agregarMiembro(null));
    }

}