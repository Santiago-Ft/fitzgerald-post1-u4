package com.universidad.compras.estado;

public class EstadoCancelada implements EstadoSolicitud {

    @Override
    public void aprobar(ContextoSolicitud contexto) {
        // Estado terminal: Una solicitud cancelada no puede ser aprobada
        System.out.println("Error: No se puede aprobar una solicitud en estado CANCELADA.");
    }

    @Override
    public void rechazar(ContextoSolicitud contexto) {
        // Estado terminal: Una solicitud cancelada no puede ser rechazada
        System.out.println("Error: No se puede rechazar una solicitud en estado CANCELADA.");
    }

    @Override
    public void ejecutar(ContextoSolicitud contexto) {
        // Estado terminal: Una solicitud cancelada no puede ejecutarse
        System.out.println("Error: No se puede ejecutar una solicitud en estado CANCELADA.");
    }

    @Override
    public void cancelar(ContextoSolicitud contexto) {
        // Estado terminal: Ya se encuentra cancelada
        System.out.println("Aviso: La solicitud ya se encuentra en estado CANCELADA.");
    }

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }
}