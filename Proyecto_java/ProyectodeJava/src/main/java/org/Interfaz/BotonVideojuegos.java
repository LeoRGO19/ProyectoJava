package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonVideojuegos extends BotonBase{

    public BotonVideojuegos(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(595,320, 200,200);

        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/Videojuegos.jpg"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelDisciplina(frame));
        cambiarPanel(new PanelVideojuegos(frame));
        System.out.println("¡Botón Videojuegos presionado!");
    }


}
