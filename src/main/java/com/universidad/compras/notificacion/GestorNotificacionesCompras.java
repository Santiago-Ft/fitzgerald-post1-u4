package com.universidad.compras.notificacion;

public class GestorNotificacionesCompras {
    private final PublicadorNotificaciones publicador;

    public GestorNotificacionesCompras(PublicadorNotificaciones publicador) {
        this.publicador = publicador;
        registrarSuscriptoresBase();
    }

    private void registrarSuscriptoresBase() {
        // 1. Envío de Correo
        publicador.suscribir((solicitud, anterior, nuevo, detalle) -> 
            ClientesNotificacion.enviarCorreo(
                solicitud.getSolicitanteEmail(), 
                "Cambio de estado en solicitud " + solicitud.getId(), 
                "Tu solicitud pasó de " + anterior + " a " + nuevo
            )
        );

        // 2. Actualización de Dashboard de Contabilidad
        publicador.suscribir((solicitud, anterior, nuevo, detalle) -> 
            ClientesNotificacion.actualizarDashboardContabilidad(
                solicitud.getId(), 
                nuevo, 
                solicitud.getMonto()
            )
        );

        // 3. Registro de Auditoría
        publicador.suscribir((solicitud, anterior, nuevo, detalle) -> 
            ClientesNotificacion.registrarAuditoria(
                solicitud.getId(), 
                nuevo, 
                detalle
            )
        );
    }
}