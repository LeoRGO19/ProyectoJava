package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonTick extends BotonBase{

    public BotonTick(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(595,320, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/tick.jpg"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelTipoTorneo(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón CSGO presionado!");
    }
}
