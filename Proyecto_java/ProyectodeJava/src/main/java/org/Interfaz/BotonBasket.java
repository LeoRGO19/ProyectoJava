package org.Interfaz;


public class BotonBasket extends BotonBase{

    public BotonBasket() {
        super();
    }

    @Override
    public void configurar() {
        setBounds(500,50, 50,50);
        setBackground(java.awt.Color.BLACK);
        setForeground(java.awt.Color.BLACK);
        setImagen(("C:/Users/Canito301/Desktop/ProyectoDOO/Proyecto_java/ProyectodeJava/src/main/resources/basket_pelota.jpg"));
    }

    @Override
    public void alPresionar() {
        System.out.println("¡Botón Basket presionado!");
    }
}
