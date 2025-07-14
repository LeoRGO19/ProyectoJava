package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la selección del tipo de torneo.
 * Extiende {@link PanelBase} y contiene botones para elegir entre diferentes formatos de torneo,
 * además de un botón para volver al panel anterior.
 */
public class PanelTipoTorneo extends PanelBase {

    /**
     * Constructor que inicializa el panel de tipos de torneo con el JFrame principal.
     * Configura el panel y agrega los botones correspondientes.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelTipoTorneo(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    /**
     * Configura las propiedades visuales del panel:
     * - Layout nulo para posicionamiento manual.
     * - Color de fondo verde oscuro.
     * - Imagen de fondo personalizada.
     */
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(30, 70, 20));
        setImagenFondo("/fondoprincipal.jpg");
    }

    /**
     * Agrega los botones al panel:
     * - Botón para seleccionar Liga.
     * - Botón para seleccionar Eliminación Simple.
     * - Botón "Volver" para regresar a la pantalla anterior, con posicionamiento manual.
     */
    @Override
    public void agregarComponentes() {
        panel.add(new BotonLiga(frame));
        panel.add(new BotonEliminacionSimple(frame));

        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(85, 320, 200, 200);
        panel.add(botonVolver);
    }

    /**
     * Obtiene el {@link JPanel} principal de este panel de tipo de torneo.
     *
     * @return el JPanel que contiene todos los componentes.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
