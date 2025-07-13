package org.Interfaz;


import javax.swing.*;

public class BotonFifa extends BotonBase{

    public BotonFifa(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1100,400, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/fifa_icono.png"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Fifa presionado!");
    }
}
