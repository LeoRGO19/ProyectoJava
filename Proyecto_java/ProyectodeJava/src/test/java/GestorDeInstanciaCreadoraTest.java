import org.Logica.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorDeInstanciaCreadoraTest {

    @Test
    public void testGetInstance_CasoNormal() {
        GestorDeInstanciaCreadora instancia1 = GestorDeInstanciaCreadora.getInstance();
        GestorDeInstanciaCreadora instancia2 = GestorDeInstanciaCreadora.getInstance();
        assertNotNull(instancia1);
        assertSame(instancia1, instancia2);
    }

    @Test
    public void testObtenerCreador_CasoNormalEliminacionSimple() throws TorneoException {
        GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
        var creador = gestor.obtenerCreador(FormatoTorneo.ELIMINACION_SIMPLE);
        assertNotNull(creador);
    }

    @Test
    public void testObtenerCreador_CasoNormalLiga() throws TorneoException {
        GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
        var creador = gestor.obtenerCreador(FormatoTorneo.LIGA);
        assertNotNull(creador);
    }

    @Test
    public void testObtenerCreador_CasoErrorFormatoInvalido() {
        GestorDeInstanciaCreadora gestor = GestorDeInstanciaCreadora.getInstance();
        assertThrows(TorneoException.class, () -> gestor.obtenerCreador(null));
    }
}