package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonFifa extends BotonBase{

    public BotonFifa(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1100,400, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/fifa_icono.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "FIFA";
        System.out.println("¡Botón Fifa presionado!");
    }
}
