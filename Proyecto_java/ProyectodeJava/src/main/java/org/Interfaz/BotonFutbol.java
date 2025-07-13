package org.Interfaz;


public class BotonFutbol extends BotonBase{

    public BotonFutbol() {
        super();
    }

    @Override
    public void configurar() {
        setBounds(400,50, 50,50);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("C:/Users/Canito301/Desktop/ProyectoDOO/Proyecto_java/ProyectodeJava/src/main/resources/pelota_futbol.jpg"));
    }

    @Override
    public void alPresionar() {
        System.out.println("¡Botón Futbol presionado!");
    }
}