package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonLiga extends BotonBase{

    public BotonLiga(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(595,320,200,200);
        setBackground(Color.black);
        setForeground(Color.WHITE);
        setText("LIGA");

    }

    @Override
    public void alPresionar() {
        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Liga presionado!");
    }
}
