package org.Interfaz;


public class BotonCS extends BotonBase{

    public BotonCS() {
        super();
    }

    @Override
    public void configurar() {
        setBounds(500,110, 50,50);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("C:/Users/Canito301/Desktop/ProyectoDOO/Proyecto_java/ProyectodeJava/src/main/resources/cs_icono.jpg"));
    }

    @Override
    public void alPresionar() {
        System.out.println("¡Botón CSGO presionado!");
    }
}
