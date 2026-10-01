package com.universidad.compras.estado;

public class EstadoAprobada implements EstadoSolicitud {
    @Override
    public void aprobar(ContextoSolicitud contexto) {}

    @Override
    public void rechazar(ContextoSolicitud contexto) {}

    @Override
    public void ejecutar(ContextoSolicitud contexto) {
        contexto.cambiarEstado(new EstadoEjecutada());
    }

    @Override
    public void cancelar(ContextoSolicitud contexto) {
        contexto.cambiarEstado(new EstadoCancelada());
    }

    @Override
    public String getNombreEstado() { return "APROBADA"; }
}