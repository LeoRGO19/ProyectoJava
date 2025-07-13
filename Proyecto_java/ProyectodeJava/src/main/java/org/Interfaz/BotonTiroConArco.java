package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonTiroConArco extends BotonBase{

    public BotonTiroConArco(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1100,400, 200,200);
        setBackground(Color.LIGHT_GRAY);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/tiroconarco.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        System.out.println("¡Botón TiroConArco presionado!");
    }
}