package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelConfirmacion extends PanelBase {


    public PanelConfirmacion(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();

    }

    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(30, 70, 20));
        setImagenFondo("/fondoprincipal.jpg");
    }

    @Override
    public void agregarComponentes() {

        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(85,320,200,200);
        panel.add(botonVolver);
        panel.add(new BotonTick(frame));
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

}