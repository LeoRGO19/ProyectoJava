package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelLiga extends PanelBase {


    public PanelLiga(JFrame frame) {
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
        JButton estado = new JButton("Observar Partido");
        estado.setBackground(new Color(40, 40, 40));
        estado.setForeground(Color.WHITE);
        estado.setFont(new Font("Segoe UI", Font.BOLD, 18));
        estado.setFocusPainted(false);
        estado.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        estado.setCursor(new Cursor(Cursor.HAND_CURSOR));
        estado.setBounds(800,170,200,200);
        panel.add(estado);

        JButton Tabla = new JButton("Observar Partido");
        Tabla.setBackground(new Color(40, 40, 40));
        Tabla.setForeground(Color.WHITE);
        Tabla.setFont(new Font("Segoe UI", Font.BOLD, 18));
        Tabla.setFocusPainted(false);
        Tabla.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        Tabla.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Tabla.setBounds(800,170,200,200);
        panel.add(Tabla);

        BotonVolver botonVolver = new BotonVolver(frame);
        panel.add(botonVolver);
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }


}
