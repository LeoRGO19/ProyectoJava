package org.Interfaz;


import java.awt.*;

public class BotonTiroConArco extends BotonBase{

    public BotonTiroConArco() {
        super();
    }

    @Override
    public void configurar() {
        setBounds(400,170, 50,50);
        setBackground(Color.LIGHT_GRAY);
        setForeground(java.awt.Color.BLACK);
        setImagen(("C:/Users/Canito301/Desktop/ProyectoDOO/Proyecto_java/ProyectodeJava/src/main/resources/tiroconarco.png"));
    }

    @Override
    public void alPresionar() {
        System.out.println("¡Botón TiroConArco presionado!");
    }
}