package org.Interfaz;


import javax.swing.*;
import java.awt.*;

public class BotonDeportes extends BotonBase{

    public BotonDeportes(JFrame frame) {
        super(frame);
    }

    @Override
    public void configurar() {
        setBounds(1115,320, 200,200);

        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("/deportes.jpg"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    @Override
    public void alPresionar() {

        Navegador.historial.push(new PanelDisciplina(frame));
        cambiarPanel(new PanelDeportes(frame));
        System.out.println("¡Botón Deportes presionado!");
    }
}