package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PanelTabla muestra la tabla de clasificación o estado del torneo activo.
 * Usa un SwingWorker para actualizar periódicamente la información en pantalla.
 */
public class PanelTabla extends PanelBase {
    private JPanel tablaPanel;        // Panel donde se muestran las líneas de texto de la tabla
    private SwingWorker<Void, List<String>> worker;  // Worker que actualiza la tabla periódicamente

    /**
     * Constructor: configura el panel, agrega componentes y comienza actualizaciones periódicas.
     * @param frame JFrame principal donde se muestra este panel
     */
    public PanelTabla(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
        iniciarActualizaciones();
    }

    /**
     * Configura el panel base:
     * - Usa BorderLayout para agregar el scroll y el botón de volver.
     * - Color de fondo personalizado.
     * - Imagen de fondo.
     */
    @Override
    public void configurar() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(70, 45, 90));
        setImagenFondo("/fondo.jpg");
    }

    /**
     * Crea y agrega los componentes visuales al panel:
     * - Un JPanel dentro de JScrollPane para mostrar la tabla (líneas de texto).
     * - Un botón "Volver" para regresar a la pantalla anterior.
     */
    @Override
    public void agregarComponentes() {
        // Panel que contiene las líneas de texto, con layout tipo Grid para filas
        tablaPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        tablaPanel.setOpaque(false);

        // Scroll para el panel tabla, para manejar grandes cantidades de líneas
        JScrollPane scroll = new JScrollPane(tablaPanel);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 400, 0, 0)); // Margen izquierdo

        panel.add(scroll, BorderLayout.CENTER);

        // Botón para volver al panel anterior
        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(0, 600, 150, 150);
        botonVolver.setBackground(Color.white);
        panel.add(botonVolver, BorderLayout.WEST);
    }

    /**
     * Crea e inicia un SwingWorker que periódicamente:
     * - Obtiene las líneas que representan la tabla actualizada.
     * - Publica estas líneas para ser procesadas y renderizadas en la interfaz.
     *
     * El worker se ejecuta hasta que sea cancelado (por ejemplo, al salir del panel).
     */
    private void iniciarActualizaciones() {
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (!isCancelled()) {
                    publish(generarLineas()); // Publica las líneas que deben mostrarse
                    try {
                        Thread.sleep(1000);  // Espera 1 segundo entre actualizaciones
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;  // Sale si se interrumpe el hilo
                    }
                }
                return null;
            }

            /**
             * Método llamado en el hilo de la interfaz cuando se publican nuevos datos.
             * Actualiza la tabla con las líneas recibidas.
             *
             * @param chunks Lista de listas de líneas publicadas (la última es la más reciente)
             */
            @Override
            protected void process(List<List<String>> chunks) {
                List<String> lineas = chunks.get(chunks.size() - 1); // Última actualización

                tablaPanel.removeAll(); // Limpia líneas anteriores
                tablaPanel.setLayout(new GridLayout(lineas.size(), 1, 10, 10)); // Nueva cantidad de filas

                // Agrega cada línea como JLabel con formato de fuente monoespaciada y color blanco
                for (String linea : lineas) {
                    JLabel label = new JLabel(linea);
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("Monospaced", Font.PLAIN, 14));
                    tablaPanel.add(label);
                }
                tablaPanel.revalidate();
                tablaPanel.repaint();
            }
        };

        worker.execute(); // Inicia el hilo en background
    }

    /**
     * Genera las líneas que representan la tabla de clasificación o el estado actual del torneo.
     * Depende del tipo de torneo activo en Navegador.torneo:
     * - Para TorneoLiga, genera la tabla ordenada con puntos y estadísticas.
     * - Para TorneoEliminacionSimple, solo muestra ronda actual y mensaje.
     *
     * @return Lista de líneas de texto para mostrar.
     */
    private List<String> generarLineas() {
        List<String> out = new ArrayList<>();

        if (Navegador.torneo instanceof TorneoLiga liga) {
            out.add("Tabla de Clasificación: " + liga.nombre);
            out.add("-".repeat(81));
            out.add(String.format("%-20s | %-6s | %-8s | %-8s | %-10s | %-10s",
                    "Participante", "Puntos", "Victorias", "Derrotas", "P.Favor", "P.Contra"));
            out.add("-".repeat(81));

            // Ordena índices de participantes según criterios: puntos, victorias, puntos a favor, puntos en contra
            ArrayList<Integer> idx = new ArrayList<>();
            for (int i = 0; i < liga.participantes.size(); i++) idx.add(i);

            idx.sort((i, j) -> {
                int c = liga.puntos.get(j) - liga.puntos.get(i);
                if (c != 0) return c;
                c = liga.victorias.get(j) - liga.victorias.get(i);
                if (c != 0) return c;
                c = liga.puntosAFavor.get(j) - liga.puntosAFavor.get(i);
                return (c != 0) ? c : liga.puntosEnContra.get(i) - liga.puntosEnContra.get(j);
            });

            // Añade cada participante con sus datos formateados
            for (int i : idx) {
                out.add(String.format("%-20s | %-6d | %-8d | %-8d | %-10d | %-10d",
                        liga.participantes.get(i).obtenerNombre(),
                        liga.puntos.get(i), liga.victorias.get(i), liga.derrotas.get(i),
                        liga.puntosAFavor.get(i), liga.puntosEnContra.get(i)));
            }

            out.add("-".repeat(81));

        } else if (Navegador.torneo instanceof TorneoEliminacionSimple elim) {
            // Mensaje para torneo de eliminación simple (sin tabla)
            out.add("Tabla de Clasificación no disponible para Eliminación Simple");
            out.add("-".repeat(81));
            out.add("Ronda actual: " + (elim.obtenerRondaActual() + 1));
            out.add("-".repeat(81));
        }
        return out;
    }

    /**
     * Devuelve el JPanel principal de este panel.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    /**
     * Cancela el worker para detener las actualizaciones cuando el panel ya no se use.
     * Esto es importante para liberar recursos y evitar actualizaciones innecesarias.
     */
    public void cleanup() {
        if (worker != null) worker.cancel(true);
    }
}