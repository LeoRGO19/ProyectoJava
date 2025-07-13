package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonLoL extends BotonBase{

    public BotonLoL(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1100,150, 200,200);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/lol_icono.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelVideojuegos(frame)); //Variable global para poder volver si hay mas de un panel
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "LOL";
        System.out.println("¡Botón LoL presionado!");
    }
}
