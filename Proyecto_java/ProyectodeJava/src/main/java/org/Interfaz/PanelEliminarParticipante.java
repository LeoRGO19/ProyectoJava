package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;

public class PanelEliminarParticipante extends PanelBase{

    /**
     * Constructor que inicializa el panel base con el JFrame dado.
     * Crea un JPanel con layout nulo y sobreescribe paintComponent para pintar la imagen de fondo.
     *
     * @param frame el JFrame contenedor donde se mostrará este panel.
     */
    public PanelEliminarParticipante(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(70, 45, 90));
    }
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
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
