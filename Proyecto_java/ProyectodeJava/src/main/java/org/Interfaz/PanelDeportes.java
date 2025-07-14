package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la sección de deportes dentro de la aplicación.
 * Extiende {@link PanelBase} y contiene botones para diferentes deportes.
 */
public class PanelDeportes extends PanelBase {

    /**
     * Constructor que inicializa el panel de deportes con el JFrame principal.
     * Configura el panel y agrega los botones de deportes.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelDeportes(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    /**
     * Configura las propiedades visuales del panel:
     * - Layout nulo para posicionamiento manual.
     * - Color de fondo morado oscuro.
     */
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(70, 45, 90));
    }

    /**
     * Agrega los botones de los diferentes deportes al panel:
     * - Fútbol, Básquet, Tenis, Ping Pong, Tiro con arco.
     * - Botón "Volver" para regresar a la pantalla anterior.
     */
    @Override
    public void agregarComponentes() {
        panel.add(new BotonFutbol(frame));
        panel.add(new BotonBasket(frame));
        panel.add(new BotonTenis(frame));
        panel.add(new BotonPingPong(frame));
        panel.add(new BotonTiroConArco(frame));
        panel.add(new BotonVolver(frame));
    }

    /**
     * Obtiene el {@link JPanel} principal de este panel de deportes.
     *
     * @return el JPanel que contiene todos los componentes.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}