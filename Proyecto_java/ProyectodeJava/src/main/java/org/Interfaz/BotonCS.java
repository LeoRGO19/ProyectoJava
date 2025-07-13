package org.Interfaz;


import javax.swing.*;

public class BotonCS extends BotonBase{

    public BotonCS(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(200,400, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/cs_icono.jpg"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón CSGO presionado!");
    }
}
