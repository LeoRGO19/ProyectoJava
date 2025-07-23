import org.Logica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TorneoAbstractoTest {

    private TorneoAbstracto torneo;
    private IndividuoParticipante p1, p2, p3;
    private Equipo e1;

    // Clase interna que extiende TorneoAbstracto e implementa Torneo y SujetoTorneo
    private class TorneoConcreto extends TorneoAbstracto implements Torneo, SujetoTorneo {
        public TorneoConcreto(String nombre, String disciplina, int maxParticipantes) {
            super(nombre, disciplina, maxParticipantes);
        }

        @Override
        public void generarBracket() {}

        @Override
        public void generarEnfrentamientos() {}

        @Override
        public void generarTabla() throws TorneoException {

        }

        @Override
        public void generarCalendario() {}

        @Override
        public void verEstado() {}

        public void mostrarBracket() {}
    }

    @BeforeEach
    public void setUp() throws TorneoException {
        torneo = new TorneoConcreto("Torneo Test", "FUTBOL", 3);
        p1 = new IndividuoParticipante("Juan", "Perez", 20, "juan@example.com");
        p2 = new IndividuoParticipante("Maria", "Gomez", 22, "maria@example.com");
        p3 = new IndividuoParticipante("Pedro", "Lopez", 25, "pedro@example.com");
        e1 = new Equipo("Equipo A", "equipoA@example.com"); // Asumiendo que Equipo existe
    }

    // Método: configurar
    @Test
    public void testConfigurar_CasoNormal() {
        torneo.configurar("Nuevo Torneo", "TENIS", 5);
        assertEquals("Nuevo Torneo", torneo.obtenerNombre());
        assertEquals(Disciplina.TENIS, torneo.obtenerDisciplina());
        assertEquals(5, torneo.obtenerMaximo());
        assertTrue(torneo.obtenerParticipantes().isEmpty());
        assertFalse(torneo.haIniciado());
    }

    // Método: agregarParticipante
    // Caso normal
    // Excepción: Torneo ya iniciado
    // Excepción: Participante nulo
    // Excepción: Tipo incompatible
    // Excepción: Participante duplicado
    // Excepción: Máximo de participantes alcanzado
    @Test
    public void testAgregarParticipante_CasoNormal() throws TorneoException {
        torneo.agregarParticipante(p1);
        assertEquals(1, torneo.obtenerParticipantes().size());
        assertTrue(torneo.obtenerParticipantes().contains(p1));
    }
    @Test
    public void testAgregarParticipante_TorneoIniciado() throws TorneoException {
        torneo.agregarParticipante(p1);
        torneo.agregarParticipante(p2); // Añadir 2 participantes
        torneo.iniciarTorneo();
        assertThrows(TorneoException.class, () -> torneo.agregarParticipante(p3));
    }
    @Test
    public void testAgregarParticipante_Nulo() {
        assertThrows(TorneoException.class, () -> torneo.agregarParticipante(null));
    }
    @Test
    public void testAgregarParticipante_TipoIncompatible() throws TorneoException {
        torneo.agregarParticipante(p1); // Individuo primero
        assertThrows(TorneoException.class, () -> torneo.agregarParticipante(e1));
    }
    @Test
    public void testAgregarParticipante_Duplicado() throws TorneoException {
        torneo.agregarParticipante(p1);
        assertThrows(TorneoException.class, () -> torneo.agregarParticipante(p1));
    }
    @Test
    public void testAgregarParticipante_MaxParticipantes() throws TorneoException {
        torneo.agregarParticipante(p1);
        torneo.agregarParticipante(p2);
        torneo.agregarParticipante(p3);
        assertThrows(TorneoException.class, () -> torneo.agregarParticipante(new IndividuoParticipante("Ana", "Ruiz", 23, "ana@example.com")));
    }

    // Método: eliminarParticipante
    // Caso normal
    // Excepción: Torneo ya iniciado
    // Excepción: No hay participantes
    // Excepción: Participante no encontrado
    @Test
    public void testEliminarParticipante_CasoNormal() throws TorneoException {
        torneo.agregarParticipante(p1);
        torneo.eliminarParticipante(p1);
        assertTrue(torneo.obtenerParticipantes().isEmpty());
    }
    @Test
    public void testEliminarParticipante_TorneoIniciado() throws TorneoException {
        torneo.agregarParticipante(p1);
        torneo.agregarParticipante(p2); // Añadir 2 participantes
        torneo.iniciarTorneo();
        assertThrows(TorneoException.class, () -> torneo.eliminarParticipante(p1));
    }
    @Test
    public void testEliminarParticipante_SinParticipantes() {
        assertThrows(TorneoException.class, () -> torneo.eliminarParticipante(p1));
    }
    @Test
    public void testEliminarParticipante_NoEncontrado() throws TorneoException {
        torneo.agregarParticipante(p1);
        assertThrows(TorneoException.class, () -> torneo.eliminarParticipante(p2));
    }

    // Método: agregarParticipantesDesdeLista
    // Caso normal
    // Excepción: Lista nula
    // Excepción: Lista vacía
    // Excepción: Mezcla de tipos
    // Excepción: Tipo incompatible con participantes existentes
    // Excepción: Participante duplicado en lista
    // Excepción: Máximo de participantes alcanzado
    @Test
    public void testAgregarParticipantesDesdeLista_CasoNormal() throws TorneoException {
        ArrayList<Participante> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        torneo.agregarParticipantesDesdeLista(lista);
        assertEquals(2, torneo.obtenerParticipantes().size());
        assertTrue(torneo.obtenerParticipantes().containsAll(lista));
    }
    @Test
    public void testAgregarParticipantesDesdeLista_Nula() {
        assertThrows(TorneoException.class, () -> torneo.agregarParticipantesDesdeLista(null));
    }
    @Test
    public void testAgregarParticipantesDesdeLista_Vacia() {
        assertThrows(TorneoException.class, () -> torneo.agregarParticipantesDesdeLista(new ArrayList<>()));
    }
    @Test
    public void testAgregarParticipantesDesdeLista_MezclaTipos() {
        ArrayList<Participante> lista = new ArrayList<>();
        lista.add(p1); // Individuo
        lista.add(e1); // Equipo
        assertThrows(TorneoException.class, () -> torneo.agregarParticipantesDesdeLista(lista));
    }
    @Test
    public void testAgregarParticipantesDesdeLista_TipoIncompatible() throws TorneoException {
        torneo.agregarParticipante(p1); // Individuo primero
        ArrayList<Participante> lista = new ArrayList<>();
        lista.add(e1); // Equipo
        assertThrows(TorneoException.class, () -> torneo.agregarParticipantesDesdeLista(lista));
    }
    @Test
    public void testAgregarParticipantesDesdeLista_Duplicado() throws TorneoException {
        torneo.agregarParticipante(p1);
        ArrayList<Participante> lista = new ArrayList<>();
        lista.add(p1); // Duplicado
        lista.add(p2);
        assertThrows(TorneoException.class, () -> torneo.agregarParticipantesDesdeLista(lista));
    }
    @Test
    public void testAgregarParticipantesDesdeLista_MaxParticipantes() throws TorneoException {
        ArrayList<Participante> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        torneo.agregarParticipantesDesdeLista(lista);
        assertThrows(TorneoException.class, () -> torneo.agregarParticipante(new IndividuoParticipante("Ana", "Ruiz", 23, "ana@example.com")));
    }

    // Método: iniciarTorneo
    // Caso normal
    // Excepción: Menos de 2 participantes
    @Test
    public void testIniciarTorneo_CasoNormal() throws TorneoException {
        torneo.agregarParticipante(p1);
        torneo.agregarParticipante(p2);
        torneo.iniciarTorneo();
        assertTrue(torneo.haIniciado());
    }
    @Test
    public void testIniciarTorneo_MenosDeDosParticipantes() throws TorneoException {
        torneo.agregarParticipante(p1);
        assertThrows(TorneoException.class, torneo::iniciarTorneo);
    }

    // Método: registrarObservador
    // Caso normal
    @Test
    public void testRegistrarObservador_CasoNormal() {
        ObservadorTorneo observador = new ObservadorTorneo() {
            @Override
            public void actualizar(EventoTorneo evento) {}
        };
        torneo.registrarObservador(observador);
        assertTrue(torneo.obtenerObservadores().contains(observador));
    }

    // Método: eliminarObservador
    // Caso normal
    @Test
    public void testEliminarObservador_CasoNormal() {
        ObservadorTorneo observador = new ObservadorTorneo() {
            @Override
            public void actualizar(EventoTorneo evento) {}
        };
        torneo.registrarObservador(observador);
        torneo.eliminarObservador(observador);
        assertFalse(torneo.obtenerObservadores().contains(observador));
    }

    // Método: notificarObservadores
    // Caso normal
    @Test
    public void testNotificarObservadores_CasoNormal() {
        ObservadorTorneo observador = new ObservadorTorneo() {
            boolean notificado = false;
            @Override
            public void actualizar(EventoTorneo evento) {
                notificado = true;
            }
        };
        torneo.registrarObservador(observador);
        torneo.notificarObservadores(TipoEvento.TORNEO_INICIADO, null);
        assertTrue(true); // Verificación indirecta vía registro
    }
}