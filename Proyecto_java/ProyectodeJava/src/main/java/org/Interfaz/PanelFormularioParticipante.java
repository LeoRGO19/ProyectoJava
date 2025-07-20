package org.Interfaz;

import org.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelFormularioParticipante extends PanelBase{
    /**
     * Constructor que inicializa el panel base con el JFrame dado.
     * Crea un JPanel con layout nulo y sobreescribe paintComponent para pintar la imagen de fondo.
     *
     * @param frame el JFrame contenedor donde se mostrará este panel.
     */
    ArrayList<Participante> participantesEntrada = new ArrayList<>();
    private JRadioButton rbEquipos;
    private JRadioButton rbIndividuales;
    private JTextField campoCantidadPorEquipo;
    private JLabel lblCantidadPorEquipo;
    int cantidadPorEquipo;
    public PanelFormularioParticipante(JFrame frame) {
        super(frame);
        configurar();
        agregarComponentes();
    }
    @Override
    public void configurar() {
        panel.setLayout(null);
        panel.setBackground(new Color(70, 45, 90));
    }
    @Override
    public void agregarComponentes() {
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setBounds(550, 100, 100, 50);
        panel.add(lblNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(650, 100, 200, 50);
        panel.add(campoNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setForeground(Color.WHITE);
        lblApellido.setBounds(550, 180, 100, 50);
        panel.add(lblApellido);

        JTextField campoApellido = new JTextField();
        campoApellido.setBounds(650, 180, 200, 50);
        panel.add(campoApellido);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setForeground(Color.WHITE);
        lblEdad.setBounds(550, 270, 100, 50);
        panel.add(lblEdad);

        JTextField campoEdad = new JTextField();
        campoEdad.setBounds(650, 270, 200, 50);
        panel.add(campoEdad);

        JLabel lblContacto = new JLabel("Contacto:");
        lblContacto.setForeground(Color.WHITE);
        lblContacto.setBounds(550, 360, 100, 50);
        panel.add(lblContacto);

        JTextField campoContacto = new JTextField();
        campoContacto.setBounds(650, 360, 200, 50);
        panel.add(campoContacto);

        JLabel lblLista = new JLabel("Lista de participantes");
        lblLista.setForeground(Color.WHITE);
        lblLista.setBounds(950, 200, 200, 50);
        panel.add(lblLista);

        JTextArea areaParticipantes = new JTextArea();
        areaParticipantes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaParticipantes);
        scroll.setBounds(950, 300, 300, 200);
        panel.add(scroll);

        rbIndividuales = new JRadioButton("Participantes individuales");
        rbIndividuales.setForeground(Color.WHITE);
        rbIndividuales.setBackground(new Color(70, 45, 90));
        rbIndividuales.setBounds(550, 50, 200, 30);
        rbIndividuales.setSelected(true); // Seleccionado por defecto
        rbIndividuales.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty());

        rbEquipos = new JRadioButton("Equipos");
        rbEquipos.setForeground(Color.WHITE);
        rbEquipos.setBackground(new Color(70, 45, 90));
        rbEquipos.setBounds(750, 50, 100, 30);
        rbEquipos.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty());

        ButtonGroup grupoTipoParticipante = new ButtonGroup();
        grupoTipoParticipante.add(rbIndividuales);
        grupoTipoParticipante.add(rbEquipos);

        panel.add(rbIndividuales);
        panel.add(rbEquipos);


        lblCantidadPorEquipo = new JLabel("Cantidad por equipo:");
        lblCantidadPorEquipo.setForeground(Color.WHITE);
        lblCantidadPorEquipo.setBounds(850, 50, 150, 30);
        lblCantidadPorEquipo.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected());
        panel.add(lblCantidadPorEquipo);
        campoCantidadPorEquipo = new JTextField();
        campoCantidadPorEquipo.setBounds(900, 50, 100, 30);
        campoCantidadPorEquipo.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected());
        panel.add(campoCantidadPorEquipo);
        // Listener para mostrar/ocultar campoCantidadPorEquipo según rbEquipos
        rbEquipos.addActionListener(e -> {
            lblCantidadPorEquipo.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected());
            campoCantidadPorEquipo.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected());
        });
        rbIndividuales.addActionListener(e -> {
            lblCantidadPorEquipo.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected());
            campoCantidadPorEquipo.setVisible(((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected());
        });
        // Botón Submit

        JButton botonSubmit = new JButton("Agregar");
        botonSubmit.setBackground(new Color(40, 40, 40));
        botonSubmit.setForeground(Color.WHITE);
        botonSubmit.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonSubmit.setFocusPainted(false);
        botonSubmit.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        botonSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSubmit.setBounds(600, 450, 200, 30);
        panel.add(botonSubmit);


        // Acción del botón
        botonSubmit.addActionListener(e -> {
            String nombre = campoNombre.getText().trim();
            String apellido = campoApellido.getText().trim();
            String edadTexto = campoEdad.getText().trim();
            String contacto = campoContacto.getText().trim();
            String cantidadPorEquipoTexto = campoCantidadPorEquipo.getText().trim();


            if (nombre.isEmpty() || apellido.isEmpty() || edadTexto.isEmpty() || contacto.isEmpty() ||
                    (((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && !rbIndividuales.isSelected() && !rbEquipos.isSelected()) ||
                    (((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected() && participantesEntrada.isEmpty() && cantidadPorEquipoTexto.isEmpty())) {
                JOptionPane.showMessageDialog(panel, "Todos los campos deben estar completos, incluyendo la cantidad por equipo si aplica.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int edad = Integer.parseInt(edadTexto);
                Participante participante = new IndividuoParticipante(nombre, apellido, edad, contacto);
                cantidadPorEquipo = 2; // Valor por defecto para equipos
                boolean esTorneoDeEquipos = !((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() &&
                        ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().getFirst() instanceof Equipo;

                // Determinar cantidadPorEquipo
                if (esTorneoDeEquipos) {
                    cantidadPorEquipo = ((Equipo) ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().getFirst()).obtenerListaEquipo().size();
                } else if (((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected() && participantesEntrada.isEmpty()) {
                    try {
                        cantidadPorEquipo = Integer.parseInt(cantidadPorEquipoTexto);
                        if (cantidadPorEquipo < 2) {
                            JOptionPane.showMessageDialog(panel, "La cantidad por equipo debe ser al menos 2.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "La cantidad por equipo debe ser un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (esTorneoDeEquipos || (((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().isEmpty() && rbEquipos.isSelected())) {
                    participantesEntrada.add(participante);
                    if (participantesEntrada.size() < cantidadPorEquipo) {
                        JOptionPane.showMessageDialog(panel, "Los participantes son equipos, llevas " + participantesEntrada.size() + " de " + cantidadPorEquipo, "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int numero = ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes().size() + 1;
                        String nombreE = "Equipo" + numero;
                        String contactoE = "equipo_" + numero + "@gmail.com";
                        Equipo equipo = new Equipo(nombreE, contactoE);
                        for (Participante p : participantesEntrada) {
                            equipo.agregarMiembro((IndividuoParticipante) p);
                        }
                        participantesEntrada.clear();
                        ((TorneoAbstracto) Navegador.torneo).agregarParticipante(equipo);
                    }
                } else {
                    ((TorneoAbstracto) Navegador.torneo).agregarParticipante(participante);
                    participantesEntrada.clear();
                }

                campoNombre.setText("");
                campoApellido.setText("");
                campoEdad.setText("");
                campoContacto.setText("");
                campoCantidadPorEquipo.setText("");
                // Ocultar radio buttons y campo de cantidad después de agregar
                rbIndividuales.setVisible(false);
                rbEquipos.setVisible(false);
                lblCantidadPorEquipo.setVisible(false);
                campoCantidadPorEquipo.setVisible(false);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "La edad debe ser un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            } catch (TorneoException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }

        });
        new Timer(500, e -> {
            StringBuilder texto = new StringBuilder();
            for (Participante p : ((TorneoAbstracto) Navegador.torneo).obtenerParticipantes()) {
                texto.append("Nombre: ")
                        .append(p.obtenerNombre())
                        .append(" ")
                        .append("Contacto: ")
                        .append(p.obtenerContacto())
                        .append("\n");
            }
            areaParticipantes.setText(texto.toString());

        }).start();
        panel.add(new BotonVolver(frame));
    }

    @Override
    public JPanel obtenerPanel() {
        return panel;
    }
}
