package org.Interfaz;


import javax.swing.*;

public class BotonVideojuegos extends BotonBase{

    public BotonVideojuegos(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(595,320, 200,200);

        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/Videojuegos.jpg"));

    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelVideojuegos(frame));
        System.out.println("¡Botón Videojuegos presionado!");
    }


}
