package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonFutbol extends BotonBase{

    public BotonFutbol(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(650,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/pelota_futbol.jpg"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón Futbol presionado!");
    }
}