package org.Interfaz;

import org.Logica.*;

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

        botonCrearTorneo.setBounds(600, 170, 200, 40);
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


        JButton botonInscribirEnTorneo = new JButton("Inscripcion Torneo");
        botonInscribirEnTorneo.setBackground(new Color(40, 40, 40));
        botonInscribirEnTorneo.setForeground(Color.WHITE);
        botonInscribirEnTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonInscribirEnTorneo.setFocusPainted(false);
        botonInscribirEnTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonInscribirEnTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonInscribirEnTorneo.setBounds(1115,170,200,40);
        panel.add(botonInscribirEnTorneo);







        JButton botonSpectearTorneo = new JButton("Observar Torneo");
        botonSpectearTorneo.setBackground(new Color(40, 40, 40));
        botonSpectearTorneo.setForeground(Color.WHITE);
        botonSpectearTorneo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonSpectearTorneo.setFocusPainted(false);
        botonSpectearTorneo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonSpectearTorneo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSpectearTorneo.setBounds(80,170,200,40);
        panel.add(botonSpectearTorneo);

        botonSpectearTorneo.addActionListener(e->{
        PanelLiga panelLiga = new PanelLiga(frame);
        Navegador.historial.push(new PanelPrincipal(frame));  // guardar antes de cambiar
            frame.setContentPane(new PanelLiga(frame).obtenerPanel());
            frame.revalidate();
            frame.repaint();

            frame.setContentPane(panelLiga.obtenerPanel());
            frame.revalidate();
            frame.repaint();

        });



    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}