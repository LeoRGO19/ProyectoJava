package org.Interfaz;

public class BotonRocket extends BotonBase{

    public BotonRocket() {
        super();
    }

    @Override
    public void configurar() {
        setBounds(400,170, 50,50);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("C:/Users/Canito301/Desktop/ProyectoDOO/Proyecto_java/ProyectodeJava/src/main/resources/rocket_icono.png"));
    }

    @Override
    public void alPresionar() {
        System.out.println("¡Botón Rocket League presionado!");
    }
}
