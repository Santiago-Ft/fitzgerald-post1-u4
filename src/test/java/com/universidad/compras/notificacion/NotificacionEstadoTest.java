package com.universidad.compras.notificacion;

import com.universidad.compras.modelo.Solicitud;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificacionEstadoTest {

    @Test
    void cambiarEstadoDisparaLasTresReaccionesSinLanzarExcepcion() {
        Solicitud s = new Solicitud("S-020", "ana@udes.edu.co", 2500000, "SOFTWARE", "CC-100");
        PublicadorNotificaciones publicador = new PublicadorNotificaciones();
        new GestorNotificacionesCompras(publicador);

        assertDoesNotThrow(() -> {
            publicador.notificar(s, "PENDIENTE", "APROBADA", "Evaluación de prueba exitosa");
        });
    }

    @Test
    void agregarUnCuartoSuscriptorDePruebaNoRequiereModificarElMecanismo() {
        Solicitud s = new Solicitud("S-021", "pedro@udes.edu.co", 1200000, "MATERIAL_OFICINA", "CC-100");
        PublicadorNotificaciones publicador = new PublicadorNotificaciones();
        new GestorNotificacionesCompras(publicador);

        final boolean[] suscriptorCuartoNotificado = {false};

        assertDoesNotThrow(() -> {
            // Se suscribe el 4to observador dinámicamente sin modificar clases centrales
            publicador.suscribir((solicitud, anterior, nuevo, detalle) -> {
                suscriptorCuartoNotificado[0] = true;
            });

            publicador.notificar(s, "PENDIENTE", "APROBADA", "Prueba cuarto suscriptor");
            assertTrue(suscriptorCuartoNotificado[0]);
        });
    }
}