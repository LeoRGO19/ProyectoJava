package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelDeportes extends PanelBase {


    public PanelDeportes(JFrame frame) {
        super(frame);
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
        panel.add(new BotonFutbol(frame));
        panel.add(new BotonBasket(frame));
        panel.add(new BotonTenis(frame));
        panel.add(new BotonPingPong(frame));
        panel.add(new BotonTiroConArco(frame));
        BotonVolver botonVolver = new BotonVolver(frame, new PanelDisciplina(frame));
        panel.add(botonVolver);
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    public int getTipo() {
        return 2; //Para poder volver al panel correcto.
    }
}