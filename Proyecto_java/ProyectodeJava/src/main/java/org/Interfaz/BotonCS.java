package org.Interfaz;


import org.Logica.FormatoTorneo;

import javax.swing.*;
import java.awt.*;

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
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "CSGO";
        System.out.println("¡Botón CSGO presionado!");
    }
}
