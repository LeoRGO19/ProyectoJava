package org.Interfaz;

import org.Logica.FormatoTorneo;
import org.Logica.Torneo;

import java.util.Stack;

public class Navegador {
    public static Stack<PanelBase> historial = new Stack<>();
    public static FormatoTorneo t;
    public static String palabra;
    public static Torneo torneo;

    public static FormatoTorneo getValue(){
        return t;
    }
}
