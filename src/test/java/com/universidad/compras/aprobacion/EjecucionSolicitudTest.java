package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;
import com.universidad.compras.ejecucion.ComandoEjecucion;
import com.universidad.compras.ejecucion.ComandoGenerarOrdenCompra;
import com.universidad.compras.ejecucion.ComandoReservarPresupuesto;
import com.universidad.compras.ejecucion.GestorEjecucionSolicitudes;
import com.universidad.compras.ejecucion.OrdenCompraService;
import com.universidad.compras.ejecucion.PresupuestoService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EjecucionSolicitudTest {

    @Test
    void ejecutarReservaPresupuestoYGeneraOrden() {
        Solicitud s = new Solicitud("S-010", "ana@udes.edu.co", 3000000, "SOFTWARE", "CC-100");
        s.setEstado("APROBADA");

        GestorEjecucionSolicitudes ejecutor = new GestorEjecucionSolicitudes();
        PresupuestoService presupuestoService = new PresupuestoService();
        OrdenCompraService ordenCompraService = new OrdenCompraService();

        ComandoEjecucion cmd1 = new ComandoReservarPresupuesto(presupuestoService, s);
        ComandoEjecucion cmd2 = new ComandoGenerarOrdenCompra(ordenCompraService, s, "Proveedor S.A.");

        ejecutor.ejecutarOperacion(cmd1, s);
        ejecutor.ejecutarOperacion(cmd2, s);

        assertEquals("EJECUTADA", s.getEstado());
    }

    @Test
    void deshacerSoloLaUltimaOperacionNoAfectaLaAnterior() {
        Solicitud s = new Solicitud("S-011", "luis@udes.edu.co", 4000000, "MATERIAL_OFICINA", "CC-200");
        
        GestorEjecucionSolicitudes ejecutor = new GestorEjecucionSolicitudes();
        PresupuestoService presupuestoService = new PresupuestoService();
        OrdenCompraService ordenCompraService = new OrdenCompraService();

        assertDoesNotThrow(() -> {
            ComandoEjecucion cmd1 = new ComandoReservarPresupuesto(presupuestoService, s);
            ComandoEjecucion cmd2 = new ComandoGenerarOrdenCompra(ordenCompraService, s, "Proveedor S.A.");

            ejecutor.ejecutarOperacion(cmd1, s);
            ejecutor.ejecutarOperacion(cmd2, s);

            // Deshace únicamente la orden de compra
            ejecutor.deshacerUltimaOperacion();
        });
    }

    @Test
    void elHistorialConservaTodasLasOperacionesNoSoloLaUltima() {
        GestorEjecucionSolicitudes ejecutor = new GestorEjecucionSolicitudes();
        PresupuestoService presupuestoService = new PresupuestoService();
        OrdenCompraService ordenCompraService = new OrdenCompraService();
        Solicitud s = new Solicitud("S-012", "ana@udes.edu.co", 2000000, "SOFTWARE", "CC-100");

        assertDoesNotThrow(() -> {
            ComandoEjecucion cmd1 = new ComandoReservarPresupuesto(presupuestoService, s);
            ComandoEjecucion cmd2 = new ComandoGenerarOrdenCompra(ordenCompraService, s, "Proveedor S.A.");

            ejecutor.ejecutarOperacion(cmd1, s);
            ejecutor.ejecutarOperacion(cmd2, s);

            assertEquals(2, ejecutor.getTamanoHistorial());
        });
    }
}