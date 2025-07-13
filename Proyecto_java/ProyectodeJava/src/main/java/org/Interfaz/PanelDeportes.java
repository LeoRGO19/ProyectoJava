package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelDeportes implements InterfazPaneles {

    private JPanel panel;

    public PanelDeportes() {
        panel = new JPanel();
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
        panel.add(new BotonFutbol());
        panel.add(new BotonBasket());
        panel.add(new BotonTenis());
        panel.add(new BotonPingPong());
        panel.add(new BotonTiroConArco());
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}