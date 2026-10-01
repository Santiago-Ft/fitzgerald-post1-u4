package com.universidad.compras.estado;

public class EstadoPendiente implements EstadoSolicitud {
    @Override
    public void aprobar(ContextoSolicitud contexto) {
        contexto.cambiarEstado(new EstadoAprobada());
    }

    @Override
    public void rechazar(ContextoSolicitud contexto) {
        contexto.cambiarEstado(new EstadoRechazada());
    }

    @Override
    public void ejecutar(ContextoSolicitud contexto) {
        // Operación inválida en Pendiente: No cambia de estado
    }

    @Override
    public void cancelar(ContextoSolicitud contexto) {
        contexto.cambiarEstado(new EstadoCancelada());
    }

    @Override
    public String getNombreEstado() { return "PENDIENTE"; }
}