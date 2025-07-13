package org.Interfaz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class BotonBaseCambiarPanelTest {

    private JFrame frame;
    private BotonPrueba boton;

    // Subclase mínima para testear BotonBase sin GUI real
    static class BotonPrueba extends BotonBase {
        public BotonPrueba(JFrame frame) {
            super(frame);
        }

        @Override
        public void alPresionar() {} // No lo usaremos
    }

    // PanelBase ficticio que devuelve un panel vacío
    static class PanelFalso extends PanelBase {
        private final JPanel panel;

        public PanelFalso(JFrame frame) {
            super(frame);
            panel = new JPanel();
        }

        @Override
        public JPanel obtenerPanel() {
            return panel;
        }
    }

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        boton = new BotonPrueba(frame);
    }

    @Test
    void cambiarPanelReemplazaElContenidoDelFrame() {
        PanelFalso nuevoPanel = new PanelFalso(frame);

        // El panel original no debe ser el nuevo
        JPanel original = (JPanel) frame.getContentPane();
        assertNotSame(nuevoPanel.obtenerPanel(), original);

        // Acción: cambiar el panel
        boton.cambiarPanel(nuevoPanel);

        // Verificación: ahora el contentPane debe ser el nuevo panel
        assertSame(nuevoPanel.obtenerPanel(), frame.getContentPane(),
                "El contentPane debe ser reemplazado por el nuevo panel");
    }
}