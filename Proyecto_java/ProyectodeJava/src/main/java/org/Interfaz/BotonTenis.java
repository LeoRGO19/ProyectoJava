package org.Interfaz;


public class BotonTenis extends BotonBase{

    public BotonTenis() {
        super();
    }

    @Override
    public void configurar() {
        setBounds(400,110, 50,50);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("C:/Users/Canito301/Desktop/ProyectoDOO/Proyecto_java/ProyectodeJava/src/main/resources/tenis.jpg"));
    }

    @Override
    public void alPresionar() {
        System.out.println("¡Botón Tenis presionado!");
    }
}