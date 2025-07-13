package org.Interfaz;

import javax.swing.*;
import java.awt.*;
public abstract class BotonBase extends JButton implements InterfazBotones {

    public BotonBase() {
        super();
        configurar();
        addActionListener(e -> alPresionar());
    }
    public void configurar() {
        setSize(new Dimension(100, 50)); // Tamaño por defecto
        setFocusPainted(false); // Opcional: elimina el borde de enfoque
    }
    public void setImagen(String ruta){
        int ancho = 50;
        int alto = 50;
        ImageIcon imagen = new ImageIcon(ruta);
        Image imagenEscalada = imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagenEscalada);
        setIcon(imagenFinal);
    }

}
