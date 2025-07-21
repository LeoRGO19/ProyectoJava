package org.Interfaz;

import org.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * PanelBracket es un panel que muestra visualmente el estado del torneo en formato bracket o
 * enfrentamientos según el tipo de torneo activo (Eliminación Simple o Liga).
 * <p>
 * Utiliza un {@link SwingWorker} para actualizar dinámicamente la información cada segundo,
 * mostrando los enfrentamientos actuales, resultados y estado de cada ronda o encuentro.
 * <p>
 * Este panel tiene un botón "Volver" que permite regresar al panel anterior.
 * <p>
 * La presentación del bracket para Eliminación Simple muestra cada ronda con sus enfrentamientos,
 * mientras que para Liga muestra una lista con los enfrentamientos, fechas y resultados.
 */
public class PanelBracket extends PanelBase {
    private JPanel bracketPanel;  // Panel interno para mostrar las líneas de texto del bracket

    private SwingWorker<Void, List<String>> worker;// Worker que actualiza la información periódicamente

    /**
     * Constructor que crea el panel y configura su apariencia y comportamiento.
     * Inicia las actualizaciones automáticas del contenido.
     *
     * @param frame El JFrame principal donde se inserta este panel.
     */
    public PanelBracket(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
        iniciarActualizaciones();
    }

    /**
     * Configura el panel base:
     * - Usa BorderLayout para distribuir componentes.
     * - Establece un color de fondo y una imagen personalizada.
     */
    @Override
    public void configurar() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(70, 45, 90));
        setImagenFondo("/fondoprincipal.jpg");
    }

    /**
     * Agrega los componentes visuales al panel:
     * - Un panel con scroll para mostrar las líneas de texto del bracket.
     * - Un panel con botón "Volver" para regresar al panel previo.
     */
    @Override
    public void agregarComponentes() {
        bracketPanel = new JPanel();
        bracketPanel.setOpaque(false);
        bracketPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JScrollPane scrollPane = new JScrollPane(bracketPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 400, 0, 0));  // Margen para visualización
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(70, 45, 90));

        BotonVolver botonVolver = new BotonVolver(frame);
        buttonPanel.add(botonVolver);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Crea e inicia un {@link SwingWorker} que actualiza periódicamente el contenido del bracket.
     * <p>
     * Dependiendo del tipo de torneo activo, genera diferentes vistas:
     * <ul>
     *     <li>Para {@link TorneoEliminacionSimple}, muestra las rondas con enfrentamientos, puntajes y estado.</li>
     *     <li>Para {@link TorneoLiga}, muestra los enfrentamientos programados con fecha y resultados.</li>
     * </ul>
     * <p>
     * El worker se ejecuta cada segundo hasta que es cancelado, permitiendo una actualización dinámica.
     */
    private void iniciarActualizaciones() {
        worker = new SwingWorker<Void, List<String>>() {
            @Override
            protected Void doInBackground() {
                while (!isCancelled()) {
                    List<String> lineas = new ArrayList<>();

                    // Visualización para torneo Eliminación Simple
                    if (Navegador.torneo instanceof TorneoEliminacionSimple) {
                        TorneoEliminacionSimple torneo = (TorneoEliminacionSimple) Navegador.torneo;
                        lineas.add("Bracket de Eliminación Simple: " + torneo.obtenerNombre());
                        lineas.add("---------------------------------------------------------------------------------");

                        Enfrentamiento[][] rondas = torneo.obtenerRondas();

                        for (int r = 0; r <= torneo.obtenerRondaActual() && r < torneo.obtenerNumRondas(); r++) {
                            if (rondas[r] != null && rondas[r].length > 0) {
                                lineas.add("Ronda " + (r + 1) + ":");

                                for (Enfrentamiento enf : rondas[r]) {
                                    if (enf != null) {
                                        String p2Nombre = enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                                        String ganador = enf.obtenerGanador() != null ? enf.obtenerGanador().obtenerNombre() : "Pendiente";
                                        String estado = switch (enf.obtenerEstado()) {
                                            case 0 -> "Pendiente";
                                            case 1 -> "En curso";
                                            case 2 -> "Terminado";
                                            default -> "Desconocido";
                                        };
                                        lineas.add(String.format("├── %-20s %d vs %-20s %d (%s) → Ganador: %s",
                                                enf.obtenerParticipante1().obtenerNombre(), enf.obtenerPuntaje1(),
                                                p2Nombre, enf.obtenerPuntaje2(), estado, ganador));
                                    } else {
                                        lineas.add("├── Enfrentamiento no inicializado");
                                    }
                                }
                                if (r < torneo.obtenerRondaActual()) {
                                    lineas.add("│");
                                }
                            }
                        }
                        lineas.add("---------------------------------------------------------------------------------");

                        // Visualización para torneo Liga
                    } else if (Navegador.torneo instanceof TorneoLiga) {
                        TorneoLiga torneo = (TorneoLiga) Navegador.torneo;
                        lineas.add("Enfrentamientos de Liga: " + torneo.obtenerNombre());
                        lineas.add("---------------------------------------------------------------------------------");
                        lineas.add(String.format("%-30s | %-19s | %-10s", "Enfrentamiento", "Fecha", "Resultado"));
                        lineas.add("-------------------------------------------------------------");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        for (Enfrentamiento enf : torneo.obtenerEnfrentamientos()) {
                            String p2Nombre = (enf.obtenerParticipante2() != null) ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
                            String fechaFormateada = (enf.obtenerFecha() != null) ? enf.obtenerFecha().format(formatter) : "Sin fecha";
                            String resultado = enf.haTerminadoEncuentro() ?
                                    enf.obtenerParticipante1().obtenerNombre() + " " + enf.obtenerPuntaje1() + " - " +
                                            enf.obtenerPuntaje2() + " " + p2Nombre : "Pendiente";
                            lineas.add(String.format("%-30s | %-19s | %-10s",
                                    enf.obtenerParticipante1().obtenerNombre() + " vs " + p2Nombre,
                                    fechaFormateada,
                                    resultado));
                        }
                        lineas.add("-------------------------------------------------------------");
                    }

                    publish(new CopyOnWriteArrayList<>(lineas));  // Publica la lista para el método process

                    try {
                        Thread.sleep(1000);  // Pausa de 1 segundo antes de actualizar de nuevo
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;  // Termina la ejecución si se interrumpe
                    }
                }
                return null;
            }

            /**
             * Método invocado en el hilo de la interfaz gráfica para actualizar la vista
             * con las líneas de texto recibidas del método doInBackground.
             *
             * @param chunks listas de líneas publicadas, se procesa la última
             */
            @Override
            protected void process(List<List<String>> chunks) {
                for (List<String> lineas : chunks) {
                    bracketPanel.removeAll();
                    bracketPanel.setLayout(new GridLayout(lineas.size(), 1, 10, 10));
                    for (String linea : lineas) {
                        JLabel label = new JLabel(linea);
                        label.setForeground(Color.WHITE);
                        label.setFont(new Font("Monospaced", Font.PLAIN, 14));
                        bracketPanel.add(label);
                    }
                    bracketPanel.revalidate();
                    bracketPanel.repaint();
                }
            }
        };
        worker.execute();  // Inicia el worker en segundo plano
    }

    /**
     * Obtiene el panel principal de este componente.
     *
     * @return el JPanel principal con todos los componentes añadidos.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    /**
     * Detiene el {@link SwingWorker} para liberar recursos cuando el panel deja de usarse.
     * Es importante llamar a este método para evitar que el worker siga ejecutándose en background.
     */
    public void cleanup() {
        if (worker != null) {
            worker.cancel(true);
        }
    }
}