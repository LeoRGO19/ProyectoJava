package org.Interfaz;


import javax.swing.*;
import java.awt.*;

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
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón PingPong presionado!");
    }
}
