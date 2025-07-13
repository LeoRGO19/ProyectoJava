package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal implements InterfazPaneles {

    private JPanel panel;
    private JFrame frame;

    public PanelPrincipal(JFrame frame) {
        this.frame = frame;
        panel = new JPanel();
        configurar();
        agregarComponentes();
    }

    @Override
    public void configurar() {

    }

    @Override
    public void agregarComponentes() {
        JLabel titulo = new JLabel("Bienvenido al Menú Principal");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(500, 50, 400, 30);
        panel.add(titulo);

        JButton botonVideojuegos = new JButton("Ir a Videojuegos");
        botonVideojuegos.setBounds(600, 150, 200, 40);
        panel.add(botonVideojuegos);

        JButton botonDeportes = new JButton("Ir a Deportes");
        botonDeportes.setBounds(600, 210, 200, 40);
        panel.add(botonDeportes);

        // Cambiar de panel al presionar botones
        botonVideojuegos.addActionListener(e -> {
            PanelVideojuegos panelVideojuegos = new PanelVideojuegos();
            frame.setContentPane(panelVideojuegos.obtenerPanel());
            frame.revalidate();
            frame.repaint();
        });

        botonDeportes.addActionListener(e -> {
            PanelDeportes panelDeportes = new PanelDeportes();
            frame.setContentPane(panelDeportes.obtenerPanel());
            frame.revalidate();
            frame.repaint();
        });
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}