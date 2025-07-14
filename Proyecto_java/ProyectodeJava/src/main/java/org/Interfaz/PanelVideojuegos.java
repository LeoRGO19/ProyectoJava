package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la selección de videojuegos para torneos.
 * Extiende {@link PanelBase} y contiene botones para elegir distintos videojuegos populares,
 * además de un botón para volver al panel anterior.
 */
public class PanelVideojuegos extends PanelBase {

    /**
     * Constructor que inicializa el panel de videojuegos con el JFrame principal.
     * Configura el panel y agrega los botones correspondientes.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelVideojuegos(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    /**
     * Configura las propiedades visuales del panel:
     * - Layout nulo para posicionamiento manual.
     * - Color de fondo azul oscuro (tema gamer).
     */
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 60));
    }

    /**
     * Agrega los botones al panel:
     * - Botones para seleccionar Valorant, LoL, FIFA, CS y Rocket League.
     * - Botón "Volver" para regresar a la pantalla anterior.
     */
    @Override
    public void agregarComponentes() {
        panel.add(new BotonValorant(frame));
        panel.add(new BotonLoL(frame));
        panel.add(new BotonFifa(frame));
        panel.add(new BotonCS(frame));
        panel.add(new BotonRocket(frame));

        BotonVolver botonVolver = new BotonVolver(frame);
        panel.add(botonVolver);
    }

    /**
     * Obtiene el {@link JPanel} principal de este panel de videojuegos.
     *
     * @return el JPanel que contiene todos los componentes.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}