package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la vista de una liga deportiva.
 * Extiende {@link PanelBase} y contiene botones para observar partidos y tabla de posiciones,
 * además de un botón para volver al panel anterior.
 */
public class PanelLiga extends PanelBase {

    /**
     * Constructor que inicializa el panel de la liga con el JFrame principal.
     * Configura el panel y agrega los componentes gráficos.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelLiga(JFrame frame) {
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
     * - Botón "Observar Partido" (duplicado, deberían tener textos y posiciones diferentes).
     * - Botón "Volver" para regresar a la pantalla anterior.
     */
    @Override
    public void agregarComponentes() {
        JButton estado = new JButton("Observar Partido");
        estado.setBackground(new Color(40, 40, 40));
        estado.setForeground(Color.WHITE);
        estado.setFont(new Font("Segoe UI", Font.BOLD, 18));
        estado.setFocusPainted(false);
        estado.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        estado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        estado.setBounds(800, 170, 200, 200);
        panel.add(estado);

        JButton tabla = new JButton("Observar Tabla");
        tabla.setBackground(new Color(40, 40, 40));
        tabla.setForeground(Color.WHITE);
        tabla.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tabla.setFocusPainted(false);
        tabla.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tabla.setBounds(1050, 170, 200, 200);
        panel.add(tabla);

        panel.add(new BotonVolver(frame));
    }

    /**
     * Obtiene el {@link JPanel} principal de este panel de liga.
     *
     * @return el JPanel que contiene todos los componentes.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
