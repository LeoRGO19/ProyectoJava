package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelDisciplina extends PanelBase {


    public PanelDisciplina(JFrame frame) {
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
        panel.add(new BotonVideojuegos(frame));
        panel.add(new BotonDeportes(frame));
        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(85,320,200,200);
        panel.add(botonVolver);
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

}
