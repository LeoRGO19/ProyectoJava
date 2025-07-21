package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;
/**
 * Panel que permite eliminar un participante de un torneo.
 * Extiende {@link PanelBase} y proporciona un combo box para seleccionar el participante a eliminar.
 */
public class PanelEliminarParticipante extends PanelBase{
    /**
     * Constructor que inicializa el panel eliminar participante con el JFrame principal.
     * Configura el panel.
     *
     * @param frame el JFrame donde se mostrará este panel.
     */
    public PanelEliminarParticipante(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }
    /**
     * Configura las propiedades visuales del panel, incluyendo el layout y el color de fondo.
     */
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(70, 45, 90));
    }
    /**
     * Agrega los componentes gráficos al panel, incluyendo:
     * - Combo box con los nombres de los participantes actuales.
     * - Botón para eliminar el participante seleccionado.
     * - Botón para volver al panel anterior.
     */
    @Override
    public void agregarComponentes() {
        JComboBox<String> comboBoxParticipantes = new JComboBox<>();
        comboBoxParticipantes.setBounds(500, 200, 300, 30);
        panel.add(comboBoxParticipantes);

        // Mapear nombres a objetos Participante
        java.util.List<Participante> listaParticipantes = ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes();
        for (Participante p : listaParticipantes) {
            comboBoxParticipantes.addItem(p.obtenerNombre());
        }

        // Botón Eliminar
        JButton botonEliminar = new JButton("Eliminar");
        botonEliminar.setBackground(new Color(40, 40, 40));
        botonEliminar.setForeground(Color.WHITE);
        botonEliminar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonEliminar.setFocusPainted(false);
        botonEliminar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonEliminar.setBounds(500, 350, 200, 30);
        panel.add(botonEliminar);

        // Acción al presionar el botón
        botonEliminar.addActionListener(e -> {
            int indexSeleccionado = comboBoxParticipantes.getSelectedIndex();
            if (indexSeleccionado >= 0 && indexSeleccionado < listaParticipantes.size()) {Participante seleccionado = listaParticipantes.get(indexSeleccionado);
                try {
                    ((TorneoAbstracto) Navegador.torneo).eliminarParticipante(seleccionado);
                } catch (TorneoException ex) {
                    JOptionPane.showMessageDialog(panel, "error:" + ex.getMessage(), "Error de entrada", JOptionPane.ERROR_MESSAGE);
                }
                comboBoxParticipantes.removeItemAt(indexSeleccionado);
                JOptionPane.showMessageDialog(frame, "Participante eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione un participante válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
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
