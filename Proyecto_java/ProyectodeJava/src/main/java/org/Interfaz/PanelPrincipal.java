package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends PanelBase{


    public PanelPrincipal(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    @Override
    public void configurar() {
        setImagenFondo("/fondoprincipal.jpg");
    }

    @Override
    public void agregarComponentes() {
        JLabel titulo = new JLabel("Bienvenido al Menú Principal");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(500, 50, 400, 30);
        panel.add(titulo);

        JButton botonCrearTorneo = new JButton("Crear Torneo");
        botonCrearTorneo.setBackground(new Color(40, 40, 40));
        botonCrearTorneo.setForeground(Color.WHITE);

        botonCrearTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonCrearTorneo.setFocusPainted(false);
        botonCrearTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonCrearTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor a "mano"

        botonCrearTorneo.setBounds(600, 150, 200, 40);
        panel.add(botonCrearTorneo);


        botonCrearTorneo.addActionListener(e -> {
            PanelDisciplina panelDisciplina = new PanelDisciplina(frame);
            Navegador.historial.push(new PanelPrincipal(frame));  // guardar antes de cambiar
            frame.setContentPane(new PanelDisciplina(frame).obtenerPanel());
            frame.revalidate();
            frame.repaint();

            frame.setContentPane(panelDisciplina.obtenerPanel());
            frame.revalidate();
            frame.repaint();
        });

    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}