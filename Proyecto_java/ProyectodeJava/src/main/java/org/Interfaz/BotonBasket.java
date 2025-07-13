package org.Interfaz;


import javax.swing.*;

public class BotonBasket extends BotonBase{

    public BotonBasket(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1100,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/basket_pelota.jpg"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Basket presionado!");
    }
}
