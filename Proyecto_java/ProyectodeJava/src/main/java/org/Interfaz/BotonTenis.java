package org.Interfaz;


import javax.swing.*;

public class BotonTenis extends BotonBase{

    public BotonTenis(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(650,400, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/tenis.jpg"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Tenis presionado!");
    }
}