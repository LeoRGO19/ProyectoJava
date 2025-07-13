package org.Interfaz;

import javax.swing.*;
import java.awt.*;

public class BotonValorant extends BotonBase{

    public BotonValorant(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(650,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/valorant_icono.jpg"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "VALORANT";
        System.out.println("¡Botón Valorant presionado!");
    }
}
