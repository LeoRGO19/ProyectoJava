package org.Interfaz;

import org.Logica.FormatoTorneo;
import org.Logica.Torneo;

import java.util.Stack;
//Costó mucho pero orgulloso de la clase.
/**
 * Clase que actúa como controlador de navegación entre paneles y almacena
 * información global relevante para la aplicación.
 *
 * Contiene un historial de paneles para permitir navegación hacia atrás,
 * además de variables estáticas que guardan el formato de torneo actual,
 * una palabra clave y la instancia del torneo creado.
 */
public class Navegador {
    /** Constructor por defecto */
    public Navegador(){}
    /**
     * Pila (stack) que almacena los paneles visitados, permitiendo retroceder
     * a paneles anteriores.
     */
    public static Stack<PanelBase> historial = new Stack<>();

    /**
     * Formato de torneo seleccionado actualmente.
     */
    public static FormatoTorneo t;

    /**
     * Palabra clave relacionada con la disciplina o categoría seleccionada.
     */
    public static String palabra;

    /**
     * Instancia del torneo actualmente creado o en curso.
     */
    public static Torneo torneo;

    /**
     * Obtiene el formato de torneo seleccionado.
     *
     * @return el valor actual de {@link FormatoTorneo}.
     */
    public static FormatoTorneo getValue() {
        return t;
    }
}
