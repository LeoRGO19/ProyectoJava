package org.Interfaz;


import javax.swing.*;

public class BotonFutbol extends BotonBase{

    public BotonFutbol(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(650,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/pelota_futbol.jpg"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Futbol presionado!");
    }
}