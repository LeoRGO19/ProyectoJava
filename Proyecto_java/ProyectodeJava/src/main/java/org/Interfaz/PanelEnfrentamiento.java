package org.Interfaz;

import org.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelEnfrentamiento extends PanelBase {
    private JLabel texto;
    private JLabel texto2;
    private JButton botonSiguiente;
    private int enfrentamientoActual;
    private Enfrentamiento[] enfrentamientos;
    private SwingWorker<Void, String> worker;


    /** Constructor que recibe de parametro un frame y tiene las caracteristicas basicas de la interfaz */
    public PanelEnfrentamiento(JFrame frame) {
        super(frame);
        enfrentamientoActual = 0;
        enfrentamientos = getEnfrentamientos();
        configurar();
        agregarComponentes();
        iniciarActualizaciones();
    }

    @Override
    public void configurar() {
        panel.setBackground(new Color(70, 45, 90));
        panel.setLayout(null);
        switch (Navegador.palabra){
            case "FUTBOL"->{
                setImagenFondo("/campo_futbol.jpg");
            }
            case "BASKETBALL"->{
                setImagenFondo("/cancha_basket.jpg");
            }
            case "TENIS"->{
                setImagenFondo("/cancha_tenis.jpg");
            }
            case "TENISDEMESA"->{
                setImagenFondo("/cancha_ping_pong.jpg");
            }
            case "TIROCONARCO"->{
                setImagenFondo("/campotiro.jpg");
            }
            case "VALORANT"->{
                setImagenFondo("/valorantmapa.jpg");
            }
            case "LOL"->{
                setImagenFondo("/leaguemapa.jpg");
            }
            case "CSGO"->{
                setImagenFondo("/csgo.jpg");
            }
            case "ROCKETLEAGUE"->{
                setImagenFondo("/rocketleague.png");
            }
            case "FIFA"->{
                setImagenFondo("/fifa_estadio.jpg");
            }
        }
    }

    @Override
    public void agregarComponentes() {
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2, 1, 10, 10));
        centro.setOpaque(false);

        texto = new JLabel("Esperando enfrentamiento...");
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        texto.setBounds(700,20,350,150);

        texto2 = new JLabel("");
        texto2.setForeground(Color.WHITE);
        texto2.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        texto2.setBounds(600,50,500,300);


        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(70, 45, 90));

        BotonVolver botonVolver = new BotonVolver(frame);
        botonVolver.setBounds(600, 600, 150, 150);
        botonVolver.setBackground(Color.white);
        buttonPanel.add(botonVolver);

        botonSiguiente = new JButton("Siguiente");
        botonSiguiente.setBackground(new Color(40, 40, 40));
        botonSiguiente.setForeground(Color.WHITE);
        botonSiguiente.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botonSiguiente.setFocusPainted(false);
        botonSiguiente.setBounds(800,600,150,150);
        botonSiguiente.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonSiguiente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSiguiente.addActionListener(e -> mostrarSiguienteEnfrentamiento());
        buttonPanel.add(botonSiguiente);
        panel.setComponentZOrder(botonSiguiente, 0);
        panel.setComponentZOrder(texto, 0);
        panel.setComponentZOrder(texto2, 0);
        panel.setComponentZOrder(botonVolver,0);
        panel.revalidate();
        panel.repaint();
    }

    private Enfrentamiento[] getEnfrentamientos() {
        if (Navegador.torneo instanceof TorneoEliminacionSimple) {
            return ((TorneoEliminacionSimple) Navegador.torneo).rondasS;
        } else if (Navegador.torneo instanceof TorneoLiga) {
            return ((TorneoLiga) Navegador.torneo).enfrentamientos.toArray(new Enfrentamiento[0]);
        }
        return new Enfrentamiento[0];
    }

    private void mostrarSiguienteEnfrentamiento() {
        if (enfrentamientos == null || enfrentamientos.length == 0) {
            texto.setText("No hay enfrentamientos disponibles");
            texto2.setText("");
            botonSiguiente.setEnabled(false);
            return;
        }

        enfrentamientoActual = (enfrentamientoActual + 1) % enfrentamientos.length;
        actualizarEnfrentamiento();
    }

    private void actualizarEnfrentamiento() {
        if (enfrentamientos != null && enfrentamientoActual < enfrentamientos.length && enfrentamientos[enfrentamientoActual] != null) {
            Enfrentamiento enf = enfrentamientos[enfrentamientoActual];
            String p2Nombre = enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
            String estado = switch (enf.obtenerEstado()) {
                case 0 -> "Pendiente";
                case 1 -> "En curso";
                case 2 -> "Terminado";
                default -> "Desconocido";
            };
            texto.setText(enf.obtenerParticipante1().obtenerNombre() + " vs " + p2Nombre);
            texto2.setText("Puntaje: " + enf.obtenerPuntaje1() + " - " + enf.obtenerPuntaje2() + " (" + estado + ")");
        } else {
            texto.setText("No hay enfrentamiento disponible");
            texto2.setText("");
        }
    }

    private void iniciarActualizaciones() {
        worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() {
                while (!isCancelled()) {
                    if (enfrentamientos != null && enfrentamientoActual < enfrentamientos.length && enfrentamientos[enfrentamientoActual] != null) {
                        Enfrentamiento enf = enfrentamientos[enfrentamientoActual];
                        String estado = switch (enf.obtenerEstado()) {
                            case 0 -> "Pendiente";
                            case 1 -> "En curso";
                            case 2 -> "Terminado";
                            default -> "Desconocido";
                        };
                        String textoActual = enf.obtenerParticipante1().obtenerNombre() + " vs " +
                                (enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE");
                        String textoPuntaje = "Puntaje: " + enf.obtenerPuntaje1() + " - " + enf.obtenerPuntaje2() + " (" + estado + ")";
                        publish(textoActual + "|" + textoPuntaje);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                for (String chunk : chunks) {
                    String[] partes = chunk.split("\\|");
                    if (partes.length == 2) {
                        texto.setText(partes[0]);
                        texto2.setText(partes[1]);
                    }
                }
            }
        };
        worker.execute();
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
   /** Metodo para interrumpir la funcion*/
    public void cleanup() {
        if (worker != null) {
            worker.cancel(true);
        }
    }
}