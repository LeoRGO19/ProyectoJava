package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;

/**
 * Botón de confirmación para crear un torneo con la configuración seleccionada.
 * Extiende {@link BotonBase} y ejecuta la creación del torneo al ser presionado.
 */
public class BotonTick extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonTick(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen asociada al botón.
     * - Cursor tipo mano.
     * Este método se invoca automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(595, 320, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/tick.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Guarda el panel actual (PanelTipoTorneo) en el historial para navegación.
     * - Cambia el contenido del JFrame al panel principal.
     * - Obtiene la instancia singleton del gestor de creadores de torneos.
     * - Obtiene el creador de torneos correspondiente al formato seleccionado.
     * - Crea un nuevo torneo con nombre "TORNEO1", disciplina seleccionada y 8 participantes.
     * - Asigna el torneo creado a {@link Navegador#torneo}.
     * - Imprime un mensaje de seguimiento en consola.
     *
     * @throws TorneoException si ocurre un error durante la creación del torneo.
     */
    @Override
    public void alPresionar() throws TorneoException {
        Navegador.historial.push(new PanelTipoTorneo(frame));
        cambiarPanel(new PanelPrincipal(frame));

        GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
        Creador pedro = gestor.obtenerCreador(Navegador.t);

        Navegador.torneo = pedro.crearTorneo("TORNEO1", Navegador.palabra, 8);
        System.out.println("¡Botón Confirmar presionado!");
    }
}
