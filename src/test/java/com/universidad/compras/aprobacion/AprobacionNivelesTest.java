package com.universidad.compras.aprobacion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AprobacionNivelesTest {

    @Test
    void solicitudDentroDeAutoridadDelSupervisorSeAprueba() {
        // Construcción de la cadena: Supervisor -> Gerente -> Director
        Aprobador director = new DirectorFinancieroAprobador(null);
        Aprobador gerente = new GerenteAreaAprobador(director);
        Aprobador supervisor = new SupervisorAreaAprobador(gerente);

        ServicioAprobacion servicio = new ServicioAprobacionImpl(supervisor);
        Solicitud s = new Solicitud("S-001", "ana@udes.edu.co", 1500000, "MATERIAL_OFICINA", "CC-100");

        ResultadoAprobacion r = servicio.evaluar(s);
        assertTrue(r.isAprobada());
        assertEquals("Supervisor de Área", r.getNivelResolutor());
    }

    @Test
    void solicitudQueSuperaAlSupervisorEscalaAlGerente() {
        // Construcción de la cadena: Supervisor -> Gerente -> Director
        Aprobador director = new DirectorFinancieroAprobador(null);
        Aprobador gerente = new GerenteAreaAprobador(director);
        Aprobador supervisor = new SupervisorAreaAprobador(gerente);

        ServicioAprobacion servicio = new ServicioAprobacionImpl(supervisor);
        Solicitud s = new Solicitud("S-002", "luis@udes.edu.co", 6000000, "SOFTWARE", "CC-200");

        ResultadoAprobacion r = servicio.evaluar(s);
        assertTrue(r.isAprobada());
        assertEquals("Gerente de Área", r.getNivelResolutor());
    }

    @Test
    void solicitudInternacionalPasaPorCumplimientoAntesDelNivelPorMonto() {
        // Construcción con el nivel de Cumplimiento antes del nivel por monto
        Aprobador director = new DirectorFinancieroAprobador(null);
        Aprobador gerente = new GerenteAreaAprobador(director);
        Aprobador supervisor = new SupervisorAreaAprobador(gerente);
        Aprobador cumplimiento = new RevisorCumplimientoAprobador(supervisor);

        ServicioAprobacion servicio = new ServicioAprobacionImpl(cumplimiento);
        Solicitud s = new Solicitud("S-003", "gerencia@udes.edu.co", 1000000, "INTERNACIONAL", "CC-300");

        ResultadoAprobacion r = servicio.evaluar(s);
        assertEquals("Revisor de Cumplimiento Normativo", r.getNivelResolutor());
    }
}