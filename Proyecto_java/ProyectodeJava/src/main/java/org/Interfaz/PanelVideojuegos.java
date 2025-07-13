package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelVideojuegos implements InterfazPaneles {

    private JPanel panel;

    public PanelVideojuegos() {
        panel = new JPanel();
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
        panel.add(new BotonValorant());
        panel.add(new BotonLoL());
        panel.add(new BotonFifa());
        panel.add(new BotonCS());
        panel.add(new BotonRocket());
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
