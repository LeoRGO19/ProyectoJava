package org.Interfaz;


import javax.swing.*;

public class BotonPingPong extends BotonBase{

    public BotonPingPong(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(200,400, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/pingpong_icono.jpg"));
    }

    @Override
    public void alPresionar() {

        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón PingPong presionado!");
    }
}
