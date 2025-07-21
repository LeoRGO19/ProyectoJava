package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelInscripcion extends PanelBase{
    /**
     * Constructor que inicializa el panel base con el JFrame dado.
     * Crea un JPanel con layout nulo y sobreescribe paintComponent para pintar la imagen de fondo.
     *
     * @param frame el JFrame contenedor donde se mostrará este panel.
     */
    public PanelInscripcion(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }
    @Override
    public void configurar() {
        panel.setLayout(null);
        setImagenFondo("/fondoprincipal.jpg");// Se sobreescribe el primer setBackground
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void agregarComponentes() {
        panel.add(new BotonAgregarParticipante(frame));
        panel.add(new BotonAgregarLista(frame));
        panel.add(new BotonQuitarParticipante(frame));
        panel.add(new BotonVolver(frame));
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
