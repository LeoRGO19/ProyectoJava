package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonEliminacionSimple extends BotonBase{

    public BotonEliminacionSimple(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1115,320,200,200);
        setBackground(Color.black);
        setForeground(Color.WHITE);
        setText("ELIMINACION SIMPLE");

    }

    @Override
    public void alPresionar() {
        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Eliminacion simple presionado!");
    }
}