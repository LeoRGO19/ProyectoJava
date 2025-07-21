package org.Interfaz;

import javax.swing.*;
import java.awt.*;
/**
 * Panel que permite gestionar la inscripción de participantes en un torneo.
 * Extiende {@link PanelBase} y contiene botones para agregar participantes individualmente,
 * agregar listas de participantes y quitar participantes, además de un botón para volver.
 */
public class PanelInscripcion extends PanelBase{
    /**
     * Constructor que inicializa el panel inscripcion con el JFrame principal.
     * Configura el panel.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelInscripcion(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }
    /**
     * Configura las propiedades visuales del panel, incluyendo el layout, la imagen de fondo,
     * el color de texto, la fuente, el borde y el cursor.
     */
    @Override
    public void configurar() {
        panel.setLayout(null);
        setImagenFondo("/fondoprincipal.jpg");// Se sobreescribe el primer setBackground
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    /**
     * Agrega los componentes gráficos al panel, incluyendo:
     * - Botón para agregar un participante individual.
     * - Botón para agregar una lista de participantes.
     * - Botón para quitar un participante.
     * - Botón para volver al panel anterior.
     */
    @Override
    public void agregarComponentes() {
        panel.add(new BotonAgregarParticipante(frame));
        panel.add(new BotonAgregarLista(frame));
        panel.add(new BotonQuitarParticipante(frame));
        panel.add(new BotonVolver(frame));
    }
    /**
     * Obtiene el {@link JPanel} principal que contiene todos los componentes del panel.
     *
     * @return el {@link JPanel} configurado con los componentes
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
