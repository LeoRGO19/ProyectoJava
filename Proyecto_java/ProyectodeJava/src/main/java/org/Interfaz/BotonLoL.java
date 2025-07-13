package org.Interfaz;


public class BotonLoL extends BotonBase{

    public BotonLoL() {
        super();
    }

    @Override
    public void configurar() {
        setBounds(500,50, 50,50);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("C:/Users/Canito301/Desktop/ProyectoDOO/Proyecto_java/ProyectodeJava/src/main/resources/lol_icono.png"));
    }

    @Override
    public void alPresionar() {
        System.out.println("¡Botón LoL presionado!");
    }
}
