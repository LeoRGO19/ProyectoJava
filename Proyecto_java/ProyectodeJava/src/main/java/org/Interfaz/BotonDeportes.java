package org.Interfaz;


import javax.swing.*;

public class BotonDeportes extends BotonBase{

    public BotonDeportes(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1115,320, 200,200);

        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/deportes.jpg"));

    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelDeportes(frame));
        System.out.println("¡Botón Deportes presionado!");
    }
}