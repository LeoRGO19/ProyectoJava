package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de confirmación que extiende {@link PanelBase}.
 * Contiene botones para volver y confirmar una acción dentro de la interfaz.
 */
public class PanelConfirmacion extends PanelBase {

    /**
     * Constructor que inicializa el panel de confirmación con el JFrame principal.
     * Configura el panel y agrega los componentes gráficos necesarios.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelConfirmacion(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    /**
     * Configura las propiedades visuales del panel:
     * - Layout nulo para posicionamiento manual.
     * - Color de fondo.
     * - Imagen de fondo personalizada.
     */
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(30, 70, 20));
        setImagenFondo("/fondoprincipal.jpg");
    }

    /**
     * Agrega los componentes gráficos al panel:
     * - Botón "Volver" para regresar a la pantalla anterior.
     * - Botón "Tick" para confirmar la acción.
     */
    @Override
    public void agregarComponentes() {
        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(85, 320, 200, 200);
        panel.add(botonVolver);

        panel.add(new BotonTick(frame));
    }

    /**
     * Obtiene el {@link JPanel} principal de este panel de confirmación.
     *
     * @return el JPanel que contiene todos los componentes.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}