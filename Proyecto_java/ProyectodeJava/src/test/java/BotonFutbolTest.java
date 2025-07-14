

import org.Interfaz.BotonFutbol;
import org.Interfaz.Navegador;
import org.Interfaz.PanelDeportes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class BotonFutbolTest {

    private BotonFutbol boton;
    private JFrame dummyFrame;

    @BeforeEach
    public void setUp() {
        dummyFrame = new JFrame(); // No se mostrará, solo para cumplir con el constructor
        boton = new BotonFutbol(dummyFrame);
        // Asegúrate de que historial esté limpio
        Navegador.historial.clear();
        Navegador.palabra = "";
    }

    /**
     * Testea que el método configurar() coloque correctamente los valores de posición, color, cursor, etc.
     */
    @Test
    public void testConfigurar() {
        boton.configurar();

        assertEquals(650, boton.getX());
        assertEquals(150, boton.getY());
        assertEquals(200, boton.getWidth());
        assertEquals(200, boton.getHeight());

        assertEquals(Color.BLACK, boton.getBackground());
        assertEquals(Color.BLACK, boton.getForeground());
        assertEquals(Cursor.HAND_CURSOR, boton.getCursor().getType());


    }

    /**
     * Testea que al presionar el botón, se modifique la palabra del Navegador
     * y se actualice el historial correctamente.
     */
    @Test
    public void testAlPresionar() {
        boton.alPresionar();

        assertEquals("FUTBOL", Navegador.palabra);
        assertFalse(Navegador.historial.isEmpty());
        assertTrue(Navegador.historial.peek() instanceof PanelDeportes);

    }
}
