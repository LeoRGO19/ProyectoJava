package org.Interfaz;

import javax.swing.*;

public class BotonRocket extends BotonBase{

    public BotonRocket(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(650,400, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/rocket_icono.png"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Rocket League presionado!");
    }
}
