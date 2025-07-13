package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class BotonRocket extends BotonBase{

    public BotonRocket(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(650,400, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/rocket_icono.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "ROCKETLEAGUE";
        System.out.println("¡Botón Rocket League presionado!");
    }
}
