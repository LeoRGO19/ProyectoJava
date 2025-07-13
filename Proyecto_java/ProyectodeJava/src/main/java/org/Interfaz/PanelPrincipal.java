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
        botonCrearTorneo.setBounds(600, 150, 200, 40);
        panel.add(botonCrearTorneo);

        JButton botonDeportes = new JButton("Ir a Deportes");
        botonDeportes.setBounds(600, 210, 200, 40);
        panel.add(botonDeportes);

        botonCrearTorneo.addActionListener(e -> {
            PanelDisciplina panelDisciplina = new PanelDisciplina(frame);
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