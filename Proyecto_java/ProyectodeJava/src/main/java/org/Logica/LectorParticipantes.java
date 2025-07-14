package org.Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para leer participantes desde un archivo de texto.
 * <p>
 * El archivo puede contener individuos y equipos. Los individuos se especifican con líneas
 * que contienen nombre, apellido, edad y opcionalmente correo. Los equipos se forman a partir
 * de grupos de individuos separados por líneas que contienen sólo el carácter '%'.
 * </p>
 *
 * <p>
 * Si el archivo contiene al menos un separador '%', se interpretan los grupos de participantes
 * como equipos. Si no hay separadores, todos los participantes se consideran individuos sueltos.
 * </p>
 */

public class LectorParticipantes {

    /**
     * Lee participantes desde un archivo en la ruta especificada.
     * <p>
     * El formato esperado por línea es:
     * <ul>
     *     <li>Para participantes individuales: "Nombre Apellido Edad [Correo]"</li>
     *     <li>Para separar equipos: una línea con sólo "%"</li>
     * </ul>
     * <p>
     * Los equipos se forman con los participantes entre separadores '%'.
     * Si no hay separadores, todos se consideran individuos.
     * </p>
     *
     * @param rutaArchivo La ruta del archivo de texto que contiene los participantes.
     * @return Una lista con dos listas internas:
     *         <ul>
     *           <li>En la posición 0, la lista de participantes individuales.</li>
     *           <li>En la posición 1, la lista de equipos.</li>
     *         </ul>
     * @throws TorneoException Si el formato del archivo es inválido o hay errores al crear participantes.
     */

    public static List<List<Participante>> leerParticipantes(String rutaArchivo) throws TorneoException {

        List<Participante> individuos = new ArrayList<>();
        List<Participante> equipos = new ArrayList<>();
        List<IndividuoParticipante> grupoActual = new ArrayList<>();
        int equipoCount = 1;
        boolean haySeparador = false;


        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();
                if (linea.equals("%")) {
                    haySeparador = true;
                    if (!grupoActual.isEmpty()) {
                        // Crear un equipo con los participantes del grupo actual
                        String nombreEquipo = "Equipo " + equipoCount++;
                        Equipo equipo = new Equipo(nombreEquipo, nombreEquipo.toLowerCase().replace(" ", "_") + "@equipo.com");
                        for (IndividuoParticipante miembro : grupoActual) {
                            equipo.agregarMiembro(miembro);
                        }
                        equipos.add(equipo);
                        grupoActual.clear();
                    }
                } else if (!linea.isEmpty()) {
                    // Procesar línea como un solo participante
                    String[] partes = linea.split("\\s+");
                    if (partes.length < 3 || partes.length > 4) {
                        throw new TorneoException("Formato de línea inválido en línea " + numeroLinea + ": " + linea + " (se espera 'Nombre Apellido Edad [Correo]')");
                    }
                    try {
                        String nombre = partes[0];
                        String apellido = partes[1];
                        int edad = Integer.parseInt(partes[2]);
                        String correo = partes.length == 4 ? partes[3] : nombre.toLowerCase() + "." + apellido.toLowerCase() + "@default.com";
                        IndividuoParticipante participante = new IndividuoParticipante(nombre, apellido, edad, correo);
                        grupoActual.add(participante);
                    } catch (NumberFormatException e) {
                        throw new TorneoException("Edad inválida en línea " + numeroLinea + ": " + linea);
                    }
                }
            }
            // Procesar el último grupo o añadir individuos
            if (!grupoActual.isEmpty()) {
                if (haySeparador) {
                    // Si hubo separadores, el grupo actual es un equipo
                    String nombreEquipo = "Equipo " + equipoCount++;
                    Equipo equipo = new Equipo(nombreEquipo, nombreEquipo.toLowerCase().replace(" ", "_") + "@equipo.com");
                    for (IndividuoParticipante miembro : grupoActual) {
                        equipo.agregarMiembro(miembro);
                    }
                    equipos.add(equipo);
                } else {
                    // Si no hubo separadores, todos son individuos
                    individuos.addAll(grupoActual);
                }
            }
        } catch (IOException e) {
            throw new TorneoException("Error al leer el archivo: " + e.getMessage());
        }

        List<List<Participante>> resultado = new ArrayList<>();
        resultado.add(individuos);
        resultado.add(equipos);
        return resultado;
    }
}
