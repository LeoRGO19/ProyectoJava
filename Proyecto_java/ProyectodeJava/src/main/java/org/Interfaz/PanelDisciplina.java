package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la selección de disciplinas deportivas o videojuegos.
 * Extiende {@link PanelBase} y contiene botones para elegir entre Videojuegos y Deportes,
 * además de un botón para volver al panel anterior.
 */
public class PanelDisciplina extends PanelBase {

    /**
     * Constructor que inicializa el panel de disciplinas con el JFrame principal.
     * Configura el panel y agrega los botones correspondientes.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelDisciplina(JFrame frame) {
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
     * - Botón para seleccionar Videojuegos.
     * - Botón para seleccionar Deportes.
     * - Botón "Volver" para regresar a la pantalla anterior, con posicionamiento manual.
     */
    @Override
    public void agregarComponentes() {
        panel.add(new BotonVideojuegos(frame));
        panel.add(new BotonDeportes(frame));

        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(85, 320, 200, 200);
        panel.add(botonVolver);
    }

    /**
     * Obtiene el {@link JPanel} principal de este panel de disciplinas.
     *
     * @return el JPanel que contiene todos los componentes.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
