package org.Interfaz;
import org.Logica.*;
import org.Logica.TorneoException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BotonAgregarLista extends BotonBase{
    /**
     * Constructor que inicializa el botón y lo configura.
     * También registra el listener para la acción del botón que llama a {@link #alPresionar()}.
     *
     * @param frame el JFrame principal que contiene el botón
     */
    public BotonAgregarLista(JFrame frame) {
        super(frame);
    }
    @Override
    public void configurar() {
        setBounds(600, 150, 330, 150);
        setText("Agregar lista de participantes");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void alPresionar() throws TorneoException {
        Navegador.historial.push(new PanelInscripcion(frame));
        cambiarPanel(new PanelAgregarLista(frame));
        System.out.println("¡Botón AgregarLista presionado!");
    }
}
