package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la vista de observación de torneos.
 * Extiende {@link PanelBase} y contiene botones para mostrar el bracket, la tabla,
 * los enfrentamientos y el calendario, además de un botón para volver al panel anterior.
 */
public class PanelObservador extends PanelBase {

    /**
     * Constructor que inicializa el panel observador con el JFrame principal.
     * Configura el panel y agrega los componentes gráficos.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelObservador(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    /**
     * Configura las propiedades visuales del panel:
     * - Layout nulo para posicionamiento manual.
     * - Color de fondo (último seteado: gris oscuro).
     * - Color de primer plano, fuente, borde y cursor.
     */
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(40, 40, 40)); // Se sobreescribe el primer setBackground
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Agrega botones al panel:
     * - Botón para mostrar el bracket.
     * - Botón para mostrar la tabla.
     * - Botón para mostrar los enfrentamientos.
     * - Botón para mostrar el calendario.
     * - Botón "Volver" para regresar a la pantalla anterior.
     */
    @Override
    public void agregarComponentes() {
        panel.add(new BotonBracket(frame));
        panel.add(new BotonTabla(frame));
        panel.add(new BotonEnfrentamiento(frame));
        panel.add(new BotonCalendario(frame));
        panel.add(new BotonVolver(frame));
    }

    /**
     * Obtiene el {@link JPanel} principal de este panel de observador.
     *
     * @return el JPanel que contiene todos los componentes.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}