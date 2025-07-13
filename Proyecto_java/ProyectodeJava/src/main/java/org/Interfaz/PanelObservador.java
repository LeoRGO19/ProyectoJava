package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelObservador extends PanelBase {
    public PanelObservador(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(70, 45, 90));
        panel.setBackground(new Color(40, 40, 40));
        panel.setForeground(Color.WHITE);
        panel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void agregarComponentes() {
        panel.add(new BotonBracket(frame));
        panel.add(new BotonTabla(frame));
        panel.add(new BotonEnfrentamiento(frame));
        panel.add(new BotonCalendario(frame));

        BotonVolver botonVolver = new BotonVolver(frame);
        panel.add(botonVolver);
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

}
