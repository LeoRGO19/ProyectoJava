package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelVideojuegos extends PanelBase {


    public PanelVideojuegos(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }

    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 60));  // azul oscuro tipo gamer
    }

    @Override
    public void agregarComponentes() {
        panel.add(new BotonValorant(frame));
        panel.add(new BotonLoL(frame));
        panel.add(new BotonFifa(frame));
        panel.add(new BotonCS(frame));
        panel.add(new BotonRocket(frame));
        BotonVolver botonVolver = new BotonVolver(frame, new PanelDisciplina(frame));
        panel.add(botonVolver);
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    public int getTipo() {
        return 1; // Para poder volver al panel correcto
    }
}
